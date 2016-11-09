package com.queqiaolove.adapter.find;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.javabean.find.MakemakingActivityListBean;
import com.queqiaolove.util.CommonUtil;

import java.util.List;

/**
 * 发现-福利
 * Created by WD on 2016/10/2.
 */
public class ActivityLvAdapter extends BaseAdapter {
    private final Activity mActivity;
    List<MakemakingActivityListBean.ListBean> activityList;
    public ActivityLvAdapter(Activity activity, List<MakemakingActivityListBean.ListBean> list) {
        mActivity = activity;
        activityList = list;
    }

    @Override
    public int getCount() {
        return activityList.size();
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
        Viewholder holder = new Viewholder();
        if (convertView == null) {
            convertView = View.inflate(mActivity, R.layout.lvitem_activity_find, null);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }
        holder.iv_cover_video = (ImageView) convertView.findViewById(R.id.iv_cover_video);
        holder.tv_name_activity = (TextView) convertView.findViewById(R.id.tv_name_activity);
        holder.tv_city_activity = (TextView) convertView.findViewById(R.id.tv_city_activity);
        holder.tv_endofday_activity = (TextView) convertView.findViewById(R.id.tv_endofday_activity);
        holder.tv_numofjoin_activity = (TextView) convertView.findViewById(R.id.tv_numofjoin_activity);
        holder.tv_numoflook_activity = (TextView) convertView.findViewById(R.id.tv_numoflook_activity);
        holder.tv_numofpraise_activity = (TextView) convertView.findViewById(R.id.tv_numofpraise_activity);
        /*获取数据*/
        MakemakingActivityListBean.ListBean data = activityList.get(position);

        String apic = data.getApic();
        String atitle = data.getAtitle();
        String city = data.getCity();
        String daydiff = data.getDaydiff();
        String participant_num = data.getParticipant_num();
        String watch_num = data.getWatch_num();
        String like_num = data.getLike_num();
        String if_like = data.getIf_like();//是否点赞
        /*设置内容*/
        CommonUtil.loadImage(R.mipmap.ic_default_welfare, holder.iv_cover_video, apic);
        holder.tv_name_activity.setText(atitle);
        holder.tv_city_activity.setText(city);
        holder.tv_endofday_activity.setText(daydiff);
        holder.tv_numofjoin_activity.setText(participant_num  );
        holder.tv_numoflook_activity.setText( watch_num );
        holder.tv_numofpraise_activity.setText( like_num );

        return convertView;
    }

    public class Viewholder {
        ImageView iv_cover_video;//封面图片
        TextView tv_name_activity;//观看人数
        TextView tv_city_activity;//主播名
        TextView tv_endofday_activity;//标题
        TextView tv_numofjoin_activity;//标题
        TextView tv_numoflook_activity;//标题
        TextView tv_numofpraise_activity;//标题
    }
}
