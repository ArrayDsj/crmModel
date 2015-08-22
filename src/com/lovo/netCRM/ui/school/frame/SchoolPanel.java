package com.lovo.netCRM.ui.school.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoList;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;

/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ѧУ��ʾ���
 * ��������:2012-10-11
 */
public class SchoolPanel extends JPanel{
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
	public SchoolPanel(JFrame jf){
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
		LovoButton lbadd = new LovoButton("¼����ѧУ",100,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				new SchoolAddDialog(jf,SchoolPanel.this);
			}});
		
		
		LovoButton lbshow = new LovoButton("�鿴ѧУ��Ϣ",300,500,this);
		lbshow.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"��ѡ����");
//					return;
//				}
				
				new SchoolShowDialog(jf,key);
			}});
		
		LovoButton lbupdate = new LovoButton("�޸�ѧУ��Ϣ",500,500,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"��ѡ����");
//					return;
//				}
				
				new SchoolUpdateDialog(jf,key,SchoolPanel.this);
			}});
		
		
		LovoButton lbask = new LovoButton("��������",100,580,this);
		lbask.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"��ѡ����");
//					return;
//				}
				
				apply(key,schoolTable.getSelectColumn(3));
			}});
		
		LovoButton lbcheck = new LovoButton("���",300,580,this);
		lbcheck.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"��ѡ����");
//					return;
//				}
				
				checkSchool(key,schoolTable.getSelectColumn(3));
			}});
		
		LovoButton lbAddSpeak = new LovoButton("��ӹ�ͨ��¼",500,580,this);
		lbAddSpeak.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"��ѡ����");
//					return;
//				}
				
				new SchoolCommunicateAddDialog(jf,key);
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
				new String[]{"ѧУ����","У��","¼��ʱ��","״̬"},
				new String[]{},//ѧУʵ������������ new String[]{"schoolName","schoolMaster"}
				"");//���������� schoolId
		schoolTable.setSizeAndLocation(180, 90, 550, 300);
	
	}
	/**
	 * ��ʼ���б��
	 *
	 */
	private void initList() {
		cityList.setList(new ArrayList());
	}
	/**
	 * ��ʾ���ж�Ӧ��ѧУ
	 * @param cityObj ���ж���
	 */
	private void showSchool(int cityId){
		
//		���±��,����List����
		schoolTable.updateLovoTable(null);
	}
	/**
	 * ��������
	 * @param schoolId ѧУID
	 * @param schoolState ѧУ״̬
	 * @param cityObj�����ж���
	 */
	private void apply(int schoolId,String schoolState){
		//��֤״̬�Ƿ�Ϊ����Ǣ�С�
		
		//��ʾ����������
		this.initData();
	}
	/**
	 * ���
	 * @param schoolId ѧУID
	 * @param schoolState ѧУ״̬
	 * @param cityObj ���ж���
	 */
	private void checkSchool(int schoolId,String schoolState){
//		��֤״̬�Ƿ�Ϊ�����󡱻����δͨ����
		
		new SchoolCheckDialog(jf,schoolId,this);
	}
}
