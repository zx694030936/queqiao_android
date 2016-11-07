package com.queqiaolove.adapter.mine.baseinfo;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.queqiaolove.R;
import com.queqiaolove.javabean.mine.UserBaseInfoBean;
import com.queqiaolove.util.CommonUtils;

import java.util.List;

/**
 * Created by zchk on 2016/11/3.
 */
public class PicMlvAdapter extends BaseAdapter {
    private final Activity mActivity;
    List<UserBaseInfoBean.PicListBean> pic_list;
    public PicMlvAdapter(Activity activity, List<UserBaseInfoBean.PicListBean> list) {
        mActivity =activity;
        pic_list = list;
    }

    @Override
    public int getCount() {
        return 3;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mActivity, R.layout.lv_pic_baseinfo,null);
        ImageView iv = (ImageView) v.findViewById(R.id.iv_pic1_mine);
        String upic = "";
        if (pic_list.size()-1<=i){
            v.setVisibility(View.INVISIBLE);
        }else {
            upic = pic_list.get(i).getUpic();
            CommonUtils.loadImage(R.mipmap.ic_default_uploadphoto, iv, upic);
        }

        return v;
    }
}
