package com.lovo.netCRM.ui.dept.frame;

import com.lovo.netCRM.bean.DepartBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.component.LovoTxtArea;
import com.lovo.netCRM.service.imp.DepartServiceImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 部门添加对话框
 * 开发日期:2012-10-6
 */
public class DeptAddDialog extends JDialog{
	/**部门主面板*/
	private DeptPanel deptPanel;
	/**部门名称文本框*/
	private LovoTxt nameTxt = new LovoTxt("部门名称",50,50,this);
	/**成立时间文本框*/
	private LovoDate timeTxt = new LovoDate("成立时间",50,100,this);
	/**部门描述文本域*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("部门描述",50,150,120,60,this);
	public DeptAddDialog(JFrame jf,DeptPanel deptPanel){
		super(jf,true);
		this.deptPanel = deptPanel;
		this.setLayout(null);
		this.setTitle("添加新部门");
		
		this.init();
		
		this.setBounds(400, 150, 350, 350);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		LovoButton lbadd = new LovoButton("添加",50,250,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addDept();
				if(isOk){
					DeptAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("取消",180,250,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				DeptAddDialog.this.dispose();
			}});
	}
	
	//----------------------
	/**
	 * 添加部门
	 */
	private boolean addDept(){
		//验证数据，数据验证失败返回false
		DepartBean newDept = new DepartBean();
		String errorStr = "";
		if(!this.nameTxt.getText().matches("[\\u4e00-\\u9fa5]{2,10}")){
			errorStr += "部门名称格式错误(2到10个汉字)\n";
		}
		if(!this.descriptionTxt.getText().matches("[a-zA-Z0-9\\u4e00-\\u9fa5]{2,10}")){
			errorStr += "描述格式错误\n";
		}
		if(errorStr.length() != 0) {
			JOptionPane.showMessageDialog(null, errorStr);
			return false;
		}
		//封装实体
		else{
			newDept.setDepartName(nameTxt.getText());
			newDept.setBuildTime(timeTxt.getDate());
			newDept.setDescribe(descriptionTxt.getText());
		}
		//存入数据库
		new DepartServiceImp().addDept(newDept);
		//更新表格，显示添加部门结果
		deptPanel.initData();
		return true;
	}
}
