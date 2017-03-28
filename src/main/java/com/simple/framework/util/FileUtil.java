package com.simple.framework.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p> Description: 文件操作工具类 </p>
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
public final class FileUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * 
	 * getRealFileName
	 * 方法描述: 获取真实文件名（自动去掉文件路径）
	 * 逻辑描述: 
	 * @param fileName
	 * @return
	 * @since Ver 1.00
	 */
	public static String getRealFileName(String fileName){
		return FilenameUtils.getName(fileName);
	}
	
	/**
	 * 
	 * createFile
	 * 方法描述: 创建文件
	 * 逻辑描述: 
	 * @param filePath
	 * @return
	 * @since Ver 1.00
	 */
	public static File createFile(String filePath){
		File file;
		try {
			file = new File(filePath);
			File parentDir = file.getParentFile();
			if(!parentDir.exists()){
				FileUtils.forceMkdir(parentDir);
			}
		} catch (Exception e) {
			LOGGER.error("create file error .",e);
			throw new RuntimeException(e);
		}
		return file;
	}
	
	
}
