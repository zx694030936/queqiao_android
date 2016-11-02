package com.queqiaolove.adapter.live.horizontal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.fragment.live.horizontal.AnchorInfoLiveFragment;
import com.queqiaolove.fragment.live.horizontal.ChartLiveFragment;

/**
 * Created by WD on 2016/10/7.
 */
public class ChartOrInfoVpAdapter extends FragmentStatePagerAdapter {

    private BaseFragment mFragment;

    public ChartOrInfoVpAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                mFragment = new ChartLiveFragment();
                break;
            case 1:
                mFragment = new AnchorInfoLiveFragment();
                break;
        }
        return mFragment;
    }

}
