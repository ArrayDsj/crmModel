package com.lovo.netCRM.ui.school.frame;

import com.lovo.netCRM.bean.ConnectRecordBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.dao.imp.ConnectRecordDaoImp;
import com.lovo.netCRM.service.imp.ConnectionServiceImp;
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
 * @description ��ӹ�ͨ��¼�Ի���
 * ��������:2012-10-12
 */
public class SchoolCommunicateAddDialog extends JDialog{
	/**ѧУid*/
	private int schoolId;
	/**��ͨʱ���ı���*/
	private LovoDate timeTxt = new LovoDate("��ͨʱ��",50,50,this);
	/**У����ϵ���ı���*/
	private LovoTxt connectorTxt = new LovoTxt("У����ϵ��",50,100,this);
	/**ְ���ı���*/
	private LovoTxt jobTxt = new LovoTxt("ְ��",50,150,this);
	/**��ϵ��*/
	private LovoComboBox employeeTxt;
	
	/**��ͨ����*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("��ͨ����",50,250,120,60,this);
	public SchoolCommunicateAddDialog(JFrame jf,int schoolId){
		super(jf,true);
		this.schoolId = schoolId;
		this.setLayout(null);
		this.setTitle("��ӹ�ͨ��¼");
		
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
				boolean isOk = addCommunicate(schoolId);
				if(isOk){
					SchoolCommunicateAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",180,340,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolCommunicateAddDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * ��ʼ��������������
	 *
	 */
	private void initComboBox(int schoolId){

		//��Ӹ����˼���
		//ͨ��ѧУID��Ա��
        ArrayList<Object> allEmp = new ConnectionServiceImp().getAllEmpBySchoolID(schoolId);
		this.employeeTxt = new LovoComboBox("������",allEmp,50,200,this);
		
	}
	/**
	 * ��ӹ�ͨ��¼
	 * @param schoolId ѧУID
	 */
	private boolean addCommunicate(int schoolId){
        //��֤���ݣ���֤ʧ�ܷ���false
        ConnectRecordBean con = new ConnectRecordBean();
        //��֤����,��֤ʧ�ܷ���false
        String error = "";
        if(timeTxt.getText() == null || timeTxt.getText().equals("")){
            error += "��ͨʱ�䲻��Ϊ��\n";
        }
        if(connectorTxt.getText() == null || connectorTxt.getText().equals("")){
            error += "��ϵ�˲���Ϊ��\n";
        }if(jobTxt.getText() == null || jobTxt.getText().equals("")){
            error += "У����ϵ��ְ����Ϊ��\n";
        }if(employeeTxt.getItem() == null || employeeTxt.getItem().equals("")){
            error += "��ѡ������\n";
        }if(descriptionTxt.getText() == null || descriptionTxt.getText().equals("")){
            error += "��ͨ���ݲ���Ϊ��\n";
        }
        if(error.length() != 0) {
            JOptionPane.showMessageDialog(null, error);
            return false;
        } else {
            //��װʵ��
            con.setTime(timeTxt.getDate());
            con.setMan(connectorTxt.getText());
            con.setPos(jobTxt.getText());
            //����ѡ��ĸ����˵������ҳ����Ա������
            con.setEmp(new EmployeeServiceImp().getEmpByName(employeeTxt.getItem().toString()));
            con.setDescribe(descriptionTxt.getText());

        }

		//��ӹ�ͨ��¼
        new ConnectRecordDaoImp().addObject(schoolId,con);
		return true;
		
	}
}
