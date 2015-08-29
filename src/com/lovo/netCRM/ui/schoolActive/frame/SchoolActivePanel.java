package com.lovo.netCRM.ui.schoolActive.frame;

import com.lovo.netCRM.bean.AreaBean;
import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoList;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
import com.lovo.netCRM.dao.imp.SchoolDaoImp;
import com.lovo.netCRM.service.imp.AreaServiceImp;

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
				if(key == -1){
					JOptionPane.showMessageDialog(null,"��ѡ����");
					return;
				}
				new SchoolActiveAddDialog(SchoolActivePanel.this,jf,key);
			}});
		
		
		LovoButton lbshow = new LovoButton("�鿴���Ϣ",400,500,this);
		lbshow.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"��ѡ����");
					return;
				}
				//schoolTable.getSelectColumn(0) ��˼��ѡ��ѧУ�б��еĵ�һ����,����ѧУ����
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
		if(cityObj instanceof AreaBean){
			AreaBean area = (AreaBean)cityObj;
			return area.getId();
		}
		return 0;
	}
	/**
	 * ��ʼ�����
	 */
	private void initTable() {
		schoolTable = new LovoTable(this,
				new String[]{"ѧУ����","У��","¼��ʱ��","��ϵ�绰"},
				//ѧУʵ������������ new String[]{"schoolName","schoolMaster"}
				new String[]{"name","master","inTime","phone"},
				"id");//���������� schoolId
		schoolTable.setSizeAndLocation(180, 90, 550, 300);

	}
	/**
	 * ��ʼ���б��
	 *
	 */
	private void initList() {
		//�������е���
		ArrayList<Object> allAreas = new AreaServiceImp().getAllAreas();
		cityList.setList(allAreas);
	}
	/**
	 * ��ʾ���ж�Ӧ��ѧУ
	 *
	 */
	private void showSchool(int cityId){
		ArrayList<SchoolBean> schools = new ArrayList<SchoolBean>();
		schools = new SchoolDaoImp().getSchoolByAreaID(cityId);
		//���±��,����List����
		schoolTable.updateLovoTable(schools);
	}
}
