package com.queqiaolove.activity.live.horizontal;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMChatRoomChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chatuidemo.DemoHelper;
import com.hyphenate.chatuidemo.DemoModel;
import com.hyphenate.easeui.EaseConstant;
import com.queqiaolove.R;
import com.queqiaolove.adapter.live.horizontal.ChartOrInfoVpAdapter;
import com.queqiaolove.im.GiftShowManager;
import com.queqiaolove.im.GiftVo;
import com.queqiaolove.javabean.live.LiveUrlListBean;
import com.queqiaolove.widget.NoScrollViewPager;
import com.queqiaolove.widget.dialog.NoPusherDialog;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import java.util.ArrayList;
import java.util.List;
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
public class HorizontalLiveActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, ITXLivePlayListener,EMMessageListener {

    private static String NORMAL_HORIZONTALLIVE = "NORMAL";
    private RadioGroup rg_chartorinfo;
    private NoScrollViewPager vp_chartorinfo;
    private RelativeLayout rl_live_above;
    private boolean issetVisibility = true;
    private ImageView iv_back;

    private ImageView iv_switch_block_groupmsg;//开启弹幕

    private ImageView iv_switch_unblock_groupmsg;//关闭弹幕
    /*观看直播控件*/
    private TXLivePlayer mLivePlayer;
    private TXCloudVideoView mPlayerView;
    private ImageView iv_fullscreen;
    private ImageView iv_littlescreen;
    private ChartOrInfoVpAdapter adapter;
    private int chatType;
    private String toChatUsername;

    public List<EMMessage> messagelist = new ArrayList<>();//存放聊天消息的列表
    public static  HorizontalLiveActivity instance;

    private TextView send;
    private EditText editText;

    private EMChatRoomChangeListener chatRoomChangeListener;
    private DemoModel settingsModel;

    private SurfaceView surfaceView;//后面的SurfaceView
    private LinearLayout giftCon;//礼物的里列表的外层ViewGroup
    private int SCREEN_WITH = 0;//屏幕的宽度
    private int SCREEN_HEIGHT = 0;//屏幕的高度
    private GiftShowManager giftManger;
    private ImageView img_gift;

    private boolean showDanmaku;
    private DanmakuView danmakuView;
    private DanmakuContext danmakuContext;
    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };
    private String username;
    private String roomid;
    private String watch_num;
    private String play_rtmp = "";
    private String groupid;
    private LiveUrlListBean.ListBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_littlescreen_live);
        messagelist.clear();
        activityOnCreate(getIntent().getExtras());
        instance = this;
        initVerticalView();
        initVerticalEvent();
        initEventData();
        initIM();//配置环信

    }
    private void activityOnCreate(Bundle extras) {
        data = (LiveUrlListBean.ListBean)extras.getSerializable(NORMAL_HORIZONTALLIVE);
        if (data != null) {
            // isend = data.getIsend();
            username = data.getUsername();
            roomid = data.getId();
            watch_num = data.getWatch_num();
            play_rtmp = data.getPlay_rtmp();
            groupid = data.getGroupid();
        }
    }
    /**
     * 配置环信
     */
    private void initIM(){
        settingsModel = DemoHelper.getInstance().getModel();
        settingsModel.setSettingMsgNotification(false);//关闭消息通知（通知栏），聊天室消息不需要消息提醒。
        onChatRoomViewCreation();
    }

    /**
     * 送礼物动画
     */
    private void initGif(){
        img_gift = (ImageView) findViewById(R.id.img_gift);
        img_gift.setOnClickListener(this);
        DisplayMetrics dm = getResources().getDisplayMetrics();

        SCREEN_WITH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;

        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        surfaceView.getHolder().addCallback(new LivingHolderCallBack());
        giftCon = (LinearLayout) findViewById(R.id.gift_con);


        giftManger = new GiftShowManager(this, giftCon);


    }


    private void initEventData(){
        chatType = getIntent().getExtras().getInt(EaseConstant.EXTRA_CHAT_TYPE,0) ;//获取聊天类型
        toChatUsername = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_ID);//获取聊天ID
        Log.w("chatType", "chatType" + chatType);
        Log.w("toChatUsername", "toChatUsername" + toChatUsername);
    }

    private void initVerticalView() {
        issetVisibility = true;
        iv_back = (ImageView) findViewById(R.id.iv_back);
        rl_live_above = (RelativeLayout) findViewById(R.id.rl_live_above);
        iv_fullscreen = (ImageView) findViewById(R.id.iv_full_norhorizontallive);

        rg_chartorinfo = (RadioGroup) findViewById(R.id.rg_chartorinfo);
        vp_chartorinfo = (NoScrollViewPager) findViewById(R.id.vp_chartorinfo);
        adapter = new ChartOrInfoVpAdapter(getSupportFragmentManager(),getIntent().getExtras());

        vp_chartorinfo.setAdapter(adapter);


        if (mLivePlayer == null || mPlayerView == null) {
            //先创建一个Player对象,并使用setPlayerView将这个TXLivePlayer对象与我们刚刚添加到界面上的TXCloudVideoView控件进行关联
            mLivePlayer = new TXLivePlayer(this);
            int result = mLivePlayer.startPlay(play_rtmp, TXLivePlayer.PLAY_TYPE_LIVE_RTMP);

            mPlayerView = (TXCloudVideoView) findViewById(R.id.txcv_player);
            mLivePlayer.setPlayerView(mPlayerView);
            mPlayerView.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
        } else {
            mPlayerView = (TXCloudVideoView) findViewById(R.id.txcv_player);
            mLivePlayer.setPlayerView(mPlayerView);
            mPlayerView.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
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
    public static void intent(Activity activity, LiveUrlListBean.ListBean data) {
        Intent intent = new Intent();
        intent.setClass(activity, HorizontalLiveActivity.class);
        intent.putExtra(NORMAL_HORIZONTALLIVE, data);
//        intent.putExtra(EaseConstant.EXTRA_USER_ID, "114714059982504528");//测试用的聊天室ID
        intent.putExtra(EaseConstant.EXTRA_USER_ID, data.getGroupid());//测试用的聊天室ID
        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, 3);
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
            case R.id.iv_switch_block_groupmsg:
                if (iv_switch_block_groupmsg.getVisibility() == View.VISIBLE){
                    iv_switch_unblock_groupmsg.setVisibility(View.VISIBLE);
                    iv_switch_block_groupmsg.setVisibility(View.GONE);
                    stopDanMu();
                }
                break;
            case R.id.iv_switch_unblock_groupmsg:
                if (iv_switch_block_groupmsg.getVisibility() == View.GONE){
                    iv_switch_unblock_groupmsg.setVisibility(View.GONE);
                    iv_switch_block_groupmsg.setVisibility(View.VISIBLE);
                    initDanMu();
                }
                break;
            case R.id.img_gift:
                giftManger.showGift();//开始显示礼物
                new Thread(new GetGiftRunnable()).start();//启动线程获取礼物的Vo
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true);
        stopDanMu();
        EMClient.getInstance().chatManager().removeMessageListener(this);
        EMClient.getInstance().chatroomManager().leaveChatRoom(toChatUsername);

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
            initDanMu();//初始化弹幕
            initGif();//送礼物动画

        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // port do nothing is ok竖
            setContentView(R.layout.activity_littlescreen_live);
            Log.e("verticalscreen", "create");
            initVerticalView();
            initVerticalEvent();
            stopDanMu();//停止弹幕，必须调用
           // initDanMu();
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

        iv_switch_block_groupmsg = (ImageView) findViewById(R.id.iv_switch_block_groupmsg);
        iv_switch_unblock_groupmsg = (ImageView) findViewById(R.id.iv_switch_unblock_groupmsg);

        iv_switch_block_groupmsg.setOnClickListener(this);
        iv_switch_unblock_groupmsg.setOnClickListener(this);



    }

    private void initHorizontalEvent() {
        iv_back.setOnClickListener(this);
        mPlayerView.setOnClickListener(this);
        mLivePlayer.setPlayListener(this);
        iv_littlescreen.setOnClickListener(this);
    }

    /**
     * 初始化弹幕
     */
    private void initDanMu(){
        danmakuView = (DanmakuView) findViewById(R.id.danmaku_view);
        initVoiceView();//设置弹幕的高度
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
        send = (TextView) findViewById(R.id.send);
        editText = (EditText) findViewById(R.id.edit_text);
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
                    sendTextMessage(content);
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
    private void addDanmaku(String content, boolean withBorder) {//第二个参数为是否带绿色边框
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
        EMClient.getInstance().chatManager().addMessageListener(this);//注册消息监听
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


    /**
     * 设置弹幕的高度
     */
    private void initVoiceView() {

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
         int height = metric.heightPixels;//获取总高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.height = height/2;
        params.width = params.MATCH_PARENT;
        danmakuView.setLayoutParams(params);
    }


    //线往队列中加入礼物
    private class GetGiftRunnable implements Runnable {
        @Override
        public void run() {
            Log.w("123","123");
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
     * 接收到他人发的消息后，发送广播
     */
    private void sendReceiver(List<EMMessage> messages){
        HorizontalLiveActivity.instance.messagelist.addAll(messages);
        Intent intent = new Intent();
        intent.setAction("danmu_message");
        sendBroadcast(intent);//发送广播，通知ChartLiveFragment刷新
        Log.w("list", "list" + HorizontalLiveActivity.instance.messagelist.size());
        sendDanMu(messages);
    }

    private void sendDanMu(List<EMMessage> messages){
        for (EMMessage message : messages) {
            // sendDanMu(message);
            EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
            String msg = txtBody.getMessage();
            addDanmaku(msg, false);
        }

    }




    /**
     * 接收消息的监听
     */

    @Override
    public void onMessageReceived(List<EMMessage> messages) {//收到消息
            sendReceiver(messages);

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








    /**
     * 发送消息（横屏）
     * @param content
     */
    private void sendTextMessage(String content) {

        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
        /**
         * 消息扩展,昵称以及头像等
         */
        message.setAttribute("usernick",message.getFrom());//昵称
        //message.setAttribute("userhead",message.getFrom());//头像
        //message.setAttribute("userlevel",message.getFrom());//会员等级

        sendMessage(message, content);

    }

    private void sendMessage(EMMessage message, final String content){
        if (message == null) {
            return;
        }
        if(chatType == EaseConstant.CHATTYPE_CHATROOM){//聊天室类型的
            message.setChatType(EMMessage.ChatType.ChatRoom);
        }
        EMClient.getInstance().chatManager().sendMessage(message);//环信发送消息的方法
        messagelist.add(message);
        editText.setText("");
        if (iv_switch_block_groupmsg.getVisibility() == View.VISIBLE){//弹幕开关开启，才能显示弹幕
            addDanmaku(content, true);
        }


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
                    }else{
                        showChatroomToast("member : " + participant + " was kicked from the room : " + roomId + " room name : " + roomName);
                    }
                }
            }

        };

        EMClient.getInstance().chatroomManager().addChatRoomChangeListener(chatRoomChangeListener);
    }





    protected void showChatroomToast(final String toastContent){
       runOnUiThread(new Runnable() {
           public void run() {
               Toast.makeText(HorizontalLiveActivity.this, toastContent, Toast.LENGTH_SHORT).show();
           }
       });
    }



}
