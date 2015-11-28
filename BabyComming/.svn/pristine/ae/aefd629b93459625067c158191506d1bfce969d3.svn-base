package com.aohuan.utils;

import com.aohuan.utils.http.EFaceType;
import com.aohuan.utils.http.GetDataAsync;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.request.RequestBaseMap;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

public class CollectUtils {

	 public static void collectOrCancel(Activity activity,final Handler handler,String userID,
			 String team_id){
		 
		 
		 GetDataAsync gs=new GetDataAsync(activity, new IUpdateUI() {
			
			@Override
			public void updata(Object allData) {
				Message message=handler.obtainMessage();
				message.what=1;
				message.obj=allData;
				handler.sendMessage(message);
				
			}
		}, false, RequestBaseMap.getCollect(team_id, userID));
		 
		 gs.execute(EFaceType.URL_COLLECT);
		 
	 }
}
