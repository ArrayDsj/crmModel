package com.lovo.netCRM.ui.student.frame;

import com.lovo.netCRM.component.*;
import com.lovo.netCRM.dao.imp.ClassesDaoImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ���ѧ�����
 * ��������:2012-10-14
 */
public class StudentAddDialog extends JDialog{
	/**ѧ�������*/
	private StudentPanel studentPanel;
	/**ѧУid*/
	private int schoolId;
	/**�����ı���*/
	private LovoTxt nameTxt = new LovoTxt("��    ��",50,50,this);
	/**�Ա�ѡť*/
	private LovoRadioButton sexTxt = new LovoRadioButton("��    ��",new String[]{"��","Ů"},320,50,this);
	/**���������ı���*/
	private LovoDate birthdayTxt = new LovoDate("��������",50,100,this);
	/**�����༶*/
	private LovoComboBox classTxt;
	/**��ͥ��ַ�ı���*/
	private LovoTxt addressTxt = new LovoTxt("��ͥ��ַ",50,150,this);
	/**��ϵ�绰�ı���*/
	private LovoTxt phoneTxt = new LovoTxt("��ϵ�绰",320,150,this);
	/**�����ı���*/
	private LovoTxt fatherTxt = new LovoTxt("��    ��",50,200,this);
	/**���׵绰�ı���*/
	private LovoTxt fatherPhoneTxt = new LovoTxt("���׵绰",320,200,this);
	/**ĸ���ı���*/
	private LovoTxt mumTxt = new LovoTxt("ĸ    ��",50,250,this);
	/**ĸ�׵绰�ı���*/
	private LovoTxt mumPhoneTxt = new LovoTxt("ĸ�׵绰",320,250,this);
	
	/**��ע�ı���*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("��    ע",50,300,400,120,this);
	
	public StudentAddDialog(JFrame jf,int schoolId,StudentPanel studentPanel){
		super(jf,true);
		this.studentPanel = studentPanel;
		this.schoolId = schoolId;
		this.setLayout(null);
		this.setTitle("���ѧ����Ϣ");
		
		this.init();
		
		this.setBounds(300, 150, 650, 530);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		this.initComboBox(schoolId);
		
		LovoButton lbadd = new LovoButton("���",200,450,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addStudent();
				if(isOk){
					StudentAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",400,450,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				StudentAddDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * ��ʼ���༶������
	 *
	 */
	private void initComboBox(int schoolId){
//		��Ӱ༶ ����
        ArrayList<Object> allClassBySchool = new ClassesDaoImp().getObjectByschID(schoolId);
		this.classTxt = new LovoComboBox("�����༶",allClassBySchool,320,100,this);
	}
	
	/**
	 * ���ѧ��
	 */
	private boolean addStudent(){
		//��֤���ݣ���֤ʧ�ܷ���false
		
		//��װʵ��
		
		//���±����ʾ��ӽ��
		this.studentPanel.initData();
		
		return true;
	}
}
