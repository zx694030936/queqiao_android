package com.queqiaolove.adapter.main;

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
 * Created by WD on 2016/10/2.
 */
public class MatchMakingLiveGvAdapter extends BaseAdapter {
    private final Activity mActivity;
    private final List<RecommendDataBean.HdzbListBean> hdzb_list;

    public MatchMakingLiveGvAdapter(Activity activity, List<RecommendDataBean.HdzbListBean> list) {
        mActivity = activity;
        hdzb_list = list;
    }

    @Override
    public int getCount() {
        return 2;
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
        if(convertView == null){
            convertView = View.inflate(mActivity, R.layout.gvitem_matchmaking_live,null);
            convertView.setTag(holder);
        }else{
            holder = (Viewholder) convertView.getTag();
        }
        holder.iv_anchor_phonelive = (MyImageView) convertView.findViewById(R.id.iv_anchor_live);
        holder.tv_numoflook_phonelive = (TextView) convertView.findViewById(R.id.tv_numoflook_phonelive);
        holder.tv_headline_phonelive = (TextView) convertView.findViewById(R.id.tv_headline_phonelive);
        /*获取数据*/
        RecommendDataBean.HdzbListBean data = hdzb_list.get(position);
        String zhibo_fm_pic = data.getZhibo_fm_pic();
        String btitle = data.getBtitle();
        String saytitle = data.getSaytitle();
        String watch_num = data.getWatch_num();
        String username = data.getUsername();
        /*设置内容*/
        CommonUtil.loadImage(R.mipmap.ic_peopleoflook_live,holder.iv_anchor_phonelive,zhibo_fm_pic);
        holder.tv_numoflook_phonelive.setText(watch_num);
        holder.tv_headline_phonelive.setText(saytitle+btitle);

        return convertView;
    }
    public class Viewholder{
        MyImageView iv_anchor_phonelive;//封面图片
        TextView tv_numoflook_phonelive;//观看人数
        TextView tv_anchor_phonelive;//主播名
        TextView tv_headline_phonelive;//位置
        public TextView tv_headlineandtopic_phonelive;//话题标题
    }
}
