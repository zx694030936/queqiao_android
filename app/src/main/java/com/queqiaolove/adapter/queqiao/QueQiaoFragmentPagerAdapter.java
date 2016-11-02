package com.queqiaolove.adapter.queqiao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.fragment.queqiao.QueQiaoFragmentFactory;


/**
 * Created by WD on 2016/8/5.
 */
public class QueQiaoFragmentPagerAdapter extends FragmentPagerAdapter {


    private BaseFragment mFragment;

    public QueQiaoFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    //实现getItem返回Pager对应的Fragment
    @Override
    public Fragment getItem(int position) {
        Log.e("fdsa",position+"");
        mFragment = (BaseFragment) QueQiaoFragmentFactory.createFragment(position);
        return mFragment;

    }

    @Override
    public int getCount() {
        return 4;
    }


}
