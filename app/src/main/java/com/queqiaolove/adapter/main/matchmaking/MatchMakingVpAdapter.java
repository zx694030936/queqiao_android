package com.queqiaolove.adapter.main.matchmaking;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.fragment.main.matchmaking.LiveMMFragment;
import com.queqiaolove.fragment.main.matchmaking.PrevueMMFragment;
import com.queqiaolove.fragment.main.matchmaking.VideoMMFragment;

/**
 * Created by WD on 2016/10/7.
 */
public class MatchMakingVpAdapter extends FragmentPagerAdapter {

    private BaseFragment mFragment;

    public MatchMakingVpAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                mFragment = new LiveMMFragment();
                break;
            case 1:
                mFragment = new VideoMMFragment();
                break;
            case 2:
                mFragment = new PrevueMMFragment();
                break;
        }
        return mFragment;
    }
}
