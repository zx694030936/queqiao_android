package com.queqiaolove.adapter.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.queqiaolove.fragment.main.MainFragmentFactory;


/**
 * Created by WD on 2016/8/5.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {


    private Fragment mFragment;


    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }



    //实现getItem返回Pager对应的Fragment
    @Override
    public Fragment getItem(int position) {
        mFragment = MainFragmentFactory.createFragment(position);
        return mFragment;

    }

    @Override
    public int getCount() {
        return 4;
    }


}
