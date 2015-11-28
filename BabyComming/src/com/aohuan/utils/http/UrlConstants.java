package com.aohuan.utils.http;

import java.util.HashMap;
import java.util.Map;


public class UrlConstants {
	
	private  static Map<Integer , EFaceType> storeMap;
	
	private static Map<Integer, EFaceType> taoCanMap;
	
	private static Map<Integer, EFaceType> typeMap;
	static{				
		storeMap=new HashMap<Integer, EFaceType>();
		storeMap.put(1, EFaceType.URL_PHOTOGRA_STORE);
		storeMap.put(2, EFaceType.URL_CANFU_STORE);
		storeMap.put(3, EFaceType.URL_TAIMAO_STORE);
		
		taoCanMap=new HashMap<Integer, EFaceType>();
		taoCanMap.put(1, EFaceType.URL_PHOTOGRA_GOODS);
		taoCanMap.put(2, EFaceType.URL_CANFU_TAOCAN);
		taoCanMap.put(3, EFaceType.URL_TAIMAO_TAOCAN);
		
		typeMap=new HashMap<Integer, EFaceType>();
		typeMap.put(1, EFaceType.URL_PHOTOGRA_TYPE);
		typeMap.put(2, EFaceType.URL_CHANFU_TYPE);
		typeMap.put(3, EFaceType.URL_TAIMAO_TYPE);
	}
	
	public  static EFaceType getStoreEFaceType(int type){
		
		return storeMap.get(type);	
	}
	
	public static EFaceType getTaoCanEFaceType(int type){
		
		return taoCanMap.get(type);	
	}
	
	public static EFaceType getTypeEFaceType(int type){
		
		return typeMap.get(type);
	}
	
	private UrlConstants() throws UnsupportedOperationException{
		throw new UnsupportedOperationException("cannot be instantiated");
	}
	
	public static final String URL = "http://baoer.com.h001.360sheji.cn/";
	
	public static final String PATH_STRING="http://baoer.com.h001.360sheji.cn/apphtml/app_teamxiangqing.php?team_id=";
	//public static boolean ISLOGIN=false;
	
	public static String SELECT_DANGQIAN = "frag";
}
