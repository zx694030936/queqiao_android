package com.queqiaolove.activity.pusher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.queqiaolove.QueQiaoLoveApp;
import com.queqiaolove.R;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.global.Constants;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.LiveAPI;
import com.queqiaolove.javabean.push.GetPushUrlBean;
import com.queqiaolove.widget.dialog.LockHomePusherDialog;
import com.queqiaolove.widget.dialog.SelectTopicPusherDialog;


import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by WD on 2016/10/8.
 */
public class HomePusherActivity extends BaseActivity implements View.OnClickListener, SelectTopicPusherDialog.SearchRangeListener {
    private static String HOME_PUSHER = "HOME";
    private ImageView iv_back;
    private TextView tv_addtopic;//添加话题
    private ImageView iv_lock;
    private TextView tv_startpusher;
    private EditText et_headline;
    private String topic;
    private String headline;
    private int ticket_price;
    private String tag = "imgroup";

    @Override
    protected void initTitle() {

    }

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_homepusher, null);
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mContentView.findViewById(R.id.iv_back);
        tv_addtopic = (TextView) mContentView.findViewById(R.id.tv_addtopic_homepusher);
        iv_lock = (ImageView) mContentView.findViewById(R.id.iv_lock_homepusher);
        tv_startpusher = (TextView) mContentView.findViewById(R.id.tv_startpusher);
        et_headline = (EditText) mContentView.findViewById(R.id.et_headline_homepusher);
        /*im 通讯管理器初始化*/
        //initIM();
    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        tv_addtopic.setOnClickListener(this);
        iv_lock.setOnClickListener(this);
        tv_startpusher.setOnClickListener(this);
    }

    /**
     * 从外部跳转到本类的反复
     *
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, HomePusherActivity.class);
        intent.putExtra(HOME_PUSHER, data);
        activity.startActivity(intent);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        return ContentPage.RequestState.STATE_SUCCESS;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:// 返回按钮
                finish();
                break;
            case R.id.tv_addtopic_homepusher://添加话题
                SelectTopicPusherDialog selectTopicPusherDialog = new SelectTopicPusherDialog(mActivity);
                selectTopicPusherDialog.show();
                selectTopicPusherDialog.setSearchRangeListener(this);
                break;
            case R.id.iv_lock_homepusher://加密选项
                LockHomePusherDialog lockHomePusherDialog = new LockHomePusherDialog(mActivity);
                lockHomePusherDialog.show();
                break;
            case R.id.tv_startpusher://开始直播
                headline = et_headline.getText().toString().trim();
                topic = tv_addtopic.getText().toString().trim();
                //headline="标题";
                //topic = "话题";
                ticket_price = 0;
                if (topic.equals("") || headline.equals("")) {
                    Toast.makeText(mActivity, "标题或话题不能为空!", Toast.LENGTH_SHORT).show();
                    break;
                }
                getPushUrl();
                //guestLogin();
                //createLiveGroup("tvshow");
                //getProviceList();
                break;
        }
    }




    /*获取推流地址*/
    private void getPushUrl() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put(Constants.USERID, 2);
        map.put(Constants.IF_OPEN, Constants.IF_OPEN_OPEN);

        map.put(Constants.TICKET_PRICE, ticket_price);
        map.put(Constants.BEGIN_AGE, 0);
        map.put(Constants.END_AGE, 0);
        map.put(Constants.MONTH_INCOME, 0);
        map.put(Constants.MARITAL_STATUS, 0);

        map.put(Constants.LIVETYPE, Constants.LIVETYPE_PHONE);
        /*Log.e("getpush","?"+Constants.BTITLE+"="+headline);
        Log.e("getpush","&"+Constants.SAYTITLE+"="+topic);
        for (String key :map.keySet()) {
            Log.e("getpush","&"+key+"="+map.get(key)+"");
        }*/

        Log.e("getpushurl", "?btitle=" + headline + "&saytitle=" + topic + "&userid=" + 2 + "&if_open=" + 1 + "&ticket_price=" + null +
                "&begin_age=" + null + "&end_age=" + null + "&month_income=" + null +
                "&marital_status=" + null + "&livetype=" + 1);
        LiveAPI liveAPI = Http.getInstance().create(LiveAPI.class);
        liveAPI.getPushUrl("encoding =utf-8", headline, topic, map).enqueue(new Callback<GetPushUrlBean>() {
            @Override
            public void onResponse(Call<GetPushUrlBean> call, Response<GetPushUrlBean> response) {
                if (response.body().getReturnvalue().equals("true")) {
                    GetPushUrlBean pushUrlBean = response.body();
                    String push_url = pushUrlBean.getPush_url();
                    //推流
                    StartPusherActivity.intent(mActivity, push_url);
                    finish();

                    Log.e("pushurl", pushUrlBean.getPush_url());
                } else {
                    Log.e("pusherror", response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<GetPushUrlBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }

    @Override
    public void searchrange(String range) {
        tv_addtopic.setText(range);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
