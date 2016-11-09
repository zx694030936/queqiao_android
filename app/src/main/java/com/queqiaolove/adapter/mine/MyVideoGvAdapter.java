package com.queqiaolove.adapter.mine;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.queqiaolove.R;
import com.queqiaolove.util.CommonUtil;

/**
 * Created by WD on 2016/10/13.
 */
public class MyVideoGvAdapter extends BaseAdapter {
    private final Activity mActivity;
    private int _2dp = CommonUtil.dip2px(2);;

    public MyVideoGvAdapter(Activity activity) {
        mActivity=activity;
    }

    @Override
    public int getCount() {
        return 50;
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
        View view =View.inflate(mActivity, R.layout.gvitem_myvideo_mine,null);
        final ImageView iv =  (ImageView) view.findViewById(R.id.iv_cover_mine);
        final ImageView tv_play_mine =  (ImageView) view.findViewById(R.id.tv_play_mine);
        if (position==0){
            iv.setImageResource(R.mipmap.btn_mine_uploadvideo);
            tv_play_mine.setVisibility(View.GONE);
        }
        ViewTreeObserver viewTreeObserver = iv.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = iv.getMeasuredWidth();
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv.getLayoutParams();
                params.width = width;
                params.height = width;
                params.setMargins(_2dp,_2dp,_2dp,_2dp);
                iv.setLayoutParams(params);
            }
        });

        return view;
    }
}
