package com.lovo.netCRM.ui.student.frame;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.bean.RecallRecordBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.dao.imp.EmployeeDaoImp;
import com.lovo.netCRM.dao.imp.RecallRecordDaoImp;
import com.lovo.netCRM.service.imp.ConnectionServiceImp;
import com.lovo.netCRM.service.imp.EmployeeServiceImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

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
        ArrayList<Object> allEmp = new ConnectionServiceImp().getAllEmpBySchoolID(schoolId);
		this.employeeTxt = new LovoComboBox("负责人",allEmp,50,100,this);
		
	}
	/**
	 * 添加回访记录
	 * @param studentId 学生ID
	 */
	private void addVisit(int studentId){
		//验证数据
        RecallRecordBean recall = new RecallRecordBean();
        //验证数据,验证失败返回false
        String error = "";
        if(timeTxt.getText() == null || timeTxt.getText().equals("")){
            error += "沟通时间不能为空\n";
        }
        if(connectorTxt.getText() == null || connectorTxt.getText().equals("")){
            error += "回访人不能为空\n";
        }
        if(titleTxt.getText() == null || titleTxt.getText().equals("")){
            error += "回访主题不能为空\n";
        }
        if(employeeTxt.getItem() == null || employeeTxt.getItem().equals("")){
            error += "请选择负责人\n";
        }if(descriptionTxt.getText() == null || descriptionTxt.getText().equals("")){
            error += "内容不能为空\n";
        }
        if(error.length() != 0) {
            JOptionPane.showMessageDialog(null, error);
        }
        recall.setTime(timeTxt.getDate());
        recall.setRecallMan(connectorTxt.getText());
        recall.setTitle(titleTxt.getText());
        recall.setEmp(new EmployeeServiceImp().getEmpByName(employeeTxt.getItem().toString()));
        recall.setDescribe(descriptionTxt.getText());
        new RecallRecordDaoImp().addObject(studentId,recall);
	}
}
