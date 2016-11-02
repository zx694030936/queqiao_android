package com.queqiaolove.activity.livevideo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.adapter.livevideo.LiveVideoLvAdapter;

/**
 * Created by WD on 2016/10/7.
 */
public class LiveVideoActivity extends Activity implements View.OnClickListener {

    private static String NORMAL_HORIZONTALLIVE = "NORMAL";
    private boolean issetVisibility = true;
    private ImageView iv_back;
    /*观看直播控件*/
    private ImageView mPlayerView;
    private View iv_fullscreen;//全屏
    private ImageView iv_littlescreen;//缩小屏幕
    private ListView lv_comment;//评论列表
    private TextView tv_title;
    private RelativeLayout rl_live_above;//工具隐藏

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livevideo_littlescreen);
        initVerticalView();
        initVerticalEvent();
    }

    private void initVerticalView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        lv_comment = (ListView) findViewById(R.id.lv_comment_livevideo);
        iv_fullscreen = findViewById(R.id.iv_fullscreen_livevideo);


        tv_title.setText("点播名");
        lv_comment.setAdapter(new LiveVideoLvAdapter(this));
    }

    private void initVerticalEvent() {
        iv_back.setOnClickListener(this);
        iv_fullscreen.setOnClickListener(this);
    }

    /**
     * 从外部跳转到本类的反复
     *
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, LiveVideoActivity.class);
        intent.putExtra(NORMAL_HORIZONTALLIVE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            /*竖屏*/

            case R.id.iv_fullscreen_livevideo://竖屏设置为全屏
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            /*横屏*/

            case R.id.iv_littlescreen_livevideo://全屏变竖屏
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case R.id.iv_player_livevideo://隐藏或显示工具栏
                issetVisibility = !issetVisibility;
                rl_live_above.setVisibility(issetVisibility?
                        View.VISIBLE:View.INVISIBLE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    /*切换横竖屏*/
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // land do nothing is ok横
            setContentView(R.layout.activity_livevideo_fullscreen);
            initHorizontalView();
            initHorizontalEvent();

        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // port do nothing is ok竖
            setContentView(R.layout.activity_livevideo_littlescreen);
            initVerticalView();
            initVerticalEvent();
        }
    }

    /*初始化横屏布局*/
    private void initHorizontalView() {
        issetVisibility = true;
        iv_back = (ImageView) findViewById(R.id.iv_back);
        rl_live_above = (RelativeLayout) findViewById(R.id.rl_livevideo_above);
        iv_littlescreen = (ImageView) findViewById(R.id.iv_littlescreen_livevideo);
        mPlayerView = (ImageView) findViewById(R.id.iv_player_livevideo);

        rl_live_above.setVisibility(View.VISIBLE);
    }

    private void initHorizontalEvent() {
        iv_back.setOnClickListener(this);
        mPlayerView.setOnClickListener(this);
        //mLivePlayer.setPlayListener(this);
        iv_littlescreen.setOnClickListener(this);
    }
}
