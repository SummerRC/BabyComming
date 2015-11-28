package com.aohuan.utils.adapter;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ViewHolderDif {
	private final HashMap<String,View> mViews;
	private int mPosition;
	private View mConvertView;
	private String mType;

	private ViewHolderDif(Context context, ViewGroup parent, int layoutId,
			int position,String type) {
		this.mPosition = position;
		this.mType = type;
		this.mViews = new HashMap<String,View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		// setTag
		mConvertView.setTag(this);
	}

	/**
	 * 拿到ViewHolder对象
	 * 
	 * @param context
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @param position
	 * @return
	 */
	public static ViewHolderDif get(Context context, View convertView,
			ViewGroup parent, int layoutId, int position, String type) {
		if (convertView == null) {
			return new ViewHolderDif(context, parent, layoutId, position, type);
		}
		return (ViewHolderDif) convertView.getTag();
	}

	public View getConvertView() {
		return mConvertView;
	}

	/**
	 * 通过控件的Id获取对于的控件，如果没有则加入views
	 * 
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(String type,int viewId) {
		String key = type + viewId;
		View view = mViews.get(key);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(key, view);
		}
		return (T) view;
	}

	/**
	 * 为TextView设置字符�?
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolderDif setText(String type,int viewId, String text) {
		TextView view = getView(type,viewId);
		view.setText(text);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolderDif setImageResource(String type,int viewId, int drawableId) {
		ImageView view = getView(type,viewId);
		view.setImageResource(drawableId);

		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolderDif setImageBitmap(String type,int viewId, Bitmap bm) {
		ImageView view = getView(type,viewId);
		view.setImageBitmap(bm);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolderDif setImageByUrl(String type,int viewId, String url,Context context) {
//		ImageLoader.getInstance(3, Type.LIFO).loadImage(url,
//				(ImageView) getView(viewId));
		ImageLoad.loadImage((ImageView)getView(type,viewId), url, context);
		
		return this;
	}
	
	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolderDif setImageByUrl(String type,int viewId, String url,Context context,int drawId) {
		ImageLoad.loadImageByDefaultImage((ImageView)getView(type,viewId), url, context, drawId);
		
		return this;
	}

	public int getPosition() {
		return mPosition;
	}

}
