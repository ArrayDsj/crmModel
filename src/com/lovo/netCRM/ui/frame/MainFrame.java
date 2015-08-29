package com.lovo.netCRM.ui.frame;

import com.lovo.netCRM.bean.EmployeeBean;
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
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 布局主界面
 * 开发日期:2012-4-20
 */
public class MainFrame extends JFrame{
	/**树形菜单*/
	private LovoTree tree;
	/**右边的容器组件*/
	private JPanel mainPanel = new JPanel();
	/**卡片布局*/
	private CardLayout card = new CardLayout();
	/**登陆用户实体*/
	private EmployeeBean userObj;
	/**员工主面板*/
	private EmployeePanel emPanel ;
	/**部门主面板*/
	private DeptPanel deptPanel = new DeptPanel(this);
	/**职位主面板*/
	private WorkPanel workPanel = new WorkPanel(this);
	/**学校活动主面板*/
	private SchoolActivePanel schoolActivePanel =  new SchoolActivePanel(this);
	/**学校管理主面板*/
	private SchoolPanel schoolPanel ;
	/**班级管理主面板*/
	private ClassManagerPanel classManagerPanel;
	/**学生主面板*/
	 StudentPanel studentPanel = new StudentPanel(this);
	
	/**地区统计主面板*/
	private AddressCountPanel addressCountPanel = new AddressCountPanel();
	/**学校统计主面板*/
	private SchoolCountPanel schoolCountPanel = new SchoolCountPanel();

	public EmployeeBean getUserObj() {
		return userObj;
	}

	public void setUserObj(EmployeeBean userObj) {
		this.userObj = userObj;
	}

	public MainFrame(Object userObj){
		super("四川网脉CRM系统");
		this.userObj = (EmployeeBean) userObj;

		setTitle(((EmployeeBean) userObj).getName());
		this.initTree();
		this.initPanel();
		
		
		this.setSize(1000,700);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
	}
	/**
	 * 初始化上面的容器
	 *
	 */
	private void initPanel() {
		//设置容器的布局管理器为卡片布局
		System.out.println(userObj.getName() + "登录了");
		mainPanel.setLayout(this.card);
		this.add(mainPanel);

		schoolPanel = new SchoolPanel(this);
		emPanel = new EmployeePanel(this);
		classManagerPanel = new ClassManagerPanel(this);
		//给卡布局的容器添加面板，每加上一个面板，由第二个参数给面板取名
		mainPanel.add(new InitPanel(),"init");//给主面板添加一张图片
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
	 * 设置树形菜单
	 *
	 */
	private void initTree() {
		//根节点
		LovoTreeNode rootNode = new LovoTreeNode("四川网脉CRM系统");
		
		//枝节点
		LovoTreeNode sorceNode = new LovoTreeNode("资料管理");
		LovoTreeNode schoolNode = new LovoTreeNode("学校管理");
		LovoTreeNode userNode = new LovoTreeNode("用户管理");
		LovoTreeNode countNode = new LovoTreeNode("统计信息");
		
		//叶节点 员工管理
		LovoTreeNode employeeNode = new LovoTreeNode("员工管理"){
			
			public void addListener(){
				//EmployeePanel面板,在MainFrame被创建的时候,所有面板均被创建
				//然后调用初始化数据的方法
				emPanel.initData();
				//切换到"employee"标签指定的面板
				card.show(mainPanel, "employee");
			}
		};
		
		//部门管理
		LovoTreeNode deptNode = new LovoTreeNode("部门管理"){
			
			public void addListener(){
				deptPanel.initData();
				card.show(mainPanel, "dept");
			}
		};

		//职位管理
		LovoTreeNode workNode = new LovoTreeNode("职位管理"){
			
			public void addListener(){
				workPanel.initData();
				card.show(mainPanel, "work");
			}
		};


		LovoTreeNode activeNode = new LovoTreeNode("学校活动"){
			public void addListener(){
				schoolActivePanel.initData();
				card.show(mainPanel, "active");
			}
		};
		LovoTreeNode smNode = new LovoTreeNode("学校管理"){
			public void addListener(){
				schoolPanel.initData();
				card.show(mainPanel, "school");
			}
		};
		
		LovoTreeNode studentNode = new LovoTreeNode("学生管理"){
			public void addListener(){
				studentPanel.setSchoolId(0);
				studentPanel.initData();
				card.show(mainPanel, "student");
			}
		};
		
		LovoTreeNode classNode = new LovoTreeNode("班级管理"){
			public void addListener(){
				studentPanel.setSchoolId(0);
				classManagerPanel.initData();
				card.show(mainPanel, "class");
			}
		};
		
		
		LovoTreeNode areaNode = new LovoTreeNode("地区统计"){
			public void addListener(){
				addressCountPanel.initData();
				card.show(mainPanel, "addressCount");
			}
		};
		
		LovoTreeNode scNode = new LovoTreeNode("学校统计"){
			public void addListener(){
				schoolCountPanel.initData();
				card.show(mainPanel, "schoolCount");
			}
		};



		//将枝节点加入根节点。其中添加的节点要根据登陆用户的等级决定
		/**
		 * 根据权限显示节点
		 */
		//查询权限
		boolean checkRight = userObj.getPos().isCheckRight();
		//考核权限
		boolean queryRight = userObj.getPos().isQueryRight();
		//销售统计权限
		boolean saleRight = userObj.getPos().isSaleRight();
		//权限管理权限
		boolean managerRight = userObj.getPos().isManagerRight();
		//后台管理权限
		boolean backRight = userObj.getPos().isBackRight();

		//学校管理
		schoolNode.add(activeNode);
		schoolNode.add(smNode);

		//用户管理
		userNode.add(studentNode);
		userNode.add(classNode);

		//统计信息
		countNode.add(areaNode);
		countNode.add(scNode);

		if(managerRight){
			sorceNode.add(employeeNode);
			sorceNode.add(deptNode);
			if(backRight){
				sorceNode.add(workNode);
			}
			rootNode.add(sorceNode);
		}
		if(checkRight){
			rootNode.add(schoolNode);
			rootNode.add(userNode);
		}
		if(saleRight){
			rootNode.add(countNode);
		}

		//创建树形菜单
		this.tree = new LovoTree(rootNode);
		//设置树形菜单的字体
		this.tree.setFont(new Font("微软雅黑",Font.BOLD,20));
		
		//将树形菜单加入窗体
		this.add(this.tree,BorderLayout.WEST);
	}
}
