package me.heyboy.mymvpdemo;

import android.content.res.Resources;

/**
 * 创 建 人： Henning
 * 创建时间： 17-10-23 下午1:33
 * 工程名称： MyApplication
 * 包   名： me.heyboy.mymvpdemo
 */

public interface BasePresenter {
    // 作用就是让Presenter开始获取数据并调用View 的方法刷新页面
    void start();


}
