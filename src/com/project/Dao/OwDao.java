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
		/*������ݿ�����*/
		Connection conn = DBUtil.getConnection();
		/*��дSQL���*/
		String sql = " " + 
					 "insert into overWatcher" 							+ 
					 "(user_name,sex,age,birthday,email,mobile," 		+ 
					 "create_user,create_date,update_user,update_date)" + 
					 "values(?,?,?,?,?,?,?,current_date(),?,current_date())";  // �ʺ�Ϊռλ��
		/*Ԥ���루����������SQL����������ȥ�����ǲ�������ִ�У�*/
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString (1, gd.getUser_name());
		ptmt.setInt	   (2, gd.getSex());
		ptmt.setInt	   (3, gd.getAge());
		ptmt.setDate   (4, new Date(gd.getBirthday().getTime()));
		ptmt.setString (5, gd.getEmail());
		ptmt.setString (6, gd.getMobile());
		ptmt.setString (7, gd.getCreate_user());
		ptmt.setString (8, gd.getUpdate_user());	
		
		ptmt.execute();  // ִ��SQL���
	}
	
	
	public void updateGoddess(overWatcher gd) throws Exception {
		/*������ݿ�����*/
		Connection conn = DBUtil.getConnection();
		/*��дSQL���*/
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
		/*Ԥ���루����������SQL����������ȥ�����ǲ�������ִ�У�*/
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
		/*������ݿ�����*/
		Connection conn = DBUtil.getConnection();
		/*��дSQL���*/
		String sql = " delete from overWatcher" +
				     " where id = ?";
		/*Ԥ���루����������SQL����������ȥ�����ǲ�������ִ�У�*/
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);  // ����ռλ������ֵ
		
		ptmt.execute();
	}
	
	
	public List<overWatcher> Query() throws Exception 
	{
		Connection    conn = DBUtil.getConnection();
		
		StringBuilder sb   = new  StringBuilder();  // ʹ��stringBuilder����Ч��
		sb.append("select id,user_name,age from overWatcher");
		
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ResultSet 		  res  = ptmt.executeQuery();  // ���ܷ��ز�ѯ��Ϣ�ļ���
		
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
		/*������ݿ�����*/
		Connection  conn = DBUtil.getConnection();
		/*��дSQL���*/
		String sql = " select * from overWatcher" +
				     " where id = ?";
		/*Ԥ���루����������SQL����������ȥ�����ǲ�������ִ�У�*/
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
	
	
	/*���в����Ĳ�ѯ������չ*/
	public List<overWatcher> Query(String name) throws Exception 
	{
		Connection 	  conn = DBUtil.getConnection();
		
		StringBuilder sb   = new  StringBuilder();  // ʹ��stringBuilder����Ч��
		sb.append("select * from overWatcher");
		sb.append(" where user_name like ?");  // ���ȱʡֵ���е�ͨ�������
		
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
	
	/*���в����Ĳ�ѯ��չ��*/
	public List<overWatcher> Query(List<Map<String, Object>> params) throws Exception 
	{
		Connection    conn = DBUtil.getConnection();
		
		StringBuilder sb   = new  StringBuilder(); // ʹ��stringBuilder����Ч��
		sb.append("select * from overWatcher where 1=1");  // ������һ��С���� ��1 = 1��
		
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
