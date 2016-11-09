package com.queqiaolove.adapter.main.matchmaking;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.javabean.live.LiveUrlListBean;
import com.queqiaolove.util.CommonUtil;

import java.util.List;

/**
 * Created by LENOVO on 2016/10/17.
 */
public class LiveMMGvAdapter extends BaseAdapter{
    private final Activity mActivity;
    private final List<LiveUrlListBean.ListBean> datalist;

    public LiveMMGvAdapter(Activity activity, List<LiveUrlListBean.ListBean> list) {
        mActivity = activity;
        datalist = list;
    }

    @Override
    public int getCount() {
        return datalist.size();
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
        View v = View.inflate(mActivity,R.layout.gvitem_matchmaking_live,null);
        Viewholder holder = new Viewholder();
        if(convertView == null){
            convertView = View.inflate(mActivity, R.layout.gvitem_matchmaking_live,null);
            convertView.setTag(holder);
        }else{
            holder = (Viewholder) convertView.getTag();
        }
        holder.iv_anchor_phonelive = (ImageView) convertView.findViewById(R.id.iv_anchor_live);
        holder.tv_numoflook_phonelive = (TextView) convertView.findViewById(R.id.tv_numoflook_phonelive);
        holder.tv_location_phonelive = (TextView) convertView.findViewById(R.id.tv_location_phonelive);
        /*获取数据*/
        LiveUrlListBean.ListBean data = datalist.get(position);
        String zhibo_fm_pic = data.getZhibo_fm_pic();
        String btitle = data.getBtitle();
        String saytitle = data.getSaytitle();
        String watch_num = data.getWatch_num();
        String username = data.getUsername();
        String city = data.getCity();
        /*设置内容*/
        CommonUtil.loadImage(R.mipmap.ic_peopleoflook_live,holder.iv_anchor_phonelive,zhibo_fm_pic);
        holder.tv_numoflook_phonelive.setText(watch_num);
        holder.tv_location_phonelive.setText(city);

        return convertView;
    }
    public class Viewholder{
        ImageView iv_anchor_phonelive;//封面图片
        TextView tv_numoflook_phonelive;//观看人数
        TextView tv_anchor_phonelive;//主播名
        TextView tv_location_phonelive;//位置
        public TextView tv_headlineandtopic_phonelive;//话题标题
    }
}
