package com.queqiaolove.adapter.queqiao;

import android.app.Activity;
import android.view.View;

import com.huxq17.swipecardsview.BaseCardAdapter;
import com.queqiaolove.R;
import com.queqiaolove.activity.user.UserInfoActivity;

import java.util.List;

/**
 * Created by LENOVO on 2016/10/24.
 */
public class SwipePhotoAdapter extends BaseCardAdapter {
    private final Activity mActivity;
    private final List<String> urlList;

    public SwipePhotoAdapter(Activity activity, List<String> list) {
        mActivity = activity;
        urlList = list;
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
    public void onBindData(int position, View cardview, Object data) {
        View tv = cardview.findViewById(R.id.iv_user_photo);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfoActivity.intent(mActivity,"");
            }
        });
    }
}