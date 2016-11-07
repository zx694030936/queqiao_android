package com.queqiaolove.adapter.queqiao.recommend;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.queqiaolove.R;

/**
 * Created by LENOVO on 2016/10/17.
 */
public class RecommendPcLiveGvAdapter extends BaseAdapter{
    private final Activity mActivity;

    public RecommendPcLiveGvAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return 4;
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
        View v = View.inflate(mActivity,R.layout.gvitem_horizontal_live,null);
        return v;
    }
}
