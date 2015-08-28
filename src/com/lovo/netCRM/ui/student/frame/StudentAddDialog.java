package com.lovo.netCRM.ui.student.frame;

import com.lovo.netCRM.bean.ClassesBean;
import com.lovo.netCRM.bean.StudentBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.dao.imp.ClassesDaoImp;
import com.lovo.netCRM.dao.imp.StudentDaoImp;
import com.lovo.netCRM.ui.frame.MainFrame;

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

	MainFrame mainFrame ;
	public StudentAddDialog(JFrame jf,int schoolId,StudentPanel studentPanel){
		super(jf,true);

		this.mainFrame = (MainFrame)jf;

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
				boolean isOk = addStudent(schoolId);
				if(isOk){
					mainFrame.getStudentPanel().initAccordion();
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
        classTxt.getItemAt(0);
	}
	
	/**
	 * ���ѧ��
	 */
	private boolean addStudent(int schoolId){
		//��֤���ݣ���֤ʧ�ܷ���false
        StudentBean stu = new StudentBean();
        //��֤����,��֤ʧ�ܷ���false
        String error = "";
        if(nameTxt.getText() == null || nameTxt.getText().equals("")){
            error += "��������Ϊ��\n";
        }
        if(sexTxt.getItem() == null || sexTxt.getItem().equals("")){
            error += "��ѡ�Ա�\n";
        }
        if(birthdayTxt.getDate() == null || birthdayTxt.getDate().equals("")){
            error += "ûУ��,������\n";
        }
        if(phoneTxt.getText() == null || phoneTxt.getText().equals("")){
            error += "û�ֻ�,������\n";
        }
        if(classTxt.getItem() == null || classTxt.getItem().equals("")){
            error += "û�༶,������\n";
        }
        if(addressTxt.getText() == null || addressTxt.getText().equals("")){
            error += "û��ַ,������\n";
        }
        if(fatherTxt.getText() == null || fatherTxt.getText().equals("")){
            error += "û�Ϻ���,������\n";
        }
        if(fatherPhoneTxt.getText() == null || fatherPhoneTxt.getText().equals("")){
            error += "û�Ϻ����绰,������\n";
        }
        if(mumTxt.getText() == null || mumTxt.getText().equals("")){
            error += "û����,������\n";
        }if(descriptionTxt.getText() == null || descriptionTxt.getText().equals("")){
            error += "û˵��,������\n";
        }if(mumPhoneTxt.getText() == null || mumPhoneTxt.getText().equals("")){
            error += "û����绰,������\n";
        }
        if(error.length() != 0) {
            JOptionPane.showMessageDialog(null, error);
            return false;
        }
        //��װʵ��
        stu.setName(nameTxt.getText());
        stu.setSex(sexTxt.getItem());
        stu.setBirthday(birthdayTxt.getDate());
        stu.setPhone(phoneTxt.getText());
        ClassesBean cla = new ClassesDaoImp().getClassesByName(classTxt.getItem().toString());
        stu.setClasses(cla);
        stu.setAddress(addressTxt.getText());
        stu.setFather(fatherTxt.getText());
        stu.setFatherPhone(fatherPhoneTxt.getText());
        stu.setMother(mumTxt.getText());
        stu.setMotherPhone(mumPhoneTxt.getText());
        stu.setDescribe(descriptionTxt.getText());
        stu.setStatus(true);
        stu.setVip(false);

        //д�����ݿ�
		new StudentDaoImp().addObject(schoolId, stu);
        //����༶��������һ

        cla.setStuNum(cla.getStuNum()+1);
		JOptionPane.showMessageDialog(null,cla.getStuNum());
        new ClassesDaoImp().alterObject(cla.getId(), cla);
		//���±����ʾ��ӽ��
		this.studentPanel.initData();
		
		return true;
	}
}
