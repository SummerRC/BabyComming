package com.aohuan.utils.propriety;

import com.aohuan.bean.UserBean;
import com.aohuan.bean.UserBean.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginHelper {

	public static final String ISLOGIN = "ISLOGIN";
	public static final String ID = "id";
	public static final String USERNAME = "username";
	public static final String IMAGE = "image";

	public static void saveLogin(Context mContext, String id, String username,
			String image) {
		SharedPreferences preferences = mContext.getSharedPreferences(
				"userInfo", mContext.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(ID, id);
		editor.putString(USERNAME, username);
		editor.putString(IMAGE, image);
		editor.putBoolean(ISLOGIN, true);
		editor.commit();
	}

	public static void logOut(Context mContext) {
		SharedPreferences preferences = mContext.getSharedPreferences(
				"userInfo", mContext.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(ID, "");
		editor.putString(USERNAME, "");
		editor.putString(IMAGE, "");
		editor.putBoolean(ISLOGIN, false);
		editor.commit();
	}

	public static User getUser(Context mContext){
		SharedPreferences preferences = mContext.getSharedPreferences("userInfo", mContext.MODE_PRIVATE);
		boolean islogin = preferences.getBoolean(ISLOGIN, false);
		if(!islogin){
			return null;
		}
		UserBean.User user = new UserBean().new User();
		user.setId(preferences.getString(ID, ""));
		user.setUsername(preferences.getString(USERNAME, ""));
		user.setImage(preferences.getString(IMAGE, ""));
		return user;
	}
}
