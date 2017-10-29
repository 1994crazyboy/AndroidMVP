package me.heyboy.mymvpdemo.presenter;

import java.util.List;

import me.heyboy.mymvpdemo.PhotoContract;
import me.heyboy.mymvpdemo.dao.ImgRecorderDao;
import me.heyboy.mymvpdemo.model.entities.ImgRecorder;

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


    /**
     * 在构造函数中设置这两个变量
     * @param photoView
     * @param imgRecorderDao
     */
    public PhotoPresenter(PhotoContract.View photoView, ImgRecorderDao imgRecorderDao) {
        mPhotoView = photoView;
        mImgRecorderDao = imgRecorderDao;

        mPhotoView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void download() {

    }

    @Override
    public List<ImgRecorder> fetchRecorders() {


        return null;
    }
}
