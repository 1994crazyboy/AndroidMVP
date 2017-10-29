package me.heyboy.mymvpdemo.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * 创 建 人： Henning
 * 创建时间： 17-10-25 下午10:43
 * 工程名称： MyApplication
 * 包   名： me.heyboy.mymvpdemo.util
 */

public class ActivityUtils {

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment,int frameId){
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(frameId,fragment);
        transaction.commit();
    }
}
