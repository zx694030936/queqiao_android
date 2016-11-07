package com.queqiaolove.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.queqiaolove.R;
import com.queqiaolove.base.ContentPage.RequestState;

import java.util.List;

public abstract class BaseFragment extends Fragment {
	protected View mContentView;
	protected Activity mActivity;
	protected LinearLayout mTitleView;//标题布局
	protected LinearLayout mContent;//内容布局
	private View mView;
	protected String userid = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mActivity = getActivity();
		mView = View.inflate(mActivity, R.layout.fragment_base,null);
		mTitleView = (LinearLayout) mView.findViewById(R.id.ll_title_base);
		mContent = (LinearLayout) mView.findViewById(R.id.ll_content_base);
		if (initTitleView()!=null){
			mTitleView.addView(initTitleView());
		}
		mContentView = initContentLayout();
		initView();
		initEvent();


		ContentPage contentPage = new ContentPage(getActivity()) {

			@Override
			protected View onCreateSuccessView() {
				return BaseFragment.this.onCreateSuccessView();
			}

			@Override
			protected RequestState onLoad() {
				return BaseFragment.this.onLoad();
			}
			
		};
		mContent.addView(contentPage);

		return mView;
	}

	protected abstract View initTitleView();

	protected abstract View initContentLayout();

	protected abstract void initView();

	protected abstract void initEvent();

	/**
	 * 具体请求网络的操作由子Fragment实现
	 * @return
	 */
	protected abstract RequestState onLoad();
	/**
	 * 成功对应的界面由具体子Fragment实现
	 * @return
	 */
	public View onCreateSuccessView(){return mContentView;}
	
	protected RequestState check(List data){
		RequestState state = null;
		if(data == null){
			state = RequestState.STATE_ERROR;
		}else if(data.size() == 0){
			state = RequestState.STATE_EMPTY;
		}else{
			state = RequestState.STATE_SUCCESS;
		}
		return state;
	}
	/**
	 * toast工具类
	 */
	public void toast(String str){
		Toast.makeText(mActivity,str, Toast.LENGTH_SHORT).show();
	}
}
