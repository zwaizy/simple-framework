package com.simple.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p> Description: 反射工具类 </p>
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
public final class ReflectionUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);
	
	/**
	 * 
	 * newInstance
	 * 方法描述: 创建实例
	 * 逻辑描述: 
	 * @param clazz
	 * @return
	 * @since Ver 1.00
	 */
	public static Object newInstance(Class<?> clazz){
		Object instance;
		try {
			instance = clazz.newInstance();
		} catch (Exception e) {
			LOGGER.error("new instance error..",e);
			throw new RuntimeException(e);
		}
		return instance;
	}
	
	/**
	 * 
	 * invokeMethod
	 * 方法描述: 调用方法 
	 * 逻辑描述: 
	 * @param obj
	 * @param method
	 * @param objects
	 * @return
	 * @since Ver 1.00
	 */
	public static Object invokeMethod(Object obj,Method method,Object...args ){
		Object result;
		try {
			method.setAccessible(true);
			result = method.invoke(obj, args);
		} catch (Exception e) {
			LOGGER.error("invoke method error ..",e);
			throw new RuntimeException(e);
		}
		return result;
	}
	
	/**
	 * 
	 * setField
	 * 方法描述: 设置成员变量的值
	 * 逻辑描述: 
	 * @param obj
	 * @param field
	 * @param value
	 * @since Ver 1.00
	 */
	public static void setField(Object obj,Field field,Object value){
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			LOGGER.error("set field error ..",e);
			throw new RuntimeException(e);
		}
	}

}
