package com.queqiaolove.activity.pusher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.queqiaolove.R;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

/**
 * Created by WD on 2016/10/8.
 */
public class StartPusherActivity extends BaseActivity implements View.OnClickListener {
    private static String START_PUSHER = "start";
    private TXLivePusher mLivePusher;
    private TXCloudVideoView mCaptureView;
    private TXLivePushConfig mPushConfig;
    private ImageView iv_back;
    private String pushurl;

    @Override
    protected void initTitle() {

    }

    @Override
    protected void activityOnCreate(Bundle extras) {
        pushurl=extras.getString(START_PUSHER);
        Log.e("pushurl",pushurl);
    }

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_startpusher,null);
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mContentView.findViewById(R.id.iv_back);

        //先创建一个Pusher对象，它是所有SDK调用接口的承载者
        mLivePusher = new TXLivePusher(mActivity);
        //创建config类对象
        mPushConfig = new TXLivePushConfig();
        mLivePusher.setConfig(mPushConfig);

        //设置推流地址
        mLivePusher.startPusher(pushurl);

        //推流view
        mCaptureView = (TXCloudVideoView) mContentView.findViewById(R.id.txcv_pusher);
        //将摄像头采集到画面渲染到屏幕上
        mLivePusher.startCameraPreview(mCaptureView);
    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        return ContentPage.RequestState.STATE_SUCCESS;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                mLivePusher.stopPusher();
                EndPusherActivity.intent(mActivity,"");//开启直播结束页
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*关闭*/
        mLivePusher.stopPusher();
    }
    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, StartPusherActivity.class);
        intent.putExtra(START_PUSHER, data);
        activity.startActivity(intent);
    }
    /*返回键监听*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            //返回键
            mLivePusher.stopPusher();
            EndPusherActivity.intent(mActivity,"");//开启直播结束页
            finish();
            return true;
        }

        return false;

    }

}
