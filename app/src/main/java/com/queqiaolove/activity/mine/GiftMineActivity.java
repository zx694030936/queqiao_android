package com.queqiaolove.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.adapter.mine.GiftFragmentPagerAdapter;
import com.queqiaolove.widget.NoScrollViewPager;

/**
 * Created by WD on 2016/10/11.
 */
public class GiftMineActivity extends FragmentActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static String REGISTER = "register";
    private ImageView tv_back;
    private TextView tv_title;
    private RadioGroup rg_mygift;
    private NoScrollViewPager vp_mygift;
    private Activity mActivity;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mView = View.inflate(mActivity,R.layout.activity_gift_mine,null);
        initView();
        initEvent();

        setContentView(mView);
    }

    protected void initView() {
        tv_title = (TextView) mView.findViewById(R.id.tv_title);
        tv_title.setText("我的礼物");
        tv_back = (ImageView) mView.findViewById(R.id.iv_back);

        rg_mygift = (RadioGroup) mView.findViewById(R.id.rg_mygift);
        vp_mygift = (NoScrollViewPager) mView.findViewById(R.id.vp_mygift);

        vp_mygift.setAdapter(new GiftFragmentPagerAdapter(getSupportFragmentManager()));
        vp_mygift.setCurrentItem(0,false);

    }
    protected void initEvent() {
        tv_back.setOnClickListener(this);
        rg_mygift.setOnCheckedChangeListener(this);
        rg_mygift.check(R.id.rb_receive_mygift);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, GiftMineActivity.class);
        intent.putExtra(REGISTER, data);
        activity.startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId){
            case R.id.rb_receive_mygift:
                vp_mygift.setCurrentItem(0,false);
                break;
            case R.id.rb_give_mygift:
                vp_mygift.setCurrentItem(1,false);
                break;
        }
    }
}
