package com.project.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
import java.sql.Statement;
import java.sql.ResultSet;
*/


public class DBUtil {
	
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/jdbc_connection";
	private static final String NAME = "root";
	private static final String PASSWORD = "6664265ch";
	
	private static  Connection conn = null;  //申明数据库连接接口
	
	static{//静态代码块
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL,NAME,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return conn;
	}


	/*
	 * public static void main(String[] args)throws Exception {
		//加载驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		//获得数据库的链接
		Connection conn = DriverManager.getConnection(URL,NAME,PASSWORD);
		//通过数据库的连接操作数据库，实现增删改查
		Statement state = conn.createStatement();
		ResultSet res =  state.executeQuery("select id,user_name,age from goddness");//获得数据保存在此对象中
		while(res.next()){
			System.out.println(res.getInt("id") + " " + res.getString("user_name") + " " +  res.getInt("age"));
		}
	}
	 */
}
