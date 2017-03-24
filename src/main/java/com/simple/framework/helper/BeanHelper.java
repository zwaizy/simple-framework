package com.simple.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.simple.framework.util.CollectionUtil;
import com.simple.framework.util.ReflectionUtil;

/**
 * 
 * <p> Description: Bean 助手类 </p>
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
public final class BeanHelper {
	
	/**
	 * 定义bean映射（用于存放Bean类与Bean实例的映射关系）
	 */
	private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();
	
	static {
		Set<Class<?>> classSet = ClassHelper.getBeanSet();
		if(CollectionUtil.isNotEmpty(classSet)){
			for(Class<?> clazz : classSet){
				Object obj = ReflectionUtil.newInstance(clazz);
				BEAN_MAP.put(clazz, obj);
			}
		}
	}
	
	public static void setBeanMap(Class<?> cls,Object obj){
		BEAN_MAP.put(cls, obj);
	}
	
	/**
	 * 
	 * getBeanMap
	 * 方法描述: 获取Bean映射
	 * 逻辑描述: 
	 * @return
	 * @since Ver 1.00
	 */
	public static Map<Class<?>,Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	 * 
	 * getBeanObj
	 * 方法描述: 获取Bean实例 
	 * 逻辑描述: 
	 * @return
	 * @since Ver 1.00
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeanObj(Class<T> clazz){
		if(!BEAN_MAP.containsKey(clazz)){
			throw new RuntimeException("can not get bean by class : "+ clazz);
		}
		return (T) BEAN_MAP.get(clazz);
	}

}
