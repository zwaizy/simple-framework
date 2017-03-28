package com.simple.framework.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.simple.framework.util.CastUtil;
import com.simple.framework.util.CollectionUtil;
import com.simple.framework.util.StringUtil;

/**
 * 
 * <p> Description: 请求参数对象 </p>
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
public class Param {
	
	private List<FormParam> formParamList;
	private List<FileParam> fileParamList;
	
	public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
		super();
		this.formParamList = formParamList;
		this.fileParamList = fileParamList;
	}
	
	public Param(List<FormParam> formParamList) {
		super();
		this.formParamList = formParamList;
	}

	/**
	 * 
	 * getFieldMap
	 * 方法描述: 获取请求参数映射 
	 * 逻辑描述: 
	 * @return
	 * @since Ver 1.00
	 */
	public Map<String,Object> getFieldMap(){
		Map<String,Object> fieldMap = new HashMap<String,Object>();
		if(CollectionUtil.isNotEmpty(formParamList)){
			for(FormParam formParam : formParamList){
				String fieldName = formParam.getFieldName();
				Object fieldValue = formParam.getFieldValue();
				if(fieldMap.containsKey(fieldName)){
					fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
				}
				fieldMap.put(fieldName, fieldValue);
			}
		}
		return fieldMap;
	}
	
	/**
	 * 
	 * getFileMap
	 * 方法描述: 获取上传文件映射 
	 * 逻辑描述: 
	 * @return
	 * @since Ver 1.00
	 */
	public Map<String,List<FileParam>> getFileMap(){
		Map<String,List<FileParam>> fileMap = new HashMap<String,List<FileParam>>();
		if(CollectionUtil.isNotEmpty(fileParamList)){
			for(FileParam fileParam : fileParamList){
				String fieldName = fileParam.getFieldName();
				List<FileParam> list ;
				if(fileMap.containsKey(fieldName)){
					list = fileMap.get(fieldName);
				}else{
					list = new ArrayList<FileParam>();
				}
				list.add(fileParam);
				fileMap.put(fieldName, list);
			}
		}
		return fileMap;
	}

	/**
	 * 
	 * getFileParams
	 * 方法描述: 获取所有上传文件 
	 * 逻辑描述: 
	 * @return
	 * @since Ver 1.00
	 */
	public List<FileParam> getFileList(String fieldName){
		return getFileMap().get(fieldName);
	}
	
	/**
	 * 
	 * getFile
	 * 方法描述: 获取唯一上传文件 
	 * 逻辑描述: 
	 * @param fieldName
	 * @return
	 * @since Ver 1.00
	 */
	public FileParam getFile(String fieldName){
		List<FileParam> list = getFileList(fieldName);
		if(CollectionUtil.isNotEmpty(list) && list.size() == 1){
			return list.get(0);
		}
		return null;
	}
	
	public boolean isEmpty(){
		return CollectionUtil.isEmpty(fileParamList) && CollectionUtil.isEmpty(formParamList);
	}

	public String getString(String fieldName){
		return CastUtil.castString(getFieldMap().get(fieldName));
	}
}
