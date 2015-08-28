package com.lovo.netCRM.ui.work.frame;

import com.lovo.netCRM.bean.PositionBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoCheckBox;
import com.lovo.netCRM.component.LovoTxt;
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
 * @description ���ְλ�Ի���
 * ��������:2012-10-6
 */
public class WorkAddDialog extends JDialog {
	/**
	 * ְλ�����
	 */
	private WorkPanel workPanel;
	/**
	 * ְλ�����ı���
	 */
	private LovoTxt nameTxt = new LovoTxt("ְλ����", 50, 50, this);
	/**
	 * ְλ�����ı���
	 */
	private LovoTxtArea descriptionTxt = new LovoTxtArea("ְλ����", 50, 100, 300, 80, this);
	/**
	 * Ȩ�޸�ѡ��
	 */
	private LovoCheckBox gradeTxt = new LovoCheckBox("Ȩ��",
			new String[]{"��ѯȨ��", "����Ȩ��", "����ͳ�Ʒ���", "Ȩ�޹���", "��̨����"}, 50, 200, this);

	public WorkAddDialog(JFrame jf, WorkPanel workPanel) {
		super(jf, true);
		this.workPanel = workPanel;
		this.setLayout(null);
		this.setTitle("�����ְλ");

		this.init();

		this.setBounds(350, 150, 550, 400);
		this.setVisible(true);
	}

	/**
	 * ��ʼ��
	 */
	private void init() {
		LovoButton lbadd = new LovoButton("���", 150, 300, this);
		lbadd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addWork();
				if (isOk) {
					WorkAddDialog.this.dispose();
				}
			}
		});

		LovoButton lbcancel = new LovoButton("ȡ��", 330, 300, this);
		lbcancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				WorkAddDialog.this.dispose();
			}
		});
	}

	//----------------------

	/**
	 * ���ְλ
	 */
	private boolean addWork() {
		//��֤���ݣ�������֤ʧ�ܷ���false
		PositionBean newPos = new PositionBean();
		String errorStr = "";
		if (!this.nameTxt.getText().matches("[\\u4e00-\\u9fa5]{2,10}")) {
			errorStr += "ְλ���Ƹ�ʽ����(2��10������)\n";
		}
		if (!this.descriptionTxt.getText().matches("[a-zA-Z0-9\\u4e00-\\u9fa5]{3,50}")) {
			errorStr += "������ʽ����\n";
		}
		if (errorStr.length() != 0) {
			JOptionPane.showMessageDialog(null, errorStr);
			return false;
		}
		//��װʵ��
		else {
			newPos.setName(nameTxt.getText());
			newPos.setDescribe(descriptionTxt.getText());
			//Ĭ��Ϊfalse
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
		}
		JOptionPane.showMessageDialog(null, newPos.isCheckRight());
		JOptionPane.showMessageDialog(null, newPos.isQueryRight());
		JOptionPane.showMessageDialog(null, newPos.isSaleRight());
		JOptionPane.showMessageDialog(null, newPos.isManagerRight());
		JOptionPane.showMessageDialog(null, newPos.isBackRight());
		new PositionServiceImp().addPosition(newPos);
		//���±����ʾ���ְλ���
		workPanel.initData();
		return true;
	}
}
