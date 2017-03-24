package com.simple.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <p> Description: 返回视图对象 </p>
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
public class View {
	
	private String path;
	
	private Map<String,Object> model;

	public View(String path) {
		super();
		this.path = path;
		this.model = new HashMap<String,Object>();
	}

	public View addModel(String key,Object value){
		model.put(key, value);
		return this;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	
	

}
