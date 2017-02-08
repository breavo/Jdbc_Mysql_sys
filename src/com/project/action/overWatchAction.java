package com.project.action;


import java.util.List;
import java.util.Map;

import com.project.Dao.OwDao;
import com.project.model.overWatcher;

public class overWatchAction 
{
	public void add(overWatcher goddess) throws Exception
	{
		OwDao gDao = new OwDao();
		gDao.addGoddess(goddess);
	}
	
	public void edit(overWatcher goddess) throws Exception 
	{
		OwDao gDao = new OwDao();
		gDao.updateGoddess(goddess);
	}
	
	public void del(Integer id)throws Exception 
	{
		OwDao gDao = new OwDao();
		gDao.delGoddess(id);
	}
	
	public List<overWatcher> query() throws Exception 
	{
		OwDao 			  gDao = new OwDao();
		List<overWatcher> qg   = gDao.Query();
		return qg;
	}
	
	public List<overWatcher> query(List<Map<String, Object>> param) throws Exception
	{
		OwDao 			  gDao = new OwDao();
		List<overWatcher> qg   = gDao.Query(param);
		return qg;
	}
	
	public overWatcher getone(Integer id) throws Exception
	{
		OwDao 		gDao   = new OwDao();
		overWatcher getone = gDao.getoneGoddness(id);
		return getone;
	}
	
}

/*测试代码
 * 
 * 	public static void main(String[] args) throws Exception {
		//普通查询
		GoddnessDao qgDao = new GoddnessDao();
		//添加
		GoddnessDao agDao = new GoddnessDao();
		//更新
		GoddnessDao ugDao = new GoddnessDao();
		//删除
		GoddnessDao dgDao = new GoddnessDao();
		//单个查询
		GoddnessDao ggDao = new GoddnessDao();
		//模糊查询
		GoddnessDao mapgDao = new GoddnessDao();
		
		Goddess gd = new Goddess();				
		gd.setUser_name("猎空");
		gd.setSex(1);
		gd.setAge(20);
		gd.setBirthday(new Date());
		gd.setEmail("111111@163.com");
		gd.setMobile("111111");
		gd.setUpdate_user("Breavo");
		gd.setIsdel(1);

		List<Map<String, Object>> params = new ArrayList<Map<String,Object>>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("name", "user_name");
		param.put("rela", "like");
		param.put("value", "'%查%'");
		params.add(param);
		List<Goddess> gres = mapgDao.Query(params);
		for (int i = 0; i < gres.size(); i++) {
			System.out.println(gres.get(i).toString());
		}

	  Goddess ggres =  ggDao.getoneGoddness(gd.getId());
	  System.out.println(ggres.toString());
	  dgDao.delGoddess(gd.getId());
	  ugDao.updateGoddess(gd);	
      agDao.addGoddess(gd); 
      
	  List<Goddess> gres = qgDao.Query("黑百合");
		for (int i = 0; i < gres.size(); i++) {
			System.out.println(gres.get(i).toString());
		}
	}
	*/
