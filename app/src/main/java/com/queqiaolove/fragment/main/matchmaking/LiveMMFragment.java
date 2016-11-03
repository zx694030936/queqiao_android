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

/**
 * Created by LENOVO on 2016/10/17.
 */
public class LiveMMFragment extends BaseFragment implements PullToRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    protected PullToRefreshLayout refresh_view;//可刷新的布局
    private PullableGridView gv_pulltofresh;
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
        gv_pulltofresh.setAdapter(new LiveMMGvAdapter(mActivity));
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HorizontalLiveActivity.intent(mActivity,null);
    }
}
