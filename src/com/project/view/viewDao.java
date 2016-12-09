package com.project.view;

import java.util.ArrayList;
import java.util.List;


import com.project.action.overWatchAction;
import com.project.model.overWatcher;

public class viewDao {
	
	overWatchAction action = new overWatchAction();
	
	public void returnToMenu() {	
			System.out.println("返回主菜单");
			System.out.println( view.CONTEXT);
	}
	
	public void exitMenu() {	
		System.out.println("退出成功");
		return;
	}
	
	public void query() throws Exception {
		List<overWatcher> gall = new ArrayList<overWatcher>();
		gall = action.query();
		for (overWatcher goddess : gall) {
			System.out.println(goddess.getId() + "," + goddess.getUser_name() + "," + goddess.getAge());
		}		
	}
/*
	public void getSpecial(int step,String content,String previous) throws Exception {
		
		int sp = step;
		Scanner scan = new Scanner(System.in);//扫描等待用户输入
		String conn = scan.next().toString();
		
		previous = view.OPERATION_GET;//保持在此功能中
		List<Map<String, Object>> params = new ArrayList<Map<String,Object>>();
		Map<String, Object> param = new HashMap<String, Object>();
		switch (sp) {
		
		case 1:
			System.out.println("请输入查询人的【姓名】：");
			break;
			
		case 2:
			param.put("name", "user_name");
			param.put("rela", "like");
			String con = content;
			param.put("value", "\"%"+ con + "%\"");
			params.add(param);
			List<Goddess> gres = action.query(params);
				for (Goddess goddess : gres) {
					StringBuilder sb = new StringBuilder();
					sb.append(goddess.getId() + "," + goddess.getUser_name() + "," + goddess.getAge());
					System.out.println(sb);		
					
				}
			System.out.println("按R键继续查询，或按M键返回主菜单");
			step = 0;	
			break;
		}							
		if (view.OPERATION_GET.equals(previous)) {//switch之后执行的语句
			sp++;
		}else{
			System.out.println("您输入的值为:" + content);
		}
		
	}*/
}


