package com.project.view;

import java.util.ArrayList;
import java.util.List;


import com.project.action.overWatchAction;
import com.project.model.overWatcher;

public class viewDao {
	
	overWatchAction action = new overWatchAction();
	
	public void returnToMenu() {	
			System.out.println("�������˵�");
			System.out.println( view.CONTEXT);
	}
	
	public void exitMenu() {	
		System.out.println("�˳��ɹ�");
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
		Scanner scan = new Scanner(System.in);//ɨ��ȴ��û�����
		String conn = scan.next().toString();
		
		previous = view.OPERATION_GET;//�����ڴ˹�����
		List<Map<String, Object>> params = new ArrayList<Map<String,Object>>();
		Map<String, Object> param = new HashMap<String, Object>();
		switch (sp) {
		
		case 1:
			System.out.println("�������ѯ�˵ġ���������");
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
			System.out.println("��R��������ѯ����M���������˵�");
			step = 0;	
			break;
		}							
		if (view.OPERATION_GET.equals(previous)) {//switch֮��ִ�е����
			sp++;
		}else{
			System.out.println("�������ֵΪ:" + content);
		}
		
	}*/
}


