package com.lovo.netCRM.ui.frame;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoImageLabel;
import com.lovo.netCRM.component.LovoPassWordTxt;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.service.imp.EmployeeServiceImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ��½����
 * ��������:2012-10-16
 */
public class LoginFrame extends JFrame{
	/**�û����ı���*/
	private LovoTxt userTxt = new LovoTxt("�û���",100,150,this);
	/**�����ı���*/
	private LovoPassWordTxt pwdTxt = new LovoPassWordTxt("����",100,200,this);
	
	public LoginFrame(){
		this.setLayout(null);
		LovoImageLabel crmImg = new LovoImageLabel(100,50,246,71,this);
		crmImg.setImage("image/logo_north.jpg");
		
		LovoButton lb = new LovoButton("��½",230,250,this);
		lb.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			//����¼�,��¼
			login();
		}});
		
		this.setSize(450,330);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������ʾ
		this.setLocationRelativeTo(null);
	}
	
	//--------------------
	/**
	 * ��½����
	 */
	private void login(){
		//��ͼ�����ҵ���login()������ǰ��¼�û�;
		EmployeeBean loginEmp = new EmployeeServiceImp().login(userTxt.getText(), pwdTxt.getText());
		if(loginEmp != null) {
			//Window��dispose���� �ͷ��ɴ� Window�������������ӵ�е������������ʹ�õ����б�����Ļ��Դ
			//��������login����
			this.dispose();
			//�����û�ʵ�����
			new MainFrame(loginEmp);
		}
	}
}
