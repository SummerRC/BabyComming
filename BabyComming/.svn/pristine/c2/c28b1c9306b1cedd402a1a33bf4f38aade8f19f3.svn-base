package com.aohuan.utils;

import com.aohuan.utils.http.EFaceType;
import com.aohuan.utils.http.GetDataAsync;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.request.RequestBaseMap;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

public class FindPwdUtils {

	  public static void findPwd(Activity activity,String username,String result,
			  String question_id,final Handler handler){
		  
		  GetDataAsync gs=new GetDataAsync(activity, new IUpdateUI() {
			
			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				Message message=handler.obtainMessage();
				message.what=4;
				message.obj=allData;
				handler.sendMessage(message);
			}
		}, false, RequestBaseMap.getFindPwd(username, result, question_id));
		  gs.execute(EFaceType.URL_FIND_PWD1);
	  }
}
