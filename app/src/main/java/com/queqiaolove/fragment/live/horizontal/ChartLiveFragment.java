package com.queqiaolove.fragment.live.horizontal;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMChatRoomChangeListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.hyphenate.util.EMLog;
import com.queqiaolove.R;
import com.queqiaolove.activity.live.horizontal.HorizontalLiveActivity;
import com.queqiaolove.adapter.live.horizontal.HorizontalLiveBulletLvAdapter;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.im.imDanMuAdapter;


import java.util.ArrayList;
import java.util.List;

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
    protected EMConversation conversation;
    protected Handler handler = new Handler();
    protected EMMessage contextMenuMessage;
    private EMChatRoomChangeListener chatRoomChangeListener;





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
        chatType = fragmentArgs.getInt(EaseConstant.EXTRA_CHAT_TYPE);//获取聊天类型（该数值为聊天室的）
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

       // onChatRoomViewCreation();//配置聊天室相关的信息
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
                lv_bulletscreen.setSelection(lv_bulletscreen.getBottom());//收到消息时，滑动到ListView的底部
                adapter.notifyDataSetChanged();
                Log.w("adapter","adapter");

            }
        }
    }







//    protected void onChatRoomViewCreation() {
//        final ProgressDialog pd = ProgressDialog.show(getActivity(), "", "Joining......");
//        EMClient.getInstance().chatroomManager().joinChatRoom(toChatUsername, new EMValueCallBack<EMChatRoom>() {
//            @Override
//            public void onSuccess(final EMChatRoom value) {
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(getActivity().isFinishing() || !toChatUsername.equals(value.getId()))
//                            return;
//                        pd.dismiss();
//                        EMChatRoom room = EMClient.getInstance().chatroomManager().getChatRoom(toChatUsername);
//                        if (room != null) {
//                            EMLog.d(TAG, "join room success : " + room.getName());
//                        } else {
//                        }
//                        addChatRoomChangeListenr();
//                        onConversationInit();
//                    }
//                });
//            }
//
//            @Override
//            public void onError(final int error, String errorMsg) {
//
//            }
//        });
//    }


    protected void onConversationInit(){
        conversation = EMClient.getInstance().chatManager().getConversation(toChatUsername, EaseCommonUtils.getConversationType(chatType), true);
        conversation.markAllMessagesAsRead();
        final List<EMMessage> msgs = conversation.getAllMessages();

    }


//    protected void addChatRoomChangeListenr() {
//        chatRoomChangeListener = new EMChatRoomChangeListener() {
//
//            @Override
//            public void onChatRoomDestroyed(String roomId, String roomName) {
//                if (roomId.equals(toChatUsername)) {
//                    showChatroomToast(" room : " + roomId + " with room name : " + roomName + " was destroyed");
//                    getActivity().finish();
//                }
//            }
//
//            @Override
//            public void onMemberJoined(String roomId, String participant) {
//                showChatroomToast("member : " + participant + " join the room : " + roomId);
//            }
//
//            @Override
//            public void onMemberExited(String roomId, String roomName, String participant) {
//                showChatroomToast("member : " + participant + " leave the room : " + roomId + " room name : " + roomName);
//            }
//
//            @Override
//            public void onMemberKicked(String roomId, String roomName, String participant) {
//                if (roomId.equals(toChatUsername)) {
//                    String curUser = EMClient.getInstance().getCurrentUser();
//                    if (curUser.equals(participant)) {
//                        EMClient.getInstance().chatroomManager().leaveChatRoom(toChatUsername);
//                        getActivity().finish();
//                    }else{
//                        showChatroomToast("member : " + participant + " was kicked from the room : " + roomId + " room name : " + roomName);
//                    }
//                }
//            }
//
//        };
//
//        EMClient.getInstance().chatroomManager().addChatRoomChangeListener(chatRoomChangeListener);
//    }





    protected void showChatroomToast(final String toastContent){
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getActivity(), toastContent, Toast.LENGTH_SHORT).show();
            }
        });
    }















    /**
     * 发送消息
     * @param content
     */
    private void sendTextMessage(String content) {

        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
        sendMessage(message,content);

    }

    private void sendMessage(EMMessage message,String content){
        if (message == null) {
            return;
        }
        if (chatType == EaseConstant.CHATTYPE_GROUP){
            message.setChatType(EMMessage.ChatType.GroupChat);
        }else if(chatType == EaseConstant.CHATTYPE_CHATROOM){
            message.setChatType(EMMessage.ChatType.ChatRoom);
        }
        EMClient.getInstance().chatManager().sendMessage(message);

        HorizontalLiveActivity.instance.messagelist.add(message);
        edit_text.setText("");


    }







}
