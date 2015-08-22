package com.lovo.netCRM.ui.frame;

import com.lovo.netCRM.component.LovoTree;
import com.lovo.netCRM.component.LovoTreeNode;
import com.lovo.netCRM.ui.classManager.frame.ClassManagerPanel;
import com.lovo.netCRM.ui.count.frame.AddressCountPanel;
import com.lovo.netCRM.ui.count.frame.SchoolCountPanel;
import com.lovo.netCRM.ui.dept.frame.DeptPanel;
import com.lovo.netCRM.ui.employee.frame.EmployeePanel;
import com.lovo.netCRM.ui.school.frame.SchoolPanel;
import com.lovo.netCRM.ui.schoolActive.frame.SchoolActivePanel;
import com.lovo.netCRM.ui.student.frame.StudentPanel;
import com.lovo.netCRM.ui.work.frame.WorkPanel;

import javax.swing.*;
import java.awt.*;


/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ����������
 * ��������:2012-4-20
 */
public class MainFrame extends JFrame{
	/**���β˵�*/
	private LovoTree tree;
	/**�ұߵ��������*/
	private JPanel mainPanel = new JPanel();
	/**��Ƭ����*/
	private CardLayout card = new CardLayout();
	/**��½�û�ʵ��*/
	private Object userObj;
	/**Ա�������*/
	private EmployeePanel emPanel = new EmployeePanel(this);
	/**���������*/
	private DeptPanel deptPanel = new DeptPanel(this);
	/**ְλ�����*/
	private WorkPanel workPanel = new WorkPanel(this);
	/**ѧУ������*/
	private SchoolActivePanel schoolActivePanel =  new SchoolActivePanel(this);
	/**ѧУ���������*/
	private SchoolPanel schoolPanel = new SchoolPanel(this);
	/**�༶���������*/
	private ClassManagerPanel classManagerPanel = new ClassManagerPanel(this);
	/**ѧ�������*/
	private StudentPanel studentPanel = new StudentPanel(this);
	
	/**����ͳ�������*/
	private AddressCountPanel addressCountPanel = new AddressCountPanel();
	/**ѧУͳ�������*/
	private SchoolCountPanel schoolCountPanel = new SchoolCountPanel();
	public MainFrame(Object userObj){
		super("�Ĵ�����CRMϵͳ");
		this.userObj = userObj;
		this.initTree();
		this.initPanel();
		
		
		this.setSize(1000,700);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
	}
	/**
	 * ��ʼ�����������
	 *
	 */
	private void initPanel() {
		//���������Ĳ��ֹ�����Ϊ��Ƭ����
		mainPanel.setLayout(this.card);
		this.add(mainPanel);
		
		//�������ֵ����������壬ÿ����һ����壬�ɵڶ������������ȡ��
		mainPanel.add(new InitPanel(),"init");
		mainPanel.add(emPanel,"employee");
		mainPanel.add(deptPanel,"dept");
		mainPanel.add(workPanel,"work");
		mainPanel.add(schoolActivePanel,"active");
		mainPanel.add(schoolPanel,"school");
		mainPanel.add(classManagerPanel,"class");
		mainPanel.add(studentPanel,"student");
		mainPanel.add(addressCountPanel,"addressCount");
		mainPanel.add(schoolCountPanel,"schoolCount");
	}
	
	
	/**
	 * �������β˵�
	 *
	 */
	private void initTree() {
		//���ڵ�
		LovoTreeNode rootNode = new LovoTreeNode("�Ĵ�����CRMϵͳ");
		
		//֦�ڵ�
		LovoTreeNode sorceNode = new LovoTreeNode("���Ϲ���");
		LovoTreeNode schoolNode = new LovoTreeNode("ѧУ����");
		LovoTreeNode userNode = new LovoTreeNode("�û�����");
		LovoTreeNode countNode = new LovoTreeNode("ͳ����Ϣ");
		
		//Ҷ�ڵ�
		LovoTreeNode employeeNode = new LovoTreeNode("Ա������"){
			
			public void addListener(){
				emPanel.initData();
				//�л���"employee"��ǩָ�������
				card.show(mainPanel, "employee");
			}
		};
		
		
		LovoTreeNode deptNode = new LovoTreeNode("���Ź���"){
			
			public void addListener(){
				//deptPanel.initData();
				card.show(mainPanel, "dept");
			}
		};
		
		LovoTreeNode workNode = new LovoTreeNode("ְλ����"){
			
			public void addListener(){
				//workPanel.initData();
				card.show(mainPanel, "work");
			}
		};
		
		LovoTreeNode activeNode = new LovoTreeNode("ѧУ�"){
			public void addListener(){
				//schoolActivePanel.initData();
				card.show(mainPanel, "active");
			}
		};
		LovoTreeNode smNode = new LovoTreeNode("ѧУ����"){
			public void addListener(){
				//schoolPanel.initData();
				card.show(mainPanel, "school");
			}
		};
		
		LovoTreeNode studentNode = new LovoTreeNode("ѧ������"){
			public void addListener(){
				//studentPanel.setSchoolId(0);
				//studentPanel.initData();
				card.show(mainPanel, "student");
			}
		};
		
		LovoTreeNode classNode = new LovoTreeNode("�༶����"){
			public void addListener(){
				//studentPanel.setSchoolId(0);
				//classManagerPanel.initData();
				card.show(mainPanel, "class");
			}
		};
		
		
		LovoTreeNode areaNode = new LovoTreeNode("����ͳ��"){
			public void addListener(){
				//addressCountPanel.initData();
				card.show(mainPanel, "addressCount");
			}
		};
		
		LovoTreeNode scNode = new LovoTreeNode("ѧУͳ��"){
			public void addListener(){
				//schoolCountPanel.initData();
				card.show(mainPanel, "schoolCount");
			}
		};
		
		//��Ҷ�ڵ����֦�ڵ�
		sorceNode.add(employeeNode);
		sorceNode.add(deptNode);
		sorceNode.add(workNode);
		
		schoolNode.add(activeNode);
		schoolNode.add(smNode);
		
		userNode.add(studentNode);
		userNode.add(classNode);
		
		countNode.add(areaNode);
		countNode.add(scNode);
		
		//��֦�ڵ������ڵ㡣������ӵĽڵ�Ҫ���ݵ�½�û��ĵȼ�����
		int grade = 9;
		
		//-------------------------------
		if(grade == 0){
			rootNode.add(sorceNode);
		}else{
			rootNode.add(sorceNode);
			rootNode.add(schoolNode);
			rootNode.add(userNode);
			rootNode.add(countNode);
		}
		
		
		//�������β˵�
		this.tree = new LovoTree(rootNode);
		//�������β˵�������
		this.tree.setFont(new Font("�����п�",Font.BOLD,25));
		
		//�����β˵����봰��
		this.add(this.tree,BorderLayout.WEST);
	}
}
