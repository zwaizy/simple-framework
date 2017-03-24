package com.simple.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 
 * <p> Description: 数组工具类 </p>
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
public final class ArrayUtil {

	public static Boolean isEmpty(Object[] t){
		return ArrayUtils.isEmpty(t);
	}
	
	public static Boolean isNotEmpty(Object[] t){
		return !isEmpty(t);
	}
	
}
