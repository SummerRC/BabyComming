package com.aohuan.utils.loding;



import com.aohuan.babycomming.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class ProgressUtils {
	private Dialog mDialog;
	

	//加载进度条
	 public void showDialog(Context mContext){
	        mDialog = new AlertDialog.Builder(mContext).create();
	        View view = LayoutInflater.from(mContext).inflate(R.layout.loading_process_dialog_anim, null);
	        // 添加布局
	        mDialog.show();
	        mDialog.setContentView(view);
	        mDialog.setCancelable(false);  //点击空白不消失
	    }
	 //完成时消失
	 public void dismissDialog(){
		 if (mDialog != null){
			 mDialog.dismiss();
		 }
			 
	 }
	 

}
