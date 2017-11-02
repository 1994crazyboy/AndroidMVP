package me.heyboy.mymvpdemo.services;

import android.os.HandlerThread;

/**
 * 创 建 人： Henning
 * 创建时间： 17-11-3 上午7:10
 * 工程名称： MyApplication
 * 包   名： me.heyboy.mymvpdemo.services
 */

public class PhotoDownloader<T> extends HandlerThread {
    private static final String TAG = "PhotoDownloader";

    private Boolean mHasQuit = false;

    public PhotoDownloader() {
        super(TAG);
    }

    @Override
    public boolean quit() {
        mHasQuit = true;
        return super.quit();
    }
}
