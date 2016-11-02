package com.queqiaolove.fragment.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.queqiaolove.R;
import com.queqiaolove.adapter.find.FindFragmentPagerAdapter;
import com.queqiaolove.widget.NoScrollViewPager;


public class FindFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

	private RadioGroup rg_find;
	private NoScrollViewPager vp_find;
	private Activity mActivity;
	private View mView;
	@Override
	public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
		mActivity = getActivity();
		mView = View.inflate(mActivity,R.layout.fragment_find_main,null);
		initView();
		initEvent();

		return mView;
	}


	protected void initView() {
		/*rg_find.推荐,直播,点播*/
		rg_find = (RadioGroup) mView.findViewById(R.id.rg_find);
		vp_find = (NoScrollViewPager) mView.findViewById(R.id.vp_find);
		vp_find.setAdapter(new FindFragmentPagerAdapter(getChildFragmentManager()));
		vp_find.setCurrentItem(0,false);

	}

	protected void initEvent() {
		rg_find.setOnCheckedChangeListener(this);
		rg_find.check(R.id.rb_activity_find);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId){
			case R.id.rb_activity_find:
				vp_find.setCurrentItem(0,false);
				break;
			case R.id.rb_welfare_find:
				vp_find.setCurrentItem(1,false);
				break;
			case R.id.rb_nearby_find:
				vp_find.setCurrentItem(2,false);
				break;
		}
	}
}
