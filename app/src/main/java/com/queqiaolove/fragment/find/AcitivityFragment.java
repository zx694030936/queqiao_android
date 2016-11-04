package com.queqiaolove.fragment.find;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.queqiaolove.QueQiaoLoveApp;
import com.queqiaolove.R;
import com.queqiaolove.activity.main.matchmaking.ActivityDetailActivity;
import com.queqiaolove.adapter.find.ActivityLvAdapter;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.FindAPI;
import com.queqiaolove.javabean.find.MakemakingActivityListBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/2.
 */
public class AcitivityFragment extends BaseFragment implements PullToRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private ListView lv_activity;
    private PullToRefreshLayout refresh_view;
    private List<MakemakingActivityListBean.ListBean> activityList = new ArrayList<>();

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.fragment_activity_find,null);
    }

    @Override
    protected void initView() {
        lv_activity = (ListView) mContentView.findViewById(R.id.lv_pulltofresh);
        /*可刷新布局*/
        refresh_view = (PullToRefreshLayout) mContentView.findViewById(R.id.refresh_view);
        refresh_view.setOnRefreshListener(this);
    }

    @Override
    protected void initEvent() {
        lv_activity.setOnItemClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        userid = QueQiaoLoveApp.getUserId();
        loadActivityList();
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /*加载活动列表*/
    private void loadActivityList() {
        FindAPI findAPI = Http.getInstance().create(FindAPI.class);
        findAPI.makemakingActivityList(userid,pageno,pagesize).enqueue(new Callback<MakemakingActivityListBean>() {
            @Override
            public void onResponse(Call<MakemakingActivityListBean> call, Response<MakemakingActivityListBean> response) {
                MakemakingActivityListBean body = response.body();
                if (body.getReturnvalue().equals("true")){
                    activityList.addAll(body.getList());
                    lv_activity.setAdapter(new ActivityLvAdapter(mActivity,activityList));
                }else {
                    toast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<MakemakingActivityListBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        pageno = 1;
        activityList= new ArrayList<>();
        loadActivityList();
        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        pageno ++;
        loadActivityList();
        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        String id = activityList.get(position).getId();
        ActivityDetailActivity.intent(mActivity,id);
    }
}
