package com.queqiaolove.adapter.find;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.fragment.find.FindFragmentFactory;


/**
 * Created by WD on 2016/8/5.
 */
public class FindFragmentPagerAdapter extends FragmentPagerAdapter {


    private BaseFragment mFragment;

    public FindFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    //实现getItem返回Pager对应的Fragment
    @Override
    public Fragment getItem(int position) {
        Log.e("fdsa",position+"");
        mFragment = (BaseFragment) FindFragmentFactory.createFragment(position);
        return mFragment;

    }

    @Override
    public int getCount() {
        return 3;
    }


}
