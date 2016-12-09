package com.testAction;

import java.util.Date;

import com.project.action.overWatchAction;
import com.project.model.overWatcher;

public class testAction {
	
	public static void main(String[] args) throws Exception {
		overWatchAction tsAction = new overWatchAction();
	/*查询
		List<Goddess> res =  tsAction.query();
		
		for (int i = 0; i < res.size(); i++) {
			System.out.println("编号："+res.get(i).getId() +"  姓名："+ res.get(i).getUser_name() + 
							   "  年龄："+res.get(i).getAge());
		}
	*/
		overWatcher gd = new overWatcher();				
		gd.setUser_name("黑影");
		gd.setSex(1);
		gd.setAge(16);
		gd.setBirthday(new Date());
		gd.setEmail("222222222@163.com");
		gd.setMobile("22222");
		gd.setUpdate_user("Breavo");
		gd.setIsdel(1);
		tsAction.add(gd);
		System.out.println(gd.toString());
		
	}
}
