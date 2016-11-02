package com.queqiaolove.widget.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.queqiaolove.R;

import java.util.ArrayList;


public class SelectTopicPusherDialog extends Dialog implements View.OnClickListener, AdapterView.OnItemClickListener, Animation.AnimationListener, TextView.OnEditorActionListener {

    private final Activity mActivity;
    private SearchRangeListener mSearchRangeListener;//回调接口引用
    private String range;
    private EditText et_content_dialog;
    private GridView gv_search;
    private TextView tv_cancel;
    private ArrayList<String> topicList = new ArrayList<>();
    private Animation endAnima ;
    public SelectTopicPusherDialog(Activity context) {
        //构造方法中传入一个主题，去除dialog的默认背景
        super(context, R.style.dialog_style_search);
        mActivity = context;
        this.setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //去除dialog的标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_selecttopic_pusher);

        //设置dialog的显示位置
        LayoutParams params = getWindow().getAttributes();
        params.width = LayoutParams.MATCH_PARENT;//宽度填充父布局
        params.height = LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.TOP;

        //初始化控件
        initView();
        initEvent();
    }

    private void initView() {
        et_content_dialog = (EditText) findViewById(R.id.et_content_dialog);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        gv_search = (GridView) findViewById(R.id.gv_search);

        loadData();
    }

    private void initEvent() {
        tv_cancel.setOnClickListener(this);
        et_content_dialog.setOnEditorActionListener(this);
        gv_search.setOnItemClickListener(this);
    }

    private void loadData() {
        topicList.add("#新人求关注#");
        topicList.add("#逗比#");
        topicList.add("#大学生#");
        topicList.add("#我是小鲜肉#");
        topicList.add("#女神#");
        topicList.add("#话唠#");
        topicList.add("#好身材show出来#");
        topicList.add("#吃货驾到#");
        topicList.add("#我颜值高#");
        topicList.add("#麦霸#");
        topicList.add("#我会跳舞#");
        topicList.add("#我是大厨#");
        topicList.add("#爱做家务#");
        topicList.add("#多才多艺#");
        topicList.add("#爱旅行#");
        topicList.add("#我的新装#");
        topicList.add("#美丽婚纱#");
        topicList.add("#度蜜月#");
        topicList.add("#秀恩爱#");

        gv_search.setAdapter(new MyAdapter());
        Animation startAnima = AnimationUtils.loadAnimation(mActivity, R.anim.dialog_enter_search);
        gv_search.startAnimation(startAnima);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel://取消按钮
                endAnima = AnimationUtils.loadAnimation(mActivity, R.anim.dialog_exit_search);
                gv_search.startAnimation(endAnima);
                endAnima.setAnimationListener(this);
                break;
        }
    }

    /*条目点击事件*/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0) {
            TextView tv_areaname = (TextView) view.findViewById(R.id.tv_name_topic);
            range= tv_areaname.getText().toString().trim();
            if (mSearchRangeListener != null) {
                mSearchRangeListener.searchrange(range);
                endAnima = AnimationUtils.loadAnimation(mActivity, R.anim.dialog_exit_search);
                gv_search.startAnimation(endAnima);
                endAnima.setAnimationListener(this);
            }
        }
    }


    //设置回调接口的方法
    public void setSearchRangeListener(SearchRangeListener searchRangeListener) {
        this.mSearchRangeListener = searchRangeListener;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        dismiss();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    /*输入法点击搜索按钮监听*/
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            range = et_content_dialog.getText().toString().trim();
            if (range.trim().equals("")){
                return true;
            }
            if (mSearchRangeListener != null) {
                mSearchRangeListener.searchrange(range);
                endAnima = AnimationUtils.loadAnimation(mActivity, R.anim.dialog_exit_search);
                gv_search.startAnimation(endAnima);
                endAnima.setAnimationListener(this);
            }
            return true;

        } else if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED && event != null) {

            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                range = et_content_dialog.getText().toString().trim();
                if (range.trim().equals("")){
                    return true;
                }
                if (mSearchRangeListener != null) {
                    mSearchRangeListener.searchrange(range);
                    endAnima = AnimationUtils.loadAnimation(mActivity, R.anim.dialog_exit_search);
                    gv_search.startAnimation(endAnima);
                    endAnima.setAnimationListener(this);
                }
                return true;
            }

        }
        return false;
    }

    //dialog的回调接口
    public interface SearchRangeListener {
        public void searchrange(String range);
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return topicList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(mActivity, R.layout.gvitem_searchdialog_pusher, null);
            TextView tv_areaname = (TextView) convertView.findViewById(R.id.tv_name_topic);

            String topic = topicList.get(position);

            tv_areaname.setText(topic);
            return convertView;
        }
    }
}

