package com.queqiaolove;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

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
        Log.e("userid", mAppUserId +"");
        return mAppUserId;
    }
    /**
     * 设置用户id
     */
    public static void setUserId(int userid){
        //获取当前登录用户id
        SharedPrefUtil.putInt(mContext, Constants.USERID,userid);
        Log.e("userid", mAppUserId +"");
    }
    @Override
    public ComponentName startService(Intent service) {

        return super.startService(service);
    }

}
