package com.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Driver;

/*
 * Ϊ�˽�Լ��Դ
 * 
 */
public class connectUtils {
	// ֻ��Ҫȡһ��
	private static   ComboPooledDataSource  pool =new ComboPooledDataSource(); 
	public static Connection getConnection() throws SQLException {
		
		return pool.getConnection();
		
	}
	
	

	public static void free(Connection conn, Statement statement) {

		// ���ܷ�һ��try�в������Ⱥ�˳��
		if (statement != null) {

			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		if (conn != null) {

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public static void free(ResultSet res, Statement statement, Connection conn) {
		// ���ܷ�һ��try�в������Ⱥ�˳��(�������ж��Ƿ�Ϊ��)
		if (res != null) {

			try {
				res.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
		if (statement != null) {

			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		if (conn != null) {

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
