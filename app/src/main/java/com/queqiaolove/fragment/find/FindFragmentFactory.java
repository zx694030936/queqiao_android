package com.queqiaolove.fragment.find;

import android.support.v4.app.Fragment;

/**
 * 创建管理Fragment的工厂类
 *
 * @author Abner
 */
public class FindFragmentFactory {



    public static Fragment createFragment(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new AcitivityFragment();
                break;
            case 1:
                fragment = new WelfareFragment();
                break;
            case 2:
                fragment = new NearbyPeopleFragment();
                break;
        }

        return fragment;
    }

}
