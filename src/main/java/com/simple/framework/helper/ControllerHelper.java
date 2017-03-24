package com.simple.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import com.simple.framework.annotation.Action;
import com.simple.framework.bean.Handler;
import com.simple.framework.bean.Request;
import com.simple.framework.util.ArrayUtil;
import com.simple.framework.util.CollectionUtil;

/**
 * 
 * <p> Description: Controller助手类 </p>
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
public final class ControllerHelper {
	
	private static final Map<Request,Handler> ACTION_MAP = new HashMap<Request,Handler>();
	
	static {
		//获取所有的controller类
		Set<Class<?>> controllerSet = ClassHelper.getControllerSet();
		if(CollectionUtil.isNotEmpty(controllerSet)){
			for(Class<?> clazz : controllerSet){
				Method[] methods = clazz.getDeclaredMethods();
				if(ArrayUtil.isNotEmpty(methods)){
					for(Method method : methods){
						//判断当前方法是否带有Action注解
						if(method.isAnnotationPresent(Action.class)){
							//从action注解中提取URL映射规则
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							//验证URL映射规则
							if(mapping.matches("\\w+:/\\w*")){
								String[] array = mapping.split(":");
								if(ArrayUtil.isNotEmpty(array) && array.length == 2){
									//获取请求方法和请求路径
									String requestMethod = array[0];
									String requestUrl = array[1];
									Request request = new Request(requestMethod,requestUrl);
									Handler handler = new Handler(clazz,method);
									//初始化ActionMap
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * getHandler
	 * 方法描述: 获取handler 
	 * 逻辑描述: 
	 * @param requestMethod
	 * @param requestUrl
	 * @return
	 * @since Ver 1.00
	 */
	public static Handler getHandler(String requestMethod,String requestUrl){
		return ACTION_MAP.get(new Request(requestMethod,requestUrl));
	}
}
