package com.simple.framework.proxy;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * <p> Description: 代理管理类 </p>
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
public class ProxyManager {
	
	@SuppressWarnings("unchecked")
	public static <T> T createProxy(final Class<?> targetClass,final List<Proxy> proxyList){
		return (T) Enhancer.create(targetClass, new MethodInterceptor() {
			public Object intercept(Object targetObject, Method method, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
				return new ProxyChain(targetClass, targetObject, method, methodProxy, methodParams, proxyList).doProxyChain();
			}
		});
	}

}
