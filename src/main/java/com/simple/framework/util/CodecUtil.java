package com.simple.framework.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p> Description: 编码与解码操作工具类 </p>
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
public final class CodecUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);
	
	/**
	 * 
	 * encodeURL
	 * 方法描述: 将URL编码 
	 * 逻辑描述: 
	 * @param resource
	 * @return
	 * @since Ver 1.00
	 */
	public static String encodeURL(String resource){
		String target ;
		try {
			target = URLEncoder.encode(resource, "UTF-8");
		} catch (Exception e) {
			LOGGER.error("encode url error ...",e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
	/**
	 * 
	 * decodeURL
	 * 方法描述: 将URL解码 
	 * 逻辑描述: 
	 * @param resource
	 * @return
	 * @since Ver 1.00
	 */
	public static String decodeURL(String resource){
		String target ;
		try {
			target = URLDecoder.decode(resource, "UTF-8");
		} catch (Exception e) {
			LOGGER.error("decode url error ...",e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
	public static String md5(String source){
		return DigestUtils.md5Hex(source);
	}
}
