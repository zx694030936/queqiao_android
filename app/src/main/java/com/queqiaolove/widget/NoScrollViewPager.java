package com.queqiaolove.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context) {
		super(context);
	}

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// 直接return true, 什么都不做
		return true;
	}
	
	// 这个方法会拦截子View的事件
	// 默认情况下, 事件会交给最里面的View处理
	// 这个方法的返回值决定, 是否要拦下子View的事件
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// return false, 表示所有的事件都不拦截, 都交给子View处理
		return false;
	}

}
