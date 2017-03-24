package com.simple.framework.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.omg.CosNaming.IstringHelper;

/**
 * 
 * <p> Description: 集合工具类 </p>
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
public final class CollectionUtil {
	
	public static boolean isEmpty(Collection<?> collection){
		return CollectionUtils.isEmpty(collection);
	}
	
	public static boolean isNotEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}
	
	public static boolean isEmpty(Map<?,?> map){
		return MapUtils.isEmpty(map);
	}
	
	public static boolean isNotEmpty(Map<?,?> map){
		return !isEmpty(map);
	}
}
