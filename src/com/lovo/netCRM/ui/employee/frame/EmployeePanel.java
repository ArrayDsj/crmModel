package com.lovo.netCRM.ui.employee.frame;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.service.imp.EmployeeServiceImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 员工管理面板
 * 开发日期:2012-10-5
 */
public class EmployeePanel extends JPanel{
	/**员工表格组件*/
	private LovoTable employeeTable;
	/**窗体组件*/
	private JFrame jf;
	/**页码文本框*/
	private JTextField pageNOTxt = new JTextField("1");
	/**总页数标签*/
	private JLabel jltTotalPage = new JLabel("/   页");
	/**选项下拉框*/
	private  LovoComboBox<String> itemCombox;
	/**值文本框*/
	private  JTextField valueTxt = new JTextField();

	//当前页
	private static int pageNow = 1;
	//分页大小
	private final int pageSize = 3;
	//总记录数
	private  static int counts;
	//总页数
	private static int pageNum;

	private String item = null;

	private String value = "";

	private boolean onceFind = false;

	public EmployeePanel(JFrame jf){

		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		new LovoTitleLabel("员 工 管 理",this);
		//初始化表格
		this.initTable();
		//初始化按钮
		this.initButton();
		//查询按钮页
		this.initFindPanel();
		//初始化数据
		this.initData();
	}
	/**
	 * 初始化数据
	 */
	public void initData(){
		onceFind = false;
		pageNow = 1;
		this.updateEmployeeTable();//pageNow=1
	}
	/**
	 * 初始化按钮
	 *
	 */
	private void initButton() {
		LovoButton prevButton = new LovoButton("上一页",0,0,this);
		prevButton.setBounds(50, 400, 50, 20);
		
		prevButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(pageNow > 1){
					pageNOTxt.setText(--pageNow + "");
					prevClick();
				}

			}});
		
		LovoButton nextButton = new LovoButton("下一页",0,0,this);
		nextButton.setBounds(120, 400, 50, 20);
		
		nextButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(pageNow < pageNum){
					pageNOTxt.setText(++pageNow + "");
					nextClick();
				}

			}});
		
		JLabel jld = new JLabel("第");
		jld.setBounds(200, 400, 20, 20);
		this.add(jld);
		
		pageNOTxt.setBounds(215, 400, 20, 20);
		this.add(pageNOTxt);
		
		
		jltTotalPage.setBounds(240, 400, 50, 20);
		this.add(jltTotalPage);
		
		LovoButton goButton = new LovoButton("go",0,0,this);
		goButton.setBounds(295, 400, 25, 20);
		goButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				goClick(pageNOTxt.getText());
			}});
		
		
		LovoButton lbadd = new LovoButton("添加新员工",50,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				new EmployeeAddDialog(jf,EmployeePanel.this);
				//设置初始化
				onceFind = false;
				/**
				 * 判断是否翻页
				 */
				EmployeeBean empCounts = (EmployeeBean) new EmployeeServiceImp().getAllStaffs("所有员工", "").get(0);
				//得到满足条件的总记录数
				int allCounts = empCounts.getID();
				counts = allCounts;
				//得到未添加学生的学生总页数
				int oldPageNum = pageNum;
				int newPageNum = (int) Math.ceil(counts / (pageSize * 1.0));

				if(oldPageNum < newPageNum){//增加了总页数
					pageNum = newPageNum;
				}
				//跳转到更新的那一页
				ArrayList<Object> checkEmps = new EmployeeServiceImp().getAllStaffs(pageNum, pageSize, "所有员工", "");
				//设置总页数
				setTotalPage(pageNum);
				//设置当前页为最后一页
				pageNow = pageNum;
				pageNOTxt.setText(pageNow+"");
				employeeTable.updateLovoTable(checkEmps);
			}});
		
		LovoButton lbdel = new LovoButton("删除员工",250,500,this);
		lbdel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = employeeTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				delEmployee(key);
				onceFind = false;
			}});
		
		
		LovoButton lbupdate = new LovoButton("修改员工信息",50,570,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = employeeTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				new EmployeeUpdateDialog(jf,key,EmployeePanel.this);
				onceFind = false;
			}});
		
		
		LovoButton lbshow = new LovoButton("查看员工信息",250,570,this);
		lbshow.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = employeeTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				new EmployeeShowDialog(jf,key);
				onceFind = false;
			}});
	}
	/**
	 * 初始化查询按钮
	 *
	 */
	private void initFindPanel(){
		LovoTitlePanel jp = new LovoTitlePanel("查询员工",400, 480, 320, 150,this);
		this.itemCombox = new LovoComboBox<String>(
				new String[]{"所有员工","员工姓名","所属部门",
						"文化程度","工作职位"},30,50,jp);
		valueTxt.setBounds(160, 50, 120, 20);
		jp.add(valueTxt);
		LovoButton lb = new LovoButton("查找",180,100,jp);
		lb.setSize(60, 20);

		//点击查询事件
		lb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findEmployee();
			}
		});
		
	}
	/**
	 * 设置总页数
	 *
	 */
	private void setTotalPage(int pageNum) {
		this.jltTotalPage.setText("/" + pageNum + "  页");
	}
	
	
	//-------------------------------------------------
	/**
	 * 初始化表格
	 *
	 */
	private void initTable() {
		employeeTable = new LovoTable(this,
				new String[]{"姓名","性别","出生日期","文化程度","联系方式","所在部门", "工作职位"},
				//员工实体属性名数组 new String[]{"employeeName","sex"}
				new String[]{"name","sex","birthday","edu","phone","dept","position"},
				//主键属性名 employeeId
				"ID");
		//调用一次数据库数据
		updateEmployeeTable();//pageNow=1
		employeeTable.setSizeAndLocation(20, 90, 700, 300);
	}
	/**
	 * 更新表格数据
	 */
	private void updateEmployeeTable(){//只有初始化和添加员工成功后才会调用
		//默认查找全部员工
		ArrayList<Object> limitAllEmps = new EmployeeServiceImp().getAllStaffs(1, pageSize, "所有员工", "");
		ArrayList<Object> allEmps =new EmployeeServiceImp().getAllStaffs();
		//无条件下总记录数
		counts = allEmps.size();
		pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
		//设置总页数
		pageNOTxt.setText(pageNow + "");
		this.setTotalPage(pageNum);//总页数就是无条件下的全部记录总数除以分页大小
		employeeTable.updateLovoTable(limitAllEmps);
	}
	
	/**
	 * 上一页点击事件
	 */
	private void prevClick(){
		ArrayList<Object> limitEmps = null;
		if(!onceFind){//如果没有点击查询 则是按全部的来显示
			limitEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, "所有员工", "");
			if(limitEmps != null){//如果为空,就不更新表格
				employeeTable.updateLovoTable(limitEmps);
			}
		}else{
			limitEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, item, value);
			if(limitEmps != null){//如果为空,就不更新表格
				employeeTable.updateLovoTable(limitEmps);
			}
		}

	}
	/**
	 * 下一页点击事件
	 */
	private void nextClick(){
		ArrayList<Object> limitEmps = null;
		if(!onceFind){
			limitEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, "所有员工", "");
			if(limitEmps != null){//如果为空,就不更新表格
				employeeTable.updateLovoTable(limitEmps);
			}
		}else{
			limitEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, item, value);
			if(limitEmps != null){//如果为空,就不更新表格
				employeeTable.updateLovoTable(limitEmps);
			}
		}
	}
	/**
	 * 转向指定页码
	 */
	private void goClick(String pageNO){
		ArrayList<Object> limitEmps = null;
		if(item == null){
			item = "所有员工";
		}
		int page = -1;
		try{
			page = Integer.parseInt(pageNO);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"输入错误");
		}
		if(page <= pageNum){//如果输入的页数小于等于总页数
			pageNow = Integer.parseInt(pageNO);//跳转到用户输入的页数
			limitEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, item, value);
		}
		if(limitEmps != null){
			employeeTable.updateLovoTable(limitEmps);
		}
	}
	
	/**
	 * 删除员工
	 * @param employeeId 员工ID
	 */
	private void delEmployee(int employeeId){
		//更新表格,显示删除结果
		//调用数据库操作,删除选中的用户(只是更改status状态,原来为0,改为1)
		if((JOptionPane.showConfirmDialog(null,"是否删除选中员工信息","删除",JOptionPane.YES_NO_OPTION)) == 0){
			boolean dele = new EmployeeServiceImp().deleteStaff(employeeId);
			if(dele){
				if(!onceFind){//没有点击查询时,无条件查询
					//总记录数
					ArrayList<Object> allEmps =new EmployeeServiceImp().getAllStaffs();
					counts = allEmps.size();
					pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
					if(pageNow > pageNum){//如果删除的最后一个是那一页的第一个
						ArrayList<Object> limitAllEmps = new EmployeeServiceImp().getAllStaffs(pageNum, pageSize, "所有员工", "");
						pageNow = pageNum;
						this.setTotalPage(pageNum);
						pageNOTxt.setText(pageNum+"");
						employeeTable.updateLovoTable(limitAllEmps);
					}else{
						ArrayList<Object> limitAllEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, "所有员工", "");
						this.setTotalPage(pageNow);
						employeeTable.updateLovoTable(limitAllEmps);
					}
				}else{
					EmployeeBean empCounts = (EmployeeBean) new EmployeeServiceImp().getAllStaffs(item, value).get(0);
					//得到满足条件的总记录数
					int allCounts = empCounts.getID();
					counts = allCounts;
					pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
					if(pageNow > pageNum){//如果删除的最后一个是那一页的第一个
						ArrayList<Object> limitAllEmps = new EmployeeServiceImp().getAllStaffs(pageNum, pageSize,  item, value);
						pageNow = pageNum;
						this.setTotalPage(pageNum);
						pageNOTxt.setText(pageNum+"");
						employeeTable.updateLovoTable(limitAllEmps);
					}else{
						ArrayList<Object> limitAllEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, item, value);
						this.setTotalPage(pageNow);
						employeeTable.updateLovoTable(limitAllEmps);
					}
				}

			}
		}
	}
	/**
	 * 	查找员工
	 *  item 条件选项
	 *  value 条件值
	 */
	private void findEmployee(){
		onceFind = true;
		//得到选项(条件)
		item = itemCombox.getItem();
		//得到选项值(模糊查询条件)
		value = valueTxt.getText();
		EmployeeBean empCounts = (EmployeeBean) new EmployeeServiceImp().getAllStaffs(item, value).get(0);
		//得到满足条件的总记录数
		int allCounts = empCounts.getID();
		counts = allCounts;
		if(allCounts != 0){
			ArrayList<Object> checkEmps = new EmployeeServiceImp().getAllStaffs(1, pageSize, item, value);
			//满足条件的所有记录的总和
			//更新表格,显示查询结果
			if(checkEmps != null){
				//JOptionPane.showMessageDialog(null,checkEmps.size());
				employeeTable.updateLovoTable(checkEmps);
				pageNum = (int) Math.ceil(allCounts / (pageSize * 1.0));
				this.setTotalPage(pageNum);
				pageNow = 1;
				pageNOTxt.setText("1");
			}
		}else {
			JOptionPane.showMessageDialog(null, "无查询结果");
			onceFind = false;
			valueTxt.setText("");
			//总记录数
			ArrayList<Object> allEmps =new EmployeeServiceImp().getAllStaffs();
			counts = allEmps.size();
			pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
			ArrayList<Object> limitAllEmps = new EmployeeServiceImp().getAllStaffs(1, pageSize, "所有员工", "");
			pageNow = 1;
			this.setTotalPage(pageNum);
			pageNOTxt.setText(pageNow+"");
			employeeTable.updateLovoTable(limitAllEmps);
		}
	}
}
