package me.heyboy.mymvpdemo.services;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.heyboy.mymvpdemo.model.entities.ImgRecorder;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * 创 建 人： Henning
 * 创建时间： 17-10-27 下午1:21
 * 工程名称： MyApplication
 * 包   名： me.heyboy.mymvpdemo.services
 * <p>
 * 执行下载任务
 */

public class PhotoImgService {
    private static final String TAG = "FlickrFetchr";
    private static final String API_KEY = "8cac56725686f0638e903188f187858b";
    private static final String URL = "http://api.tianapi.com/";

    public static OkHttpClient mOkHttpClient = new OkHttpClient().newBuilder().build();

    public static List<ImgRecorder> getImgRecorders() throws IOException{
        List<ImgRecorder> items=new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .build();
        PhotoRecorderService photoRecorderService = retrofit.create(PhotoRecorderService.class);
        Call<ResponseBody> call = photoRecorderService.getImages(API_KEY, 50);
        //Call<ResponseBody> call = photoRecorderService.getImagesDefault();
        //call.enqueue(new Callback<ResponseBody>(){
        //    @Override
        //    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
        //        System.out.println(response.body().toString());
        //    }
        //    @Override
        //    public void onFailure(Call<ResponseBody> call, Throwable t) {
        //        System.out.println("错误！");
        //        t.printStackTrace();
        //    }
        //});

        ResponseBody responseBody = call.execute().body();
        String resultBody = responseBody.string();
        System.out.println(resultBody);
        JSONObject jsonObject = JSONObject.parseObject(resultBody);
        JSONArray photoJsonArray = jsonObject.getJSONArray("newslist");
        for(int i=0;i<photoJsonArray.size();i++){
            JSONObject photoJsonObject = photoJsonArray.getJSONObject(i);
            ImgRecorder item = new ImgRecorder();
            item.setUrl(photoJsonObject.getString("picUrl"));
            item.setDescription(photoJsonObject.getString("description"));
            item.setTitle(photoJsonObject.getString("title"));
            items.add(item);
        }
        return items;
    }


}
