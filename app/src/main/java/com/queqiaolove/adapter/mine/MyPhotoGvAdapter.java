package com.queqiaolove.adapter.mine;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.queqiaolove.R;
import com.queqiaolove.javabean.mine.MyPhotoListBean;
import com.queqiaolove.util.CommonUtils;

import java.util.List;

/**
 * Created by WD on 2016/10/13.
 */
public class MyPhotoGvAdapter extends BaseAdapter {
    private final Activity mActivity;
    private final List<MyPhotoListBean.ListBean> myphotolist;
    private int _2dp = CommonUtils.dip2px(2);;

    public MyPhotoGvAdapter(Activity activity, List<MyPhotoListBean.ListBean> list) {
        mActivity=activity;
        myphotolist = list;
    }

    @Override
    public int getCount() {
        return myphotolist.size();
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
        }else {
            MyPhotoListBean.ListBean listBean = myphotolist.get(position);
            String upic = listBean.getUpic();
            CommonUtils.loadImage(R.mipmap.mine_myphoto_default, iv, upic);
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
