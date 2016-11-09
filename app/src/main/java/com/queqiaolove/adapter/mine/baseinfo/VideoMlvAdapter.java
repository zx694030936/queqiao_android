package com.queqiaolove.adapter.mine.baseinfo;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.queqiaolove.R;
import com.queqiaolove.util.CommonUtil;

import java.util.List;

/**
 * Created by zchk on 2016/11/3.
 */
public class VideoMlvAdapter extends BaseAdapter {
    private final Activity mActivity;
    List<String> video_list;
    public VideoMlvAdapter(Activity activity, List<String> list) {
        mActivity =activity;
        video_list = list;
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
        View v = View.inflate(mActivity, R.layout.lv_video_baseinfo,null);
        ImageView iv = (ImageView) v.findViewById(R.id.iv_video1_mine);
        String upic= "";
        if (video_list.size()-1<=i) {
            v.setVisibility(View.INVISIBLE);
        }else {
            upic = video_list.get(i);
            CommonUtil.loadImage(R.mipmap.ic_default_uploadphoto, iv, upic);
        }

        return v;
    }
}
