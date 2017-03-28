package com.simple.framework.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.framework.bean.FileParam;
import com.simple.framework.bean.FormParam;
import com.simple.framework.bean.Param;
import com.simple.framework.util.CollectionUtil;
import com.simple.framework.util.FileUtil;
import com.simple.framework.util.StreamUtil;
import com.simple.framework.util.StringUtil;

/**
 * 
 * <p> Description: 上传文件帮助类 </p>
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
public final class UploadHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);
	
	private static ServletFileUpload servletFileUpload;
	
	public static void init(ServletContext servletContext){
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));
		int uploadLimit = ConfigHelper.getAppUploadLimit();
		if(uploadLimit != 0){
			servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
		}
	}
	
	/**
	 * 
	 * isMutipart
	 * 方法描述: 判断请求是否为multipart类型
	 * 逻辑描述: 
	 * @param request
	 * @return
	 * @since Ver 1.00
	 */
	public static boolean isMutipart(HttpServletRequest request){
		return ServletFileUpload.isMultipartContent(request);
	}
	
	/**
	 * 
	 * createParam
	 * 方法描述: 创建请求对象
	 * 逻辑描述: 
	 * @param request
	 * @return
	 * @throws FileUploadException 
	 * @throws IOException 
	 * @since Ver 1.00
	 */
	public static Param createParam(HttpServletRequest request) {
		List<FormParam> formParamList = new ArrayList<FormParam>();
		List<FileParam> fileParamList = new ArrayList<FileParam>();
		try {
			Map<String,List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);
			if(CollectionUtil.isNotEmpty(fileItemListMap)){
				for(Map.Entry<String, List<FileItem>> entry : fileItemListMap.entrySet()){
					String fieldName = entry.getKey();
					List<FileItem> fileItemList = entry.getValue();
					if(CollectionUtil.isNotEmpty(fileItemList)){
						for(FileItem fileItem : fileItemList){
							if(fileItem.isFormField()){
								String fieldValue = fileItem.getString("UTF-8");
								formParamList.add(new FormParam(fieldName, fieldValue));
							}else{
								String fileName = FileUtil.getRealFileName(new String(fileItem.getName().getBytes(),"UTF-8"));
								if(StringUtil.isNotEmpty(fileName)){
									long fileSize = fileItem.getSize();
									String contentType = fileItem.getContentType();
									InputStream inputStream = fileItem.getInputStream();
									fileParamList.add(new FileParam(fieldName, fileName, fileSize, contentType, inputStream));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("creat param error..",e);
			throw new RuntimeException(e);
		}
		return new Param(formParamList, fileParamList);
	}
	
	/**
	 * 
	 * uploadFile
	 * 方法描述: 上传文件
	 * 逻辑描述: 
	 * @param basePath
	 * @param fileParam
	 * @since Ver 1.00
	 */
	public static void uploadFile(String basePath,FileParam fileParam){
		try {
			if(fileParam != null){
				String filePath = basePath + fileParam.getFileName();
				FileUtil.createFile(filePath);
				InputStream inputStream = new BufferedInputStream(fileParam.getInputStream());
				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
				StreamUtil.copyStream(inputStream,outputStream);
			}
		} catch (Exception e) {
			LOGGER.error("upload file error ...",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * uploadFile
	 * 方法描述: 批量上传文件 
	 * 逻辑描述: 
	 * @param basePath
	 * @param fileParamList
	 * @since Ver 1.00
	 */
	public static void uploadFile(String basePath,List<FileParam> fileParamList){
		try {
			if(CollectionUtil.isNotEmpty(fileParamList)){
				for(FileParam fileParam : fileParamList){
					uploadFile(basePath, fileParam);
				}
			}
		} catch (Exception e) {
			LOGGER.error("upload file error ...",e);
			throw new RuntimeException(e);
		}
	}
	
}
