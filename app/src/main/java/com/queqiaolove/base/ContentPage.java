package com.queqiaolove.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.queqiaolove.R;
import com.queqiaolove.util.CommonUtil;

public abstract class ContentPage extends FrameLayout {
	
	
	/**
	 * 请求网络的结果对应的状态
	 */
//	//正在加载
//	public static final int STATE_LOADING = 0;
//	//加载成功
//	public static final int STATE_SUCCESS = 1;
//	//加载失败
//	public static final int STATE_ERROR = 2;
//	//返回数据为空
//	public static final int STATE_EMPTY = 3;
	
	private View loadingView;
	private View emptyView;
	private View errorView;
	private View successView;
	private LayoutParams params;
	
	public enum RequestState{
		STATE_LOADING,STATE_SUCCESS,STATE_ERROR,STATE_EMPTY
	}
	
	//当前状态
	private RequestState state;

	public ContentPage(Context context) {
		super(context);
		
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		//初始化子view
		initView();
		//请求网络数据
		loadDataAndRefreshView();
	}

	/**
	 * 把child从父控件中移除
	 * @param child
	 */
	private void removeFromParent(View child) {
		if(child != null){
			ViewParent parent = child.getParent();
			if(parent != null && parent instanceof ViewGroup){
				ViewGroup p = (ViewGroup) parent;
				p.removeView(child);
			}
		}
	}

	/**
	 * 请求数据并且刷新界面
	 */
	public void loadDataAndRefreshView() {
		//每次请求数据时把状态改为loading，然后显示界面
		state = RequestState.STATE_LOADING;
		showPage();
		
		//请求网络数据是耗时的操作，放在子线程中执行
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				state = onLoad();
				showPageSafe();
			}
		}).start();
	}

	/**
	 * 安全显示界面
	 */
	protected void showPageSafe() {
		CommonUtil.runInMainThread(new Runnable() {
			
			@Override
			public void run() {
				showPage();
			}
		});
	}

	/**
	 * 根据网络请求的状态决定界面的显示
	 */
	protected void showPage() {
		loadingView.setVisibility(state==RequestState.STATE_LOADING?View.VISIBLE:View.GONE);
		errorView.setVisibility(state==RequestState.STATE_ERROR?View.VISIBLE:View.GONE);
		emptyView.setVisibility(state==RequestState.STATE_EMPTY?View.VISIBLE:View.GONE);
		
		//处理成功对应的界面
		if(successView == null && state == RequestState.STATE_SUCCESS){
			successView = onCreateSuccessView();
			if(successView != null){
				addView(successView,params);
				successView.setVisibility(View.VISIBLE);
			}else{
				throw new IllegalStateException("onCreateSuccessView can't return null");
			}
		}
	}

	/**
	 * 返回成功对应的界面
	 * 成功对应的界面，对父类未知，交给子类实现
	 * @return
	 */
	protected abstract View onCreateSuccessView();

	/**
	 * 具体请求网络的操作由该方法完成
	 * 具体的请求操作，对父类是未知的，所以抽象，交给子类实现
	 */
	protected abstract RequestState onLoad();

	private void initView() {
		if(loadingView == null){
			loadingView = View.inflate(getContext(), R.layout.layout_loading, null);
			addView(loadingView, params);
		}
		
		if(emptyView == null){
			emptyView = View.inflate(getContext(), R.layout.layout_empty, null);
			addView(emptyView, params);
		}
		
		if(errorView == null){
			errorView = View.inflate(getContext(), R.layout.layout_error, null);
			errorView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					loadDataAndRefreshView();
				}
			});
			addView(errorView, params);
		}
	}
	
	
	
	
	
}
