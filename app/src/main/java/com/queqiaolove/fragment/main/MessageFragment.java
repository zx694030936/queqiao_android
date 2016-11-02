package com.queqiaolove.fragment.main;

import android.view.View;
import android.widget.ListView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.queqiaolove.R;
import com.queqiaolove.adapter.message.MessageLvAdapter;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/2.
 */
public class MessageFragment extends BaseFragment implements PullToRefreshLayout.OnRefreshListener {

    private ListView lv_message;
    private View mHearderView;
    private PullToRefreshLayout refresh_view;

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_main,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity,R.layout.fragment_message_main,null);
    }

    @Override
    protected void initView() {
        lv_message = (ListView) mContentView.findViewById(R.id.lv_pulltofresh);
        lv_message.addFooterView(View.inflate(mActivity,R.layout.split_10rectangle,null));
        /*添加头布局*/
        mHearderView = View.inflate(mActivity,R.layout.headerview_message,null);
        lv_message.addHeaderView(mHearderView);
        /*可刷新布局*/
        refresh_view = (PullToRefreshLayout) mContentView.findViewById(R.id.refresh_view);
        refresh_view.setOnRefreshListener(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected ContentPage.RequestState onLoad() {
        lv_message.setAdapter(new MessageLvAdapter(mActivity));
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
