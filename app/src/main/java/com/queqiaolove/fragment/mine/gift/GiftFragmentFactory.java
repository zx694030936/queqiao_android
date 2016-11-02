package com.queqiaolove.fragment.mine.gift;

import android.support.v4.app.Fragment;

/**
 * 创建管理Fragment的工厂类
 *
 * @author Abner
 */
public class GiftFragmentFactory {



    public static Fragment createFragment(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ReceivedGiftFragment();
                break;
            case 1:
                fragment = new ReceivedGiftFragment();
                break;
        }

        return fragment;
    }

}
