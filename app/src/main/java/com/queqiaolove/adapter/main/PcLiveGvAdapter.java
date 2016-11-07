package com.queqiaolove.adapter.main;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.javabean.live.LiveUrlListBean;
import com.queqiaolove.util.CommonUtils;

import java.util.List;

/**
 * Created by LENOVO on 2016/10/17.
 */
public class PcLiveGvAdapter extends BaseAdapter {
    private final Activity mActivity;
    private final List<LiveUrlListBean.ListBean> pclist;

    public PcLiveGvAdapter(Activity activity, List<LiveUrlListBean.ListBean>list) {
        mActivity = activity;
        pclist = list;
    }

    @Override
    public int getCount() {
        return pclist.size();
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
            convertView = View.inflate(mActivity, R.layout.gvitem_horizontal_live, null);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }
        holder.iv_cover_video = (ImageView) convertView.findViewById(R.id.iv_anchor_live);
        holder.tv_numoflook_pclive = (TextView) convertView.findViewById(R.id.tv_numoflook_pclive);
        holder.tv_city_activity = (TextView) convertView.findViewById(R.id.tv_anchor_pclive);
        holder.tv_name_activity = (TextView) convertView.findViewById(R.id.tv_headline_pclive);
        /*获取数据*/
        LiveUrlListBean.ListBean data = pclist.get(position);
        String zhibo_fm_pic = data.getZhibo_fm_pic();
        String btitle = data.getBtitle();
        String saytitle = data.getSaytitle();
        String watch_num = data.getWatch_num();
        String username = data.getUsername();
        String city = data.getCity();
        /*设置内容*/
        CommonUtils.loadImage(R.mipmap.ic_peopleoflook_live, holder.iv_cover_video, zhibo_fm_pic);
        holder.tv_city_activity.setText(username);
        holder.tv_numoflook_pclive.setText(watch_num);
        holder.tv_name_activity.setText(saytitle + btitle);

        return convertView;
    }

    public class Viewholder {
        ImageView iv_cover_video;//封面图片
        TextView tv_numoflook_pclive;//观看人数
        TextView tv_anchor_pclive;//主播名
        TextView tv_city_activity;//主播名
        TextView tv_name_activity;//标题
    }
}
