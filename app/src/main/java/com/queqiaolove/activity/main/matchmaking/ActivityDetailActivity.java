package com.queqiaolove.activity.main.matchmaking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.QueQiaoLoveApp;
import com.queqiaolove.R;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.FindAPI;
import com.queqiaolove.javabean.find.JoinActivityBean;
import com.queqiaolove.javabean.find.MakemakingActivityDetailBean;
import com.queqiaolove.javabean.find.MakemakingActivityListBean;
import com.queqiaolove.util.CommonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/17.
 */
public class ActivityDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_back;
    private static final String TYPE = "type";
    private String title;
    private TextView tv_next;
    MakemakingActivityListBean.ListBean data;
    private String id;
    private String apic;
    private String atitle;
    private String city;
    private String address;
    private String daydiff;
    private String participant_num;
    private String watch_num;
    private String like_num;

    private ImageView iv_cover_activity;
    private TextView iv_name_activity;
    private TextView iv_location_activity;
    private TextView iv_numofjoin_activity;
    private TextView iv_dayofend_activity;
    private WebView wv_content_activity;
    private MakemakingActivityDetailBean activityDetailBean;
    private String ncontent;

    @Override
    protected void activityOnCreate(Bundle extras) {
        id = extras.getString(TYPE);
    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_mine,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_activitydetail_matchmaking,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("相亲活动名");
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);
        tv_next = (TextView) mContentView.findViewById(R.id.tv_next);

        iv_cover_activity = (ImageView) mContentView.findViewById(R.id.iv_cover_activity);
        iv_name_activity = (TextView) mContentView.findViewById(R.id.iv_name_activity);
        iv_location_activity = (TextView) mContentView.findViewById(R.id.iv_location_activity);
        iv_numofjoin_activity = (TextView) mContentView.findViewById(R.id.iv_numofjoin_activity);
        iv_dayofend_activity = (TextView) mContentView.findViewById(R.id.iv_dayofend_activity);
        wv_content_activity = (WebView) mContentView.findViewById(R.id.wv_content_activity);
    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        tv_next.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        userid = QueQiaoLoveApp.getUserId();
        if (id !=null) {
            Log.e("activityid",id+"");
            loadActivityDetail();
        }
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /*活动详情*/
    private void loadActivityDetail() {
        FindAPI findAPI = Http.getInstance().create(FindAPI.class);
        findAPI.makemakingActivityDetail(userid, Integer.parseInt(id)).enqueue(new Callback<MakemakingActivityDetailBean>() {
            @Override
            public void onResponse(Call<MakemakingActivityDetailBean> call, Response<MakemakingActivityDetailBean> response) {
                activityDetailBean = response.body();
                if (activityDetailBean.getReturnvalue().equals("true")){
                    showDetail();
                }else {
                    toast(activityDetailBean.getMsg());
                }
            }

            @Override
            public void onFailure(Call<MakemakingActivityDetailBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }

    private void showDetail() {
        apic = activityDetailBean.getApic();
        atitle = activityDetailBean.getAtitle();
        city = activityDetailBean.getCity();
        address = activityDetailBean.getAddress();
        daydiff = activityDetailBean.getDaydiff();
        participant_num = activityDetailBean.getParticipant_num();
        watch_num = activityDetailBean.getWatch_num();
        like_num = activityDetailBean.getLike_num();
        ncontent = activityDetailBean.getNcontent();

        CommonUtils.loadImage(R.mipmap.ic_default_welfare,iv_cover_activity,apic);
        iv_name_activity.setText(atitle);
        iv_location_activity.setText(city);
        iv_numofjoin_activity.setText(participant_num);
        iv_dayofend_activity.setText(daydiff);
        /*详情内容*/
        WebSettings settings = wv_content_activity.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDefaultTextEncodingName("utf-8"); //设置文本编码
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);//设置缓存模式

        wv_content_activity.loadDataWithBaseURL(null,ncontent, "text/html", "utf-8",null);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wv_content_activity.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_next://报名
                userid = QueQiaoLoveApp.getUserId();
                joinActivity();
                //ConstructionActivity.intent(mActivity,"");
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    /*报名活动*/
    private void joinActivity() {
        FindAPI findAPI = Http.getInstance().create(FindAPI.class);
        findAPI.joinActivity(userid, Integer.parseInt(id)).enqueue(new Callback<JoinActivityBean>() {
            @Override
            public void onResponse(Call<JoinActivityBean> call, Response<JoinActivityBean> response) {
                JoinActivityBean body = response.body();
                if (body.getReturnvalue().equals("true")){
                    toast("报名成功");
                    finish();
                }else {
                    toast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<JoinActivityBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }

    /**
     * 从外部跳转到本类的反复
     *
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, ActivityDetailActivity.class);
        intent.putExtra(TYPE, data);
        activity.startActivity(intent);
    }
}
