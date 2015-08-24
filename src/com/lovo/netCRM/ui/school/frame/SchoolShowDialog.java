package com.lovo.netCRM.ui.school.frame;

import com.lovo.netCRM.bean.ConnectRecordBean;
import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.dao.imp.SchoolDaoImp;

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
 * @description ѧУ��Ϣ��ǩ
 * ��������:2012-10-11
 */
public class SchoolShowDialog extends JDialog{

	/**ѧУid*/
	private int schoolId;
	/**ѧУ��ͨ��¼���*/
	private LovoTable communicateTable;
	/**ѧУ���Ʊ�ǩ*/
	private LovoLabel nameLabel = new LovoLabel("ѧУ����",50,40,this);
	/**��������*/
	private LovoLabel cityLabel = new LovoLabel("��������",320,40,this);;
	/**��ַ��ǩ*/
	private LovoLabel addressLabel = new LovoLabel("ѧУ��ַ",50,80,this);
	/**У����ǩ*/
	private LovoLabel masterLabel = new LovoLabel("У��",320,80,this);
	/**��ϵ�绰��ǩ*/
	private LovoLabel phoneLabel = new LovoLabel("��ϵ�绰",50,120,this);
	/**ѧ��������ǩ*/
	private LovoLabel studentNumberLabel = new LovoLabel("ѧ������",320,120,this);
	/**��ʦ������ǩ*/
	private LovoLabel teacherNumberLabel = new LovoLabel("��ʦ����",50,160,this);
	/**ip��ַ��ǩ*/
	private LovoLabel ipLabel = new LovoLabel("ip��ַ",320,160,this);
	/**���������ǩ*/
	private LovoLabel netFluxLabel = new LovoLabel("�������",50,200,this);
	/**״̬��ǩ*/
	private LovoLabel stateLabel = new LovoLabel("״̬",320,200,this);
	
	/**�����ű�ǩ*/
	private LovoLabel deptLabel = new LovoLabel("������",50,240,this);
	/**������*/
	private LovoLabel employeeLabel = new LovoLabel("������",320,240,this);
	/**¼��ʱ��*/
	private LovoLabel enterTimeLabel = new LovoLabel("¼��ʱ��",50,280,this);
	/**��������ʱ��*/
	private LovoLabel applyTimeLabel = new LovoLabel("��������ʱ��",320,280,this);
	/**������׼ʱ��*/
	private LovoLabel passTimeLabel = new LovoLabel("������׼ʱ��",50,320,this);
	/**˵���ı���*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("˵��",50,360,150,60,this);
	/**��������ı���*/
	private LovoTxtArea mindTxt = new LovoTxtArea("�������",320,360,150,60,this);
	
	public SchoolShowDialog(JFrame jf,int schoolId){
		super(jf,true);
		this.schoolId = schoolId;
		
		this.setLayout(null);
		this.setTitle("ѧУ��Ϣһ��");
		
		this.init();
		
		this.setBounds(300, 10, 650, 700);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		this.initData(this.schoolId);
		this.descriptionTxt.setEditable(false);
		this.mindTxt.setEditable(false);
		
		/*��ͨ��¼����*/
		LovoTitlePanel jp = new LovoTitlePanel("��ͨ��¼",80, 420, 500, 200,this);
		
		this.initTable(this.schoolId,jp);
		
		LovoButton lbcancel = new LovoButton("ȷ��",280,640,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolShowDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * ��ʼ����ǩ����
	 * @param schoolId ѧУID
	 */
	private void initData(int schoolId){
		//��ѧУID������Ϣ
		SchoolBean sch = (SchoolBean)new SchoolDaoImp().getObjectByID(schoolId);
		nameLabel.setText(sch.getName());
		cityLabel.setText(sch.getArea().getName());
		addressLabel.setText(sch.getAddress());
		masterLabel.setText(sch.getMaster());
		phoneLabel.setText(sch.getPhone());
		studentNumberLabel.setText(sch.getStuNum()+"");
		teacherNumberLabel.setText(sch.getTeaNum()+"");
		ipLabel.setText(sch.getIPAddress());
		netFluxLabel.setText(sch.getFlow());
		stateLabel.setText(sch.getStatus());
		deptLabel.setText(sch.getEmp().getDept());
		employeeLabel.setText(sch.getEmp().getName());
		enterTimeLabel.setText(sch.getFoundTime().toString());
		descriptionTxt.setText(sch.getDescribe());
		mindTxt.setText(sch.getCheckNotic());
		if(sch.getProposeTime() == null){
			applyTimeLabel.setText("");
		}else
			applyTimeLabel.setText(sch.getProposeTime().toString());
		if(sch.getPermitTime() == null){
			passTimeLabel.setText("");
		} else
			passTimeLabel.setText(sch.getPermitTime().toString());

	}
	/**
	 * ��ʼ�����
	 * @param schoolId ѧУID
	 */
	private void initTable(int schoolId,JPanel jp){
		communicateTable = new LovoTable(jp,
				new String[]{"ʱ��","У����ϵ��","ְ��","��ͨ��","��ͨ����"},
				new String[]{"time","man","pos","emp.name","describe"},//ѧУʵ������������ new String[]{"time","schoolConnector"}
				"id");//���������� schoolId
		communicateTable.setSizeAndLocation(10, 20, 480, 170);
		
		//���±��,����List����
		ArrayList<ConnectRecordBean> arrCon = new SchoolDaoImp().getConnectRecordBySchoolID(schoolId);
		communicateTable.updateLovoTable(arrCon);
	}
	

}
