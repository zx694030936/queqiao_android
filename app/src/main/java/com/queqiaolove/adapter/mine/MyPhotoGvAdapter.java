package com.queqiaolove.adapter.mine;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.queqiaolove.R;
import com.queqiaolove.util.CommonUtils;

/**
 * Created by WD on 2016/10/13.
 */
public class MyPhotoGvAdapter extends BaseAdapter {
    private final Activity mActivity;
    private int _2dp = CommonUtils.dip2px(2);;

    public MyPhotoGvAdapter(Activity activity) {
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
        View view =View.inflate(mActivity, R.layout.gvitem_myphoto_mine,null);
        final ImageView iv =  (ImageView) view.findViewById(R.id.iv_cover_mine);

        if (position==0){
            iv.setImageResource(R.mipmap.btn_mine_uploadphoto);
        }
        /*int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.EXACTLY);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        iv.measure(w, h);
        int width =iv.getMeasuredWidth();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv.getLayoutParams();
        params.width = width;
        params.height = width;
        params.setMargins(_2dp,_2dp,_2dp,_2dp);
        iv.setLayoutParams(params);*/
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
