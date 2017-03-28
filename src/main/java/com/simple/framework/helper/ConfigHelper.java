package com.simple.framework.helper;

import java.util.Properties;

import com.simple.framework.config.ConfigConstant;
import com.simple.framework.util.PropUtil;

/**
 * 
 * <p> Description: 属性文件助手类 </p>
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
public final class ConfigHelper {

	private static final Properties CONFIG_PROP = PropUtil.loadProps(ConfigConstant.CONFIG_FILE);
	
	public static String getJDBCDriver(){
		return PropUtil.getString(CONFIG_PROP, ConfigConstant.JDBC_DRIVER);
	}
	
	public static String getJDBCUrl(){
		return PropUtil.getString(CONFIG_PROP, ConfigConstant.JDBC_URL);
	}
	
	public static String getJDBCUsername(){
		return PropUtil.getString(CONFIG_PROP, ConfigConstant.JDBC_USERNAME);
	}
	
	public static String getJDBCPassword(){
		return PropUtil.getString(CONFIG_PROP, ConfigConstant.JDBC_PASSWORD);
	}
	
	public static String getAppBasePackage(){
		return PropUtil.getString(CONFIG_PROP, ConfigConstant.APP_BASE_PACKAGE);
	}
	
	public static String getAppJSPPath(){
		return PropUtil.getString(CONFIG_PROP, ConfigConstant.APP_JSP_PATH ,"/WEB-INF/view/");
	}
	
	public static String getAppAssetPath(){
		return PropUtil.getString(CONFIG_PROP, ConfigConstant.APP_ASSET_PATH,"/asset/");
	}
	
	public static int getAppUploadLimit(){
		return PropUtil.getInt(CONFIG_PROP, ConfigConstant.APP_UPLOAD_LIMIT, 10);
	}
}
