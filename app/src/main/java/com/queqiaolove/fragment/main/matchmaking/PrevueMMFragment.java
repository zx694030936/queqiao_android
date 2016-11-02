package com.queqiaolove.fragment.main.matchmaking;

import android.view.View;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.pullableview.PullableListView;
import com.queqiaolove.R;
import com.queqiaolove.adapter.main.matchmaking.PrevueMMGvAdapter;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;

/**
 * Created by LENOVO on 2016/10/17.
 */
public class PrevueMMFragment extends BaseFragment implements PullToRefreshLayout.OnRefreshListener {
    protected PullToRefreshLayout refresh_view;//可刷新的布局
    private PullableListView lv_pulltofresh;
    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.fragment_prevue_matchmaking,null);
    }

    @Override
    protected void initView() {

        lv_pulltofresh = (PullableListView) mContentView.findViewById(R.id.lv_pulltofresh);
        /*可刷新布局*/
        refresh_view = (PullToRefreshLayout) mContentView.findViewById(R.id.refresh_view);
        refresh_view.setOnRefreshListener(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected ContentPage.RequestState onLoad() {
        lv_pulltofresh.setAdapter(new PrevueMMGvAdapter(mActivity));
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
}
