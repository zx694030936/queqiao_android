package com.queqiaolove.adapter.queqiao;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.queqiaolove.R;

/**
 * Created by WD on 2016/10/2.
 */
public class HotGvAdapter extends BaseAdapter {
    private final Activity mActivity;

    public HotGvAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mActivity, R.layout.gvitem_horizontal_live,null);
        return v;
    }
}
