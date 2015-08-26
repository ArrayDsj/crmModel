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
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ���ѧ���طü�¼
 * ��������:2012-10-14
 */
public class StudentVisitAddialog extends JDialog{
	/**ѧ��id*/
	private int studentId;
	/**�ط�ʱ���ı���*/
	private LovoDate timeTxt = new LovoDate("�ط�ʱ��",50,50,this);
	/**������*/
	private LovoComboBox employeeTxt;
	/**�ط����ı���*/
	private LovoTxt connectorTxt = new LovoTxt("�ط���",50,150,this);
	/**�ط������ı���*/
	private LovoTxt titleTxt = new LovoTxt("�ط�����",50,200,this);
	/**�ط�����*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("�ط�����",50,250,120,60,this);
	/**ѧУid*/
	private int schoolId;
	public StudentVisitAddialog(JFrame jf,int studentId,int schoolId){
		super(jf,true);
		this.studentId =  studentId;
		this.schoolId =  schoolId;
		this.setLayout(null);
		this.setTitle("��ӻطü�¼");
		
		this.init();
		
		this.setBounds(400, 130, 350, 420);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		this.initComboBox(schoolId);
		LovoButton lbadd = new LovoButton("���",50,340,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				addVisit( studentId);
				StudentVisitAddialog.this.dispose();
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",180,340,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				StudentVisitAddialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * ��ʼ��������������
	 * @param schoolObj ѧУ����
	 */
	private void initComboBox(int schoolId){
		//��Ӹ����˼���
        ArrayList<Object> allEmp = new ConnectionServiceImp().getAllEmpBySchoolID(schoolId);
		this.employeeTxt = new LovoComboBox("������",allEmp,50,100,this);
		
	}
	/**
	 * ��ӻطü�¼
	 * @param studentId ѧ��ID
	 */
	private void addVisit(int studentId){
		//��֤����
        RecallRecordBean recall = new RecallRecordBean();
        //��֤����,��֤ʧ�ܷ���false
        String error = "";
        if(timeTxt.getText() == null || timeTxt.getText().equals("")){
            error += "��ͨʱ�䲻��Ϊ��\n";
        }
        if(connectorTxt.getText() == null || connectorTxt.getText().equals("")){
            error += "�ط��˲���Ϊ��\n";
        }
        if(titleTxt.getText() == null || titleTxt.getText().equals("")){
            error += "�ط����ⲻ��Ϊ��\n";
        }
        if(employeeTxt.getItem() == null || employeeTxt.getItem().equals("")){
            error += "��ѡ������\n";
        }if(descriptionTxt.getText() == null || descriptionTxt.getText().equals("")){
            error += "���ݲ���Ϊ��\n";
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
