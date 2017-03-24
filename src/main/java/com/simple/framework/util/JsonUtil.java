package com.simple.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * <p> Description: Json工具类 </p>
 * @Author Zhanwei
 * @Date [2017年3月24日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		zhanwei	2017年3月24日	新建文件.
 * 
 * </pre>
 */
public final class JsonUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
	
	private static final ObjectMapper MAPPER= new ObjectMapper();

	/**
	 * 
	 * toJson
	 * 方法描述: 将对象转成json
	 * 逻辑描述: 
	 * @param model
	 * @return
	 * @since Ver 1.00
	 */
	public static <T> String toJson(T model) {
		String jsonStr;
		try {
			jsonStr = MAPPER.writeValueAsString(model);
		} catch (JsonProcessingException e) {
			LOGGER.error("to json error..",e);
			throw new RuntimeException(e);
		}
		return jsonStr;
	}
	
	/**
	 * 
	 * toJson 
	 * 方法描述: 将json转成对象
	 * 逻辑描述: 
	 * @param model
	 * @return
	 * @since Ver 1.00
	 */
	public static <T> T fromJson(String json,Class<T> type) {
		T pojo;
		try {
			pojo = (T) MAPPER.readValue(json, type);
		} catch (Exception e) {
			LOGGER.error("json to object error..",e);
			throw new RuntimeException(e);
		}
		return pojo;
	}
	
}
