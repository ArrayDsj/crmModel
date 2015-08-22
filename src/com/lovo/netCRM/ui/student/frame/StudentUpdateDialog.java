package com.lovo.netCRM.ui.student.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoRadioButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.component.LovoTxtArea;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 学生信息修改对话框
 * 开发日期:2012-10-14
 */
public class StudentUpdateDialog extends JDialog{
	/**学生主面板*/
	private StudentPanel studentPanel;
	/**姓名标签*/
	private LovoLabel nameLabel = new LovoLabel("姓    名",50,50,this);
	/**性别标签*/
	private LovoLabel sexLabel = new LovoLabel("性    别",320,50,this);
	/**出生日期标签*/
	private LovoLabel birthdayLabel = new LovoLabel("出生日期",50,100,this);
	/**所属班级*/
	private LovoComboBox classTxt;
	/**家庭地址标签*/
	private LovoLabel addressLabel = new LovoLabel("家庭地址",50,150,this);
	/**联系电话文本框*/
	private LovoTxt phoneTxt = new LovoTxt("联系电话",320,150,this);
	/**父亲标签*/
	private LovoLabel fatherLabel = new LovoLabel("父    亲",50,200,this);
	/**父亲电话文本框*/
	private LovoTxt fatherPhoneTxt = new LovoTxt("父亲电话",320,200,this);
	/**母亲标签*/
	private LovoLabel mumLabel = new LovoLabel("母    亲",50,250,this);
	/**母亲电话文本框*/
	private LovoTxt mumPhoneTxt = new LovoTxt("母亲电话",320,250,this);
	/**学生状态下拉框*/
	private LovoComboBox<String> stateTxt = new LovoComboBox<String>("学生状态",
			new String[]{"会员",
			"非会员"},50,300,this);
	/**备注文本域*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("备    注",320,300,200,120,this);
	/**学校id*/
	private int schoolId;
	/**学生id*/
	private int studentId;
	
	public StudentUpdateDialog(JFrame jf,int schoolId,int studentId,StudentPanel studentPanel){
		super(jf,true);
		this.studentPanel = studentPanel;
		this.studentId = studentId;
		this.schoolId = schoolId;
		this.setLayout(null);
		this.setTitle("修改学生信息");
		
		this.init();
		
		this.setBounds(300, 150, 650, 530);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		this.initComboBox(schoolId);
		this.initData(studentId);

		LovoButton lbadd = new LovoButton("修改",200,450,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = updateStudent(studentId);
				if(isOk){
				StudentUpdateDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("取消",400,450,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				StudentUpdateDialog.this.dispose();
			}});
	}
	
	//----------------------
	/**
	 * 初始化班级下拉框
	 *
	 */
	private void initComboBox(int schoolId){
//		添加班级集合
		this.classTxt = new LovoComboBox("所属班级",new ArrayList(),320,100,this);
	}
	
	/**
	 * 初始化数据
	 * @param studentId 学生ID
	 */
	private void initData(int studentId){
		
	}

	/**
	 * 修改学生
	 * @param studentId 学生ID
	 */
	private boolean updateStudent(int studentId){
		//验证数据
		
		//封装实体
		
		//更新表格，显示修改结果
		this.studentPanel.initData();
		
		return true;
	}
}
