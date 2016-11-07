package com.queqiaolove.fragment.queqiao;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.queqiaolove.R;
import com.queqiaolove.activity.livevideo.LiveVideoActivity;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/2.
 */
public class LiveVideoFragment extends BaseFragment implements PullToRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private ListView lv_video;
    private PullToRefreshLayout refresh_view;

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.fragment_livevideo_queqiao,null);
    }

    @Override
    protected void initView() {
        lv_video = (ListView) mContentView.findViewById(R.id.lv_pulltofresh);
        /*可刷新布局*/
        refresh_view = (PullToRefreshLayout) mContentView.findViewById(R.id.refresh_view);
        refresh_view.setOnRefreshListener(this);
    }

    @Override
    protected void initEvent() {
        lv_video.setOnItemClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        loadLivevideo();
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /*加载点播列表*/
    private void loadLivevideo() {
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
        LiveVideoActivity.intent(mActivity,"");
    }
}
