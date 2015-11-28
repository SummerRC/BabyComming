package com.aohuan.utils;

import com.aohuan.utils.http.EFaceType;
import com.aohuan.utils.http.GetDataAsync;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.request.RequestBaseMap;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

public class PwdProtectUtils {

	 public static void pwdPro(Activity activity,String user_id,
			 String rewult,String ques_id,final Handler handler){
		 
		 GetDataAsync gs=new GetDataAsync(activity, new IUpdateUI() {
			
			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				Message message=handler.obtainMessage();
				message.what=3;
				message.obj=allData;
				handler.sendMessage(message);
			}
		}, false, RequestBaseMap.getPwdPro(user_id, rewult, ques_id));
		 gs.execute(EFaceType.URL_SETTIMG_PRO);
	 }
}
