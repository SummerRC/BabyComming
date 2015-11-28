package com.aohuan.utils.common;


import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;

/**
 * json与bean互转工具类
 * 
 */

public class JsonUtil {

	private static ObjectMapper om = null;

	static {
		om = new ObjectMapper();
	}

	/**
	 * @return String
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	public static String toJsonString(Object object)
			throws JsonGenerationException, JsonMappingException, IOException {
		return om.writeValueAsString(object);
	}

	/**
	 * 
	 * @author ruanxf
	 * @date 2011-8-11 下午4:37:59
	 * @version V1.0
	 * @return
	 * @return ObjectMapper
	 */
	public static ObjectMapper getObjectMapper() {

		return om;
	}

	/**
	 * @param json
	 * @param clazz
	 * @return
	 * @return T
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static <T> T toObjectByJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		return JSON.parseObject(json, clazz)/* om.readValue(json, clazz) */;

	}

	
	public static <T> List<T> parseJson2List(String json,Class<T> clazz){
		
		List<T> list=null;
		
		list=JSON.parseArray(json, clazz);
		
		return list;
		
	} 
	
	public static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * 使用jkson转换json到对象
	 * 
	 * @param obj
	 *            json串格式
	 * @param cl
	 *            需要转换对象的class
	 * @return
	 */
	public static Object json2Object(Object obj, Class<?> cl) {
		if (obj == null) {
			return null;
		}
		try {
			return mapper.readValue(obj.toString(), cl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对象转json
	 * 
	 * @param obj
	 * @return
	 */
	public static String object2JSON(Object obj) {
		if (obj == null) {
			return null;
		}
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

