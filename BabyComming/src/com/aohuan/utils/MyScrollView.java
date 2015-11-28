package com.aohuan.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView{

//	private boolean canScroll;
//
//	private GestureDetector mGestureDetector;
//	View.OnTouchListener mGestureListener;
//
//	public MyScrollView(Context context, AttributeSet attrs) {
//
//		super(context, attrs);
//		mGestureDetector = new GestureDetector(new YScrollDetector());
//		canScroll = true;
//
//	}
//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//		if(ev.getAction() == MotionEvent.ACTION_UP)
//			canScroll = true;
//		return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
//
//	}
//
//	class YScrollDetector extends SimpleOnGestureListener {
//
//		@Override
//		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//			if(canScroll)
//				if (Math.abs(distanceY) >= Math.abs(distanceX))
//					canScroll = true;
//				else
//					canScroll = false;
//			return canScroll;
//		}
//
//	}
	 // 滑动距离及坐标  
    private float xDistance, yDistance, xLast, yLast;  
  
    public MyScrollView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    @Override  
    public boolean onInterceptTouchEvent(MotionEvent ev) {  
        switch (ev.getAction()) {  
            case MotionEvent.ACTION_DOWN:  
                xDistance = yDistance = 0f;  
                xLast = ev.getX();  
                yLast = ev.getY();  
                break;  
            case MotionEvent.ACTION_MOVE:  
                final float curX = ev.getX();  
                final float curY = ev.getY();  
                  
                xDistance += Math.abs(curX - xLast);  
                yDistance += Math.abs(curY - yLast);  
                xLast = curX;  
                yLast = curY;  
                  
                if(xDistance > yDistance){  
                    return false;  
                }    
        }  
  
        return super.onInterceptTouchEvent(ev);  
    }  
}


