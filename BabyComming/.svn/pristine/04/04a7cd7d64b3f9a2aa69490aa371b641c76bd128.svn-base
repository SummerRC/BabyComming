package com.aohuan.utils.viewpager_bili;

import com.aohuan.utils.lunbo.ViewFlow;

import android.app.Activity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class SetWidthAndeHeightUtils {
	/**
	 * 设置首页轮播的高
	 * @param activity
	 * @param viewFlow
	 * @param width 
	 * @param height
	 */
	public static void setViewFlow(Activity activity, ViewFlow viewFlow,
			int width, int height){

		WindowManager wm = activity.getWindowManager();
		int w = wm.getDefaultDisplay().getWidth();
		ViewGroup.LayoutParams params = viewFlow.getLayoutParams();
		params.width = w;   
		params.height = w*height/width;
		viewFlow.setLayoutParams(params);
	}
	public static void setImageView(Activity activity, ImageView image,
			int width, int height){
		
		WindowManager wm = activity.getWindowManager();
		int w = wm.getDefaultDisplay().getWidth();
		ViewGroup.LayoutParams params = image.getLayoutParams();
		params.width = w;   
		params.height = w*height/width;
		image.setLayoutParams(params);
	}

}
