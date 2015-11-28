package com.aohuan.babycomming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.Toast;

import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.VersionBean;
import com.aohuan.utils.common.LogAh;
import com.aohuan.utils.dialog.CallPhoneUtils;
import com.aohuan.utils.http.EFaceTypeZGQ;
import com.aohuan.utils.http.GetDataAsyncZGQ;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.request.RequestBaseMapZGQ;
import com.lidroid.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_start_page_layout)
public class StartPageActivity extends BaseActiviry{
	private final int TIME = 2000;
	/** 判断是否是第一次运行 */
	private SharedPreferences mPreferences;
	/** 版本信息的实体类 */
	private VersionBean mVersionBean;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mPreferences = getSharedPreferences("StartPage", MODE_PRIVATE);
		initVersion();
	}
	
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(TIME);
				if(mPreferences.getString("one", "").equals("two")){
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(intent);
					finish();
				}else{
					Intent intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
					startActivity(intent);
					Editor editor = mPreferences.edit();
					editor.putString("one", "two");
					editor.commit();
					finish();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(mPreferences.getString("one", "").equals("two")){
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(intent);
					finish();
				}else{
					Intent intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
					startActivity(intent);
					Editor editor = mPreferences.edit();
					editor.putString("one", "two");
					editor.commit();
					finish();
				}
			}
			if(mPreferences.getString("one", "").equals("two")){
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				finish();
			}else{
				Intent intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
				startActivity(intent);
				Editor editor = mPreferences.edit();
				editor.putString("one", "two");
				editor.commit();
				finish();
			}
		}
	};
	/**
	 * 版本信息
	 */
	private void initVersion(){
		IUpdateUI ui = new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if(allData == null){
					Toast.makeText(StartPageActivity.this, "网络不给力", Toast.LENGTH_SHORT).show();
					return ;
				}
				LogAh.e("版本信息：："+allData.toString());
				mVersionBean = (VersionBean) allData;
				if(mVersionBean.getCode().equals("0")){
					int oldVersion = Integer.parseInt(getVersionName().replace(".", ""));
					int newVersion = Integer.parseInt(mVersionBean.getNumber().replace(".", ""));
					if(oldVersion >= newVersion){
//						Toast.makeText(getApplicationContext(), "版本是最新"+oldVersion, Toast.LENGTH_SHORT).show();
						new Thread(runnable).start();
					}else if(oldVersion < newVersion){
						CallPhoneUtils.showDialogVersion(StartPageActivity.this, mVersionBean.getUrl());
//						Toast.makeText(getApplicationContext(), "有最新版本"+oldVersion, Toast.LENGTH_SHORT).show();
					}
					
				}
			}
		};

		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(this, ui, true,
				RequestBaseMapZGQ.getGuanggao());
		asyncZGQ.execute(EFaceTypeZGQ.URL_VERSION);
	}
	/**
	 * 获取当前应用的版本号
	 */
	private String getVersionName(){  
		// 获取packagemanager的实例  
		PackageManager packageManager = getPackageManager();  
		// getPackageName()是你当前类的包名，0代表是获取版本信息  
		PackageInfo packInfo;
		String version = "";
		try {
			packInfo = packageManager.getPackageInfo("com.aohuan.babycomming",0);
			version = packInfo.versionName;  
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return version;  
	} 
}
