package com.queqiaolove.fragment.live.horizontal;

import android.view.View;
import android.widget.ListView;

import com.queqiaolove.R;
import com.queqiaolove.adapter.live.horizontal.HorizontalLiveBulletLvAdapter;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.imadapter.DanMuListViewAdapter;

/**
 * Created by WD on 2016/10/7.
 */
public class ChartLiveFragment extends BaseFragment{
    private ListView lv_bulletscreen;
    private DanMuListViewAdapter adapter;


    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.vpitem_chart_horizantollive,null);
    }

    @Override
    protected void initView() {
        lv_bulletscreen = (ListView) mContentView.findViewById(R.id.lv_bulletscreen);

        adapter = new DanMuListViewAdapter(getActivity());
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected ContentPage.RequestState onLoad() {
        lv_bulletscreen.setAdapter(new HorizontalLiveBulletLvAdapter(mActivity));
        return ContentPage.RequestState.STATE_SUCCESS;
    }
}
