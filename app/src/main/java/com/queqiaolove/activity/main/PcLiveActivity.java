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
import com.queqiaolove.activity.live.horizontal.HorizontalLiveActivity;
import com.queqiaolove.adapter.main.PcLiveGvAdapter;
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
public class PcLiveActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private static String CODE = "hot";
    protected PullToRefreshLayout refresh_view;//可刷新的布局
    private PullableGridView gv_pulltofresh;
    private ImageView iv_back;
    private List<LiveUrlListBean.ListBean> pclivelist;

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_matchmaking,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_hotlive,null);
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
        loadPCLivelist();
        return ContentPage.RequestState.STATE_SUCCESS;
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
        intent.setClass(activity, PcLiveActivity.class);
        intent.putExtra(CODE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HorizontalLiveActivity.intent(mActivity,null);
    }

    /*加载pc直播列表*/
    private void loadPCLivelist() {
        LiveAPI liveAPI = Http.getInstance().create(LiveAPI.class);
        liveAPI.getLiveUrlList("utf-8",pageno,pagesize, Constants.LIVETYPE_PC).enqueue(new Callback<LiveUrlListBean>() {
            @Override
            public void onResponse(Call<LiveUrlListBean> call, Response<LiveUrlListBean> response) {
                if (response.body().getReturnvalue().equals("true")){
                    pclivelist = response.body().getList();
                    Log.e("pclivelist",pclivelist.size()+"");
                    gv_pulltofresh.setAdapter(new PcLiveGvAdapter(mActivity,pclivelist));//pc列表

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
}
