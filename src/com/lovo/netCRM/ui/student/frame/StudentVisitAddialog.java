package com.lovo.netCRM.ui.student.frame;

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
 * @description 添加学生回访记录
 * 开发日期:2012-10-14
 */
public class StudentVisitAddialog extends JDialog{
	/**学生id*/
	private int studentId;
	/**回访时间文本框*/
	private LovoDate timeTxt = new LovoDate("回访时间",50,50,this);
	/**负责人*/
	private LovoComboBox employeeTxt;
	/**回访人文本框*/
	private LovoTxt connectorTxt = new LovoTxt("回访人",50,150,this);
	/**回访主题文本框*/
	private LovoTxt titleTxt = new LovoTxt("回访主题",50,200,this);
	/**回访内容*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("回访内容",50,250,120,60,this);
	/**学校id*/
	private int schoolId;
	public StudentVisitAddialog(JFrame jf,int studentId,int schoolId){
		super(jf,true);
		this.studentId =  studentId;
		this.schoolId =  schoolId;
		this.setLayout(null);
		this.setTitle("添加回访记录");
		
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
				addVisit( studentId);
				StudentVisitAddialog.this.dispose();
			}});
		
		LovoButton lbcancel = new LovoButton("取消",180,340,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				StudentVisitAddialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * 初始化负责人下拉框
	 * @param schoolObj 学校对象
	 */
	private void initComboBox(int schoolId){

		//添加负责人集合
		this.employeeTxt = new LovoComboBox("负责人",new ArrayList(),50,100,this);
		
	}
	/**
	 * 添加回访记录
	 * @param studentId 学生ID
	 */
	private void addVisit(int studentId){
		//验证数据
		
		//封装实体
		
	}
}
