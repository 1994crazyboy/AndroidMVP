package me.heyboy.mymvpdemo.model.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 创 建 人： Henning
 * 创建时间： 17-10-25 下午9:53
 * 工程名称： MyApplication
 * 包   名： me.heyboy.mymvpdemo.model.data
 */

public class DemoDbHelper {
    private static Context mContext;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static SQLiteDatabase mDb;

    //打开数据库
    public static void openDb(Context context){
        mDb=new DaoMaster.DevOpenHelper(context,"demo.db",null).getWritableDatabase();
        mDaoMaster=new DaoMaster(mDb);
        mDaoSession=mDaoMaster.newSession();
    }

    public static Context getmContext() {
        return mContext;
    }

    public static void setmContext(Context mContext) {
        DemoDbHelper.mContext = mContext;
    }

    public static DaoMaster getmDaoMaster() {
        return mDaoMaster;
    }

    public static void setmDaoMaster(DaoMaster mDaoMaster) {
        DemoDbHelper.mDaoMaster = mDaoMaster;
    }

    public static DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public static void setmDaoSession(DaoSession mDaoSession) {
        DemoDbHelper.mDaoSession = mDaoSession;
    }

    public static SQLiteDatabase getmDb() {
        return mDb;
    }

    public static void setmDb(SQLiteDatabase mDb) {
        DemoDbHelper.mDb = mDb;
    }
}
