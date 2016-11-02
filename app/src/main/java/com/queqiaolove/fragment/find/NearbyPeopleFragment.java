package com.queqiaolove.fragment.find;

import android.view.View;
import android.widget.AdapterView;

import com.queqiaolove.R;
import com.queqiaolove.activity.user.UserInfoActivity;
import com.queqiaolove.adapter.find.NearbyPeopleLvAdapter;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.widget.MyListVIew;

/**
 * Created by WD on 2016/10/2.
 */
public class NearbyPeopleFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    private MyListVIew lv_nearbypeople1;
    private MyListVIew lv_nearbypeople2;
    private MyListVIew lv_nearbypeople3;

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.fragment_nearbypeople_find,null);
    }

    @Override
    protected void initView() {
        lv_nearbypeople1 = (MyListVIew) mContentView.findViewById(R.id.lv_nearbypeople1);
        lv_nearbypeople2 = (MyListVIew) mContentView.findViewById(R.id.lv_nearbypeople2);
        lv_nearbypeople3 = (MyListVIew) mContentView.findViewById(R.id.lv_nearbypeople3);
    }

    @Override
    protected void initEvent() {
        lv_nearbypeople1.setOnItemClickListener(this);
        lv_nearbypeople2.setOnItemClickListener(this);
        lv_nearbypeople3.setOnItemClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        lv_nearbypeople1.setAdapter(new NearbyPeopleLvAdapter(mActivity,4));
        lv_nearbypeople2.setAdapter(new NearbyPeopleLvAdapter(mActivity,4));
        lv_nearbypeople3.setAdapter(new NearbyPeopleLvAdapter(mActivity,4));
        return ContentPage.RequestState.STATE_SUCCESS;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        UserInfoActivity.intent(mActivity,"");
    }
}
