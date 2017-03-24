package com.simple.framework.bean;

/**
 * 
 * <p> Description: 封装请求信息 </p>
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
public class Request {

	private String requestMethod;
	
	private String requestUrl;

	
	public Request(String requestMethod, String requestUrl) {
		super();
		this.requestMethod = requestMethod;
		this.requestUrl = requestUrl;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((requestMethod == null) ? 0 : requestMethod.hashCode());
		result = prime * result + ((requestUrl == null) ? 0 : requestUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (requestMethod == null) {
			if (other.requestMethod != null)
				return false;
		} else if (!requestMethod.equals(other.requestMethod))
			return false;
		if (requestUrl == null) {
			if (other.requestUrl != null)
				return false;
		} else if (!requestUrl.equals(other.requestUrl))
			return false;
		return true;
	}
	
	
	
}
