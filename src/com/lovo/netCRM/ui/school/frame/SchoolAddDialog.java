package com.lovo.netCRM.ui.school.frame;

import com.lovo.netCRM.bean.DepartBean;
import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.component.LovoTxtArea;
import com.lovo.netCRM.dao.imp.AreaDaoImp;
import com.lovo.netCRM.dao.imp.EmployeeDaoImp;
import com.lovo.netCRM.dao.imp.SchoolDaoImp;
import com.lovo.netCRM.service.imp.AreaServiceImp;
import com.lovo.netCRM.service.imp.DepartServiceImp;

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
 * @description ���ѧУ�Ի���
 * ��������:2012-10-11
 */
public class SchoolAddDialog extends JDialog{
	/**ѧУ�����*/
	private SchoolPanel schoolPanel;
	/**ѧУ�����ı���*/
	private LovoTxt nameTxt = new LovoTxt("ѧУ����",50,50,this);
	/**��������*/
	private LovoComboBox cityTxt;
	/**��ַ�ı���*/
	private LovoTxt addressTxt = new LovoTxt("ѧУ��ַ",50,100,this);
	/**У���ı���*/
	private LovoTxt masterTxt = new LovoTxt("У��",320,100,this);
	/**��ϵ�绰�ı���*/
	private LovoTxt phoneTxt = new LovoTxt("��ϵ�绰",50,150,this);
	/**ѧ�������ı���*/
	private LovoTxt studentNumberTxt = new LovoTxt("ѧ������",320,150,this);
	/**��ʦ�����ı���*/
	private LovoTxt teacherNumberTxt = new LovoTxt("��ʦ����",50,200,this);
	/**ip��ַ�ı���*/
	private LovoTxt ipTxt = new LovoTxt("ip��ַ",320,200,this);
	/**��������ı���*/
	private LovoTxt netFluxTxt = new LovoTxt("�������",50,250,this);
	
	/**������*/
	private LovoComboBox deptTxt;
	/**������ְλ*/
	private LovoComboBox employeeTxt = new LovoComboBox("������",new ArrayList(),50,350,this);;
	
	/**˵���ı���*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("˵��",320,250,200,120,this);

	public SchoolAddDialog(JFrame jf,SchoolPanel schoolPanel){
		super(jf,true);
		this.schoolPanel = schoolPanel;

		this.setLayout(null);
		this.setTitle("¼����ѧУ");
		
		this.init();
		
		this.setBounds(300, 150, 650, 500);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		this.initComboBox();
		
		LovoButton lbadd = new LovoButton("���",200,400,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addSchool();
				if(isOk){
					SchoolAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",400,400,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolAddDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * ��ʼ�����С������ź͸�����������
	 *
	 */
	private void initComboBox(){
//		��ӳ��м���
		ArrayList<Object> areas = new AreaServiceImp().getAllAreas();
		this.cityTxt = new LovoComboBox("��������",areas,320,50,this);
		
		//��Ӳ���List����
		ArrayList<Object> departs = new DepartServiceImp().getAllDepts();
		this.deptTxt = new LovoComboBox("������",departs,50,300,this){
			/**
			 * ���ݲ���ID��ʾԱ������
			 * @param deptObj ���Ŷ���
			 */
			public void onchange(Object deptObj){
				DepartBean dept = (DepartBean)deptObj;
				//����Ա������
				employeeTxt.setList(new EmployeeDaoImp().getAllEmpByDeptID(dept.getDepartID()));
			}
		};
		
	}
	
	/**
	 * ���ѧУ
	 */
	private boolean addSchool(){
		//��֤���ݣ���֤ʧ�ܷ���false
		SchoolBean sch = new SchoolBean();
		//��֤����,��֤ʧ�ܷ���false
		String error = "";
		if(nameTxt.getText() == null || nameTxt.getText().equals("")){
			error += "ѧУ������Ϊ��\n";
		}
		if(addressTxt.getText() == null || addressTxt.getText().equals("")){
			error += "��ַ����Ϊ��\n";
		}
		if(masterTxt.getText() == null || masterTxt.getText().equals("")){
			error += "ûУ��,������\n";
		}
		if(phoneTxt.getText() == null || phoneTxt.getText().equals("")){
			error += "û�ֻ�,������\n";
		}
		if(studentNumberTxt.getText() == null || studentNumberTxt.getText().equals("")){
			error += "ûѧ��,������\n";
		}
		if(teacherNumberTxt.getText() == null || teacherNumberTxt.getText().equals("")){
			error += "û��ʦ,������\n";
		}
		if(ipTxt.getText() == null || ipTxt.getText().equals("")){
			error += "ûIP,������\n";
		}
		if(netFluxTxt.getText() == null || netFluxTxt.getText().equals("")){
			error += "û����,������\n";
		}
		if(descriptionTxt.getText() == null || descriptionTxt.getText().equals("")){
			error += "û˵��,������\n";
		}
		if(error.length() != 0) {
			JOptionPane.showMessageDialog(null, error);
			return false;
		} else{
			//��װʵ��
			sch.setName(nameTxt.getText());
			sch.setAddress(addressTxt.getText());
			sch.setMaster(masterTxt.getText());
			sch.setPhone(phoneTxt.getText());
			sch.setStuNum(Integer.parseInt(studentNumberTxt.getText()));
			sch.setTeaNum(Integer.parseInt(teacherNumberTxt.getText()));
			sch.setIPAddress(ipTxt.getText());
			sch.setFlow(netFluxTxt.getText());
			sch.setDescribe(descriptionTxt.getText());
			sch.setArea(new AreaDaoImp().getAreaByName(cityTxt.getItem().toString()));
			sch.setEmp(new EmployeeDaoImp().getEmpByName(employeeTxt.getItem().toString()));
			sch.setStatus("��Ǣ��");
			sch.setFoundTime(new Date());

		}

		//д�����ݿ�
		new SchoolDaoImp().addObject(sch);
		//���±����ʾ��ӽ��
		this.schoolPanel.initData();
		
		return true;
	}
}
