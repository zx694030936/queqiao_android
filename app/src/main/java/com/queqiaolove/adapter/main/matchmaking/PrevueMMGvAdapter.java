package com.queqiaolove.adapter.main.matchmaking;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.queqiaolove.R;

/**
 * Created by LENOVO on 2016/10/17.
 */
public class PrevueMMGvAdapter extends BaseAdapter{
    private final Activity mActivity;

    public PrevueMMGvAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return 16;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mActivity,R.layout.lvitem_activity_find,null);
        return v;
    }
}
