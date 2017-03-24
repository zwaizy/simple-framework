package com.simple.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import com.simple.framework.annotation.Inject;
import com.simple.framework.util.ArrayUtil;
import com.simple.framework.util.CollectionUtil;
import com.simple.framework.util.ReflectionUtil;

/**
 * 
 * <p> Description: 依赖注入助手类 </p>
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
public final class IOCHelper {
	
	static {
		Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
		if(CollectionUtil.isNotEmpty(beanMap)){
			for(Map.Entry<Class<?>, Object> entry : beanMap.entrySet()){
				 Class<?> clazz = entry.getKey();
				 Object obj = entry.getValue();
				 Field[] fields = clazz.getDeclaredFields();
				 if(ArrayUtil.isNotEmpty(fields)){
					 for(Field field : fields){
						 if(field.isAnnotationPresent(Inject.class)){
							 //在 beanMap中获取beanField的实例
							 Object beanFieldInstance = beanMap.get(field.getType());
							 if(beanFieldInstance != null){
								 //通过反射初始化beanfield的值
								 ReflectionUtil.setField(obj, field, beanFieldInstance);
							 }
						 }
					 }
				 }
			}
		}
	}

}
