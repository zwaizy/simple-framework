package com.simple.framework.helper;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.simple.framework.util.DBUtil;

/**
 * 
 * <p> Description: 数据库操作助手类 </p>
 * @Author Zhanwei
 * @Date [2017年3月24日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		zhanwei	2017年3月24日	新建文件.
 * 
 * </pre>
 */
public final class DatabaseHelper {
	
	public static void beginTransaction() {
		Connection conn = DBUtil.getConnection();
		if(conn != null){
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public static void commitTransaction(){
		Connection conn = DBUtil.getConnection();
		if(conn != null){
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeConnection();
			}
		}
	}

	public static void rollbackTransaction(){
		Connection conn = DBUtil.getConnection();
		if(conn != null){
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeConnection();
			}
		}
	}
}
