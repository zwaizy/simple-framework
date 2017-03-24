package com.simple.framework.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.framework.config.ConfigConstant;

public final class PropUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropUtil.class);

	public static Properties loadProps(String configFile){
		Properties prop = null;
		InputStream in = null; 
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(ConfigConstant.CONFIG_FILE);
			if(in == null){
				throw new FileNotFoundException(configFile + "file is not found");
			}
			prop = new Properties();
			prop.load(in);
		} catch (IOException e) {
			LOGGER.error("load properties error ..", e);
			throw new RuntimeException(e);
		} finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					LOGGER.error("close input stream error ..", e);
				}
			}
		}
		return prop;
	}

	public static Integer getInt(Properties prop,String key) {
		return getInt(prop, key ,0);
	}
	
	public static Integer getInt(Properties prop,String key,Integer defaultValue) {
		Integer value = defaultValue;
		if(prop.containsKey(key)){
			value = CastUtil.castInt(prop.getProperty(key));
		}
		return value;
	}

	public static String getString(Properties prop,String key) {
		return getString(prop, key, "");
	}
	
	public static String getString(Properties prop, String key, String defaultValue) {
		String value = defaultValue;
		if(prop.containsKey(key)){
			value = prop.getProperty(key);
		}
		return value;
	}
	
	public static Boolean getBoolean(Properties prop,String key){
		return getBoolean(prop, key ,false);
	}
	
	public static Boolean getBoolean(Properties prop,String key ,Boolean defaultBoolean){
		Boolean value = defaultBoolean;
		if(prop.containsKey(key)){
			value = CastUtil.castBoolean(prop.getProperty(key));
		}
		return value;
	}
	
	public static Long getLong(Properties prop,String key){
		return getLong(prop,key,0L);
	}

	public static Long getLong(Properties prop,String key,Long defaultLongValue){
		Long value = defaultLongValue;
		if(prop.containsKey(key)){
			value = CastUtil.castLong(prop.getProperty(key));
		}
		return value;
	}
}
