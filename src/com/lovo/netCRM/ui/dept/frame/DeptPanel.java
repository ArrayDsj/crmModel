package com.lovo.netCRM.ui.dept.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 部门管理面板
 * 开发日期:2012-10-6
 */
public class DeptPanel extends JPanel{
	/**部门表格组件*/
	private LovoTable deptTable;
	/**窗体对象*/
	private JFrame jf;
	public DeptPanel(JFrame jf){
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		new LovoTitleLabel("部 门 管 理",this);
		this.initTable();
		this.initButton();
		this.initData();
	}
	/**
	 * 初始化数据
	 */
	public void initData(){
		this.updateDeptTable();
	}
	/**
	 * 初始化按钮
	 *
	 */
	private void initButton() {
		LovoButton lbadd = new LovoButton("添加新部门",200,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				new DeptAddDialog(jf,DeptPanel.this);
			}});
		
		
		LovoButton lbupdate = new LovoButton("修改部门信息",400,500,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = deptTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"请选择行");
//					return;
//				}
				
				new DeptUpdateDialog(jf,key,DeptPanel.this);
			}});
	}
	
	//--------------------------
	
	/**
	 * 初始化表格
	 */
	private void initTable() {
		deptTable = new LovoTable(this,
				new String[]{"部门名称","成立时间","部门描述"},
				new String[]{},//部门实体属性名数组 new String[]{"deptName","time"}
				"");//主键属性名 deptId
		deptTable.setSizeAndLocation(20, 90, 700, 300);
		
	}
	/**
	 * 更新部门表格
	 */
	private void updateDeptTable(){
		//更新表格,插入部门List集合
		deptTable.updateLovoTable(null);
	}
}
