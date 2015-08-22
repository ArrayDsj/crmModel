package com.lovo.netCRM.ui.count.frame;


import javax.swing.JPanel;

import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;

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
	/**����ͳ�Ʊ������*/
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
	}
	/**
	 * ��������
	 */
	public void initData(){
		this.updateAddressCountTable();
	}

	//--------------------------
	
	/**
	 * ��ʼ������
	 */
	private void initTable() {
		addressCountTable = new LovoTable(this,
				new String[]{"��������","¼��ѧУ","��Ǣ��ѧУ","����ѧУ","���δͨ��ѧУ","�ƹ㿪չѧУ"},
				new String[]{},//ͳ��ʵ������������ new String[]{"cityName","schoolNumber"}
				"");//���������� countId
		addressCountTable.setSizeAndLocation(20, 90, 700, 300);
		
	}
	/**
	 * ���±�������
	 */
	private void updateAddressCountTable(){
		//���±���,�������ͳ��List����
		addressCountTable.updateLovoTable(null);
	}
}