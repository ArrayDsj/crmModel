package com.lovo.netCRM.ui.employee.frame;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.service.imp.DepartServiceImp;
import com.lovo.netCRM.service.imp.EmployeeServiceImp;
import com.lovo.netCRM.service.imp.PositionServiceImp;

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
 * @description ����Ա���Ի���
 * ��������:2012-10-6
 */
public class EmployeeAddDialog extends JDialog{

	/**�����ı���*/
	private LovoTxt nameTxt = new LovoTxt("��    ��",40,50,this);
	/**�Ա�ѡť*/
	private LovoRadioButton sexTxt = new LovoRadioButton("��    ��",new String[]{"��","Ů"},320,50,this);
	/**��������*/
	private LovoDate birthdayTxt = new LovoDate("��������",40,100,this);
	/**�Ļ��̶�*/
	private LovoComboBox<String> eduTxt = new LovoComboBox<String>("�Ļ��̶�",new String[]{"����","��ר","����","˶ʿ"},320,100,this);
	/**����רҵ�ı���*/
	private LovoTxt specialityTxt = new LovoTxt("����רҵ",40,150,this);
	/**��ϵ��ʽ�ı���*/
	private LovoTxt phoneTxt = new LovoTxt("��ϵ��ʽ",320,150,this);
	/**��ͥסַ�ı���*/
	private LovoTxt addressTxt = new LovoTxt("��ͥסַ",40,200,this);
	/**������ò*/
	private LovoComboBox<String> polityFaceTxt = new LovoComboBox<String>("������ò",new String[]{"��Ա","��Ա","��������","�޵�����ʿ"},320,200,this);
	/**���ڲ���*/
	private LovoComboBox deptTxt;
	/**����ְλ*/
	private LovoComboBox jobTxt;
	/**ͷ��*/
	private LovoFileChooser faceTxt = new LovoFileChooser(this,"face");
	/**Ա�������*/
	private EmployeePanel emPanel;
	
	public EmployeeAddDialog(JFrame jf,EmployeePanel emPanel){
		super(jf,true);
		this.emPanel = emPanel;
		this.setLayout(null);
		this.setTitle("������Ա��");
		
		this.init();
		
		this.setBounds(300, 100, 700, 400);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init(){
		this.initComboBox();
		
		faceTxt.setBounds(580, 70, 100, 150);
		
		LovoButton lbadd = new LovoButton("����",150,310,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addEmployee();
				if(isOk){
					EmployeeAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",400,310,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				EmployeeAddDialog.this.dispose();
			}});
	}
	
	//--------------------------------------------
	/**
	 * ��ʼ�����ź�ְλ������
	 *
	 */
	private void initComboBox(){
		//���Ӳ���List����
		ArrayList<Object> departNames = new DepartServiceImp().getAllDepts();
		this.deptTxt = new LovoComboBox("��������",departNames,40,250,this);
		//����ְλList����
		ArrayList<Object> positionNames = new PositionServiceImp().getAllPositions();
		this.jobTxt = new LovoComboBox("����ְλ",positionNames,320,250,this);
	
	}
	/**
	 * ���Ӳ���
	 *
	 */
	private boolean addEmployee(){
		EmployeeBean newEmp = new EmployeeBean();
		String str = "";
		//��֤����
		if(!this.nameTxt.getText().matches("[a-zA-Z\\u4e00-\\u9fa5]{2,20}")){
			str += "�û�������Ϊ��λ������ĸ����\n";
		}
		if(!this.phoneTxt.getText().matches("^[1][0-9]{10}$")){
			str += "�绰�����ʽ����ȷ\n";
		}
		if(!specialityTxt.getText().matches("[\\u4e00-\\u9fa5]{2,20}")){
			str += "רҵ��ʽ��ȷ\n";
		}
		if(!addressTxt.getText().matches("[a-zA-Z0-9\\u4e00-\\u9fa5]{2,20}")){
			str += "��ַ��ʽ����ȷ\n";
		}

		if(str.length() != 0){
			JOptionPane.showMessageDialog(null, str);
			return false;
		}else{
			newEmp.setName(nameTxt.getText());
			newEmp.setSex(sexTxt.getItem().toString());
			newEmp.setBirthday(birthdayTxt.getDate());
			newEmp.setPhone(phoneTxt.getText());
			newEmp.setSpeciality(specialityTxt.getText());
			newEmp.setEdu(eduTxt.getItem().toString());
			newEmp.setAddress(addressTxt.getText());
			newEmp.setPolity(polityFaceTxt.getItem().toString());
			newEmp.setDept(deptTxt.getItem().toString());
			newEmp.setPosition(jobTxt.getItem().toString());

			//״̬status  1λtrue ��ְ
			newEmp.setStatus(true);
			//�Ե�ǰʱ����Ϊ��ְʱ��
			newEmp.setHireDay(new Date());
		}
		//������Ӳ���
		new EmployeeServiceImp().addStaff(newEmp);
		
//		��������,��ʾ���ӽ��
		this.emPanel.initData();
		return true;
	}
}