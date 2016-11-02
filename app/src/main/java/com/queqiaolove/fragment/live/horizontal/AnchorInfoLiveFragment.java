package com.queqiaolove.fragment.live.horizontal;

import android.view.View;

import com.queqiaolove.R;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/7.
 */
public class AnchorInfoLiveFragment extends BaseFragment{

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.vpitem_horizantollive_userinfo,null);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected ContentPage.RequestState onLoad() {
        return ContentPage.RequestState.STATE_SUCCESS;
    }
}
