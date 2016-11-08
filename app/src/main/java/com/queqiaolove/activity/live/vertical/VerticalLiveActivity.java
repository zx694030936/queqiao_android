package com.queqiaolove.activity.live.vertical;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.queqiaolove.R;
import com.queqiaolove.adapter.live.vertical.VerticalLiveBulletLvAdapter;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.im.imDanMuAdapter;
import com.queqiaolove.javabean.live.LiveUrlListBean;
import com.queqiaolove.widget.dialog.NoPusherDialog;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WD on 2016/10/7.
 */
public class VerticalLiveActivity extends BaseActivity implements View.OnClickListener, ITXLivePlayListener, EMMessageListener {

    private static String NORMAL_HORIZONTALLIVE = "NORMAL";
    private TXLivePlayer mLivePlayer;
    private TXCloudVideoView mPlayerView;
    private ImageView iv_back;
    private LiveUrlListBean.ListBean data;
    private String username = "";
    private String roomid = "";
    private String watch_num = "";
    private String play_rtmp = "";//拉流地址
    private String isend = "1";//是否在播:0在播，1结束

    private TextView tv_name;//主播名
    private TextView tv_numoflook;//观看人数
    private TextView tv_attention;//关注按钮
    private TextView tv_roomid;//房间id

    private ListView lv_bullet;//弹幕列表
    private imDanMuAdapter adapter;
    public List<EMMessage> messagelist = new ArrayList<>();
    private ImageView img_message;
    private RelativeLayout rl_bottom;
    private LinearLayout ll_bottom, ll_btngroup_vertivallive;
    private EditText edit_text;
    private Timer timer;
    private TextView tv_send;//发送消息的按钮
    private InputMethodManager imm;
    private InputMethodManager manager;


    @Override
    protected void initTitle() {

    }

    @Override
    protected void activityOnCreate(Bundle extras) {
        data = (LiveUrlListBean.ListBean) extras.getSerializable(NORMAL_HORIZONTALLIVE);
        if (data != null) {
            isend = data.getIsend();
            username = data.getUsername();
            roomid = data.getId();
            watch_num = data.getWatch_num();
            play_rtmp = data.getPlay_rtmp();
        }
    }

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        if (isend.equals("0")) {
            return View.inflate(mActivity, R.layout.activity_vertical_live, null);
        } else {
            return View.inflate(mActivity, R.layout.activity_vertical_nolive, null);
        }
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mContentView.findViewById(R.id.iv_back);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isend.equals("0")) {
            lv_bullet = (ListView) mContentView.findViewById(R.id.lv_bullet_verticallive);
            tv_name = (TextView) mContentView.findViewById(R.id.tv_name_verticallive);
            tv_numoflook = (TextView) mContentView.findViewById(R.id.tv_numoflook_verticallive);
            tv_attention = (TextView) mContentView.findViewById(R.id.tv_attention_verticallive);
            tv_roomid = (TextView) mContentView.findViewById(R.id.tv_roomid_verticallive);
            //playurl = "http://4501.liveplay.myqcloud.com/live/4501_queqiao_2.flv";
            //先创建一个Player对象,并使用setPlayerView将这个TXLivePlayer对象与我们刚刚添加到界面上的TXCloudVideoView控件进行关联
            mLivePlayer = new TXLivePlayer(mActivity);
            mPlayerView = (TXCloudVideoView) mContentView.findViewById(R.id.txcv_player);


            ll_btngroup_vertivallive = (LinearLayout) mContentView.findViewById(R.id.ll_btngroup_vertivallive);
            img_message = (ImageView) mContentView.findViewById(R.id.img_message);
            rl_bottom = (RelativeLayout) mContentView.findViewById(R.id.rl_bottom);
            edit_text = (EditText) mContentView.findViewById(R.id.edit_text);
            tv_send = (TextView) mContentView.findViewById(R.id.tv_send);
            tv_send.setOnClickListener(this);
            img_message.setOnClickListener(this);
            adapter = new imDanMuAdapter(this, messagelist);
            lv_bullet.setAdapter(adapter);

        } else {
            tv_name = (TextView) mContentView.findViewById(R.id.tv_name_verticalnolive);
            tv_numoflook = (TextView) mContentView.findViewById(R.id.tv_numoflook_verticalnolive);
            tv_attention = (TextView) mContentView.findViewById(R.id.tv_attention_verticalnolive);
            tv_roomid = (TextView) mContentView.findViewById(R.id.tv_roomid_verticalnolive);

            ll_bottom = (LinearLayout) mContentView.findViewById(R.id.ll_bottom);
            img_message = (ImageView) mContentView.findViewById(R.id.img_message);
            rl_bottom = (RelativeLayout) mContentView.findViewById(R.id.rl_bottom);
            edit_text = (EditText) mContentView.findViewById(R.id.edit_text);
            tv_send = (TextView) mContentView.findViewById(R.id.tv_send);
            tv_send.setOnClickListener(this);
            img_message.setOnClickListener(this);

        }
    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        if (isend.equals("0")) {
            mLivePlayer.setPlayListener(this);
        }
    }


    @Override
    protected ContentPage.RequestState onLoad() {

        return ContentPage.RequestState.STATE_SUCCESS;
    }

    /**
     * 从外部跳转到本类的反复
     *
     * @param activity
     */
    public static void intent(Activity activity, LiveUrlListBean.ListBean data) {
        Intent intent = new Intent();
        intent.setClass(activity, VerticalLiveActivity.class);
        intent.putExtra(NORMAL_HORIZONTALLIVE, data);
        activity.startActivity(intent);
    }

    @Override
    public View onCreateSuccessView() {
        if (username.trim().equals("")) {
            username = "主播名为空";
        }
        tv_name.setText(username);
        tv_numoflook.setText(watch_num);
        tv_roomid.setText(roomid);
        if (isend.equals("0")) {
            int result = mLivePlayer.startPlay(play_rtmp, TXLivePlayer.PLAY_TYPE_LIVE_RTMP);
            mLivePlayer.setPlayerView(mPlayerView);
            mPlayerView.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
            //设置观看的直播地址，和视频格式
        /*if (result == -2) {
            Toast.makeText(mActivity, "非腾讯云链接地址，若要放开限制，请联系腾讯云商务团队", Toast.LENGTH_SHORT).show();
        }*/
            Log.e("verticallive", result + "");

            lv_bullet.setAdapter(new VerticalLiveBulletLvAdapter(mActivity));
        } else {

        }
        return mContentView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*关闭直播观看*/
        if (mLivePlayer != null) {
            mLivePlayer.stopPlay(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                if (mLivePlayer != null) {
                    mLivePlayer.stopPlay(false);
                }
                finish();
                break;

            case R.id.img_message:
                if (isend.equals("0")) {
                    ll_btngroup_vertivallive.setVisibility(View.INVISIBLE);
                    rl_bottom.setVisibility(View.VISIBLE);
                    keyBoardShow();
                } else {
                    rl_bottom.setVisibility(View.VISIBLE);
                    ll_bottom.setVisibility(View.GONE);
                    keyBoardShow();
                }
                break;
            case R.id.tv_send:
                if (isend.equals("0")) {
                    ll_btngroup_vertivallive.setVisibility(View.VISIBLE);
                    rl_bottom.setVisibility(View.GONE);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    imm.hideSoftInputFromWindow(edit_text.getWindowToken(), 0);
                } else {
                    rl_bottom.setVisibility(View.GONE);
                    ll_bottom.setVisibility(View.VISIBLE);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    imm.hideSoftInputFromWindow(edit_text.getWindowToken(), 0);
                }
                
                


        }
    }

    void showSoftKeyboard() {
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

    }

    /*播放直播中的事件监听*/
    @Override
    public void onPlayEvent(int event, Bundle bundle) {
        switch (event) {
            case -2301://网络断连,且经多次重连抢救无效,可以放弃治疗,更多重试请自行重启播放
                NoPusherDialog noPusherDialog = new NoPusherDialog(mActivity);
                noPusherDialog.show();
                noPusherDialog.setDialogCallBackListener(new NoPusherDialog.NumDialogCallBackListener() {
                    @Override
                    public void callBack() {
                        if (mLivePlayer != null) {
                            mLivePlayer.stopPlay(true);
                        }
                        finish();
                    }
                });
                break;
        }
    }

    @Override
    public void onNetStatus(Bundle bundle) {

    }

    @Override
    public void onMessageReceived(List<EMMessage> list) {
        messagelist.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageReadAckReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageDeliveryAckReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }


    private void keyBoardShow() {
        edit_text.setFocusable(true);
        edit_text.setFocusableInTouchMode(true);
        edit_text.requestFocus();
        timer = new Timer();
        timer.schedule(new TimerTask() { //让软键盘延时弹出，以更好的加载Activity

            public void run() {
                showSoftKeyboard();
            }

        }, 300);
    }


}
