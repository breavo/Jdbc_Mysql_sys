package com.project.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.project.db.DBUtil;
import com.project.model.overWatcher;

public class OwDao
{
	public void addGoddess(overWatcher gd) throws Exception 
	{
		/*获得数据库连接*/
		Connection conn = DBUtil.getConnection();
		/*编写SQL语句*/
		String sql = " " + 
					 "insert into overWatcher" 							+ 
					 "(user_name,sex,age,birthday,email,mobile," 		+ 
					 "create_user,create_date,update_user,update_date)" + 
					 "values(?,?,?,?,?,?,?,current_date(),?,current_date())";  // 问号为占位符
		/*预编译（此条语句加载SQL到编译器里去，但是不会立即执行）*/
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString (1, gd.getUser_name());
		ptmt.setInt	   (2, gd.getSex());
		ptmt.setInt	   (3, gd.getAge());
		ptmt.setDate   (4, new Date(gd.getBirthday().getTime()));
		ptmt.setString (5, gd.getEmail());
		ptmt.setString (6, gd.getMobile());
		ptmt.setString (7, gd.getCreate_user());
		ptmt.setString (8, gd.getUpdate_user());	
		
		ptmt.execute();  // 执行SQL语句
	}
	
	
	public void updateGoddess(overWatcher gd) throws Exception {
		/*获得数据库连接*/
		Connection conn = DBUtil.getConnection();
		/*编写SQL语句*/
		String sql = " " + 
					 " update overWatcher " 			  + 
					 " set user_name = ?,"  			  +
					     " sex = ?,"					  +
					     " age = ?,"					  +
					     " birthday = ?,"				  +
					     " email = ?,"					  +
					     " mobile = ?,"					  +
					     " update_user = ?,"			  +
					     " update_date = current_date()," +
				 	     " isdel = ?"					  + 
			         " where id = ?";
		/*预编译（此条语句加载SQL到编译器里去，但是不会立即执行）*/
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString (1, gd.getUser_name());
		ptmt.setInt	   (2, gd.getSex());
		ptmt.setInt	   (3, gd.getAge());
		ptmt.setDate   (4, new Date(gd.getBirthday().getTime()));
		ptmt.setString (5, gd.getEmail());
		ptmt.setString (6, gd.getMobile());
		ptmt.setString (7, gd.getUpdate_user());	
		ptmt.setInt	   (8, gd.getIsdel());
		ptmt.setInt	   (9, gd.getId());
		
		ptmt.execute();
		
	}
	
	
	public void delGoddess(Integer id)throws Exception 
	{
		/*获得数据库连接*/
		Connection conn = DBUtil.getConnection();
		/*编写SQL语句*/
		String sql = " delete from overWatcher" +
				     " where id = ?";
		/*预编译（此条语句加载SQL到编译器里去，但是不会立即执行）*/
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);  // 更新占位符的数值
		
		ptmt.execute();
	}
	
	
	public List<overWatcher> Query() throws Exception 
	{
		Connection    conn = DBUtil.getConnection();
		
		StringBuilder sb   = new  StringBuilder();  // 使用stringBuilder提升效率
		sb.append("select id,user_name,age from overWatcher");
		
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ResultSet 		  res  = ptmt.executeQuery();  // 接受返回查询信息的集合
		
		List<overWatcher> gd = new ArrayList<overWatcher>();		
		overWatcher 	  gs = null;		
		while(res.next())
		{
			gs = new overWatcher();
			gs.setId		(res.getInt("id"));
			gs.setUser_name (res.getString("user_name"));
			gs.setAge		(res.getInt("age"));
			
			gd.add(gs);
		}
		return gd;
	}
	
	
	public overWatcher getoneGoddness(Integer id)throws Exception 
	{
		overWatcher gs   = null;
		/*获得数据库连接*/
		Connection  conn = DBUtil.getConnection();
		/*编写SQL语句*/
		String sql = " select * from overWatcher" +
				     " where id = ?";
		/*预编译（此条语句加载SQL到编译器里去，但是不会立即执行）*/
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		
		ResultSet res = ptmt.executeQuery();
		while(res.next())
		{
			gs = new overWatcher();
			gs.setId		  (res.getInt("id"));
			gs.setUser_name	  (res.getString("user_name"));
			gs.setSex		  (res.getInt("sex"));
			gs.setAge		  (res.getInt("age"));
			gs.setBirthday	  (res.getDate("birthday"));
			gs.setEmail		  (res.getString("email"));
			gs.setMobile	  (res.getString("mobile"));
			gs.setUpdate_user (res.getString("update_user"));	
			gs.setIsdel		  (res.getInt("isdel"));
		}
		return gs;
	}
	
	
	/*带有参数的查询，可扩展*/
	public List<overWatcher> Query(String name) throws Exception 
	{
		Connection 	  conn = DBUtil.getConnection();
		
		StringBuilder sb   = new  StringBuilder();  // 使用stringBuilder提升效率
		sb.append("select * from overWatcher");
		sb.append(" where user_name like ?");  // 针对缺省值进行的通配符过滤
		
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ptmt.setString(1, "%" + name + "%");
		
		ResultSet res =  ptmt.executeQuery();
		
		List<overWatcher> gd = new ArrayList<overWatcher>();		
		overWatcher 	  gs = null;		
		while(res.next()){
			gs = new overWatcher();
			gs.setId		  (res.getInt("id"));
			gs.setUser_name	  (res.getString("user_name"));
			gs.setSex		  (res.getInt("sex"));
			gs.setAge		  (res.getInt("age"));
			gs.setBirthday	  (res.getDate("birthday"));
			gs.setEmail		  (res.getString("email"));
			gs.setMobile	  (res.getString("mobile"));
			gs.setUpdate_user (res.getString("update_user"));	
			gs.setIsdel		  (res.getInt("isdel"));
			
			gd.add(gs);
		}
		return gd;
	}
	
	/*带有参数的查询扩展版*/
	public List<overWatcher> Query(List<Map<String, Object>> params) throws Exception 
	{
		Connection    conn = DBUtil.getConnection();
		
		StringBuilder sb   = new  StringBuilder(); // 使用stringBuilder提升效率
		sb.append("select * from overWatcher where 1=1");  // 开发的一个小技巧 “1 = 1”
		
		if (params != null && params.size() > 0) 
		{
			for (int i = 0; i < params.size(); i++) 
			{
				Map<String, Object> map = params.get(i);
				sb.append(" and" + " " + map.get("name") + " " + map.get("rela") + " " + map.get("value") + " ");
				//System.out.println(sb.toString());
			}
		}
		
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ResultSet 		  res  = ptmt.executeQuery();
		
		List<overWatcher> gd   = new ArrayList<overWatcher>();		
		overWatcher 	  gs   = null;		
		while(res.next())
		{
			gs = new overWatcher();
			gs.setId		  (res.getInt("id"));
			gs.setUser_name	  (res.getString("user_name"));
			gs.setSex		  (res.getInt("sex"));
			gs.setAge		  (res.getInt("age"));
			gs.setBirthday	  (res.getDate("birthday"));
			gs.setEmail		  (res.getString("email"));
			gs.setMobile	  (res.getString("mobile"));
			gs.setUpdate_user (res.getString("update_user"));	
			gs.setIsdel		  (res.getInt("isdel"));
			
			gd.add(gs);
		}
		return gd;
	}
}
