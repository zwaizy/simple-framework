package com.simple.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p> Description: 强制转换工具类 </p>
 * @Author Zhanwei
 * @Date [2017年3月23日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		zhanwei	2017年3月23日	新建文件.
 * 
 * </pre>
 */
public final class CastUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CastUtil.class);

	public static Integer castInt(Object obj) {
		return castInt(obj,0);
	}

	private static Integer castInt(Object obj, int defaultIntValue) {
		int value = defaultIntValue;
		if(obj != null){
			String strValue = castString(obj);
			if(StringUtil.isNotEmpty(strValue)){
				try {
					value = Integer.parseInt(strValue);
				} catch (NumberFormatException e) {
					LOGGER.error(strValue + "parse int error.. ",e);
					value = defaultIntValue;
				}
			}
		}
		return value;
	}

	public static String castString(Object obj) {
		return castString(obj,"");
	}

	private static String castString(Object obj, String defaultStrValue) {
		return obj == null ? defaultStrValue : String.valueOf(obj);
	}

	public static Boolean castBoolean(Object obj) {
		return castBoolean(obj,false);
	}

	private static Boolean castBoolean(Object obj, boolean defaultBoo) {
		Boolean value = defaultBoo;
		if(obj != null){
			value = Boolean.parseBoolean(castString(obj));
		}
		return value;
	}

	public static Long castLong(Object obj) {
		return castLong(obj,0L);
	}

	private static Long castLong(Object obj, long defaultValue) {
		Long value = defaultValue;
		if(obj != null){
			String strValue = castString(obj);
			if(StringUtil.isNotEmpty(strValue)){
				try {
					value = Long.parseLong(strValue);
				} catch (NumberFormatException e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}
	

	
	
}
