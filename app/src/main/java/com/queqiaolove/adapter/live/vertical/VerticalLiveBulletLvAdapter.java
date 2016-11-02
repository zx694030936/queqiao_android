package com.queqiaolove.adapter.live.vertical;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;

/**
 * Created by LENOVO on 2016/10/25.
 */
public class VerticalLiveBulletLvAdapter extends BaseAdapter {
    private final Activity mActivity;

    public VerticalLiveBulletLvAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return 12;
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
        /*Viewholder holder = new Viewholder();
        if(convertView == null){
            convertView = View.inflate(mActivity, R.layout.gvitem_vertical_live,null);
            convertView.setTag(holder);
        }else{
            holder = (Viewholder) convertView.getTag();
        }*/

        View v =  View.inflate(mActivity, R.layout.lvitem_bullet_verticallive,null);
        return v;
    }
    public class Viewholder{
        ImageView iv_anchor_phonelive;//封面图片
        TextView tv_numoflook_phonelive;//观看人数
        TextView tv_anchor_phonelive;//主播名
        TextView tv_location_phonelive;//位置
    }
}
