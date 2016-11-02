package com.queqiaolove.adapter.mine;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.queqiaolove.R;

/**
 * Created by WD on 2016/10/14.
 */
public class MyLiveVideoGvAdapter extends BaseAdapter {
    private final Activity mActiviy;

    public MyLiveVideoGvAdapter(Activity activity) {
        mActiviy = activity;
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
        View v = View.inflate(mActiviy, R.layout.gvitem_livevideo_mine,null);
        return v;
    }
}
