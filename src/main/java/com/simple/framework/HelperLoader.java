package com.simple.framework;

import com.simple.framework.helper.AopHelper;
import com.simple.framework.helper.BeanHelper;
import com.simple.framework.helper.ClassHelper;
import com.simple.framework.helper.ControllerHelper;
import com.simple.framework.helper.IOCHelper;
import com.simple.framework.util.ClassUtil;

/**
 * 
 * <p> Description: 加载相应的helper类 </p>
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
public class HelperLoader {
	
	public static void init(){
		Class<?>[] classList = {ClassHelper.class,BeanHelper.class,AopHelper.class,IOCHelper.class,ControllerHelper.class};
		
		for(Class<?> clazz : classList){
			ClassUtil.loadClass(clazz.getName(), false);
		}
		
	}

}
