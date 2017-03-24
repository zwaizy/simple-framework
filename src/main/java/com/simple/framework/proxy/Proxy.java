package com.simple.framework.proxy;

/**
 * 
 * <p> Description: 代理接口 </p>
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
public interface Proxy {
	
	/**
	 * 
	 * doProxy
	 * 方法描述: 执行链式代理 
	 * 逻辑描述: 
	 * @param proxyChain
	 * @return
	 * @throws Throwable
	 * @since Ver 1.00
	 */
	Object doProxy(ProxyChain proxyChain) throws Throwable;

}
