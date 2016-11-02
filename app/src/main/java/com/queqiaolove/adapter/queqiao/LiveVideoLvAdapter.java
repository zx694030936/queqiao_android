package com.queqiaolove.adapter.queqiao;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.queqiaolove.R;

/**
 * Created by WD on 2016/10/2.
 */
public class LiveVideoLvAdapter extends BaseAdapter {
    private final Activity mActivity;

    public LiveVideoLvAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return 5;
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
        View v  = View.inflate(mActivity, R.layout.lvitem_video_queqiao,null);
        return v;
    }
}
