package com.queqiaolove.fragment.main;

import android.support.v4.app.Fragment;

import com.hyphenate.chatuidemo.ui.ConversationListFragment;

/**
 * 创建管理Fragment的工厂类
 *
 * @author Abner
 */
public class MainFragmentFactory {

    public static Fragment createFragment(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new QueQiaoFragment();
                break;
            case 1:
                fragment = new FindFragment();
                break;
            case 2:
                //fragment = new MessageFragment();
                 if (fragment ==null){
                fragment = new ConversationListFragment();}
                break;
            case 3:
                fragment = new MineFragment();
                break;
        }

        return fragment;
    }

}
