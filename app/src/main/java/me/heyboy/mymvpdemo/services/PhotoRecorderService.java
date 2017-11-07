package me.heyboy.mymvpdemo.services;

import com.jakewharton.retrofit2.adapter.rxjava2.Result;

import io.reactivex.Observable;
import me.heyboy.mymvpdemo.model.entities.ImgRecorder2;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 创 建 人： Henning
 * 创建时间： 17-10-30 上午6:50
 * 工程名称： MyApplication
 * 包   名： me.heyboy.mymvpdemo.services
 */

public interface PhotoRecorderService {
    @GET("meinv")
    Call<ResponseBody> getImages(@Query("key") String key, @Query("num") int num);
    @GET("meinv")
    Observable<ImgRecorder2> getImagesObserv(@Query("key") String key, @Query("num") int num);

    @GET("meinv/?key=8cac56725686f0638e903188f187858b&num=10")
    Call<ResponseBody> getImagesDefault();

    @GET
    Observable<ResponseBody> downloadObserv(@Url String url);

    @GET
    Call<ResponseBody> downloadImge(@Url String url);

}
