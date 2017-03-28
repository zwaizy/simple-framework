package com.simple.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * <p> Description: String处理工具类 </p>
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
public class StringUtil {
	
	public static final String SEPARATOR = String.valueOf((char)29); 
	
	public static Boolean isNotEmpty(String str){
		return !isEmpty(str);
	}

	public static Boolean isEmpty(String str){
		if(str != null){
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}
	
	public static String[] splitString(String str,String separatorChar){
		return StringUtils.split(str, separatorChar);
	}
	
}
