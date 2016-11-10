package com.queqiaolove.adapter.mine;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.queqiaolove.R;
import com.queqiaolove.global.Constants;
import com.queqiaolove.javabean.sys.AttributeListBean;
import com.queqiaolove.util.SharedPrefUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by WD on 2016/10/15.
 */
public class LanguageEditGvAdapter extends BaseAdapter {
    private final Activity mActivity;
    List<AttributeListBean.ListBean> languagellist = new ArrayList<>();
    private HashSet<String> sp_languagelist;

    public LanguageEditGvAdapter(Activity activity, List<AttributeListBean.ListBean> list) {
        mActivity = activity;
        languagellist =list;
        sp_languagelist = new HashSet<String>();
        sp_languagelist = SharedPrefUtil.getSet(mActivity,Constants.SP_LANGUAGECODELIST,new HashSet<String>());
    }

    @Override
    public int getCount() {
        return languagellist.size();
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
        holder.cb_language_userinfo = (CheckBox) convertView.findViewById(R.id.cb_user_label);
        /*获取数据*/
        AttributeListBean.ListBean listBean = languagellist.get(position);
        String aname = listBean.getAname();
        String acode = listBean.getAcode();
        /*设置内容*/
        holder.cb_language_userinfo.setText(aname);
        holder.cb_language_userinfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischeck) {
                String id = languagellist.get(position).getAcode();
                String aname1 = languagellist.get(position).getAname();
                HashSet<String> codeset = new HashSet<String>();
                HashSet<String> strset = new HashSet<String>();
                codeset = SharedPrefUtil.getSet(mActivity,Constants.SP_LANGUAGECODELIST,new HashSet<String>());
                strset = SharedPrefUtil.getSet(mActivity,Constants.SP_LANGUAGELIST,new HashSet<String>());
                if (ischeck){
                    codeset.add(id);
                    strset.add(aname1);
                    SharedPrefUtil.putSet(mActivity, Constants.SP_LANGUAGECODELIST,codeset);
                    SharedPrefUtil.putSet(mActivity, Constants.SP_LANGUAGELIST,strset);
                }else {
                    codeset.remove(id);
                    strset.remove(aname1);
                    SharedPrefUtil.putSet(mActivity, Constants.SP_LANGUAGECODELIST,codeset);
                    SharedPrefUtil.putSet(mActivity, Constants.SP_LANGUAGELIST,strset);

                }
            }
        });
        for (String language : sp_languagelist
                ) {
            if (language.equals(acode)){
                holder.cb_language_userinfo.setChecked(true);
            }
        }

        return convertView;
    }

    public class Viewholder {
        CheckBox cb_language_userinfo;//标签
    }
}
