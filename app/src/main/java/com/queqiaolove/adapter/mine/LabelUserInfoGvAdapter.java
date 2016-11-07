package com.queqiaolove.adapter.mine;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.queqiaolove.R;

/**
 * Created by WD on 2016/10/15.
 */
public class LabelUserInfoGvAdapter extends BaseAdapter {
    private final Activity mActivity;

    public LabelUserInfoGvAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return 20;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(mActivity, R.layout.gvitem_userinfo_label,null);
        CheckBox cb = (CheckBox) v.findViewById(R.id.cb_user_label);
        cb.setText("标签"+position);
        return v;
    }
}
