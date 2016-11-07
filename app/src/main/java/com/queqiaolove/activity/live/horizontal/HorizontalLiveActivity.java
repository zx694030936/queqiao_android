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
import com.queqiaolove.global.Constants;
import com.queqiaolove.widget.NoScrollViewPager;
import com.queqiaolove.widget.dialog.NoPusherDialog;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.VideoView;

import java.util.Random;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;


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


    private static final String TAG = "MainActivity";

    private boolean showDanmaku;

    private DanmakuView danmakuView;

    private DanmakuContext danmakuContext;

    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_littlescreen_live);
        initVerticalView();
        initVerticalEvent();
        Log.e("verticalscreen", "initview");
        //initDan();
    }

    private void initVerticalView() {
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
            int result = mLivePlayer.startPlay(Constants.playUrl, TXLivePlayer.PLAY_TYPE_LIVE_RTMP);

            mPlayerView = (TXCloudVideoView) findViewById(R.id.txcv_player);
            mLivePlayer.setPlayerView(mPlayerView);
            mPlayerView.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
            mPlayerView.setRenderRotation(90);
        } else {
            mPlayerView = (TXCloudVideoView) findViewById(R.id.txcv_player);
            mLivePlayer.setPlayerView(mPlayerView);
            mPlayerView.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
            mPlayerView.setRenderRotation(90);
        }
    }

    private void initVerticalEvent() {
        iv_back.setOnClickListener(this);
        iv_fullscreen.setOnClickListener(this);
        mPlayerView.setOnClickListener(this);
        rg_chartorinfo.setOnCheckedChangeListener(this);
        rg_chartorinfo.check(R.id.rb_chart_horizontallive);
        vp_chartorinfo.setCurrentItem(0, false);

        mLivePlayer.setPlayListener(this);
    }

    /**
     * 从外部跳转到本类的反复
     *
     * @param activity
     */
    public static void intent(Activity activity, String data) {
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
        mLivePlayer.stopPlay(true);
        showDanmaku = false;
        if (danmakuView != null) {
            danmakuView.release();
            danmakuView = null;
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
            setContentView(R.layout.activity_fullscreen_live);
            initHorizontalView();
            initHorizontalEvent();
            stopDanMu();//停止弹幕，必须调用
            initDanMu();

        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // port do nothing is ok竖
            setContentView(R.layout.activity_littlescreen_live);
            Log.e("verticalscreen", "create");
            initVerticalView();
            initVerticalEvent();
            stopDanMu();//停止弹幕，必须调用
            initDanMu();
        }
    }

    /*初始化横屏布局*/
    private void initHorizontalView() {
        issetVisibility = true;
        iv_back = (ImageView) findViewById(R.id.iv_back);
        rl_live_above = (RelativeLayout) findViewById(R.id.rl_live_above);
        iv_littlescreen = (ImageView) findViewById(R.id.iv_little_horizontallive);

        mPlayerView = (TXCloudVideoView) findViewById(R.id.txcv_player);
        mLivePlayer.setPlayerView(mPlayerView);
        mPlayerView.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
        mPlayerView.setRenderRotation(90);

    }

    private void initHorizontalEvent() {
        iv_back.setOnClickListener(this);
        mPlayerView.setOnClickListener(this);
        mLivePlayer.setPlayListener(this);
        iv_littlescreen.setOnClickListener(this);
    }


    private void initDanMu(){
        danmakuView = (DanmakuView) findViewById(R.id.danmaku_view);
        danmakuView.enableDanmakuDrawingCache(true);
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                danmakuView.start();
                generateSomeDanmaku();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuContext = DanmakuContext.create();
        danmakuView.prepare(parser, danmakuContext);
        final  LinearLayout operationLayout = (LinearLayout) findViewById(R.id.operation_layout);
        final Button send = (Button) findViewById(R.id.send);
        final EditText editText = (EditText) findViewById(R.id.edit_text);
        danmakuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (operationLayout.getVisibility() == View.GONE) {
                    operationLayout.setVisibility(View.VISIBLE);
                } else {
                    operationLayout.setVisibility(View.GONE);
                }
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editText.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    addDanmaku(content, true);
                    editText.setText("");
                }
            }
        });
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == View.SYSTEM_UI_FLAG_VISIBLE) {
                    onWindowFocusChanged(true);
                }
            }
        });

        //danmakuView.setVisibility(View.GONE);//关闭弹幕

//        danmakuView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addDanmaku("121refdfdter4r",false);
//            }
//        });
    }

    /**
     * 向弹幕View中添加一条弹幕
     * @param content
     *          弹幕的具体内容
     * @param  withBorder
     *          弹幕是否有边框
     */
    private void addDanmaku(String content, boolean withBorder) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = sp2px(20);
        danmaku.textColor = Color.WHITE;
        danmaku.setTime(danmakuView.getCurrentTime());
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }
        danmakuView.addDanmaku(danmaku);
    }

    /**
     * 随机生成一些弹幕内容以供测试
     */
    private void generateSomeDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(showDanmaku) {
                    int time = new Random().nextInt(300);
                    String content = "" + time + time;
                    addDanmaku(content, false);
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * sp转px的方法。
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (danmakuView != null && danmakuView.isPrepared()) {
            danmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (danmakuView != null && danmakuView.isPrepared() && danmakuView.isPaused()) {
            danmakuView.resume();
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /**
     * 注销弹幕
     */
    private void stopDanMu(){
        showDanmaku = false;
        if (danmakuView != null) {
            danmakuView.release();
            danmakuView = null;
        }
    }

}
