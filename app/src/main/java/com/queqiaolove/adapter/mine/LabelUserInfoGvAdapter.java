package com.queqiaolove.adapter.mine;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.queqiaolove.R;
import com.queqiaolove.javabean.mine.UserInfoLabelListbean;

import java.util.List;

/**
 * Created by WD on 2016/10/15.
 */
public class LabelUserInfoGvAdapter extends BaseAdapter {
    private final Activity mActivity;
    List<UserInfoLabelListbean.ListBean> labellist;
    public LabelUserInfoGvAdapter(Activity activity, List<UserInfoLabelListbean.ListBean> list) {
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
            convertView = View.inflate(mActivity, R.layout.gvitem_userinfo_label, null);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }
        holder.cb_label_userinfo = (CheckBox) convertView.findViewById(R.id.cb_user_label);
        /*获取数据*/
        UserInfoLabelListbean.ListBean listBean = labellist.get(position);
        String lame = listBean.getLame();
        String id = listBean.getId();
        /*设置内容*/
        holder.cb_label_userinfo.setText(lame);

        return convertView;
    }

    public class Viewholder {
        CheckBox cb_label_userinfo;//标签
    }
}
