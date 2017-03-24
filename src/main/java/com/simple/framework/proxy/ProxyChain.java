package com.simple.framework.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * <p> Description: 代理链 </p>
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
public class ProxyChain {
	
	private final Class<?> targetClass;
	private final Object targetObject;
	private final Method targetMethod;
	private final MethodProxy methodProxy;
	private final Object[] methodParams;
	private int proxyIndex = 0;
	
	private List<Proxy> proxyList = new ArrayList<Proxy>();
	

	public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy,
			Object[] methodParams, List<Proxy> proxyList) {
		super();
		this.targetClass = targetClass;
		this.targetObject = targetObject;
		this.targetMethod = targetMethod;
		this.methodProxy = methodProxy;
		this.methodParams = methodParams;
		this.proxyList = proxyList;
	}
	
	public Object doProxyChain() throws Throwable{
		Object methodResult;
		if(proxyIndex < proxyList.size()){
			methodResult = proxyList.get(proxyIndex++).doProxy(this);
		}else{
			methodResult = methodProxy.invokeSuper(targetObject, methodParams);
		}
		return methodResult;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public Object getTargetObject() {
		return targetObject;
	}

	public Method getTargetMethod() {
		return targetMethod;
	}

	public MethodProxy getMethodProxy() {
		return methodProxy;
	}

	public Object[] getMethodParams() {
		return methodParams;
	}

	
}
