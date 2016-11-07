package com.queqiaolove.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.queqiaolove.R;
import com.queqiaolove.activity.pusher.HomePusherActivity;
import com.queqiaolove.adapter.main.MainFragmentPagerAdapter;
import com.queqiaolove.widget.MyRadioGroup;
import com.queqiaolove.widget.NoScrollViewPager;


/**
 * 主界面activity
 */
public class MainActivity extends FragmentActivity implements  MyRadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private MyRadioGroup rg_maintab;//主界面RadioGroup
    private NoScrollViewPager vp_main;//viewpager
    private static String MAIN_ACTIVITY = "main_activity";
    private String[] mDataArr;//传入数据
    private ImageView iv_pushe;
    //private ArrayList<BaseFragment> mArraylList = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOnCreate(getIntent().getExtras());
        //CommonUtil.initSystemBar(this);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        rg_maintab = (MyRadioGroup) findViewById(R.id.rg_maintab);
        vp_main = (NoScrollViewPager) findViewById(R.id.vp_noscrollmain);
        iv_pushe = (ImageView) findViewById(R.id.iv_pusher_main);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //初始化所有页
        /*mArraylList.clear();
        if (mArraylList == null){
            mArraylList = new ArrayList<>();
        }
        mArraylList.add(new _QueQiaoFragment());
        mArraylList.add(new EatFragment());
        mArraylList.add(new DrinkFragment());
        mArraylList.add(new MineFragment());*/

        vp_main.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager()));
        //设置默认选中
        //rg_maintab.check(R.id.rb_homepage);
        //vp_main.setCurrentItem(0,false);
        //((_QueQiaoFragment)mArraylList.get(0)).loadData();
        //根据传入参数选择对应页面
        if (mDataArr == null) {
            selectQueQiao();
        } else {
            switch (mDataArr[0].trim()) {
                case "0":
                    selectQueQiao();
                    break;
                case "1":
                    selectFind();
                    break;
                case "2":
                    selectMessage();
                    break;
                case "3":
                    selectMine();
                    break;
            }
        }
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        rg_maintab.setOnCheckedChangeListener(this);
        iv_pushe.setOnClickListener(this);
    }

    //RadioGroup选择监听
    @Override
    public void onCheckedChanged(MyRadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_queqiao:
                vp_main.setCurrentItem(0, false);
                break;
            case R.id.rb_find:
                vp_main.setCurrentItem(1, false);
                break;
            case R.id.rb_message:
                vp_main.setCurrentItem(2, false);
                break;
            case R.id.rb_mine:
                vp_main.setCurrentItem(3, false);
                break;
        }
    }
    protected void activityOnCreate(Bundle extras) {
        if (extras != null) {
            mDataArr = extras.getStringArray(MAIN_ACTIVITY);
        } else {
            mDataArr = new String[]{"0"};
        }
    }

    public static void intent(Activity activity, String[] arr) {
        Intent intent = new Intent();
        intent.setClass(activity, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//清除之前activity
        intent.putExtra(MAIN_ACTIVITY, arr);
        activity.startActivity(intent);
    }

    /**
     * 跳转到吃页
     */
    public void selectQueQiao() {
        rg_maintab.check(R.id.rb_queqiao);
    }

    /**
     * 跳转到吃页
     */
    public void selectFind() {
        rg_maintab.check(R.id.rb_find);
    }

    /**
     * 跳转到喝页
     */
    public void selectMessage() {
        rg_maintab.check(R.id.rb_message);
    }

    /**
     * 跳转到喝页
     */
    public void selectMine() {
        rg_maintab.check(R.id.rb_mine);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_pusher_main:
                HomePusherActivity.intent(this,"1");
                break;
        }
    }
}
