package com.simple.framework.proxy;

import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p> Description: 切面代理 </p>
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
public abstract class AspectProxy implements Proxy{

	private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);
	
	public final Object doProxy(ProxyChain proxyChain) throws Throwable{
		Object result ;
		Class<?> targetClass = proxyChain.getTargetClass();
		Method targetMethod = proxyChain.getTargetMethod();
		Object[] methodParams = proxyChain.getMethodParams();
		
		begin();
		try {
			if(intercept(targetClass,targetMethod,methodParams)){
				before(targetClass,targetMethod,methodParams);
				result = proxyChain.doProxyChain();
				after(targetClass,targetMethod,methodParams);
			}else{
				result = proxyChain.doProxyChain();
			}
		} catch (Exception e) {
			LOGGER.error("proxy error..",e);
			error(targetClass,targetMethod,methodParams,e);
			throw e;
		} finally {
			end();
		}
		
		return result;
	}
	
	
	private void end() {
		
	}


	private void error(Class<?> targetClass, Method targetMethod, Object[] methodParams, Exception e) {
		
	}


	public void after(Class<?> targetClass, Method targetMethod, Object[] methodParams) {
		
	}


	public void before(Class<?> targetClass, Method targetMethod, Object[] methodParams) {
		
	}


	public boolean intercept(Class<?> targetClass, Method targetMethod, Object[] methodParams) {
		return true;
	}


	public void begin(){}
	
	
}
