package com.lovo.netCRM.ui.schoolActive.frame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoList;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ѧУ����
 * ��������:2012-10-6
 */
public class SchoolActivePanel extends JPanel{
	/**ѧУ������*/
	private LovoTable schoolTable;
	/**�������*/
	private JFrame jf;
	/**����id*/
	private  int cityId;
	/**�����б��*/
	private LovoList cityList = new LovoList(20,90,150,300,this){
		public void onchange(Object t){
			cityId = getCityId(t);
			showSchool(cityId);
			System.out.println("&&&");
		}
	};
	public SchoolActivePanel(JFrame jf){
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		new LovoTitleLabel("ѧ У �� ��",this);
		this.initTable();
		this.initButton();
		this.initData();
	}
	/**
	 * ��ʼ������
	 */
	public void initData(){
		if(cityId==0){
			this.initList();
		}
		else{
			showSchool(cityId);
		}
	}
	
	/**
	 * ��ʼ����ť
	 *
	 */
	private void initButton() {
		LovoButton lbadd = new LovoButton("����»",200,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
//				if(key == -1){
//				JOptionPane.showMessageDialog(null,"��ѡ����");
//				return;
//				}
				new SchoolActiveAddDialog(jf,key);
			}});
		
		
		LovoButton lbshow = new LovoButton("�鿴���Ϣ",400,500,this);
		lbshow.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"��ѡ����");
//					return;
//				}
				
				new SchoolActiveShowDialog(jf,schoolTable.getSelectColumn(0),key);
			}});
	}
	
	//--------------------------
	/**
	 * ���ݳ��ж��󣬵õ�����id
	 * @param cityObj ���ж���
	 * @return
	 */
	private int getCityId(Object cityObj){
		
		return 0;
	}
	/**
	 * ��ʼ�����
	 */
	private void initTable() {
		schoolTable = new LovoTable(this,
				new String[]{"ѧУ����","У��","¼��ʱ��","��ϵ�绰"},
				new String[]{},//ѧУʵ������������ new String[]{"schoolName","schoolMaster"}
				"");//���������� schoolId
		schoolTable.setSizeAndLocation(180, 90, 550, 300);

	}
	/**
	 * ��ʼ���б��
	 *
	 */
	private void initList() {
		List list = new ArrayList();
		list.add("�ɶ���");
		list.add("������");
		cityList.setList(list);
	}
	/**
	 * ��ʾ���ж�Ӧ��ѧУ
	 *
	 */
	private void showSchool(int cityId){
		System.out.println("&&&&&");
//		���±��,����List����
		schoolTable.updateLovoTable(null);
	}
}
