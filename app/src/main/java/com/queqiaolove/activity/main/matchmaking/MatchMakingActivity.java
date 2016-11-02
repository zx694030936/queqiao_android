package com.queqiaolove.activity.main.matchmaking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.queqiaolove.R;
import com.queqiaolove.adapter.main.matchmaking.MatchMakingVpAdapter;
import com.queqiaolove.widget.NoScrollViewPager;

/**
 * Created by LENOVO on 2016/10/16.
 */
public class MatchMakingActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private static String CODE = "match";
    private RadioGroup rg_matchmaking;
    private NoScrollViewPager vp_matchmaking;
    private ImageView iv_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchmaking_main);
        initView();
        initEvent();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        rg_matchmaking = (RadioGroup) findViewById(R.id.rg_matchmaking);
        vp_matchmaking = (NoScrollViewPager) findViewById(R.id.vp_matchmaking);
        vp_matchmaking.setAdapter(new MatchMakingVpAdapter(getSupportFragmentManager()));
    }

    private void initEvent() {
        iv_back.setOnClickListener(this);
        rg_matchmaking.setOnCheckedChangeListener(this);
        rg_matchmaking.check(R.id.rb_live_matchmaking);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.rb_live_matchmaking:
                vp_matchmaking.setCurrentItem(0, false);
                break;
            case R.id.rb_video_matchmaking:
                vp_matchmaking.setCurrentItem(1, false);
                break;
            case R.id.rb_prevue_matchmaking:
                vp_matchmaking.setCurrentItem(2, false);
                break;
        }
    }
    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, MatchMakingActivity.class);
        intent.putExtra(CODE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
