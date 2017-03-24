package com.simple.framework.bean;

import java.util.Map;

import com.simple.framework.util.CastUtil;

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
	
	private Map<String,Object> paramMap;

	
	public Param(Map<String, Object> paramMap) {
		super();
		this.paramMap = paramMap;
	}
	
	public Long getLong(String paramName){
		return CastUtil.castLong(paramMap.get(paramName));
	}
	
	public Map<String,Object> getMap(){
		return paramMap;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	

}
