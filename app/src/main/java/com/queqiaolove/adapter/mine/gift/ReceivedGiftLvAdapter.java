package com.queqiaolove.adapter.mine.gift;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.queqiaolove.R;

/**
 * 发现-福利
 * Created by WD on 2016/10/2.
 */
public class ReceivedGiftLvAdapter extends BaseAdapter {
    private final Activity mActivity;

    public ReceivedGiftLvAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return 10;
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
        View v  = View.inflate(mActivity, R.layout.lvitem_received_mygift,null);
        return v;
    }
}
