package com.simple.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p> Description: 类加载器工具类 </p>
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
public final class ClassUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);
 
	/**
	 * 
	 * getClassLoad
	 * 方法描述: 获取类加载器
	 * 逻辑描述: 
	 * @return
	 * @since Ver 1.00
	 */
	public static ClassLoader getClassLoader(){
		return Thread.currentThread().getContextClassLoader();
	}
	
	/**
	 * 
	 * loadClass
	 * 方法描述: 加载类
	 * 逻辑描述: 
	 * @param className
	 * @param isInitialized
	 * @return
	 * @since Ver 1.00
	 */
	public static Class<?> loadClass(String className,boolean isInitialized){
		
		Class<?> cls;
		try {
			cls = Class.forName(className,isInitialized,getClassLoader());
		} catch (ClassNotFoundException e) {
			LOGGER.error(className + " class not found ",e);
			throw new RuntimeException(e);
		}
		return cls;
	}
	
	/**
	 * 
	 * getClassSet
	 * 方法描述: 获取指定包名下的所有类
	 * 逻辑描述: 
	 * @param packageName
	 * @return
	 * @since Ver 1.00
	 */
	public static Set<Class<?>> getClassSet(String packageName){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
			while(urls.hasMoreElements()){
				URL url = urls.nextElement();
				if(url != null){
					String protocol = url.getProtocol();
					if(protocol.equals("file")){
						String packagePath = url.getPath().replaceAll("%20", "");
						addClass(classSet,packagePath,packageName);
					}else if(protocol.equals("jar")){
						JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
						if(jarURLConnection != null){
							JarFile jarFiles = jarURLConnection.getJarFile();
							if(jarFiles != null){
								Enumeration<JarEntry> jarEntries =  jarFiles.entries();
								while(jarEntries.hasMoreElements()){
									JarEntry entry = jarEntries.nextElement();
									if(entry != null){
										String jarEntryName = entry.getName();
										if(jarEntryName.endsWith(".class")){
											String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
											doAddClass(classSet,className);
										}
									}
								}
									
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			LOGGER.error("get class set error",e);
			throw new RuntimeException(e);
		}
		return classSet;
	}

	private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
		File[] files = new File(packagePath).listFiles(new FileFilter() {
			
			public boolean accept(File file) {
				return file.isFile() && file.getName().endsWith(".class") || file.isDirectory();
			}
		});
		
		for(File file : files){
			String fileName = file.getName();
			if(file.isFile()){
				String className = fileName.substring(0, fileName.lastIndexOf("."));
				if(StringUtil.isNotEmpty(packageName)){
					className = packageName + "." + className;
				}
				doAddClass(classSet,className);
			}else{
				String subPackagePath = fileName;
				if(StringUtil.isNotEmpty(packagePath)){
					subPackagePath = packagePath + "." + subPackagePath;
				}
				String subPackageName = fileName;
				if(StringUtil.isNotEmpty(packageName)){
					subPackageName = packageName + "." + subPackageName;
				}
				addClass(classSet, subPackagePath, subPackageName);
			}
		}
		
	}

	private static void doAddClass(Set<Class<?>> classSet, String className) {
		Class<?> cls = loadClass(className, false);
		classSet.add(cls);
	}
		
 	
}
