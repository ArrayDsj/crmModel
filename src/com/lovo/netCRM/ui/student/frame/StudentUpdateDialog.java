package com.lovo.netCRM.ui.student.frame;

import com.lovo.netCRM.bean.StudentBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.dao.imp.ClassesDaoImp;
import com.lovo.netCRM.dao.imp.StudentDaoImp;

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
 * @description ѧ����Ϣ�޸ĶԻ���
 * ��������:2012-10-14
 */
public class StudentUpdateDialog extends JDialog{
	/**ѧ�������*/
	private StudentPanel studentPanel;
	/**������ǩ*/
	private LovoLabel nameLabel = new LovoLabel("��    ��",50,50,this);
	/**�Ա��ǩ*/
	private LovoLabel sexLabel = new LovoLabel("��    ��",320,50,this);
	/**�������ڱ�ǩ*/
	private LovoLabel birthdayLabel = new LovoLabel("��������",50,100,this);
	/**�����༶*/
	private LovoComboBox classTxt;
	/**��ͥ��ַ��ǩ*/
	private LovoLabel addressLabel = new LovoLabel("��ͥ��ַ",50,150,this);
	/**��ϵ�绰�ı���*/
	private LovoTxt phoneTxt = new LovoTxt("��ϵ�绰",320,150,this);
	/**���ױ�ǩ*/
	private LovoLabel fatherLabel = new LovoLabel("��    ��",50,200,this);
	/**���׵绰�ı���*/
	private LovoTxt fatherPhoneTxt = new LovoTxt("���׵绰",320,200,this);
	/**ĸ�ױ�ǩ*/
	private LovoLabel mumLabel = new LovoLabel("ĸ    ��",50,250,this);
	/**ĸ�׵绰�ı���*/
	private LovoTxt mumPhoneTxt = new LovoTxt("ĸ�׵绰",320,250,this);
	/**ѧ��״̬������*/
	private LovoComboBox<String> stateTxt = new LovoComboBox<String>("ѧ��״̬",
			new String[]{"��Ա",
			"�ǻ�Ա"},50,300,this);
	/**��ע�ı���*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("��    ע",320,300,200,120,this);
	/**ѧУid*/
	private int schoolId;
	/**ѧ��id*/
	private int studentId;
	
	public StudentUpdateDialog(JFrame jf,int schoolId,int studentId,StudentPanel studentPanel){
		super(jf,true);
		this.studentPanel = studentPanel;
		this.studentId = studentId;
		this.schoolId = schoolId;
		this.setLayout(null);
		this.setTitle("�޸�ѧ����Ϣ");
		
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
		this.initData(studentId);

		LovoButton lbadd = new LovoButton("�޸�",200,450,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = updateStudent(studentId);
				if(isOk){
				StudentUpdateDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",400,450,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				StudentUpdateDialog.this.dispose();
			}});
	}
	
	//----------------------
	/**
	 * ��ʼ���༶������
	 *
	 */
	private void initComboBox(int schoolId){
//		��Ӱ༶����
        ArrayList<Object> allClassBySchool = new ClassesDaoImp().getObjectByschID(schoolId);
		this.classTxt = new LovoComboBox("�����༶",allClassBySchool,320,100,this);
	}
	
	/**
	 * ��ʼ������
	 * @param studentId ѧ��ID
	 */
	private void initData(int studentId) {
        StudentBean stu = (StudentBean) new StudentDaoImp().getObjectByID(studentId);
        nameLabel.setText(stu.getName());
        sexLabel.setText(stu.getSex());
        birthdayLabel.setText(stu.getBirthday().toString());
        addressLabel.setText(stu.getAddress());
        phoneTxt.setText(stu.getPhone());
        fatherLabel.setText(stu.getFather());
        fatherPhoneTxt.setText(stu.getFatherPhone());
        mumLabel.setText(stu.getMother());
        mumPhoneTxt.setText(stu.getMotherPhone());
        descriptionTxt.setText(stu.getDescribe());
    }

	/**
	 * �޸�ѧ��
	 * @param studentId ѧ��ID
	 */
	private boolean updateStudent(int studentId){
        StudentBean stu = new StudentBean();
		//��֤����
        String error = "";
        if(phoneTxt.getText() == null || phoneTxt.getText().equals("")){
            error += "�绰����Ϊ��\n";
        }
        if(fatherPhoneTxt.getText() == null || fatherPhoneTxt.getText().equals("")){
            error += "���׵绰����Ϊ��\n";
        }
        if(mumPhoneTxt.getText() == null || mumPhoneTxt.getText().equals("")){
            error += "ĸ�׵绰����Ϊ��\n";
        }
        if(descriptionTxt.getText() == null || descriptionTxt.getText().equals("")){
            error += "��������Ϊ��\n";
        }
        if(error.length() != 0) {
            JOptionPane.showMessageDialog(null, error);
            return false;
        }

        stu.setPhone(phoneTxt.getText());
        stu.setFatherPhone(fatherPhoneTxt.getText());
        stu.setMotherPhone(mumPhoneTxt.getText());
        stu.setDescribe(descriptionTxt.getText());
        stu.setClasses(new ClassesDaoImp().getClassesByName(classTxt.getItem().toString()));
        if(stateTxt.getItem().toString().equals("�ǻ�Ա")){
            stu.setVip(false);
        }else
            stu.setVip(true);
		
		//���±����ʾ�޸Ľ��
        new StudentDaoImp().alterObject(studentId,stu);
		this.studentPanel.initData();
		
		return true;
	}
}
