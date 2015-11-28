package com.aohuan.utils;

import com.aohuan.utils.http.EFaceType;
import com.aohuan.utils.http.GetDataAsync;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.request.RequestBaseMap;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

public class modifyPwdUtils {

	 public static void modifyPwd(Activity activity,String user_id,String new_pwd,String pwd,final Handler handler){
		 
		 GetDataAsync gs=new GetDataAsync(activity, new IUpdateUI() {
			
			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				Message message=handler.obtainMessage();
				message.what=2;
				message.obj=allData;
				handler.sendMessage(message);
			}
		}, false, RequestBaseMap.getModify(user_id, pwd, new_pwd));
		 gs.execute(EFaceType.URL_MODIFY_PWD);
	 }
}
