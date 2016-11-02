package com.queqiaolove.fragment.mine.gift;

import android.view.View;
import android.widget.ListView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.queqiaolove.R;
import com.queqiaolove.adapter.mine.gift.ReceivedGiftLvAdapter;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/13.
 */
public class ReceivedGiftFragment extends BaseFragment implements PullToRefreshLayout.OnRefreshListener {

    private ListView lv_received;
    private PullToRefreshLayout refresh_view;

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.fragment_received_mygift,null);
    }

    @Override
    protected void initView() {
        lv_received = (ListView) mContentView.findViewById(R.id.lv_pulltofresh);
        /*可刷新布局*/
        refresh_view = (PullToRefreshLayout) mContentView.findViewById(R.id.refresh_view);
        refresh_view.setOnRefreshListener(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected ContentPage.RequestState onLoad() {
        lv_received.setAdapter(new ReceivedGiftLvAdapter(mActivity));
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
