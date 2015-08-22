package com.lovo.netCRM.ui.school.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.component.LovoTxtArea;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 添加沟通记录对话框
 * 开发日期:2012-10-12
 */
public class SchoolCommunicateAddDialog extends JDialog{
	/**学校id*/
	private int schoolId;
	/**沟通时间文本框*/
	private LovoDate timeTxt = new LovoDate("沟通时间",50,50,this);
	/**校方联系人文本框*/
	private LovoTxt connectorTxt = new LovoTxt("校方联系人",50,100,this);
	/**职务文本框*/
	private LovoTxt jobTxt = new LovoTxt("职务",50,150,this);
	/**联系人*/
	private LovoComboBox employeeTxt;
	
	/**沟通内容*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("沟通内容",50,250,120,60,this);
	public SchoolCommunicateAddDialog(JFrame jf,int schoolId){
		super(jf,true);
		this.schoolId = schoolId;
		this.setLayout(null);
		this.setTitle("添加沟通记录");
		
		this.init();
		
		this.setBounds(400, 130, 350, 420);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		this.initComboBox(schoolId);
		LovoButton lbadd = new LovoButton("添加",50,340,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addCommunicate(schoolId);
				if(isOk){
					SchoolCommunicateAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("取消",180,340,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolCommunicateAddDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * 初始化负责人下拉框
	 *
	 */
	private void initComboBox(int schoolId){

		//添加负责人集合
		this.employeeTxt = new LovoComboBox("负责人",new ArrayList(),50,200,this);
		
	}
	/**
	 * 添加沟通记录
	 * @param schoolId 学校ID
	 */
	private boolean addCommunicate(int schoolId){
		//验证数据，验证失败，返回false
		
		//封装实体
		
		return true;
		
	}
}
