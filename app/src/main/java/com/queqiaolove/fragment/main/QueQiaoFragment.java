package com.queqiaolove.fragment.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.queqiaolove.R;
import com.queqiaolove.adapter.queqiao.QueQiaoFragmentPagerAdapter;
import com.queqiaolove.widget.NoScrollViewPager;


public class QueQiaoFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

	private RadioGroup rg_queqiao;
	private NoScrollViewPager vp_queqiao;
	private Activity mActivity;
	private View mView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mActivity = getActivity();
		mView = View.inflate(mActivity,R.layout.fragment_queqiao_main,null);
		initView();
		initEvent();

		return mView;
	}


	protected void initView() {
		/*rg_queqiao.推荐,直播,点播*/
		rg_queqiao = (RadioGroup) mView.findViewById(R.id.rg_queqiao);
		vp_queqiao = (NoScrollViewPager) mView.findViewById(R.id.vp_queqiao);
		vp_queqiao.setAdapter(new QueQiaoFragmentPagerAdapter(getChildFragmentManager()));
		vp_queqiao.setCurrentItem(0,false);

	}

	protected void initEvent() {
		rg_queqiao.setOnCheckedChangeListener(this);
		rg_queqiao.check(R.id.rb_recommend_queqiao);


	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId){
			case R.id.rb_recommend_queqiao:
				vp_queqiao.setCurrentItem(0,false);
				break;
			case R.id.rb_live_queqiao:
				vp_queqiao.setCurrentItem(1,false);
				break;
			case R.id.rb_video_queqiao:
				vp_queqiao.setCurrentItem(2,false);
				break;
			case R.id.rb_photo_queqiao:
				vp_queqiao.setCurrentItem(3,false);
				break;
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		rg_queqiao.clearCheck();
		mView = null;
	}
}
