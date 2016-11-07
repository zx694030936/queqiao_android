package com.queqiaolove;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.hyphenate.chatuidemo.DemoHelper;
import com.queqiaolove.global.Constants;
import com.queqiaolove.util.CommonUtils;
import com.queqiaolove.util.SharedPrefUtil;


/**
 * Created by WD on 2016/10/2.
 */
public class QueQiaoLoveApp extends Application {
    private static Context mContext;
    private static int mainThreadId ;
    private static Handler handler;
    private static int mAppUserId = -1;
    public static Context applicationContext;
    private static QueQiaoLoveApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化上下文
        mContext = getApplicationContext();
        //获取主线程id
        mainThreadId = android.os.Process.myTid();
        //初始化Handler
        handler = new Handler();
        /*初始化imageloader*/
        CommonUtils.initImageLoader(mContext);
        //TIMManager.getInstance().init(mContext);
        //QalService.serviceInit(mContext,true);
        //QALSDKManager.getInstance().init(mContext,Constants.IMSDK_APPID);
        applicationContext = this;
        instance = this;
        DemoHelper.getInstance().init(applicationContext);
        //red packet code : 初始化红包上下文，开启日志输出开关

    }

    public static Context getmContext(){
        return mContext;
    }

    public static Handler getMainHandler(){
        return handler;
    }

    public static boolean isMainThread(int threadId){
        return mainThreadId == threadId;
    }

    /**
     * 获取用户id
     */
    public static int getUserId(){
        //获取当前登录用户id
        mAppUserId = SharedPrefUtil.getInt(mContext, Constants.USERID,-1);
        Log.e("memberid", mAppUserId +"");
        return mAppUserId;
    }
    /**
     * 设置用户id
     */
    public static void setUserId(int userid){
        //获取当前登录用户id
        SharedPrefUtil.putInt(mContext, Constants.USERID,userid);
        Log.e("memberid", mAppUserId +"");
    }
    @Override
    public ComponentName startService(Intent service) {

        return super.startService(service);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static QueQiaoLoveApp getInstance() {
        return instance;
    }
}
