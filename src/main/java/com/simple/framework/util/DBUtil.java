package com.simple.framework.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.Connection;
import com.simple.framework.helper.ConfigHelper;

/**
 * 
 * <p>
 * Description: 数据库工具类
 * </p>
 * 
 * @Author Zhanwei
 * @Date [2017年3月24日]
 * @Version V1.0
 * @修改记录
 * 
 *       <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		zhanwei	2017年3月24日	新建文件.
 * 
 *       </pre>
 */
public final class DBUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DBUtil.class);
	
	private static final ThreadLocal<Connection> LOCAL_CONN = new ThreadLocal<Connection>();

	public static Connection getConnection() {
		Connection conn = LOCAL_CONN.get();
		if (conn == null) {
			try {
				Class.forName(ConfigHelper.getJDBCDriver());
				conn = (Connection) DriverManager.getConnection(ConfigHelper.getJDBCUrl(), ConfigHelper.getJDBCUsername(),
						ConfigHelper.getJDBCPassword());
			} catch (Exception e) {
				LOGGER.error("get connection error...",e);
			} finally {
				LOCAL_CONN.set(conn);
			}
		}
		return conn;
	}
	
	public static void closeConnection(){
		Connection conn = LOCAL_CONN.get();
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("close connection error...",e);
			} finally {
				LOCAL_CONN.remove();
			}
		}
	}
	

}
