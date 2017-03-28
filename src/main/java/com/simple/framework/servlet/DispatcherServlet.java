package com.simple.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.simple.framework.HelperLoader;
import com.simple.framework.bean.Data;
import com.simple.framework.bean.Handler;
import com.simple.framework.bean.Param;
import com.simple.framework.bean.View;
import com.simple.framework.helper.BeanHelper;
import com.simple.framework.helper.ConfigHelper;
import com.simple.framework.helper.ControllerHelper;
import com.simple.framework.helper.RequestHelper;
import com.simple.framework.helper.ServletHelper;
import com.simple.framework.helper.UploadHelper;
import com.simple.framework.util.JsonUtil;
import com.simple.framework.util.ReflectionUtil;
import com.simple.framework.util.StringUtil;

/**
 * 
 * <p>
 * Description: 请求转发器
 * </p>
 * 
 * @Author Zhanwei
 * @Date [2017年3月23日]
 * @Version V1.0
 * @修改记录
 * 
 *       <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		zhanwei	2017年3月23日	新建文件.
 * 
 *       </pre>
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		//初始化helper类
		HelperLoader.init();
		ServletContext servletContext = config.getServletContext();
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJSPPath()+"*");
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
		
		UploadHelper.init(servletContext);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletHelper.init(req, resp);
		try {
			String method = req.getMethod().toLowerCase();
			String requestPath = req.getPathInfo();
			
			if(requestPath.equals("/favicon.ico")){
				return;
			}
			
			//获取Hanlder
			Handler handler = ControllerHelper.getHandler(method, requestPath);
			if(handler != null){
				Class<?> controllerClass = handler.getControllerClass();
				Object controllerBean = BeanHelper.getBeanObj(controllerClass);
				
				Param param ;
				if(UploadHelper.isMutipart(req)){
					param = UploadHelper.createParam(req);
				}else{
					param = RequestHelper.createParam(req);
				}
				
				//调用action方法
				Method actionMethod = handler.getActionMethod();
				Object result;
				if(param.isEmpty()){
					result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
				}else{
					result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
				}
				//处理action返回值
				if(result instanceof View){
					handleViewResult(req, resp, (View)result);
				}else if(result instanceof Data){
					handleDataResult(resp, (Data)result);
				}
			}
		} finally {
			ServletHelper.destory();
		}
	}

	private void handleDataResult(HttpServletResponse resp, Data data) throws IOException {
		Object model = data.getModel();
		if(model != null){
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.write(JsonUtil.toJson(model));
			writer.flush();
			writer.close();
		}
	}

	private void handleViewResult(HttpServletRequest req, HttpServletResponse resp, View view)
			throws IOException, ServletException {
		String path = view.getPath();
		if(StringUtil.isNotEmpty(path)){
			if(path.startsWith("/")){
				resp.sendRedirect(req.getContextPath()+path);
			}else{
				Map<String,Object> model = view.getModel();
				for(Map.Entry<String, Object> entry : model.entrySet()){
					String key = entry.getKey();
					Object value = entry.getValue();
					req.setAttribute(key, value);
				}
				req.getRequestDispatcher(ConfigHelper.getAppJSPPath()+path).forward(req, resp);
			}
		}
	}

}
