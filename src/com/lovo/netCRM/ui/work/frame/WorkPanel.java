package com.lovo.netCRM.ui.work.frame;

import com.lovo.netCRM.bean.PositionBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
import com.lovo.netCRM.service.imp.PositionServiceImp;
import com.lovo.netCRM.util.Switch;

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
 * @description ְλ�������
 * ��������:2012-10-6
 */
public class WorkPanel  extends JPanel{
	/**ְλ������*/
	private LovoTable workTable;
	/**�������*/
	private JFrame jf;
	public WorkPanel(JFrame jf){
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		new LovoTitleLabel("ְ λ �� ��",this);
		this.initTable();
		this.initButton();
		this.initData();
	}
	/**
	 * ��ʼ������
	 */
	public void initData(){
		this.updateWorkTable();
	}
	/**
	 * ��ʼ����ť
	 *
	 */
	private void initButton() {
		LovoButton lbadd = new LovoButton("�����ְλ",200,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				new WorkAddDialog(jf,WorkPanel.this);
			}});
		
		
		LovoButton lbupdate = new LovoButton("�޸�ְλȨ��",400,500,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = workTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"��ѡ����");
					return;
				}
				
				new WorkUpdateDialog(jf,key,WorkPanel.this);
			}});
	}
	
	//--------------------------
	
	/**
	 * ��ʼ�����
	 */
	private void initTable() {
		workTable = new LovoTable(this,
				new String[]{"ְλ����","ְλ����","��ѯȨ��","����Ȩ��","����ͳ�Ʒ���","Ȩ�޹���","��̨����"},
				//ְλʵ������������ new String[]{"workName","description"}
				new String[]{"name","describe","checkRight","queryRight","saleRight","managerRight","backRight"},
				"id");//���������� workId
		workTable.setSizeAndLocation(20, 90, 700, 300);
		
	}
	/**
	 * ���±������
	 */
	private void updateWorkTable(){
		
		ArrayList<Object> allPos = new PositionServiceImp().getAllPositions();
		ArrayList<Object> switchs = new ArrayList<Object>();
		int id = 0;
		for(Object pos : allPos) {
			PositionBean p = (PositionBean)pos;
			Switch s = new Switch();
			s.setName(p.getName());
			s.setDescribe(p.getDescribe());
			s.setCheckRight(Switch.boolean2String(p.isCheckRight()));
			s.setQueryRight(Switch.boolean2String(p.isQueryRight()));
			s.setSaleRight(Switch.boolean2String(p.isSaleRight()));
			s.setManagerRight(Switch.boolean2String(p.isManagerRight()));
			s.setBackRight(Switch.boolean2String(p.isBackRight()));
			switchs.add(s);
		}
		if(switchs != null){
			//workTable.updateLovoTable(switchs);
		}

	}
}
