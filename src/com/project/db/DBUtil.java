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
	
	private static  Connection conn = null;  //�������ݿ����ӽӿ�
	
	static{//��̬�����
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
		//������������
		Class.forName("com.mysql.jdbc.Driver");
		//������ݿ������
		Connection conn = DriverManager.getConnection(URL,NAME,PASSWORD);
		//ͨ�����ݿ�����Ӳ������ݿ⣬ʵ����ɾ�Ĳ�
		Statement state = conn.createStatement();
		ResultSet res =  state.executeQuery("select id,user_name,age from goddness");//������ݱ����ڴ˶�����
		while(res.next()){
			System.out.println(res.getInt("id") + " " + res.getString("user_name") + " " +  res.getInt("age"));
		}
	}
	 */
}
