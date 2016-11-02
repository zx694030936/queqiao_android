package com.queqiaolove.adapter.livevideo;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.queqiaolove.R;

/**
 * Created by LENOVO on 2016/10/29.
 */
public class LiveVideoLvAdapter extends BaseAdapter {
    private final Activity mActivity;

    public LiveVideoLvAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return 10;
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
        view = View.inflate(mActivity, R.layout.lvitem_comment_livevideo,null);

        return view;
    }
}
