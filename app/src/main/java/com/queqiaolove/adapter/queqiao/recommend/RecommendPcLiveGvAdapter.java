package com.queqiaolove.adapter.queqiao.recommend;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.javabean.RecommendDataBean;
import com.queqiaolove.util.CommonUtil;
import com.queqiaolove.widget.MyImageView;

import java.util.List;

/**
 * Created by LENOVO on 2016/10/17.
 */
public class RecommendPcLiveGvAdapter extends BaseAdapter {
    private final Activity mActivity;
    private final List<RecommendDataBean.PczbListBean> pclist;

    public RecommendPcLiveGvAdapter(Activity activity, List<RecommendDataBean.PczbListBean> list) {
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
        holder.iv_anchor_phonelive = (MyImageView) convertView.findViewById(R.id.iv_anchor_live);
        holder.tv_numoflook_pclive = (TextView) convertView.findViewById(R.id.tv_numoflook_pclive);
        holder.tv_anchor_pclive = (TextView) convertView.findViewById(R.id.tv_anchor_pclive);
        holder.tv_headline_pclive = (TextView) convertView.findViewById(R.id.tv_headline_pclive);
        /*获取数据*/
        RecommendDataBean.PczbListBean data = pclist.get(position);
        String zhibo_fm_pic = data.getZhibo_fm_pic();
        String btitle = data.getBtitle();
        String saytitle = data.getSaytitle();
        String watch_num = data.getWatch_num();
        String username = data.getUsername();
        String city = data.getCity();
        /*设置内容*/
        CommonUtil.loadImage(R.mipmap.ic_peopleoflook_live, holder.iv_anchor_phonelive, zhibo_fm_pic);
        holder.tv_anchor_pclive.setText(username);
        holder.tv_numoflook_pclive.setText(watch_num);
        holder.tv_headline_pclive.setText(saytitle + btitle);

        return convertView;
    }

    public class Viewholder {
        MyImageView iv_anchor_phonelive;//封面图片
        TextView tv_numoflook_pclive;//观看人数
        TextView tv_anchor_pclive;//主播名
        TextView tv_headline_pclive;//标题
    }
}
