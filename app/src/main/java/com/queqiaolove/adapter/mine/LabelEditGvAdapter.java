package com.queqiaolove.adapter.mine;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.queqiaolove.R;
import com.queqiaolove.global.Constants;
import com.queqiaolove.javabean.mine.UserInfoLabelListbean;
import com.queqiaolove.util.SharedPrefUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by WD on 2016/10/15.
 */
public class LabelEditGvAdapter extends BaseAdapter {
    private final Activity mActivity;
    List<UserInfoLabelListbean.ListBean> labellist = new ArrayList<>();
    private HashSet<String> sp_labellist;

    public LabelEditGvAdapter(Activity activity, List<UserInfoLabelListbean.ListBean> list) {
        mActivity = activity;
        labellist=list;
        sp_labellist = new HashSet<String>();
        sp_labellist = SharedPrefUtil.getSet(mActivity,Constants.SP_LABELLIST,new HashSet<String>());
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        Viewholder holder = new Viewholder();
        if (convertView == null) {
            convertView = View.inflate(mActivity, R.layout.gvitem_userinfo_labeledit, null);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }
        holder.cb_label_userinfo = (CheckBox) convertView.findViewById(R.id.cb_user_label);
        /*获取数据*/
        UserInfoLabelListbean.ListBean listBean = labellist.get(position);
        String lame = listBean.getLame();
        String lid = listBean.getId();
        /*设置内容*/
        holder.cb_label_userinfo.setText(lame);
        holder.cb_label_userinfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischeck) {
                String id = labellist.get(position).getId();
                HashSet<String> set = new HashSet<String>();
                set = SharedPrefUtil.getSet(mActivity,Constants.SP_LABELLIST,new HashSet<String>());
                if (ischeck){
                    set.add(id);
                    SharedPrefUtil.putSet(mActivity, Constants.SP_LABELLIST,set);
                }else {
                    set.remove(id);
                    SharedPrefUtil.putSet(mActivity, Constants.SP_LABELLIST,set);

                }
            }
        });
        for (String label :sp_labellist
                ) {
            if (label.equals(lid)){
                holder.cb_label_userinfo.setChecked(true);
            }
        }

        return convertView;
    }

    public class Viewholder {
        CheckBox cb_label_userinfo;//标签
    }
}
