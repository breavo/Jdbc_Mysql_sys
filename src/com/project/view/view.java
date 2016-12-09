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
	protected static final String CONTEXT="��ӭ���������߽�����\n" +
			"�����������߽����Ĺ����б�\n" +
			"[MAIN/M]:���˵�\n" +
			"[QUERY/Q]:�鿴ȫ�������ߵ���Ϣ\n" +
			"[GET/G]:�鿴ĳλ�����ߵ���ϸ��Ϣ\n" +
			"[ADD/A]:�����������Ϣ\n" +
			"[UPDATE/U]:������������Ϣ\n" +
			"[DELETE/D]:ɾ����������Ϣ\n" +
			"[SEARCH/S]:��ѯ��������Ϣ(�����������ֻ�������ѯ)\n" +
			"[EXIT/E]:�˳������߽���\n" +
			"[BREAK/B]:�˳���ǰ���ܣ��������˵�";

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
		
		//��ͼ�㷽��
		viewDao vd = new viewDao();
		//����ȫ�ֶ���
		overWatcher gs = new overWatcher();
		overWatchAction action = new overWatchAction();
		//�������
		String previous = null;
		//�������
		Integer step = 1;
		
		//���ֳ�������
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);//ɨ��ȴ��û�����
		while(scan.hasNext()){
					
			String content = scan.next().toString();
			
			//�������˵�
			if (       OPERATION_MAIN.equals(content.toUpperCase()) 
					|| OPERATION_MAIN.substring(0, 1).equals(content.toUpperCase())) {
				vd.returnToMenu();
				previous = null;
				step = 1;
				}
			
			//�˳�����
			else if (   OPERATION_EXIT.equals(content.toUpperCase()) 
					 || OPERATION_EXIT.substring(0, 1).equals(content.toUpperCase())) {
				vd.exitMenu();
				break;
			}
			
			//��ѯ��Ϣ�������û���
			else if (   OPERATION_QUERY.equals(content.toUpperCase()) 
					 || OPERATION_QUERY.substring(0, 1).equals(content.toUpperCase())
					 || OPERATION_QUERY.equals(previous)) {
				previous = OPERATION_QUERY;//�����ڴ˹�����
				vd.query();
				}
			
			//��ѯ�ض��û���Ϣ
			else if (   OPERATION_GET.equals(content.toUpperCase()) 
					 || OPERATION_GET.substring(0, 1).equals(content.toUpperCase())
					 || OPERATION_GET.equals(previous)) {
				previous = OPERATION_GET;//�����ڴ˹�����
				List<Map<String, Object>> params = new ArrayList<Map<String,Object>>();
				Map<String, Object> param = new HashMap<String, Object>();
				switch (step) {
				
				case 1:
					System.out.println("�������ѯ�˵ġ���������");
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
					System.out.println("��R��������ѯ����M���������˵�");
					step = 0;	
					break;
				}							
				if (OPERATION_GET.equals(previous)) {//switch֮��ִ�е����
					step++;
				}else{
					System.out.println("�������ֵΪ:" + content);
				}
			}
			
			//����û���Ϣ
			else if (    OPERATION_ADD.equals(content.toUpperCase()) 
					  || OPERATION_ADD.substring(0, 1).equals(content.toUpperCase())
				      || OPERATION_ADD.equals(previous)){
				previous = OPERATION_ADD;
				
				switch (step) {			
				case 1:
					System.out.println("�����������ߡ���������");
					break;
					
				case 2:
					gs.setUser_name(content);
					System.out.println("���������ߡ�������Ϊ��" + content);
					System.out.println("�����������ߡ��Ա𡿣�");
					break;
					
				case 3:
					gs.setSex(Integer.valueOf(content));
					System.out.println("�����������ߡ����䡿��");
					break;
					
				case 4:
					gs.setAge(Integer.valueOf(content));
					System.out.println("�����������ߡ����ա���(��ʽ��yyyy-MM-dd)");
					break;			
				case 5:
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date birthday = sf.parse(content);
						gs.setBirthday(birthday);
						System.out.println("�����������ߡ��������䡿��");
					} catch (Exception e) {
						//e.printStackTrace();
						System.out.println("��ʽ��������������~");
						step = 4;
					}
					break;
					
				case 6:
					gs.setEmail(content);
					System.out.println("�����������ߡ��绰����");
					break;
					
				case 7:
					gs.setMobile(content);
					System.out.println("�����봴���ˣ�");
					break;
					
				case 8:
					gs.setCreate_user(content);
					System.out.println("����������ˣ�");
					break;
					
				case 9:
					gs.setIsdel(Integer.valueOf(content));	
					try {							
						action.add(gs);
						System.out.println("��ӳɹ�");
						step = 0;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("���ʧ��");
					}
					break;
				}
				if (OPERATION_ADD.equals(previous)) {//switch֮��ִ�е����
					step++;
				}else{
					System.out.println("�������ֵΪ:" + content);
				}								
			}
			//ɾ���û���Ϣ
			else if (    OPERATION_DELETE.equals(content.toUpperCase()) 
					  || OPERATION_DELETE.substring(0, 1).equals(content.toUpperCase())
				      || OPERATION_DELETE.equals(previous)){
				previous = OPERATION_DELETE;
				
				switch (step) {
				case 1:
					System.out.println("������Ҫɾ����������id���");
					break;
				case 2:
					try {
						action.del(Integer.valueOf(content));
						System.out.println("ɾ���ɹ�~~");
					} catch (Exception e) {
						//e.printStackTrace();
						System.out.println("ɾ��ʧ�ܣ��������������~~");
						step = 0;
					}
				}
				if (OPERATION_DELETE.equals(previous)) {//switch֮��ִ�е����
					step++;
				}else{
					System.out.println("�������ֵΪ:" + content);
				}
			}
					
		}
	}
}
