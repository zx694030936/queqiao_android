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
import com.tencent.TIMConnListener;
import com.tencent.TIMLogLevel;
import com.tencent.TIMLogListener;
import com.tencent.TIMManager;
import com.tencent.TIMMessageListener;
import com.tencent.TIMUserStatusListener;

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
    public static int getMemberId(){
        //获取当前登录用户id
        mAppUserId = SharedPrefUtil.getInt(mContext, Constants.USERID,-1);
        Log.e("memberid", mAppUserId +"");
        return mAppUserId;
    }
    /**
     * 设置用户id
     */
    public static void setMemberId(int userid){
        //获取当前登录用户id
        SharedPrefUtil.putInt(mContext, Constants.USERID,userid);
        Log.e("memberid", mAppUserId +"");
    }
    @Override
    public ComponentName startService(Intent service) {

        return super.startService(service);
    }

    public static void initTIMManager(TIMMessageListener messageListener,
                                      TIMConnListener connListener ,
                                      TIMLogListener timLogListener,
                                      TIMUserStatusListener timUserStatusListener){
        /*im 通讯管理器初始化*/
        TIMManager.getInstance().addMessageListener(messageListener);//新消息通知
        TIMManager.getInstance().setConnectionListener(connListener);//网络事件通知
        TIMManager.getInstance().setLogListener(timLogListener);//日志事件
        TIMManager.getInstance().setUserStatusListener(timUserStatusListener);//用户状态变更
        TIMManager.getInstance().disableCrashReport();//crash上报关闭
        TIMManager.getInstance().setLogLevel(TIMLogLevel.INFO);//日志等级
        TIMManager.getInstance().initLogSettings(true ,null);//打印日志，保存路径
        TIMManager.getInstance().disableStorage();//禁用消息存储
        TIMManager.getInstance().init(mContext);
    }
}
