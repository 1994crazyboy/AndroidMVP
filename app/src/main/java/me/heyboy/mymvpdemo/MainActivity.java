package me.heyboy.mymvpdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.heyboy.mymvpdemo.dao.ImgRecorderDao;
import me.heyboy.mymvpdemo.data.DemoDbHelper;
import me.heyboy.mymvpdemo.fragment.PhotoFragment;
import me.heyboy.mymvpdemo.presenter.PhotoPresenter;
import me.heyboy.mymvpdemo.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {
    private PhotoPresenter mPhotoPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置PhotoFragment  MVP中的V  视图层
        PhotoFragment photoFragment= (PhotoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_photo);
        if(photoFragment==null){
            photoFragment=PhotoFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),photoFragment,R.id.fragment_photo);
        }


        //设置数据库操作  MVP中的  M层
        DemoDbHelper.openDb(this);
        ImgRecorderDao imgRecorderDao=DemoDbHelper.getmDaoSession().getImgRecorderDao();


        //设置Presenter
        mPhotoPresenter=new PhotoPresenter(photoFragment,imgRecorderDao);

    }


}
