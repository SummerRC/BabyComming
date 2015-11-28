package com.aohuan.utils.request;

public class RequestBaseMapLH {

	/** 注册 **/
	public static BaseMap getRegister(String username, String password) {
		BaseMap bm = new BaseMap();
		bm.put("username", username);
		bm.put("password", password);
		return bm;
	}
	
	
	/** 登录 **/
	public static BaseMap getLogin(String username, String password) {
		BaseMap bm = new BaseMap();
		bm.put("username", username);
		bm.put("password", password);
		return bm;
	}
	
	/** 删除 **/
	public static BaseMap getId(String order_id, String user_id) {
		BaseMap bm = new BaseMap();
		bm.put("order_id", order_id);
		bm.put("user_id", user_id);
		return bm;
	}
	
}
