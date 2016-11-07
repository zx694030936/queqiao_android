package com.queqiaolove.activity.find.welfare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.pullableview.PullableGridView;
import com.queqiaolove.QueQiaoLoveApp;
import com.queqiaolove.R;
import com.queqiaolove.activity.user.UserInfoActivity;
import com.queqiaolove.adapter.find.WhoLikeMeGvAdapter;
import com.queqiaolove.adapter.find.WhoLookMeGvAdapter;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.FindAPI;
import com.queqiaolove.javabean.find.WhoLikeMeListBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/14.
 */
public class WhoLikeMeActivity extends BaseActivity implements View.OnClickListener, PullToRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    private static String LOVE = "love";
    private ImageView iv_back;
    private TextView tv_next;
    private PullableGridView gv_pulltofresh;
    protected PullToRefreshLayout refresh_view;//可刷新的布局
    private String title;
    private List<WhoLikeMeListBean.ListBean> whoLikeMeList = new ArrayList<>();

    @Override
    protected void activityOnCreate(Bundle extras) {
        title = extras.getString(LOVE);
    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_mine,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_welfare_wholookedme,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText(title);
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);

        gv_pulltofresh = (PullableGridView) mContentView.findViewById(R.id.gv_pulltofresh);
        /*可刷新布局*/
        refresh_view = (PullToRefreshLayout) mContentView.findViewById(R.id.refresh_view);

    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        refresh_view.setOnRefreshListener(this);
        gv_pulltofresh.setOnItemClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        userid = QueQiaoLoveApp.getUserId();
        whoLikeMeList.clear();
        loadWhoLookMeList();
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /*谁看过我的列表*/
    private void loadWhoLookMeList() {
        FindAPI findAPI = Http.getInstance().create(FindAPI.class);
        findAPI.wholikemeList(userid,pageno,pagesize).enqueue(new Callback<WhoLikeMeListBean>() {
            @Override
            public void onResponse(Call<WhoLikeMeListBean> call, Response<WhoLikeMeListBean> response) {
                WhoLikeMeListBean body = response.body();
                if (body.getReturnvalue().equals("true")){
                    whoLikeMeList.addAll(body.getList());
                    gv_pulltofresh.setAdapter(new WhoLikeMeGvAdapter(mActivity, whoLikeMeList));
                }else {
                    toast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<WhoLikeMeListBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }

    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, WhoLikeMeActivity.class);
        intent.putExtra(LOVE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        pageno = 1;
        whoLikeMeList.clear();
        loadWhoLookMeList();
        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        pageno++;
        loadWhoLookMeList();
        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        UserInfoActivity.intent(mActivity,"");
    }
}
