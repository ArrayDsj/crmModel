package com.lovo.netCRM.ui.school.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.component.LovoTxtArea;

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
		this.employeeTxt = new LovoComboBox("������",new ArrayList(),50,200,this);
		
	}
	/**
	 * ��ӹ�ͨ��¼
	 * @param schoolId ѧУID
	 */
	private boolean addCommunicate(int schoolId){
		//��֤���ݣ���֤ʧ�ܣ�����false
		
		//��װʵ��
		
		return true;
		
	}
}
