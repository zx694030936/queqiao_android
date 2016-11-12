package com.queqiaolove.fragment.live.horizontal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.queqiaolove.R;
import com.queqiaolove.activity.live.horizontal.HorizontalLiveActivity;
import com.queqiaolove.im.imDanMuAdapter;

/**
 * Created by WD on 2016/10/7.
 */
public class ChartLiveFragment extends Fragment implements View.OnClickListener{
    private ListView lv_bulletscreen;
    private imDanMuAdapter adapter;
    private Receiver rec;//接收消息的广播
    private TextView tv_send;
    private View view;
    private Bundle fragmentArgs;
    private String toChatUsername;
    private int chatType;
    private EditText edit_text;

    protected static final String TAG = "EaseChatFragment";
    protected static final int REQUEST_CODE_MAP = 1;
    protected static final int REQUEST_CODE_CAMERA = 2;
    protected static final int REQUEST_CODE_LOCAL = 3;
    protected Handler handler = new Handler();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.vpitem_chart_horizantollive, null);
        initIMView();
        return view;
    }

    /**
     * 获取Activity向Fragment的传值，此方法里的数据和聊天相关
     */
    private void initIMData(){
        fragmentArgs = getArguments();
        toChatUsername = fragmentArgs.getString(EaseConstant.EXTRA_USER_ID);//获取聊天对象ID
        chatType = fragmentArgs.getInt(EaseConstant.EXTRA_CHAT_TYPE);//获取聊天类型
        Log.w("user", "user" + toChatUsername + "  " + chatType);

    }

    /**
     * 注册该广播接受聊天消息
     */
    private void initReceiver(){
        rec = new Receiver();
        IntentFilter bi = new IntentFilter();
        bi.addAction("danmu_message");
        getActivity().registerReceiver(rec, bi);
    }


    private void initIMView() {

        initIMData();
        initReceiver();

        edit_text = (EditText) view.findViewById(R.id.edit_text);
        lv_bulletscreen = (ListView) view.findViewById(R.id.lv_bulletscreen);
        tv_send = (TextView) view.findViewById(R.id.tv_send);
        adapter = new imDanMuAdapter(getActivity(), HorizontalLiveActivity.instance.messagelist);
        lv_bulletscreen.setAdapter(adapter);

        tv_send.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                sendTextMessage(edit_text.getText().toString().trim());
                adapter.notifyDataSetChanged();
                lv_bulletscreen.setSelection(lv_bulletscreen.getBottom());//发送消息时，滑动到ListView的底部
                break;
        }


    }



    /**
     * 接收消息的广播，收到广播后，刷新适配器
     */
    public class Receiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("danmu_message")) {
                adapter.notifyDataSetChanged();
                lv_bulletscreen.setSelection(lv_bulletscreen.getBottom());//收到消息时，滑动到ListView的底部
                Log.w("adapter","adapter");

            }
        }
    }



    /**
     * 发送消息（竖屏）
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
        sendMessage(message,content);

    }

    private void sendMessage(EMMessage message,String content){
        if (message == null) {
            return;
        }
       if(chatType == EaseConstant.CHATTYPE_CHATROOM){
            message.setChatType(EMMessage.ChatType.ChatRoom);
        }
        EMClient.getInstance().chatManager().sendMessage(message);//环信发送消息的方法
        HorizontalLiveActivity.instance.messagelist.add(message);
        edit_text.setText("");
        lv_bulletscreen.setSelection(lv_bulletscreen.getBottom());//收到消息时，滑动到ListView的底部

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(rec);
    }
}
