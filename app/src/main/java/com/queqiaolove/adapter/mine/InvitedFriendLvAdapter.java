package com.queqiaolove.adapter.mine;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.queqiaolove.R;

/**
 * Created by WD on 2016/10/13.
 */
public class InvitedFriendLvAdapter extends BaseAdapter {
    private final Activity mActivity;

    public InvitedFriendLvAdapter(Activity activity) {
        mActivity=activity;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view =View.inflate(mActivity, R.layout.lvitem_friend_invitecode,null);
        return view;
    }
}
