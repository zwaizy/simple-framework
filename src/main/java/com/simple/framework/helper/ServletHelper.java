package com.simple.framework.helper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p> Description: Servlet 助手类 </p>
 * @Author Zhanwei
 * @Date [2017年3月27日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		zhanwei	2017年3月27日	新建文件.
 * 
 * </pre>
 */
public final class ServletHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);
	
	private static final ThreadLocal<ServletHelper> SERVLET_HELPER_HOLDER = new ThreadLocal<ServletHelper>();
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private ServletHelper(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	/**
	 * 
	 * init
	 * 方法描述: 初始化 
	 * 逻辑描述: 
	 * @param request
	 * @param response
	 * @since Ver 1.00
	 */
	public static void init(HttpServletRequest request,HttpServletResponse response){
		SERVLET_HELPER_HOLDER.set(new ServletHelper(request,response));
	}
	
	/**
	 * 
	 * destory
	 * 方法描述: destory
	 * 逻辑描述: 
	 * @since Ver 1.00
	 */
	public static void destory(){
		SERVLET_HELPER_HOLDER.remove();
	}

	/**
	 * 
	 * getRequest
	 * 方法描述: 获取Request 
	 * 逻辑描述: 
	 * @return
	 * @since Ver 1.00
	 */
	public static HttpServletRequest getRequest(){
		return SERVLET_HELPER_HOLDER.get().request;
	}
	
	public static HttpServletResponse getResponse(){
		return SERVLET_HELPER_HOLDER.get().response;
	}
	
	public static HttpSession getSession(){
		return getRequest().getSession();
	}
	
	public static ServletContext getServletContext(){
		return getRequest().getServletContext();
	}
	
	public static void setRequestAttribute(String key,Object value){
		getRequest().setAttribute(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getAttribute(String key){
		return (T) getRequest().getAttribute(key);
	}
	
	public static void removeRequestAttribute(String key){
		getRequest().removeAttribute(key);
	}
	
	public static void sendRedirct(String location){
		try {
			getResponse().sendRedirect(getRequest().getContextPath() + location);
		} catch (Exception e) {
			LOGGER.error("redirct error...",e);
		}
	}
	
	public static void setSessionAttribute(String key,Object value){
		getSession().setAttribute(key, value);
	} 
	
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttribute(String key){
		return (T) getSession().getAttribute(key);
	}
	
	public static void removeSessionAttribute(String key){
		getSession().removeAttribute(key);
	}
	
	public static void invalidateSession(){
		getSession().invalidate();
	}
}
