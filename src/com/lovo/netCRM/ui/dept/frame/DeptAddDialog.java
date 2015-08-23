package com.lovo.netCRM.ui.dept.frame;

import com.lovo.netCRM.bean.DepartBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.component.LovoTxtArea;
import com.lovo.netCRM.service.imp.DepartServiceImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ������ӶԻ���
 * ��������:2012-10-6
 */
public class DeptAddDialog extends JDialog{
	/**���������*/
	private DeptPanel deptPanel;
	/**���������ı���*/
	private LovoTxt nameTxt = new LovoTxt("��������",50,50,this);
	/**����ʱ���ı���*/
	private LovoDate timeTxt = new LovoDate("����ʱ��",50,100,this);
	/**���������ı���*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("��������",50,150,120,60,this);
	public DeptAddDialog(JFrame jf,DeptPanel deptPanel){
		super(jf,true);
		this.deptPanel = deptPanel;
		this.setLayout(null);
		this.setTitle("����²���");
		
		this.init();
		
		this.setBounds(400, 150, 350, 350);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		LovoButton lbadd = new LovoButton("���",50,250,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addDept();
				if(isOk){
					DeptAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",180,250,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				DeptAddDialog.this.dispose();
			}});
	}
	
	//----------------------
	/**
	 * ��Ӳ���
	 */
	private boolean addDept(){
		//��֤���ݣ�������֤ʧ�ܷ���false
		DepartBean newDept = new DepartBean();
		String errorStr = "";
		if(!this.nameTxt.getText().matches("[\\u4e00-\\u9fa5]{2,10}")){
			errorStr += "�������Ƹ�ʽ����(2��10������)\n";
		}
		if(!this.descriptionTxt.getText().matches("[a-zA-Z0-9\\u4e00-\\u9fa5]{2,10}")){
			errorStr += "������ʽ����\n";
		}
		if(errorStr.length() != 0) {
			JOptionPane.showMessageDialog(null, errorStr);
			return false;
		}
		//��װʵ��
		else{
			newDept.setDepartName(nameTxt.getText());
			newDept.setBuildTime(timeTxt.getDate());
			newDept.setDescribe(descriptionTxt.getText());
		}
		//�������ݿ�
		new DepartServiceImp().addDept(newDept);
		//���±����ʾ��Ӳ��Ž��
		deptPanel.initData();
		return true;
	}
}
