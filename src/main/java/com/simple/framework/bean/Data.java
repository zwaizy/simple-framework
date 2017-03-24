package com.simple.framework.bean;

/**
 * 
 * <p> Description: 返回数据对象 </p>
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
public class Data {
	
	private Object model;
	
	public Data(Object model) {
		super();
		this.model = model;
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}
	
	

}
