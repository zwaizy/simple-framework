package com.simple.framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.fabric.xmlrpc.base.Data;
import com.mysql.jdbc.DatabaseMetaData;
import com.simple.framework.annotation.Transaction;
import com.simple.framework.helper.DatabaseHelper;

/**
 * 
 * <p>
 * Description: 事务代理
 * </p>
 * 
 * @Author Zhanwei
 * @Date [2017年3月24日]
 * @Version V1.0
 * @修改记录
 * 
 *       <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		zhanwei	2017年3月24日	新建文件.
 * 
 *       </pre>
 */
public class TransactionProxy implements Proxy{

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);
	
	private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
		@Override
		protected Boolean initialValue() {
			return false;
		}
	};

	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object result ;
		boolean flag = FLAG_HOLDER.get();
		Method method = proxyChain.getTargetMethod();
		if(!flag && method.isAnnotationPresent(Transaction.class)){
			FLAG_HOLDER.set(true);
			try {
				DatabaseHelper.beginTransaction();
				LOGGER.debug("begin transaction");
				result = proxyChain.doProxyChain();
				DatabaseHelper.commitTransaction();
				LOGGER.debug("commit transaction");
			} catch (Exception e) {
				DatabaseHelper.rollbackTransaction();
				LOGGER.debug("rollback transaction");
				throw e;
			} finally {
				FLAG_HOLDER.remove();
			}
		}else{
			result = proxyChain.doProxyChain();
		}
		return result;
	}
	
	

}
