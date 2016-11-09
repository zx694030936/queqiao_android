package com.queqiaolove.adapter.find;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.javabean.find.ILikeWhoListBean;
import com.queqiaolove.util.CommonUtil;

import java.util.List;

/**
 * Created by LENOVO on 2016/10/17.
 */
public class ILikeWhoGvAdapter extends BaseAdapter{
    private final Activity mActivity;
    private final List<ILikeWhoListBean.ListBean> iLikeWhoList;

    public ILikeWhoGvAdapter(Activity activity, List<ILikeWhoListBean.ListBean> list) {
        mActivity = activity;
        iLikeWhoList = list;
    }
    @Override
    public int getCount() {
        return iLikeWhoList.size();
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
        ILikeWhoListBean.ListBean listBean = iLikeWhoList.get(position);
        String pic = listBean.getPic();
        String indbdate = listBean.getIndbdate();

        CommonUtil.loadImage(R.mipmap.ic_otherhead_welfare,holder.iv_usericon,pic);
        holder.tv_time.setText(indbdate);

        return convertView;
    }
    public class Viewholder {
        ImageView iv_usericon;//头像
        TextView tv_time;//时间
    }
}
