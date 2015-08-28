package com.lovo.netCRM.ui.work.frame;

import com.lovo.netCRM.bean.PositionBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoCheckBox;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTxtArea;
import com.lovo.netCRM.service.imp.PositionServiceImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description �޸�ְλ�Ի���
 * ��������:2012-10-6
 */
public class WorkUpdateDialog extends JDialog{
	/**ְλ�����*/
	private WorkPanel workPanel;
	/**ְλ�����ı���*/
	private LovoLabel nameLabel = new LovoLabel("ְλ����",50,50,this);
	/**ְλ�����ı���*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("ְλ����",50,100,300,80,this);
	/**Ȩ�޸�ѡ��*/
	private LovoCheckBox gradeTxt = new LovoCheckBox("Ȩ��",new String[]{"��ѯȨ��","����Ȩ��","����ͳ�Ʒ���","Ȩ�޹���","��̨����"},50,200,this);
	/**ְλid*/
	private int workId;
	public WorkUpdateDialog(JFrame jf,int workId,WorkPanel workPanel){
		super(jf,true);
		this.workPanel = workPanel;
		this.workId = workId;
		this.setLayout(null);
		this.setTitle("�޸�ְλ");
		
		this.init();
		
		this.setBounds(350, 150, 550, 400);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		descriptionTxt.setEditable(false);

		this.initData(workId);

		LovoButton lbupdate = new LovoButton("�޸�",150,300,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				updateWork(workId);
				WorkUpdateDialog.this.dispose();
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",330,300,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				WorkUpdateDialog.this.dispose();
			}});
	}
	
	
	//----------------------

	/**
	 * ��ʼ������
	 * @param workId ����ְλID
	 */
	private void initData(int workId) {
		PositionBean thePos = new PositionServiceImp().getPosByID(workId);

		nameLabel.setText(thePos.getName());
		descriptionTxt.setText(thePos.getDescribe());
		//Ȩ��
		boolean checkRight = thePos.isCheckRight();
		boolean queryRight = thePos.isQueryRight();
		boolean saleRight = thePos.isSaleRight();
		boolean managerRight = thePos.isManagerRight();
		boolean backRight = thePos.isBackRight();

		String[] rights = new String[5];
		if(checkRight){
			rights[0] = "��ѯȨ��";
		}else
			rights[0] = "";
		if(queryRight){
			rights[1] = "����Ȩ��";
		}else
			rights[1] = "";
		if(saleRight){
			rights[2] = "����ͳ�Ʒ���";
		}else
			rights[2] = "";
		if(managerRight){
			rights[3] = "Ȩ�޹���";
		}else
			rights[3] = "";
		if(backRight){
			rights[4] = "��̨����";
		}else
			rights[4] = "";
		//����ѡ����
		gradeTxt.setItem(rights);
		
	}
	/**
	 * �޸�ְλ
	 * @param workId ����ְλid
	 */
	private void updateWork(int workId){
		PositionBean newPos = new PositionBean();

		newPos.setCheckRight(false);
		newPos.setQueryRight(false);
		newPos.setSaleRight(false);
		newPos.setManagerRight(false);
		newPos.setBackRight(false);
		//�õ�ѡ��������
		String[] items = gradeTxt.getItem();
		for (int i = 0; i < items.length; i++) {
			if (items[i].toString().equals("��ѯȨ��")) {
				newPos.setCheckRight(true);
			}
			if (items[i].toString().equals("����Ȩ��")) {
				newPos.setQueryRight(true);
			}
			if (items[i].toString().equals("����ͳ�Ʒ���")) {
				newPos.setSaleRight(true);
			}

			if (items[i].toString().equals("Ȩ�޹���")) {
				newPos.setManagerRight(true);
			}
			if (items[i].toString().equals("��̨����")) {
				newPos.setBackRight(true);
			}
		}
		new PositionServiceImp().alterPosition(workId,newPos);
		//���±����ʾ�޸�ְλ���
		this.workPanel.initData();
	}
}
