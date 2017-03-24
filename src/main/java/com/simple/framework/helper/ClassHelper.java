package com.simple.framework.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import com.simple.framework.annotation.Controller;
import com.simple.framework.annotation.Service;
import com.simple.framework.util.ClassUtil;

/**
 * 
 * <p> Description: 类操作助手类 </p>
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
public final class ClassHelper {

	/**
	 * 定义集合类，用于存放加载的类
	 */
	private static final Set<Class<?>> CLASS_SET;
	
	static {
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	/**
	 * 
	 * getControllerSet
	 * 方法描述: 获取应用包名下所有Controller类 
	 * 逻辑描述: 
	 * @return
	 * @since Ver 1.00
	 */
	public static Set<Class<?>> getControllerSet(){
		return getClassSet(Controller.class);
	}
	
	/**
	 * 
	 * getServiceSet
	 * 方法描述: 获取应用包名下所有Service类 
	 * 逻辑描述: 
	 * @return
	 * @since Ver 1.00
	 */
	public static Set<Class<?>> getServiceSet(){
		return getClassSet(Service.class);
	}
	
	/**
	 * 
	 * getBeanSet
	 * 方法描述: 获取应用包名下所有bean类 
	 * 逻辑描述: 
	 * @return
	 * @since Ver 1.00
	 */
	public static Set<Class<?>> getBeanSet(){
		Set<Class<?>> beanSet = new HashSet<Class<?>>();
		beanSet.addAll(getControllerSet());
		beanSet.addAll(getServiceSet());
		return beanSet;
	}
	
	private static Set<Class<?>> getClassSet(Class<? extends Annotation> clazzType){
		Set<Class<?>> controllerSet = new HashSet<Class<?>>();
		for(Class<?> clazz : CLASS_SET){
			if(clazz.isAnnotationPresent(clazzType)){
				controllerSet.add(clazz);
			}
		}
		return controllerSet;
	}
	
	/**
	 * 
	 * getClassSetBySuper
	 * 方法描述: 获取应用包名下某父类（或接口）的所有子类（或实现类）
	 * 逻辑描述: 
	 * @return
	 * @since Ver 1.00
	 */
	public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET){
			if(superClass.isAssignableFrom(cls) && !superClass.equals(cls)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 
	 * getClassSetByAnnotation
	 * 方法描述: 获取应该包名下带有某注解的所有类
	 * 逻辑描述: 
	 * @param cls
	 * @return
	 * @since Ver 1.00
	 */
	public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> clszz){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET){
			if(cls.isAnnotationPresent(clszz)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
}
