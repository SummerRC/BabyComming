package com.aohuan.utils.dialog;

import com.aohuan.babycomming.MainActivity;
import com.aohuan.babycomming.R;
import com.aohuan.babycomming.ViewPagerActivity;
import com.aohuan.utils.downlodeapk.UpdateUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class CallPhoneUtils {
	/**
	 * 客服电话
	 * @param activity
	 * @param phone
	 */
	public static void showDialog(final Activity activity, final String phone){
		final Dialog dialog = new Dialog(activity);
		View view = LayoutInflater.from(activity).inflate(R.layout.dialog_phone_layout, null);
		Button cencal = (Button) view.findViewById(R.id.btn_cencal);
		Button sure = (Button) view.findViewById(R.id.btn_sure);

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(view);
		dialog.show();
		cencal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(Intent.ACTION_DIAL);
				Uri data = Uri.parse("tel:" + phone);
				intent.setData(data);
				activity.startActivity(intent);

			}
		});
		dialogOperation(dialog , activity);
	}
	/**
	 * 版本更新
	 * @param activity
	 * @param phone
	 */
	public static void showDialogVersion(final Activity activity, final String url){
		final SharedPreferences mPreferences = activity.getSharedPreferences("StartPage", activity.MODE_PRIVATE);
		final Dialog dialog = new Dialog(activity);
		View view = LayoutInflater.from(activity).inflate(R.layout.dialog_version_layout, null);
		Button cencal = (Button) view.findViewById(R.id.btn_cencal);
		Button sure = (Button) view.findViewById(R.id.btn_sure);
		
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(view);
		dialog.setCanceledOnTouchOutside(false);//点击空白不会消失
		dialog.show();
		cencal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				if(mPreferences.getString("one", "").equals("two")){
					Intent intent = new Intent(activity, MainActivity.class);
					activity.startActivity(intent);
					activity.finish();
				}else{
					Intent intent = new Intent(activity, ViewPagerActivity.class);
					activity.startActivity(intent);
					Editor editor = mPreferences.edit();
					editor.putString("one", "two");
					editor.commit();
					activity.finish();
				}
			}
		});
		sure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
//				UpdateUtils.doloadAPK(url, activity);
				Toast.makeText(activity, url, Toast.LENGTH_SHORT).show();
				
			}
		});
		dialogOperation(dialog , activity);
	}
	/**
	 * 版本更新
	 * @param activity
	 * @param phone
	 */
	public static void showVersion(final Activity activity, final String url){
		final SharedPreferences mPreferences = activity.getSharedPreferences("StartPage", activity.MODE_PRIVATE);
		final Dialog dialog = new Dialog(activity);
		View view = LayoutInflater.from(activity).inflate(R.layout.dialog_version_layout, null);
		Button cencal = (Button) view.findViewById(R.id.btn_cencal);
		Button sure = (Button) view.findViewById(R.id.btn_sure);
		
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(view);
		dialog.setCanceledOnTouchOutside(false);//点击空白不会消失
		dialog.show();
		cencal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				if(mPreferences.getString("one", "").equals("two")){
					Intent intent = new Intent(activity, MainActivity.class);
					activity.startActivity(intent);
				}else{
					Intent intent = new Intent(activity, ViewPagerActivity.class);
					activity.startActivity(intent);
					Editor editor = mPreferences.edit();
					editor.putString("one", "two");
					editor.commit();
				}
			}
		});
		sure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Toast.makeText(activity, url, Toast.LENGTH_SHORT).show();
				
			}
		});
		dialogOperation(dialog , activity);
	}


	public static void dialogOperation(Dialog dialog, Activity activity){
		dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		WindowManager wm = activity.getWindowManager();

		int width = wm.getDefaultDisplay().getWidth();
		WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

		params.width = width/10*7;
		dialog.getWindow().setAttributes(params);
	}

}
