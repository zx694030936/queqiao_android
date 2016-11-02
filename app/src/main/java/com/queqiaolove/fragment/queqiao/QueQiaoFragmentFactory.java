package com.queqiaolove.fragment.queqiao;

import android.support.v4.app.Fragment;

/**
 * 创建管理Fragment的工厂类
 *
 * @author Abner
 */
public class QueQiaoFragmentFactory {



    public static Fragment createFragment(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new RecommendFragment();
                break;
            case 1:
                fragment = new LiveFragment();
                break;
            case 2:
                fragment = new LiveVideoFragment();
                break;
            case 3:
                fragment = new PhotoFragment();
                break;
        }

        return fragment;
    }

}
