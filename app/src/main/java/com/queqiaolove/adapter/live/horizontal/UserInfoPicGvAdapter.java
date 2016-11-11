package com.queqiaolove.adapter.live.horizontal;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.queqiaolove.R;
import com.queqiaolove.javabean.mine.UserInfroDetailBean;
import com.queqiaolove.util.CommonUtil;

import java.util.List;

/**
 * Created by LENOVO on 2016/10/25.
 */
public class UserInfoPicGvAdapter extends BaseAdapter {
    private final Activity mActivity;
    private final List<UserInfroDetailBean.PicListBean> pic_list;

    public UserInfoPicGvAdapter(Activity activity, List<UserInfroDetailBean.PicListBean> list) {
        mActivity = activity;
        pic_list = list;
    }

    @Override
    public int getCount() {
        return pic_list.size();
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
        if(convertView == null){
            convertView = View.inflate(mActivity, R.layout.gvitem_userinfo_pic,null);
            convertView.setTag(holder);
        }else{
            holder = (Viewholder) convertView.getTag();
        }
        holder.iv_liveuserinfo = (ImageView) convertView.findViewById(R.id.iv_liveuserinfo);
        UserInfroDetailBean.PicListBean picListBean = pic_list.get(position);
        String upic = picListBean.getUpic();

        CommonUtil.loadImage(R.mipmap.ic_pic_live,holder.iv_liveuserinfo,upic);

        return convertView;
    }
    public class Viewholder{
        ImageView iv_liveuserinfo;//封面图片
    }
}
