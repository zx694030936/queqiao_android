package com.queqiaolove.adapter.live.horizontal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.fragment.live.horizontal.AnchorInfoLiveFragment;
import com.queqiaolove.fragment.live.horizontal.ChartLiveFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WD on 2016/10/7.
 */
public class ChartOrInfoVpAdapter extends FragmentStatePagerAdapter {

    private Fragment mFragment;
    private Bundle fragmentArgs;

    public ChartOrInfoVpAdapter(FragmentManager fm,Bundle bundle) {
        super(fm);
        this.fragmentArgs = bundle;
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
                mFragment.setArguments(fragmentArgs);
                break;
            case 1:
                mFragment = new AnchorInfoLiveFragment();
                break;
        }
        return mFragment;
    }

}
