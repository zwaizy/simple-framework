package com.simple.framework.helper;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.framework.annotation.Aspect;
import com.simple.framework.annotation.Service;
import com.simple.framework.annotation.Transaction;
import com.simple.framework.proxy.Proxy;
import com.simple.framework.proxy.ProxyManager;
import com.simple.framework.proxy.TransactionProxy;

/**
 * 
 * <p> Description: AOP助手类 </p>
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
public final class AopHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);
	
	static {
		try {
			Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();
			Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxyMap);
			for(Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()){
				Class<?> targetClass = targetEntry.getKey();
				List<Proxy> proxyList = targetEntry.getValue();
				Object proxy = ProxyManager.createProxy(targetClass, proxyList);
				BeanHelper.setBeanMap(targetClass, proxy);
 			}
		} catch (Exception e) {
			LOGGER.error("aop error....",e);
		}
		
	}

	private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		Class<? extends Annotation> annotationClass = aspect.value();
		if(annotationClass != null && !annotationClass.equals(Aspect.class)){
			classSet.addAll(ClassHelper.getClassSetByAnnotation(annotationClass));
		}
		return classSet;
	}
	
	private static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception{
		Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<Class<?>,Set<Class<?>>>();
		addAspectProxy(proxyMap);
		addTransactionProxy(proxyMap);
		return proxyMap;
	}
	
	private static void addAspectProxy(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
		Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(Aspect.class);
		for(Class<?> clz : proxyClassSet){
			if(clz.isAnnotationPresent(Aspect.class)){
				Aspect aspect = clz.getAnnotation(Aspect.class);
				Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
				proxyMap.put(clz, targetClassSet);
			}
		}
	}
	
	private static void addTransactionProxy(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
		Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
		proxyMap.put(TransactionProxy.class, serviceClassSet);
	}
	
	private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
		Map<Class<?>,List<Proxy>> targetMap = new HashMap<Class<?>,List<Proxy>>();
		for(Map.Entry<Class<?>,Set<Class<?>>> proxyEntry : proxyMap.entrySet()){
			Class<?> proxyClass = proxyEntry.getKey();
			Set<Class<?>> targetClassSet = proxyEntry.getValue();
			for(Class<?> targetClass : targetClassSet){
				Proxy proxy = (Proxy) proxyClass.newInstance();
				if(targetMap.containsKey(targetClass)){
					targetMap.get(targetClass).add(proxy);
				}else{
					List<Proxy> proxyList = new ArrayList<Proxy>();
					proxyList.add(proxy);
					targetMap.put(targetClass, proxyList);
				}
			}
		}
		return targetMap;
	} 
	
}
