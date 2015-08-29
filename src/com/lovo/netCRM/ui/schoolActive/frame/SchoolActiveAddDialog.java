package com.lovo.netCRM.ui.schoolActive.frame;

import com.lovo.netCRM.bean.ActiveBean;
import com.lovo.netCRM.bean.DepartBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.dao.imp.EmployeeDaoImp;
import com.lovo.netCRM.service.imp.ActiveServiceImp;
import com.lovo.netCRM.service.imp.DepartServiceImp;
import com.lovo.netCRM.service.imp.EmployeeServiceImp;

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
 * @description ���ѧУ��Ի���
 * ��������:2012-10-8
 */
public class SchoolActiveAddDialog extends JDialog{
	/**ѧУId*/
	private int schoolId;
	/**������ı���*/
	private LovoTxt nameTxt = new LovoTxt("�����",50,50,this);
	/**�ʱ���ı���*/
	private LovoDate timeTxt = new LovoDate("�ʱ��",50,100,this);
	/**��ص��ı���*/
	private LovoTxt addressTxt = new LovoTxt("��ص�",50,150,this);
	/**������ı���*/
	private LovoTxt titleTxt = new LovoTxt("�����",50,200,this);
	/**������*/
	private LovoComboBox deptTxt;
	/**������*/
	private LovoComboBox employeeTxt = new LovoComboBox("������",new ArrayList(),50,300,this);;

	// ���ҳ
	private SchoolActivePanel activePanel ;
	/**
	 * ��ӻ�Ի���
	 * @param jf �������
	 *
	 *
	 */
	public SchoolActiveAddDialog(SchoolActivePanel activePanel,JFrame jf,int schoolId){
		super(jf,true);
		this.activePanel = activePanel;
		this.schoolId = schoolId;
		this.setLayout(null);
		this.setTitle("����»");
		
		this.init();
		
		this.setBounds(400, 150, 350, 450);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		this.initComboBox();

		deptTxt.setSelectedIndex(1);
		employeeTxt.setSelectedIndex(0);
		
		LovoButton lbadd = new LovoButton("���",50,350,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addActive(schoolId);
				if(isOk){
					SchoolActiveAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",180,350,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolActiveAddDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * ��ʼ��������������
	 *
	 */
	private void initComboBox(){
		//��Ӳ���List����
		ArrayList<Object> departs = new DepartServiceImp().getAllDepts();
		this.deptTxt = new LovoComboBox("������",departs,50,250,this){
			/**
			 * ���ݲ���ID��ʾԱ������
			 * @param deptObj ���Ŷ���
			 */
			public void onchange(Object deptObj){
				DepartBean dept = (DepartBean)deptObj;
				//ͨ��deptID�ҵ�����Ա��
				//����Ա������
				employeeTxt.setList(new EmployeeDaoImp().getAllEmpByDeptID(dept.getDepartID()));
			}
		};
		

	
	}
	
	/**
	 * ��ӻ
	 */
	private boolean addActive(int schoolId){
		ActiveBean active = new ActiveBean();
		//��֤����,��֤ʧ�ܷ���false
		String error = "";
		if(nameTxt.getText() == null || nameTxt.getText().equals("")){
			error += "�������Ϊ��\n";
		}
		if(timeTxt.getDate() == null ){
			error += "ʱ�䲻��Ϊ��\n";
		}
		if(addressTxt.getText() == null || addressTxt.getText().equals("")){
			error += "��ַ����Ϊ��\n";
		}
		if(titleTxt.getText() == null || titleTxt.getText().equals("")){
			error += "����ⲻ��Ϊ��\n";
		}

		if(error.length() != 0){
			JOptionPane.showMessageDialog(null, error);
			return false;
		}else{
			//��װʵ��
			active.setName(nameTxt.getText());
			active.setAddress(addressTxt.getText());
			active.setTime(timeTxt.getDate());
			active.setTitle(titleTxt.getText());
			active.setEmp(new EmployeeServiceImp().getEmpByName(employeeTxt.getItem().toString()));
		}

		//д�����ݿ�
		new ActiveServiceImp().addActive(schoolId,active);
		//��������
		activePanel.initData();
		return true;
	}
}
