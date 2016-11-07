package com.queqiaolove.adapter.find;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.javabean.find.WhoLikeMeListBean;
import com.queqiaolove.util.CommonUtils;

import java.util.List;

/**
 * Created by LENOVO on 2016/10/17.
 */
public class WhoLikeMeGvAdapter extends BaseAdapter{
    private final Activity mActivity;
    List<WhoLikeMeListBean.ListBean> whoLikeMeList;

    public WhoLikeMeGvAdapter(Activity activity, List<WhoLikeMeListBean.ListBean> list) {
        mActivity = activity;
        whoLikeMeList = list;
    }

    @Override
    public int getCount() {
        return whoLikeMeList.size();
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

        Viewholder holder = new Viewholder();
        if (convertView == null) {
            convertView = View.inflate(mActivity, R.layout.gvitem_wholookme, null);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }
        holder.iv_usericon = (ImageView) convertView.findViewById(R.id.iv_usericon_wholookme);
        holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time_wholookme);
        WhoLikeMeListBean.ListBean listBean = whoLikeMeList.get(position);
        String pic = listBean.getPic();
        String indbdate = listBean.getIndbdate();

        CommonUtils.loadImage(R.mipmap.ic_otherhead_welfare,holder.iv_usericon,pic);
        holder.tv_time.setText(indbdate);

        return convertView;
    }
    public class Viewholder {
        ImageView iv_usericon;//头像
        TextView tv_time;//时间
    }
}
