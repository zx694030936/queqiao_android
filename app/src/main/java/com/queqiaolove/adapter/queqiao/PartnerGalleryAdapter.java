package com.queqiaolove.adapter.queqiao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.util.CommonUtil;

/**
 * Created by WD on 2016/7/28.
 */
public class PartnerGalleryAdapter extends RecyclerView.Adapter<PartnerGalleryAdapter.ViewHolder> implements View.OnClickListener {
    private LayoutInflater mInflater;
    private String[] mTitles;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //条目点击监听接口
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }

    public PartnerGalleryAdapter(Context context, String[] titles) {
        mInflater = LayoutInflater.from(context);
        mTitles = titles;
    }

    /**
     * 条目要显示的view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_partner_queqiao,null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                CommonUtil.dip2px(126), CommonUtil.dip2px(172));
        params.setMargins(0,0, CommonUtil.dip2px(5),0);
        view.setLayoutParams(params);
        ViewHolder viewHolder = new ViewHolder(view);
        /*viewHolder.mImg = (ImageView) view.findViewById(R.id.iv_apprecommend);
        viewHolder.mTxt = (TextView) view.findViewById(R.id.tv_apprecommend);*/

        //给条目设置点击事件
        view.setOnClickListener(this);
        return viewHolder;
    }

    /**
     * 给条目设置值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /*holder.mTxt.setText(mTitles[position]);
        //把数据保存到itemview的tag中，以便点击时获取
        holder.itemView.setTag(mTitles[position]);*/
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener!=null){
            //用gettag()方法获取每个条目保存的数据
            mOnItemClickListener.onItemClick(v, (String) v.getTag());
        }
    }

    /**
     * 为条目设置点击监听的方法
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View arg0)
        {
            super(arg0);
        }

        ImageView mImg;
        TextView mTxt;
    }


}
