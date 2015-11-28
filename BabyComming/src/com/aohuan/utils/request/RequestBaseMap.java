package com.aohuan.utils.request;





public class RequestBaseMap {
//	/** 注册 **/
//	public static BaseMap getRegister(String remark, String passWord,
//			String phone) {
//		BaseMap bm = new BaseMap();
//		bm.put("smscode", remark);
//		bm.put("password", passWord);
//		bm.put("mobile", phone);
//		return bm;
//	}
	
	/**套餐详情**/
	public static BaseMap getTaoCanDetail(String team_id,String user_id){
		BaseMap bm = new BaseMap();
		bm.put("team_id", team_id);
		bm.put("user_id", user_id);
		return bm;
	}
	/**商家详情**/
	public static BaseMap getStoreDetail(String partner_id,String page){
		BaseMap bm = new BaseMap();
		bm.put("partner_id", partner_id);
		bm.put("page", page);
		return bm;
	}
	
	public static BaseMap getCollect(String team_id,String userid){
		BaseMap bm = new BaseMap();
		bm.put("user_id", userid);
		bm.put("team_id", team_id);		
		return bm;
	}
	
	public static BaseMap getOrderId(String userid,String pay_id,String price,String mobile
			,String address,String zipcode,String realname,String fara,String team_id,String quantity){
		BaseMap bm = new BaseMap();
		bm.put("user_id", userid);
		bm.put("pay_id", pay_id);
		bm.put("price", price);
		bm.put("mobile", mobile);
		bm.put("address", address);
		bm.put("zipcode", zipcode);
		bm.put("realname", realname);
		bm.put("fara", fara);
		bm.put("team_id", team_id);
		bm.put("quantity", quantity);
		return bm;
	}
	
	public static BaseMap getPhotoGoods(String sub_id,String city_id,String attr_id,String page){
		BaseMap bm = new BaseMap();
		bm.put("sub_id", sub_id);
		bm.put("city_id", city_id);
		bm.put("attr_id", attr_id);
		bm.put("page", page);
		return bm;
	}
	
	public static BaseMap getPhotoStore(String price,String city_id,String pingfen,String page){
		BaseMap bm = new BaseMap();
		bm.put("city_id", city_id);
		bm.put("price", price);
		bm.put("pingfen", pingfen);
		bm.put("page", page);
		return bm;
	}
	
	public static BaseMap getCollect(String user_id){
		BaseMap bm = new BaseMap();
		bm.put("user_id", user_id);
		return bm;
	}

	public static BaseMap getModify(String user_id,String pwd,String new_pwd){
		BaseMap bm = new BaseMap();
		bm.put("user_id", user_id);
		bm.put("password", pwd);
		bm.put("new_password", new_pwd);
		return bm;
	}
	
	public static BaseMap getPwdPro(String user_id,String result,String Question_id){
		BaseMap bm = new BaseMap();
		bm.put("user_id", user_id);
		bm.put("result", result);
		bm.put("question_id", Question_id);
		return bm;
	}
	
	public static BaseMap getFindPwd(String username,String result,String Question_id){
		BaseMap bm = new BaseMap();
		bm.put("username", username);
		bm.put("result", result);
		bm.put("question_id", Question_id);
		return bm;
	}
	
	public static BaseMap getPwd(String username,String password){
		BaseMap bm = new BaseMap();
		bm.put("password", password);
		bm.put("username", username);
		return bm;
	}
	
}
