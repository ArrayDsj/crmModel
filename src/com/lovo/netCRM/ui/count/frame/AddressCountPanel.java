package com.lovo.netCRM.ui.count.frame;


import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
import com.lovo.netCRM.service.imp.AddressCountServiceImp;
import com.lovo.test.JFreeChartTest;

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
 * @description ����ͳ�����
 * ��������:2012-10-14
 */
public class AddressCountPanel extends JPanel{
	/**����ͳ�Ʊ�����*/
	private LovoTable addressCountTable;

	public AddressCountPanel(){
		this.setLayout(null);
		this.init();
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		new LovoTitleLabel("�� �� ͳ ��",this);
		this.initTable();
		this.initData();
		JButton button = new JButton();
		button.setBounds(20,400,80,20);
		button.setText("����");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JFreeChartTest("����").get();
			}
		});
		this.add(button);
	}
	/**
	 * ��������
	 */
	public void initData(){
		this.updateAddressCountTable();
	}

	//--------------------------
	
	/**
	 * ��ʼ�����
	 */
	private void initTable() {
		addressCountTable = new LovoTable(this,
				new String[]{"��������","¼��ѧУ","��Ǣ��ѧУ","����ѧУ","���δͨ��ѧУ","�ƹ㿪չѧУ"},
				new String[]{"cityName","schoolNum","receivesSchoolNum","proposeSchoolNum","passSchoolNum","permitSchoolNum"},//ͳ��ʵ������������ new String[]{"cityName","schoolNumber"}
				"id");//���������� countId
		addressCountTable.setSizeAndLocation(20, 90, 700, 300);
		
	}
	/**
	 * ���±������
	 */
	private void updateAddressCountTable(){
		//���±��,�������ͳ��List����
		ArrayList<Object> addressCount = new AddressCountServiceImp().getCounts();
		addressCountTable.updateLovoTable(addressCount);
	}
}
