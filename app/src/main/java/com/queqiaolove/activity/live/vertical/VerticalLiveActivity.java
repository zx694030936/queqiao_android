package com.queqiaolove.activity.live.vertical;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMChatRoomChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chatuidemo.DemoHelper;
import com.hyphenate.chatuidemo.DemoModel;
import com.hyphenate.easeui.EaseConstant;
import com.queqiaolove.R;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.im.GiftShowManager;
import com.queqiaolove.im.GiftVo;
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
    private ImageView img_sendgif;


    private SurfaceView surfaceView;//后面的SurfaceView
    private LinearLayout giftCon;//礼物的里列表的外层ViewGroup
    private int SCREEN_WITH = 0;//屏幕的宽度
    private int SCREEN_HEIGHT = 0;//屏幕的高度
    private GiftShowManager giftManger;
    private DemoModel settingsModel;

    private int chatType;
    private String toChatUsername;
    private EMChatRoomChangeListener chatRoomChangeListener;


    @Override
    protected void initTitle() {

    }

    @Override
    protected void activityOnCreate(Bundle extras) {
        data = (LiveUrlListBean.ListBean) extras.getSerializable(NORMAL_HORIZONTALLIVE);
        if (data != null) {
            // isend = data.getIsend();
            isend = "0";
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
            img_sendgif = (ImageView) mContentView.findViewById(R.id.img_sendgif);
            img_sendgif.setOnClickListener(this);
            initGif();

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
            img_sendgif = (ImageView) mContentView.findViewById(R.id.img_sendgif);
            img_sendgif.setOnClickListener(this);
        }

        initEventData();
        initIM();//配置环信
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
        intent.putExtra(EaseConstant.EXTRA_USER_ID, "114714059982504528");//测试用的聊天室ID
//        intent.putExtra(EaseConstant.EXTRA_USER_ID, data.getGroupid());//测试用的聊天室ID
        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, 3);//聊天室为3
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

            //lv_bullet.setAdapter(new VerticalLiveBulletLvAdapter(mActivity));
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
        EMClient.getInstance().chatManager().removeMessageListener(this);
        EMClient.getInstance().chatroomManager().leaveChatRoom(toChatUsername);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(this);//注册消息监听
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
                    String content = edit_text.getText().toString().trim();
                    if (!TextUtils.isEmpty(content)) {
                        sendTextMessage(edit_text.getText().toString().trim());
                    }
                } else {
                    rl_bottom.setVisibility(View.GONE);
                    ll_bottom.setVisibility(View.VISIBLE);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    imm.hideSoftInputFromWindow(edit_text.getWindowToken(), 0);
                }


                break;

            case R.id.img_sendgif:
                giftManger.showGift();//开始显示礼物
                new Thread(new GetGiftRunnable()).start();//启动线程获取礼物的Vo
                break;


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

    /**
     * 此监听是消息接收的监听，每次加入聊天室的时候，环信会强制返回10条消息，环信的问题。。。
     * @param list
     */
    @Override
    public void onMessageReceived(List<EMMessage> list) {
        messagelist.addAll(list);
        adapter.notifyDataSetChanged();
        lv_bullet.setSelection(lv_bullet.getBottom());//收到消息时，滑动到ListView的底部

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


    /**
     * 送礼物动画
     */
    private void initGif() {

        DisplayMetrics dm = getResources().getDisplayMetrics();

        SCREEN_WITH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;

        surfaceView = (SurfaceView) mContentView.findViewById(R.id.surface_view);
        surfaceView.getHolder().addCallback(new LivingHolderCallBack());
        giftCon = (LinearLayout) mContentView.findViewById(R.id.gift_con);


        giftManger = new GiftShowManager(this, giftCon);


    }


    //往队列中加入礼物
    private class GetGiftRunnable implements Runnable {
        @Override
        public void run() {
            GiftVo vo = new GiftVo();
            vo.setUserId("unfind");
            vo.setNum(1);
            giftManger.addGift(vo);
            GiftVo vo2 = new GiftVo();
            vo2.setUserId("unfind");
            vo2.setNum(1);
            giftManger.addGift(vo2);

            GiftVo vo3 = new GiftVo();
            vo3.setUserId("zhong");
            vo3.setNum(1);
            giftManger.addGift(vo3);
            GiftVo vo4 = new GiftVo();
            vo4.setUserId("xiao");
            vo4.setNum(1);
            giftManger.addGift(vo4);
            GiftVo vo5 = new GiftVo();
            vo5.setUserId("xiao");
            vo5.setNum(1);
            giftManger.addGift(vo5);
            GiftVo vo6 = new GiftVo();
            vo6.setUserId("xiao");
            vo6.setNum(1);
            giftManger.addGift(vo6);

        }
    }


    //SurfaceView绘制直播的界面的子类
    private class LivingHolderCallBack implements SurfaceHolder.Callback {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Canvas canvas = holder.lockCanvas();

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.girl);
            RectF rectF = new RectF(0, 0, SCREEN_WITH, SCREEN_HEIGHT);   //w和h分别是屏幕的宽和高，也就是你想让图片显示的宽和高
            canvas.drawBitmap(bm, null, rectF, null);
            holder.unlockCanvasAndPost(canvas);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }


    /**
     * 配置环信
     */
    private void initIM() {
        settingsModel = DemoHelper.getInstance().getModel();
        settingsModel.setSettingMsgNotification(false);//关闭消息通知（通知栏），聊天室消息不需要消息提醒。
        onChatRoomViewCreation();
    }

    private void initEventData() {
        chatType = getIntent().getExtras().getInt(EaseConstant.EXTRA_CHAT_TYPE, 0);//获取聊天类型
        toChatUsername = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_ID);//获取聊天ID
        Log.w("chatType", "chatType" + chatType);
        Log.w("toChatUsername", "toChatUsername" + toChatUsername);
    }


    /**
     * 发送消息
     *
     * @param content
     */
    private void sendTextMessage(String content) {

        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
        /**
         * 消息扩展,昵称以及头像等
         */
        message.setAttribute("usernick", message.getFrom());//昵称（目前暂时用环信ID代替昵称显示，实际应该从本地取app的用户昵称）
        //message.setAttribute("userhead",......);//头像
        //message.setAttribute("userlevel",......);//会员等级


        sendMessage(message, content);

    }

    private void sendMessage(EMMessage message, final String content) {
        if (message == null) {
            return;
        }
        if (chatType == EaseConstant.CHATTYPE_CHATROOM) {//聊天室类型的
            message.setChatType(EMMessage.ChatType.ChatRoom);
        }
        EMClient.getInstance().chatManager().sendMessage(message);//环信发送消息的方法

        messagelist.add(message);
        adapter.notifyDataSetChanged();
        lv_bullet.setSelection(lv_bullet.getBottom());//收到消息时，滑动到ListView的底部
        edit_text.setText("");
    }


    public void onChatRoomViewCreation() {
        EMClient.getInstance().chatroomManager().joinChatRoom(toChatUsername, new EMValueCallBack<EMChatRoom>() {
            @Override
            public void onSuccess(final EMChatRoom value) {
                addChatRoomChangeListenr();
            }

            @Override
            public void onError(final int error, String errorMsg) {

            }
        });
    }


    protected void addChatRoomChangeListenr() {
        chatRoomChangeListener = new EMChatRoomChangeListener() {

            @Override
            public void onChatRoomDestroyed(String roomId, String roomName) {
                if (roomId.equals(toChatUsername)) {
                    showChatroomToast(" room : " + roomId + " with room name : " + roomName + " was destroyed");
                    finish();
                }
            }

            @Override
            public void onMemberJoined(String roomId, String participant) {
                showChatroomToast("member : " + participant + " join the room : " + roomId);
            }

            @Override
            public void onMemberExited(String roomId, String roomName, String participant) {
                showChatroomToast("member : " + participant + " leave the room : " + roomId + " room name : " + roomName);
            }

            @Override
            public void onMemberKicked(String roomId, String roomName, String participant) {
                if (roomId.equals(toChatUsername)) {
                    String curUser = EMClient.getInstance().getCurrentUser();
                    if (curUser.equals(participant)) {
                        EMClient.getInstance().chatroomManager().leaveChatRoom(toChatUsername);
                        finish();
                    } else {
                        showChatroomToast("member : " + participant + " was kicked from the room : " + roomId + " room name : " + roomName);
                    }
                }
            }

        };

        EMClient.getInstance().chatroomManager().addChatRoomChangeListener(chatRoomChangeListener);
    }


    protected void showChatroomToast(final String toastContent) {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(VerticalLiveActivity.this, toastContent, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
