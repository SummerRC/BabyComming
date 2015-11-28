package com.aohuan.view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.webkit.WebView;

public class MyWebview extends WebView {

	public MyWebview(Context context) {
		super(context);
	}

	public MyWebview(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public MyWebview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int hs=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, hs);
	}
}
