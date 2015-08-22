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
/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description Ա���޸ĶԻ���
 * ��������:2012-10-6
 */
public class EmployeeUpdateDialog extends JDialog{
	/**Ա�������*/
	private EmployeePanel emPanel;
	/**Ա��ID*/
	private int employeeId;
	/**������ǩ*/
	private LovoLabel nameLabel = new LovoLabel("��    ��",40,50,this);
	/**�Ա��ǩ*/
	private LovoLabel sexLabel = new LovoLabel("��    ��",320,50,this);
	
	/**�������ڱ�ǩ*/
	private LovoLabel birthdayLabel = new LovoLabel("��������",40,100,this);
	/**�Ļ��̶ȱ�ǩ*/
	private LovoLabel eduLabel = new LovoLabel("�Ļ��̶�",320,100,this);
	
	/**����רҵ��ǩ*/
	private LovoLabel specialityLabel = new LovoLabel("����רҵ",40,150,this);
	/**��ϵ��ʽ�ı���*/
	private LovoTxt phoneTxt = new LovoTxt("��ϵ��ʽ",320,150,this);
	/**��ͥסַ��ǩ*/
	private LovoLabel adressLabel = new LovoLabel("��ͥסַ",40,200,this);
	/**������ò*/
	private LovoComboBox<String> polityFaceTxt = new LovoComboBox<String>("������ò",new String[]{"��Ա","��Ա","��������","�޵�����ʿ"},320,200,this);
	/**���ڲ���*/
	private LovoComboBox deptTxt;
	/**����ְλ*/
	private LovoComboBox workTxt;
	/**��ְ���ڱ�ǩ*/
	private LovoLabel enterJobLabel = new LovoLabel("��ְ����",40,300,this);
	/**ͷ���ǩ*/
	private LovoImageLabel faceLabel = new LovoImageLabel(580, 70, 100, 130,this);
	
	
	public EmployeeUpdateDialog(JFrame jf,int employeeId,EmployeePanel emPanel){
		super(jf,true);
		this.emPanel = emPanel;
		this.employeeId = employeeId;
		
		this.setLayout(null);
		this.setTitle("�޸�Ա����Ϣ");
		
		this.init();
		
		this.setBounds(300, 100, 700, 450);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init(){
		this.initComboBox();
		this.initEmployeeData(this.employeeId);
		
		LovoButton lbupdate = new LovoButton("�޸�",150,350,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = updateEmployee(employeeId);
				if(isOk){
					//����UpdateDialog����
					EmployeeUpdateDialog.this.dispose();
				}else
					JOptionPane.showMessageDialog(null,"�޸�ʧ��");
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",400,350,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				EmployeeUpdateDialog.this.dispose();
			}});
	}
	
	
	//-------------------------------------------
	
	/**
	 * ��ʼ�����ź�ְλ������
	 *
	 */
	private void initComboBox(){
		//��Ӳ���List����
		//�����ݿ����ҳ����в�������
		ArrayList<Object> departNames = new DepartServiceImp().getAllDepts();
		this.deptTxt = new LovoComboBox("���ڲ���",departNames,40,250,this);
		//Ĭ��ѡ��Ա�����ڵĲ���


		//���ְλList����
		//�����ݿ����ҳ�����ְλ����
		ArrayList<Object> positionNames = new PositionServiceImp().getAllPositions();
		this.workTxt = new LovoComboBox("����ְλ",positionNames,320,250,this);
		//Ĭ��ѡ��Ա��������ְҵ


	
	}
	
	/**
	 * ��ʼ������
	 * @param employeeId��Ա��ID
	 */
	private void initEmployeeData(int employeeId){
		//������޸�Ա����Ϣ��ʱ��,��ѡ�е�Ա����Ϣ��ʾ��UpdateDialog�����
		EmployeeBean theEmp = new EmployeeServiceImp().getStaffByID(employeeId);
		nameLabel.setText(theEmp.getName());
		sexLabel.setText(theEmp.getSex());
		birthdayLabel.setText(theEmp.getBirthday().toString());
		eduLabel.setText(theEmp.getEdu());
		specialityLabel.setText(theEmp.getSpeciality());
		phoneTxt.setText(theEmp.getPhone());
		adressLabel.setText(theEmp.getAddress());
		enterJobLabel.setText(theEmp.getHireDay().toString());
		faceLabel.setImage(theEmp.getHeadFile());
	}
	
	/**
	 * �޸�Ա����Ϣ
	 * @param employeeId Ա��ID
	 */
	private boolean updateEmployee(int employeeId) {
		//��֤���ݣ�������֤ʧ�ܷ���false
		//ʹ��������ʽ��֤�绰�����Ƿ���ȷ
		String phone = phoneTxt.getText();
		if (phone.matches("^[1][0-9]{10}$")) {
			//��ȡ����ϵ�����,д�뵽���ݿ���
			//1.����ID�����û�
			EmployeeBean willUpdateEmp = new EmployeeServiceImp().getStaffByID(employeeId);
			//�޸��ֻ���
			willUpdateEmp.setPhone(phone);
			//System.out.println("ѡ��:" + deptTxt.getItem().toString());
			//�޸Ĳ���
			willUpdateEmp.setDept(deptTxt.getItem().toString());
			//�޸�ְλ
			//System.out.println("ѡ��:"+workTxt.getItem().toString());
			willUpdateEmp.setPosition(workTxt.getItem().toString());
			//�޸�������ò
			willUpdateEmp.setPolity(polityFaceTxt.getItem().toString());
			//�޸����ݿ�
			new EmployeeServiceImp().alterStaff(willUpdateEmp);
			//�������ݣ���ʾ�޸Ľ��
			this.emPanel.initData();
			return true;
		}else
			return false;
	}
}
