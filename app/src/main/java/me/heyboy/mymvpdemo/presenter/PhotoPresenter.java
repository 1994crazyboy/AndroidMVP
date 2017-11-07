package me.heyboy.mymvpdemo.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.heyboy.mymvpdemo.PhotoContract;
import me.heyboy.mymvpdemo.constant.APIConstant;
import me.heyboy.mymvpdemo.dao.ImgRecorderDao;
import me.heyboy.mymvpdemo.fragment.PhotoFragment;
import me.heyboy.mymvpdemo.model.entities.ImgRecorder;
import me.heyboy.mymvpdemo.model.entities.ImgRecorder2;
import me.heyboy.mymvpdemo.services.PhotoDownloadHandler;
import me.heyboy.mymvpdemo.services.PhotoRecorderService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static me.heyboy.mymvpdemo.constant.APIConstant.API_KEY;
import static me.heyboy.mymvpdemo.constant.APIConstant.TAG;
import static me.heyboy.mymvpdemo.constant.APIConstant.URL;

/**
 * 创 建 人： Henning
 * 创建时间： 17-10-25 下午8:48
 * 工程名称： MyApplication
 * 包   名： me.heyboy.mymvpdemo.presenter
 */

public class PhotoPresenter implements PhotoContract.Presenter {

    //Presenter 中必备两个变量  一个操作view的 使用Contract中的接口view   一个是操作数据库的dao
    private final PhotoContract.View mPhotoView;
    private final ImgRecorderDao mImgRecorderDao;
    private static List<ImgRecorder> items = new ArrayList<>();

    private Resources mResources;
    private PhotoDownloadHandler<PhotoFragment.PhotoHolder> mPhotoDownloadHandler;

    /**
     * 在构造函数中设置这两个变量
     *
     * @param photoView
     * @param imgRecorderDao
     */
    public PhotoPresenter(PhotoContract.View photoView, ImgRecorderDao imgRecorderDao) {
        super();
        mPhotoView = photoView;
        mImgRecorderDao = imgRecorderDao;

        mPhotoView.setPresenter(this);
    }


    @Override
    public void start() {

    }



    //这里调取PhotoDownloadHandler的方法
    @Override
    public void download(Object target, String url) {
        Log.i(TAG,"正在下载美女："+url);
        mPhotoDownloadHandler.downloader((PhotoFragment.PhotoHolder) target,url);
    }


    //执行下载图片
    public static byte[] downloadImg(String url) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .build();
        PhotoRecorderService photoRecorderService = retrofit.create(PhotoRecorderService.class);
        if(url==null){
            return null;
        }
        Call<ResponseBody> call = photoRecorderService.downloadImge(url);

        byte[] bytes = call.execute().body().bytes();
        return bytes;

    }

    @Override
    public List<ImgRecorder> fetchRecorders() throws IOException {

        Handler handler = new Handler();
        mPhotoDownloadHandler = new PhotoDownloadHandler<>(handler);
        mPhotoDownloadHandler.setDownloadListener(new PhotoDownloadHandler.PhotoDownloadListener<PhotoFragment.PhotoHolder>() {
            @Override
            public void photoDownloaded(PhotoFragment.PhotoHolder target, Bitmap bitmap) {
                Drawable drawable = new BitmapDrawable(mResources, bitmap);
                target.bindDrawble(drawable);
            }
        });

        mPhotoDownloadHandler.start();
        mPhotoDownloadHandler.getLooper();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(URL)
                .build();
        PhotoRecorderService photoRecorderService = retrofit.create(PhotoRecorderService.class);
        Call<ResponseBody> call = photoRecorderService.getImages(API_KEY, 50);

        photoRecorderService.getImagesObserv(API_KEY, 50)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImgRecorder2>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ImgRecorder2 value) {
                        Log.i(TAG, "执行下一个请求！！！" + JSON.toJSONString(value));
                        items = ImgRecorder2.transToImg(value);
                        mPhotoView.setAdapter(items);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "完成请求了！！！");
                    }
                });
        return items;
    }

    @Override
    public void setReources(Resources resources) {
        mResources=resources;
    }
}
