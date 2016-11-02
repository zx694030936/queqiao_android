package com.queqiaolove.adapter.find;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.queqiaolove.R;

/**
 * Created by LENOVO on 2016/10/27.
 */
public class NearbyPeopleLvAdapter extends BaseAdapter {
    private final Activity mActivity;
    private final int mNum;

    public NearbyPeopleLvAdapter(Activity activity, int num) {
        mActivity = activity;
        mNum = num;
    }

    @Override
    public int getCount() {
        return mNum;
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
        view = View.inflate(mActivity, R.layout.lvitem_nearbypeople_find,null);
        return view;
    }
}
