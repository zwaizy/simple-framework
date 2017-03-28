package com.simple.framework.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.simple.framework.bean.FormParam;
import com.simple.framework.bean.Param;
import com.simple.framework.util.ArrayUtil;
import com.simple.framework.util.CodecUtil;
import com.simple.framework.util.StreamUtil;
import com.simple.framework.util.StringUtil;

/**
 * 
 * <p> Description: 请求助手类  </p>
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
public final class RequestHelper {

	/**
	 * 
	 * createParam
	 * 方法描述: 创建请求对象
	 * 逻辑描述: 
	 * @param request
	 * @return
	 * @throws IOException 
	 * @since Ver 1.00
	 */
	public static Param createParam(HttpServletRequest request) throws IOException{
		List<FormParam> formParamList = new ArrayList<FormParam>();
		formParamList.addAll(parseParameterNames(request));
		formParamList.addAll(parseInputStream(request));
		return new Param(formParamList);
	}
	
	/**
	 * 
	 * parseParameterNames
	 * 方法描述: 获取form表单中的值
	 * 逻辑描述: 
	 * @param request
	 * @return
	 * @since Ver 1.00
	 */
	private static List<FormParam> parseParameterNames(HttpServletRequest request){
		List<FormParam> formParamList = new ArrayList<FormParam>();
		Enumeration<String> paramNames = request.getParameterNames();//form表单中的值
		while(paramNames.hasMoreElements()){
			String paramName = paramNames.nextElement();
			String[] fieldValues = request.getParameterValues(paramName);
			if(ArrayUtil.isNotEmpty(fieldValues)){
				Object fieldValue;
				if(fieldValues.length == 1){
					fieldValue = fieldValues[0];
				}else{
					StringBuilder sb = new StringBuilder();
					for(int i=0;i<fieldValues.length;i++){
						sb.append(fieldValues[i]);
						if(i != fieldValues.length - 1){
							sb.append(StringUtil.SEPARATOR);
						}
					}
					fieldValue = sb.toString();
				}
				formParamList.add(new FormParam(paramName, fieldValue));
			}
		}
		return formParamList;
	}
	
	/**
	 * 
	 * parseInputStream
	 * 方法描述: 获取浏览器地址栏参数
	 * 逻辑描述: 
	 * @param request
	 * @return
	 * @throws IOException
	 * @since Ver 1.00
	 */
	private static List<FormParam> parseInputStream(HttpServletRequest request) throws IOException{
		List<FormParam> formParamList = new ArrayList<FormParam>();
		String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
		if(StringUtil.isNotEmpty(body)){
			String[] kvs = StringUtil.splitString(body, "&");
			if(ArrayUtil.isNotEmpty(kvs)){
				for(String kv : kvs){
					String[] array = StringUtil.splitString(kv, "=");
					if(ArrayUtil.isNotEmpty(array) && array.length == 2){
						String fieldName = array[0];
						String fieldValue = array[1];
						formParamList.add(new FormParam(fieldName, fieldValue));
					}
				}
			}
		}
		return formParamList;
	}
}
