package com.queqiaolove.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.pullableview.PullableGridView;
import com.queqiaolove.R;
import com.queqiaolove.activity.live.vertical.VerticalLiveActivity;
import com.queqiaolove.adapter.main.PhoneLiveGvAdapter;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.global.Constants;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.LiveAPI;
import com.queqiaolove.javabean.live.LiveUrlListBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LENOVO on 2016/10/17.
 */
public class PhoneLiveActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private static String CODE = "hot";
    protected PullToRefreshLayout refresh_view;//可刷新的布局
    private PullableGridView gv_pulltofresh;
    private ImageView iv_back;
    private int pageno = 1;
    private int pagesize = 10;
    private List<LiveUrlListBean.ListBean> phonelivelist;//手机直播列表

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_matchmaking,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_phonelive,null);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);

        gv_pulltofresh = (PullableGridView) mContentView.findViewById(R.id.gv_pulltofresh);
        /*可刷新布局*/
        refresh_view = (PullToRefreshLayout) mContentView.findViewById(R.id.refresh_view);
        refresh_view.setOnRefreshListener(this);
    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        gv_pulltofresh.setOnItemClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        loadPhotoLivelist();
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /*加载手机直播列表*/
    private void loadPhotoLivelist() {
        LiveAPI liveAPI = Http.getInstance().create(LiveAPI.class);
        liveAPI.getLiveUrlList("encoding =utf-8",pageno,pagesize, Constants.LIVETYPE_PHONE).enqueue(new Callback<LiveUrlListBean>() {
            @Override
            public void onResponse(Call<LiveUrlListBean> call, Response<LiveUrlListBean> response) {
                if (response.body().getReturnvalue().equals("true")){
                    phonelivelist = response.body().getList();
                    Log.e("phonelivelist",phonelivelist.size()+"");
                    gv_pulltofresh.setAdapter(new PhoneLiveGvAdapter(mActivity,phonelivelist));
                }else {
                    toast("数据异常");
                }
            }

            @Override
            public void onFailure(Call<LiveUrlListBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, PhoneLiveActivity.class);
        intent.putExtra(CODE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        LiveUrlListBean.ListBean data = phonelivelist.get(position);
        String isend = data.getIsend();//0在播，1结束
        VerticalLiveActivity.intent(mActivity,data);
    }
}
