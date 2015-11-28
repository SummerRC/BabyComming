package com.aohuan.utils.common;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class AsyncImagerLoader1 {

	private final int INTNET = 1;
	private final int GETIMAGE = 2;
	private final int TEXT = 3;
	private Map<String, SoftReference<Bitmap>> map;
	private Context mContext;


	public AsyncImagerLoader1(Context mContext) {
		map = new HashMap<String, SoftReference<Bitmap>>();
		this.mContext = mContext;
	}

	public Bitmap getImageLoader(final String imageUrl,final ImageCallBack imageCallBack){

		Bitmap result = null;
		if(map.containsKey(imageUrl)){
			result = map.get(imageUrl).get();
			if(result != null){
				return result;
			}
		}

		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch(msg.what){
				case INTNET:
					Toast.makeText(mContext, "网络连接失败", Toast.LENGTH_SHORT).show();
					break;
				case TEXT:
					imageCallBack.onImageLoade(imageUrl, (Bitmap) msg.obj);
					break;
				}
			}
		};
		Runnable r = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(NetWorkUtil.isNetAvaiable(mContext)){
					Bitmap	bitmap = NetWorkUtil.getImageHttpClient(imageUrl);
					if(bitmap != null){
						map.put(imageUrl, new SoftReference<Bitmap>(bitmap));
						Message message = handler.obtainMessage(TEXT, bitmap);
						handler.sendMessage(message);
					}else{
						handler.sendEmptyMessage(GETIMAGE);
					}
				}else{
					handler.sendEmptyMessage(INTNET);
				}
			}
		};
		new Thread(r).start();
		return null;
	}


	//ʵ��һ�������ص��Ľӿ�
	public interface ImageCallBack{
		void onImageLoade(String url,Bitmap bitmap);
	}

}
