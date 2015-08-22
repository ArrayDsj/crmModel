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
		this.initTable();
		this.initButton();
		this.initFindPanel();
		this.initData();
	}
	/**
	 * 初始化数据
	 */
	public void initData(){
		this.updateEmployeeTable(1);
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
				prevClick();
			}});
		
		LovoButton nextButton = new LovoButton("下一页",0,0,this);
		nextButton.setBounds(120, 400, 50, 20);
		
		nextButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				nextClick();
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
			}});
		
		
		LovoButton lbupdate = new LovoButton("修改员工信息",50,570,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = employeeTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"请选择行");
//					return;
//				}
				
				new EmployeeUpdateDialog(jf,key,EmployeePanel.this);
			}});
		
		
		LovoButton lbshow = new LovoButton("查看员工信息",250,570,this);
		lbshow.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = employeeTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"请选择行");
//					return;
//				}
				
				new EmployeeShowDialog(jf,key);
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
		
		lb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				findEmployee(1);

			}
		});
		
	}
	/**
	 * 设置总页数
	 * @param countPage 总页数
	 */
	private void setTotalPage(int countPage) {
		this.jltTotalPage.setText("/" + countPage + "  页");
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
		updateEmployeeTable(1);
		employeeTable.setSizeAndLocation(20, 90, 700, 300);
		
	}
	/**
	 * 更新表格数据
	 */
	private void updateEmployeeTable(int pageNO){
		//更新表格,插入所有员工List集合
		//从数据库中取出数据
		ArrayList<EmployeeBean> allEmps = new EmployeeServiceImp().getAllStaffs();
		employeeTable.updateLovoTable(allEmps);
		//设置总页数
		this.setTotalPage(2);
	}
	
	/**
	 * 上一页点击事件
	 */
	private void prevClick(){
		
	}
	/**
	 * 下一页点击事件
	 */
	private void nextClick(){
		
	}
	/**
	 * 转向指定页码
	 */
	private void goClick(String pageNO){
		
	}
	
	/**
	 * 删除员工
	 * @param employeeId 员工ID
	 */
	private void delEmployee(int employeeId){
		
		//更新表格,显示删除结果
		//调用数据库操作,删除选中的用户(只是更改status状态,原来为0,改为1)
		boolean dele = new EmployeeServiceImp().deleteStaff(employeeId);
		this.updateEmployeeTable(1);
	}
	/**
	 * 查找员工
	 *  item 条件选项
	 *  value 条件值
	 */
	private void findEmployee(int pageNO){
		//得到选项
		String item = itemCombox.getItem();
		//得到选项值
		String value = valueTxt.getText();
		
		//更新表格,显示查询结果
		employeeTable.updateLovoTable(null);
		//设置总页数
		this.setTotalPage(pageNO);
	}
}
