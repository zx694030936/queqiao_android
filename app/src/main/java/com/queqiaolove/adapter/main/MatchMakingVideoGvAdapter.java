package com.queqiaolove.adapter.main;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.javabean.RecommendDataBean;
import com.queqiaolove.util.CommonUtils;

import java.util.List;

/**
 * Created by LENOVO on 2016/10/17.
 */
public class MatchMakingVideoGvAdapter extends BaseAdapter {
    private final Activity mActivity;
    List<RecommendDataBean.HdspListBean> hdsp_list;

    public MatchMakingVideoGvAdapter(Activity activity, List<RecommendDataBean.HdspListBean> list) {
        mActivity = activity;
        hdsp_list = list;
    }

    @Override
    public int getCount() {
        return 2;
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
            convertView = View.inflate(mActivity, R.layout.gvitem_matchmaking_video, null);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }
        holder.iv_anchor_live = (ImageView) convertView.findViewById(R.id.iv_anchor_live);
        holder.tv_numoflook_matchmakingvideo = (TextView) convertView.findViewById(R.id.tv_numoflook_matchmakingvideo);
        holder.tv_headline_matchmakingvideo = (TextView) convertView.findViewById(R.id.tv_headline_matchmakingvideo);
        /*获取数据*/
        if (hdsp_list.size() >=2) {
            RecommendDataBean.HdspListBean data = hdsp_list.get(position);
            String video_pic = data.getVideo_pic();
            String watch_num = data.getWatch_num();
            String atitle = data.getAtitle();
        /*设置内容*/
            CommonUtils.loadImage(R.mipmap.default_mylivevideo, holder.iv_anchor_live, video_pic);
            holder.tv_numoflook_matchmakingvideo.setText(watch_num);
            holder.tv_headline_matchmakingvideo.setText(atitle);
        }
        return convertView;
    }

    public class Viewholder {
        ImageView iv_anchor_live;//封面图片
        TextView tv_numoflook_matchmakingvideo;//观看人数
        TextView tv_headline_matchmakingvideo;//标题
    }
}
