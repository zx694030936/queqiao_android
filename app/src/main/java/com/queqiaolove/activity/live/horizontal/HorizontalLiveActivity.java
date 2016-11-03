package com.queqiaolove.activity.live.horizontal;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.queqiaolove.R;
import com.queqiaolove.adapter.live.horizontal.ChartOrInfoVpAdapter;
import com.queqiaolove.javabean.live.LiveUrlListBean;
import com.queqiaolove.widget.NoScrollViewPager;
import com.queqiaolove.widget.dialog.NoPusherDialog;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

/**
 * Created by WD on 2016/10/7.
 */
public class HorizontalLiveActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, ITXLivePlayListener {

    private static String NORMAL_HORIZONTALLIVE = "NORMAL";
    private RadioGroup rg_chartorinfo;
    private NoScrollViewPager vp_chartorinfo;
    private RelativeLayout rl_live_above;
    private boolean issetVisibility = true;
    private ImageView iv_back;
    /*观看直播控件*/
    private TXLivePlayer mLivePlayer;
    private TXCloudVideoView mPlayerView;
    private ImageView iv_fullscreen;
    private ImageView iv_littlescreen;
    private ChartOrInfoVpAdapter adapter;
    private Bundle savedInstanceState;
    private LiveUrlListBean.ListBean data;

    private String username="";
    private String roomid ="";
    private String watch_num="";
    private String play_rtmp="";//拉流地址
    private String isend ="1";//是否在播:0在播，1结束

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOnCreate(getIntent().getExtras());
        setContentView(R.layout.activity_horizantollive_littlescreen);
        initVerticalView();
        initVerticalEvent();
        Log.e("verticalscreen","initview");
    }

    private void activityOnCreate(Bundle extras) {
        data = (LiveUrlListBean.ListBean) extras.getSerializable(NORMAL_HORIZONTALLIVE);
        if(data!=null){
            isend = data.getIsend();
            username = data.getUsername();
            roomid = data.getId();
            watch_num = data.getWatch_num();
            play_rtmp = data.getPlay_rtmp();
        }
    }

    private void initVerticalView() {
        if (isend.equals("1")) {//已结束
            setContentView(R.layout.activity_horizantollive_littlescreen);
            issetVisibility = true;
            iv_back = (ImageView) findViewById(R.id.iv_back);
            rl_live_above = (RelativeLayout) findViewById(R.id.rl_live_above);
            iv_fullscreen = (ImageView) findViewById(R.id.iv_full_norhorizontallive);

            rg_chartorinfo = (RadioGroup) findViewById(R.id.rg_chartorinfo);
            vp_chartorinfo = (NoScrollViewPager) findViewById(R.id.vp_chartorinfo);
            adapter = new ChartOrInfoVpAdapter(getSupportFragmentManager());
            vp_chartorinfo.setAdapter(null);
        }else {
            issetVisibility = true;
            iv_back = (ImageView) findViewById(R.id.iv_back);
            rl_live_above = (RelativeLayout) findViewById(R.id.rl_live_above);
            iv_fullscreen = (ImageView) findViewById(R.id.iv_full_norhorizontallive);

            rg_chartorinfo = (RadioGroup) findViewById(R.id.rg_chartorinfo);
            vp_chartorinfo = (NoScrollViewPager) findViewById(R.id.vp_chartorinfo);
            adapter = new ChartOrInfoVpAdapter(getSupportFragmentManager());
            vp_chartorinfo.setAdapter(adapter);

            if (mLivePlayer == null || mPlayerView == null) {
                //先创建一个Player对象,并使用setPlayerView将这个TXLivePlayer对象与我们刚刚添加到界面上的TXCloudVideoView控件进行关联
                mLivePlayer = new TXLivePlayer(this);
                int result = mLivePlayer.startPlay(play_rtmp, TXLivePlayer.PLAY_TYPE_LIVE_RTMP);

                mPlayerView = (TXCloudVideoView) findViewById(R.id.txcv_player);
                mLivePlayer.setPlayerView(mPlayerView);
                mPlayerView.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
                //mPlayerView.setRenderRotation(90);
            } else {
                mPlayerView = (TXCloudVideoView) findViewById(R.id.txcv_player);
                mLivePlayer.setPlayerView(mPlayerView);
                mPlayerView.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
                //mPlayerView.setRenderRotation(90);
            }
        }
    }

    private void initVerticalEvent() {
        if (isend.equals("0")) {
            mLivePlayer.setPlayListener(this);
        }
        iv_back.setOnClickListener(this);
        iv_fullscreen.setOnClickListener(this);
        mPlayerView.setOnClickListener(this);
        rg_chartorinfo.setOnCheckedChangeListener(this);
        rg_chartorinfo.check(R.id.rb_chart_horizontallive);
        vp_chartorinfo.setCurrentItem(0, false);

    }

    /**
     * 从外部跳转到本类的反复
     *
     * @param activity
     */
    public static void intent(Activity activity, LiveUrlListBean.ListBean data) {
        Intent intent = new Intent();
        intent.setClass(activity, HorizontalLiveActivity.class);
        intent.putExtra(NORMAL_HORIZONTALLIVE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_chart_horizontallive:
                vp_chartorinfo.setCurrentItem(0, false);
                break;
            case R.id.rb_info_horizontallive:
                vp_chartorinfo.setCurrentItem(1, false);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*竖屏*/
            case R.id.iv_full_norhorizontallive://竖屏设置为全屏
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            /*横屏*/
            case R.id.txcv_player:
                issetVisibility = !issetVisibility;
                rl_live_above.setVisibility(issetVisibility ? View.VISIBLE : View.INVISIBLE);
                break;
            case R.id.iv_back:
                mLivePlayer.stopPlay(true);
                finish();
                break;
            case R.id.iv_little_horizontallive:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLivePlayer!=null) {
            mLivePlayer.stopPlay(true);
        }
    }

    @Override
    public void onPlayEvent(int event, Bundle bundle) {
        switch (event) {
            case -2301://网络断连,且经多次重连抢救无效,可以放弃治疗,更多重试请自行重启播放
                NoPusherDialog noPusherDialog = new NoPusherDialog(this);
                noPusherDialog.show();
                noPusherDialog.setDialogCallBackListener(new NoPusherDialog.NumDialogCallBackListener() {
                    @Override
                    public void callBack() {
                        mLivePlayer.stopPlay(true);
                        finish();
                    }
                });
                break;
        }
    }

    @Override
    public void onNetStatus(Bundle bundle) {

    }

    /*切换横竖屏*/
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // land do nothing is ok横
            if (isend.equals("1")) {//已结束
                setContentView(R.layout.activity_nolive_fullscreen);
            }else {
                setContentView(R.layout.activity_horizontallive_fullscreen);
            }
            initHorizontalView();
            initHorizontalEvent();

        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // port do nothing is ok竖
            if (isend.equals("1")) {//已结束
                setContentView(R.layout.activity_nolive_littlescreen);
            }else {
                setContentView(R.layout.activity_horizantollive_littlescreen);
            }
            Log.e("verticalscreen","create");
            initVerticalView();
            initVerticalEvent();
        }
    }

    /*初始化横屏布局*/
    private void initHorizontalView() {
        issetVisibility = true;
        iv_back = (ImageView) findViewById(R.id.iv_back);
        rl_live_above = (RelativeLayout) findViewById(R.id.rl_live_above);
        iv_littlescreen = (ImageView) findViewById(R.id.iv_little_horizontallive);
        if (isend.equals("1")) {//已结束

        }else {

            mPlayerView = (TXCloudVideoView) findViewById(R.id.txcv_player);
            mLivePlayer.setPlayerView(mPlayerView);
            mPlayerView.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
            //mPlayerView.setRenderRotation(90);
        }

    }

    private void initHorizontalEvent() {
        iv_back.setOnClickListener(this);
        mPlayerView.setOnClickListener(this);
        iv_littlescreen.setOnClickListener(this);
        if (isend.equals("0")) {
            mLivePlayer.setPlayListener(this);
        }
    }
}
