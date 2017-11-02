package me.heyboy.mymvpdemo;

import java.io.IOException;
import java.util.List;

import me.heyboy.mymvpdemo.model.entities.ImgRecorder;

/**
 * 创 建 人： Henning
 * 创建时间： 17-10-25 下午8:32
 * 工程名称： MyApplication
 * 包   名： me.heyboy.mymvpdemo
 */

public interface PhotoContract {
    interface View extends BaseView<Presenter> {

        /**
         * 显示下载进度
         */
        void showLoadding();

        /**
         * 显示美女图片
         */
        void showImg();

        void setAdapter(List<ImgRecorder> imgRecorders);
    }

    interface Presenter<T> extends BasePresenter {
        /**
         * 下载图片
         */
        void download(T target,String url);


        /**
         * 获取所有的记录
         * @return
         */
        List<ImgRecorder> fetchRecorders() throws IOException;


    }

}
