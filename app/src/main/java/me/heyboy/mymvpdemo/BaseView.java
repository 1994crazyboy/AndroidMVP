package me.heyboy.mymvpdemo;

/**
 * 创 建 人： Henning
 * 创建时间： 17-10-23 下午1:33
 * 工程名称： MyApplication
 * 包   名： me.heyboy.mymvpdemo
 */

public interface BaseView <T>{
    //view中保持对Presenter的引用
    void setPresenter(T presenter);
}
