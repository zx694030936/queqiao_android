package com.queqiaolove.adapter.mine;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.javabean.mine.UserInfroDetailBean;

import java.util.List;

/**
 * Created by WD on 2016/10/15.
 */
public class LabelListGvAdapter extends BaseAdapter {
    private final Activity mActivity;
    List<UserInfroDetailBean.LabelListBean> labellist;
    public LabelListGvAdapter(Activity activity, List<UserInfroDetailBean.LabelListBean> list) {
        mActivity = activity;
        labellist = list;
    }

    @Override
    public int getCount() {
        return labellist.size();
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
            convertView = View.inflate(mActivity, R.layout.gvitem_userinfo_labellist, null);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }
        holder.tv_label_userinfo = (TextView) convertView.findViewById(R.id.tv_user_label);
        /*获取数据*/
        UserInfroDetailBean.LabelListBean listBean = labellist.get(position);
        String aname = listBean.getAname();
        String acode = listBean.getAcode();
        /*设置内容*/
        holder.tv_label_userinfo.setText(aname);

        return convertView;
    }

    public class Viewholder {
        TextView tv_label_userinfo;//标签
    }
}
