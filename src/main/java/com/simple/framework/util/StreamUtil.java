package com.simple.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p> Description: 流操作工具类 </p>
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
public final class StreamUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);
	
	/**
	 * 
	 * copyStream
	 * 方法描述: 将输入流复制到输出流 
	 * 逻辑描述: 
	 * @param inputStream
	 * @param outputStream
	 * @since Ver 1.00
	 */
	public static void copyStream(InputStream inputStream,OutputStream outputStream){
		try {
			byte[] by = new byte[5 * 1024];
			int len = 0;
			while((len = inputStream.read()) != -1){
				outputStream.write(by, 0, len);
				outputStream.flush();
			}
		} catch (Exception e) {
			LOGGER.error("copy stream error .",e);
			throw new RuntimeException(e);
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				LOGGER.error("close stream error .",e);
			}
		}
	}
	
	public static String getString(InputStream in){
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line ;
		try {
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
		} catch (IOException e) {
			LOGGER.error("get string error..",e);
			throw new RuntimeException(e);
		} finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
