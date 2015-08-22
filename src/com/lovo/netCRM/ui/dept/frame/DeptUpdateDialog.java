package com.lovo.netCRM.ui.dept.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxtArea;
/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 部门修改对话框
 * 开发日期:2012-10-15
 */
public class DeptUpdateDialog extends JDialog{
	/**部门表格*/
	private DeptPanel deptPanel;
	/**部门id*/
	private int deptId;
	/**部门名称标签*/
	private LovoLabel nameLabel = new LovoLabel("部门名称",50,50,this);
	/**成立时间标签*/
	private LovoLabel timeLabel = new LovoLabel("成立时间",50,100,this);
	/**部门描述文本域*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("部门描述",50,150,120,60,this);
	public DeptUpdateDialog(JFrame jf,int deptId,DeptPanel deptPanel){
		super(jf,true);
		this.deptPanel = deptPanel;
		this.deptId = deptId;
		this.setLayout(null);
		this.setTitle("修改部门信息");
		
		this.init();
		
		this.setBounds(400, 150, 350, 350);
		this.setVisible(true);
	}
	/**
	 * 初始化方法
	 *
	 */
	private void init() {
		this.initData(this.deptId);
		
		LovoButton lbupdate = new LovoButton("修改",50,250,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = updateDept(deptId);
				if(isOk){
				DeptUpdateDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("取消",180,250,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				DeptUpdateDialog.this.dispose();
			}});
	}
	//---------------------------------------
	/**
	 * 初始化信息
	 */
	private void initData(int deptId) {
		
	}
	/**
	 * 修改部门信息
	 * @param deptId
	 */
	private boolean updateDept(int deptId){
		
		//更新表格，显示修改结果
		this.deptPanel.initData();
		
		return true;
	}
}
