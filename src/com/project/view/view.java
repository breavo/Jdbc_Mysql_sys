package com.project.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
















import com.project.action.overWatchAction;
import com.project.model.overWatcher;

public class view {
	protected static final String CONTEXT="欢迎来到守望者禁区：\n" +
			"下面是守望者禁区的功能列表：\n" +
			"[MAIN/M]:主菜单\n" +
			"[QUERY/Q]:查看全部守望者的信息\n" +
			"[GET/G]:查看某位守望者的详细信息\n" +
			"[ADD/A]:添加守望者信息\n" +
			"[UPDATE/U]:更新守望者信息\n" +
			"[DELETE/D]:删除守望者信息\n" +
			"[SEARCH/S]:查询守望者信息(根据姓名、手机号来查询)\n" +
			"[EXIT/E]:退出守望者禁区\n" +
			"[BREAK/B]:退出当前功能，返回主菜单";

	protected static final String OPERATION_MAIN = "MAIN";
	protected static final String OPERATION_QUERY = "QUERY";
	protected static final String OPERATION_GET = "GET";
	protected static final String OPERATION_ADD = "ADD";
	protected static final String OPERATION_UPDATE = "UPDATE";
	protected static final String OPERATION_DELETE = "DELETE";
	protected static final String OPERATION_SEARCH = "SEARCH";
	protected static final String OPERATION_EXIT = "EXIT";
	protected static final String OPERATION_BREAK = "BREAK";
	
	
	public static void main(String[] args) throws Exception {
		System.out.println(CONTEXT);
		
		//视图层方法
		viewDao vd = new viewDao();
		//定义全局对象
		overWatcher gs = new overWatcher();
		overWatchAction action = new overWatchAction();
		//记忆对象
		String previous = null;
		//步骤对象
		Integer step = 1;
		
		//保持程序运行
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);//扫描等待用户输入
		while(scan.hasNext()){
					
			String content = scan.next().toString();
			
			//返回主菜单
			if (       OPERATION_MAIN.equals(content.toUpperCase()) 
					|| OPERATION_MAIN.substring(0, 1).equals(content.toUpperCase())) {
				vd.returnToMenu();
				previous = null;
				step = 1;
				}
			
			//退出程序
			else if (   OPERATION_EXIT.equals(content.toUpperCase()) 
					 || OPERATION_EXIT.substring(0, 1).equals(content.toUpperCase())) {
				vd.exitMenu();
				break;
			}
			
			//查询信息（所有用户）
			else if (   OPERATION_QUERY.equals(content.toUpperCase()) 
					 || OPERATION_QUERY.substring(0, 1).equals(content.toUpperCase())
					 || OPERATION_QUERY.equals(previous)) {
				previous = OPERATION_QUERY;//保持在此功能中
				vd.query();
				}
			
			//查询特定用户信息
			else if (   OPERATION_GET.equals(content.toUpperCase()) 
					 || OPERATION_GET.substring(0, 1).equals(content.toUpperCase())
					 || OPERATION_GET.equals(previous)) {
				previous = OPERATION_GET;//保持在此功能中
				List<Map<String, Object>> params = new ArrayList<Map<String,Object>>();
				Map<String, Object> param = new HashMap<String, Object>();
				switch (step) {
				
				case 1:
					System.out.println("请输入查询人的【姓名】：");
					break;
					
				case 2:
					param.put("name", "user_name");
					param.put("rela", "like");
					String con = content;
					param.put("value", "\"%"+ con + "%\"");
					params.add(param);
					List<overWatcher> gres = action.query(params);
						for (overWatcher goddess : gres) {
							StringBuilder sb = new StringBuilder();
							sb.append(goddess.getId() + "," + goddess.getUser_name() + "," + goddess.getAge());
							System.out.println(sb);		
							
						}
					System.out.println("按R键继续查询，或按M键返回主菜单");
					step = 0;	
					break;
				}							
				if (OPERATION_GET.equals(previous)) {//switch之后执行的语句
					step++;
				}else{
					System.out.println("您输入的值为:" + content);
				}
			}
			
			//添加用户信息
			else if (    OPERATION_ADD.equals(content.toUpperCase()) 
					  || OPERATION_ADD.substring(0, 1).equals(content.toUpperCase())
				      || OPERATION_ADD.equals(previous)){
				previous = OPERATION_ADD;
				
				switch (step) {			
				case 1:
					System.out.println("请输入守望者【姓名】：");
					break;
					
				case 2:
					gs.setUser_name(content);
					System.out.println("输入守望者【姓名】为：" + content);
					System.out.println("请输入守望者【性别】：");
					break;
					
				case 3:
					gs.setSex(Integer.valueOf(content));
					System.out.println("请输入守望者【年龄】：");
					break;
					
				case 4:
					gs.setAge(Integer.valueOf(content));
					System.out.println("请输入守望者【生日】：(格式如yyyy-MM-dd)");
					break;			
				case 5:
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date birthday = sf.parse(content);
						gs.setBirthday(birthday);
						System.out.println("请输入守望者【电子邮箱】：");
					} catch (Exception e) {
						//e.printStackTrace();
						System.out.println("格式错误，请重新输入~");
						step = 4;
					}
					break;
					
				case 6:
					gs.setEmail(content);
					System.out.println("请输入守望者【电话】：");
					break;
					
				case 7:
					gs.setMobile(content);
					System.out.println("请输入创建人：");
					break;
					
				case 8:
					gs.setCreate_user(content);
					System.out.println("请输入更新人：");
					break;
					
				case 9:
					gs.setIsdel(Integer.valueOf(content));	
					try {							
						action.add(gs);
						System.out.println("添加成功");
						step = 0;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("添加失败");
					}
					break;
				}
				if (OPERATION_ADD.equals(previous)) {//switch之后执行的语句
					step++;
				}else{
					System.out.println("您输入的值为:" + content);
				}								
			}
			//删除用户信息
			else if (    OPERATION_DELETE.equals(content.toUpperCase()) 
					  || OPERATION_DELETE.substring(0, 1).equals(content.toUpperCase())
				      || OPERATION_DELETE.equals(previous)){
				previous = OPERATION_DELETE;
				
				switch (step) {
				case 1:
					System.out.println("请输入要删除的守望者id序号");
					break;
				case 2:
					try {
						action.del(Integer.valueOf(content));
						System.out.println("删除成功~~");
					} catch (Exception e) {
						//e.printStackTrace();
						System.out.println("删除失败，请重新输入序号~~");
						step = 0;
					}
				}
				if (OPERATION_DELETE.equals(previous)) {//switch之后执行的语句
					step++;
				}else{
					System.out.println("您输入的值为:" + content);
				}
			}
					
		}
	}
}
