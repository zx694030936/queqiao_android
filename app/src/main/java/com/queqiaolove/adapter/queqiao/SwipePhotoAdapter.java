package com.queqiaolove.adapter.queqiao;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxq17.swipecardsview.BaseCardAdapter;
import com.queqiaolove.R;
import com.queqiaolove.activity.user.UserInfoActivity;
import com.queqiaolove.javabean.main.PhotoListBean;
import com.queqiaolove.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 2016/10/24.
 */
public class SwipePhotoAdapter extends BaseCardAdapter {
    private final Activity mActivity;
    private final List<PhotoListBean.ListBean> urlList = new ArrayList<>();

    public SwipePhotoAdapter(Activity activity, List<PhotoListBean.ListBean> list) {
        mActivity = activity;
        //数据太少，多加几份
        urlList.addAll(list);
        urlList.addAll(list);
        urlList.addAll(list);
        urlList.addAll(list);
        urlList.addAll(list);
        urlList.addAll(list);
    }

    @Override
    public int getVisibleCardCount() {
        return 3;
    }

    @Override
    public List getData() {
        return urlList;
    }

    @Override
    public int getCardLayoutId() {
        return R.layout.activity_photo_queqiao;
    }

    @Override
    public void onBindData(final int position, View cardview, Object data) {
        ImageView pic = (ImageView) cardview.findViewById(R.id.iv_user_photo);
        TextView tv_name_photo = (TextView) cardview.findViewById(R.id.tv_name_photo);
        TextView tv_age_photo = (TextView) cardview.findViewById(R.id.tv_age_photo);
        TextView tv_city_photo = (TextView) cardview.findViewById(R.id.tv_city_photo);
        TextView tv_height_photo = (TextView) cardview.findViewById(R.id.tv_height_photo);
        TextView tv_numofpraise_photo = (TextView) cardview.findViewById(R.id.tv_numofpraise_photo);
        TextView tv_distance_photo = (TextView) cardview.findViewById(R.id.tv_distance_photo);
        TextView tv_percent_photo = (TextView) cardview.findViewById(R.id.tv_percent_photo);

        PhotoListBean.ListBean listBean = urlList.get(position);
        String upic = listBean.getUpic();
        String username = listBean.getUsername();
        String age = listBean.getAge();
        String city = listBean.getCity();
        String myheight = listBean.getMyheight();
        String integrity_degree = listBean.getIntegrity_degree();
        String distance = listBean.getDistance();

        tv_name_photo.setText(username);
        tv_age_photo.setText(age);
        tv_city_photo.setText(city);
        tv_height_photo.setText(myheight);
        tv_distance_photo.setText(distance);
        tv_percent_photo.setText(integrity_degree);

        CommonUtil.loadImage(R.mipmap.ic_default_photo,pic,upic);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoListBean.ListBean listBean = urlList.get(position);
                String userid = listBean.getUserid();
                UserInfoActivity.intent(mActivity,userid);
            }
        });
    }
}