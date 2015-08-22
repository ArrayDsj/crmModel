package com.lovo.netCRM.ui.work.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoCheckBox;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxtArea;
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
		this.setTitle("�����ְλ");
		
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
		//����ѡ����
		gradeTxt.setItem(new String[]{});
		
	}
	/**
	 * �޸�ְλ
	 * @param workId ����ְλid
	 */
	private void updateWork(int workId){
		
		
		//���±����ʾ�޸�ְλ���
		this.workPanel.initData();
	}
}
