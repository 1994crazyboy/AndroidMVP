package me.heyboy.mymvpdemo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gcgx on 2017/11/8 0008.
 */

@Module
public class MainModule {
    private MainActivity mMainActivity;

    public MainModule(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }


    @Provides
    public MainActivity provideMainActivity(){
        return mMainActivity;
    }



}
