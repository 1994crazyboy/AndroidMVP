package me.heyboy.mymvpdemo.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import me.heyboy.mymvpdemo.photo.PhotoPresenter;

/**
 * 创 建 人： Henning
 * 创建时间： 17-11-5 上午11:47
 * 工程名称： MyApplication
 * 包   名： me.heyboy.mymvpdemo.services
 */

public class PhotoDownloadHandler<T> extends HandlerThread {
    private static String TAG = "PhotoDownloadHandler";
    private Handler mResponseHandler;
    private Handler mRequestHandler;
    private static final int MESSAGE_DOWNLOAD=0;

    private ConcurrentHashMap<T,String> mRequestMap=new ConcurrentHashMap<>();

    private PhotoDownloadListener<T> mDownloadListener;

    //下载完成后的监听事件
    public interface PhotoDownloadListener<T>{
        void photoDownloaded(T target, Bitmap bitmap);
    }

    public PhotoDownloadHandler(Handler handler) {
        super(TAG);
        mResponseHandler = handler;
    }

    public void setDownloadListener(PhotoDownloadListener<T> downloadListener) {
        mDownloadListener = downloadListener;
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mRequestHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==MESSAGE_DOWNLOAD){
                    T target= (T) msg.obj;
                    handlerRequest(target);
                }
            }
        };

    }

    //
    public void downloader(T target,String url){
        Log.i(TAG,"下载的地址为："+url);
        if(url==null){
            mRequestMap.remove(target);
        }else{
            mRequestMap.put(target,url);
            mRequestHandler.obtainMessage(MESSAGE_DOWNLOAD,target).sendToTarget();
        }
    }


    //执行下载任务
    private void handlerRequest(final T target) {
        final String url=mRequestMap.get(target);
        try {
            byte[] bytes= PhotoPresenter.downloadImg(url);
            if(bytes==null){
                return;
            }
            Log.i(TAG,"下载后的图片为："+bytes.toString());
            final Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            mResponseHandler.post(new Runnable() {
                @Override
                public void run() {
                    //执行listener的方法
                    if(mRequestMap.get(target)!=url){
                        return;
                    }

                    mRequestMap.remove(target);
                    mDownloadListener.photoDownloaded(target,bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean quit() {
        return super.quit();
    }


}
