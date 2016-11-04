package com.queqiaolove.fragment.main.matchmaking;

import android.view.View;
import android.widget.AdapterView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.pullableview.PullableGridView;
import com.queqiaolove.R;
import com.queqiaolove.activity.live.horizontal.HorizontalLiveActivity;
import com.queqiaolove.adapter.main.matchmaking.LiveMMGvAdapter;
import com.queqiaolove.base.BaseFragment;
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
public class LiveMMFragment extends BaseFragment implements PullToRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    protected PullToRefreshLayout refresh_view;//可刷新的布局
    private PullableGridView gv_pulltofresh;
    private List<LiveUrlListBean.ListBean> list;

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.fragment_live_matchmaking,null);
    }

    @Override
    protected void initView() {

        gv_pulltofresh = (PullableGridView) mContentView.findViewById(R.id.gv_pulltofresh);
        /*可刷新布局*/
        refresh_view = (PullToRefreshLayout) mContentView.findViewById(R.id.refresh_view);
        refresh_view.setOnRefreshListener(this);
    }

    @Override
    protected void initEvent() {
        gv_pulltofresh.setOnItemClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        loadLiveList();
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /*相亲直播列表*/
    private void loadLiveList() {
        LiveAPI liveAPI = Http.getInstance().create(LiveAPI.class);
        liveAPI.getLiveUrlList("utf-9",pageno,pagesize, Constants.LIVETYPE_MM).enqueue(new Callback<LiveUrlListBean>() {
            @Override
            public void onResponse(Call<LiveUrlListBean> call, Response<LiveUrlListBean> response) {
                LiveUrlListBean body = response.body();
                if (body.getReturnvalue().equals("true")){
                    list = body.getList();
                    gv_pulltofresh.setAdapter(new LiveMMGvAdapter(mActivity,list));
                }else {
                    toast(body.getMsg());
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HorizontalLiveActivity.intent(mActivity,null);
    }
}
