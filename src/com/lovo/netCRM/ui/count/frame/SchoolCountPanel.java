package com.lovo.netCRM.ui.count.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoList;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
import com.lovo.netCRM.component.LovoTitlePanel;
/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ѧУͳ�����
 * ��������:2012-10-14
 */
public class SchoolCountPanel extends JPanel{
	/**ѧУ������*/
	private LovoTable schoolCountTable;
	/**����id*/
	private int cityId;
	
	/**�����б��*/
	private LovoList cityList = new LovoList(20,90,150,300,this){
		public void onchange(Object cityObj){
			cityId = getCityId(cityObj);
			showAll(cityId);
		}
	};
	
	public SchoolCountPanel(){
		this.setLayout(null);
		this.init();
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		new LovoTitleLabel("ѧ У ͳ ��",this);
		this.initTable();
		this.initButton();
		
		LovoTitlePanel jp = new LovoTitlePanel("��¼��ʱ���ѯ",400, 430, 320, 180,this);
		
		final LovoDate startDate = new LovoDate("��ʼ����",20,30,jp);
		
		final LovoDate endDate = new LovoDate("��������",20,80,jp);
		
		LovoButton lb = new LovoButton("����",180,130,jp);
		lb.setSize(60, 20);
		
		lb.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				findSchoolCount(cityId,startDate.getText(),endDate.getText());
				
			}});
		this.initData();
	}
	
	public void initData(){
		if(cityId==0){
			this.initList();
		}
		else{
			showAll(cityId);
		}
	}
	
	/**
	 * ��ʼ����ť
	 *
	 */
	private void initButton() {
		LovoButton lbadd = new LovoButton("����ͳ����Ϣ",200,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
//				if(cityId == 0){
//					JOptionPane.showMessageDialog(null,"��ѡ�����");
//					return;
//				}
				showAll(cityId);
			}});
		
		
	
	}
	
	//--------------------------
	/**
	 * ��ʼ�����
	 */
	private void initTable() {
		schoolCountTable = new LovoTable(this,
				new String[]{"ѧУ����","�����༶����","��Ա����","�ǻ�Ա����"},
				new String[]{},//ѧУͳ��ʵ������������ new String[]{"schoolName","netClassNumber"}
				"");//���������� schoolId
		schoolCountTable.setSizeAndLocation(180, 90, 550, 300);
		
		
	}
	/**
	 * ��ʼ�������б��
	 *
	 */
	private void initList() {
		cityList.setList(new ArrayList());
	}
	
	/**
	 * ���ݳ��ж��󣬵õ�����id
	 * @param cityObj ���ж���
	 * @return
	 */
	private int getCityId(Object cityObj){
		
		return 0;
	}
	/**
	 * ��ʾ����ѧУ����ͳ����Ϣ
	 * @param cityId ����id
	 */
	private void showAll(int cityId){
		
//		���±��,����List����
		schoolCountTable.updateLovoTable(null);
	}
	/**
	 * ��¼��ʱ���ѯ����ѧУͳ����Ϣ
	 * @param cityId ����id
	 * @param startTime ��ʼ����
	 * @param endTime ��������
	 */
	private void findSchoolCount(int cityId,String startTime,String endTime){
		
		
//		���±��,����List����
		schoolCountTable.updateLovoTable(null);
	}
	
	

}
