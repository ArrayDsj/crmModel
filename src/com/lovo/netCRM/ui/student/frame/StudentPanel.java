package com.lovo.netCRM.ui.student.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.lovo.netCRM.component.LovoAccordion;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
import com.lovo.netCRM.component.LovoTitlePanel;

/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ѧ�����
 * ��������:2012-10-14
 */
public class StudentPanel extends JPanel{
	/**ѧ���������*/
	private LovoTable studentTable;
	/**�������*/
	private JFrame jf;
	/**�����ַ������*/
	private LovoAccordion cityAccordion;
	/**���е�ѧУid*/
	private int schoolId;
	/**ҳ���ı���*/
	private JTextField pageNOTxt = new JTextField("1");
	/**��ҳ����ǩ*/
	private JLabel jltTotalPage = new JLabel("/   ҳ");
	/**ѡ��������*/
	private  LovoComboBox<String> itemCombox;
	/**ֵ�ı���*/
	private  JTextField valueTxt = new JTextField();
	
	public StudentPanel(JFrame jf){
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		new LovoTitleLabel("ѧ �� �� ��",this);
		this.initTable();
		this.initButton();
		this.initData();
		cityAccordion.setBounds(20,90,150,300);
		this.initFindPanel();
	}
	
	/**
	 * ��ʼ������
	 */
	public void initData(){
		if(schoolId==0){
			if(cityAccordion == null){
				this.initAccordion();
			}
			this.updateAccordion();
		}
		else{
			this.updateStudentTable(schoolId,1);
		}
		
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	/**
	 * ��ʼ����ť
	 *
	 */
	private void initButton() {
		LovoButton prevButton = new LovoButton("��һҳ",0,0,this);
		prevButton.setBounds(200, 400, 50, 20);
		
		prevButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				prevClick();
			}});
		
		LovoButton nextButton = new LovoButton("��һҳ",0,0,this);
		nextButton.setBounds(270, 400, 50, 20);
		
		nextButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				nextClick();
			}});
		
		JLabel jld = new JLabel("��");
		jld.setBounds(350, 400, 20, 20);
		this.add(jld);
		
		pageNOTxt.setBounds(365, 400, 20, 20);
		this.add(pageNOTxt);
		
		
		jltTotalPage.setBounds(390, 400, 50, 20);
		this.add(jltTotalPage);
		
		LovoButton goButton = new LovoButton("go",0,0,this);
		goButton.setBounds(445, 400, 25, 20);
		goButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				goClick(pageNOTxt.getText());
			}});
		
		
		LovoButton lbadd = new LovoButton("����ѧ��",50,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
//				if(schoolId == 0){
//					JOptionPane.showMessageDialog(null,"��ѡ��ѧУ");
//					return;
//				}
				
				new StudentAddDialog(jf,schoolId,StudentPanel.this);
			}});
		
		LovoButton lbshow = new LovoButton("�鿴ѧ����Ϣ",170,500,this);
		lbshow.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"��ѡ����");
//					return;
//				}
				
				new StudentShowDialog(jf,key);
			}});
		
		
		LovoButton lbdel = new LovoButton("ɾ��ѧ��",290,500,this);
		lbdel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"��ѡ����");
//					return;
//				}
				
				delEmployee(key);
			}});
		
		
		LovoButton lbupdate = new LovoButton("�޸�ѧ����Ϣ",50,570,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"��ѡ����");
//					return;
//				}
				
				new StudentUpdateDialog(jf,schoolId,key,StudentPanel.this);
			}});
		
		LovoButton lbAddBack = new LovoButton("���ӻط���¼",170,570,this);
		lbAddBack.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"��ѡ����");
//					return;
//				}
				
				new StudentVisitAddialog(jf,key,schoolId);
			}});
	}
	/**
	 * ��ʼ����ѯ��ť
	 *
	 */
	private void initFindPanel(){
		LovoTitlePanel jp = new LovoTitlePanel("��ѯѧ��",400, 480, 320, 150,this);
	
		this.itemCombox = new LovoComboBox<String>(
				new String[]{"ѧ������","�༶","��Ա",
						"�ǻ�Ա"},30,50,jp);
		
		this.valueTxt.setBounds(160, 50, 120, 20);
		jp.add(this.valueTxt);
		
		LovoButton lb = new LovoButton("����",180,100,jp);
		lb.setSize(60, 20);
		
		lb.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
//				if(schoolId == 0){
//				JOptionPane.showMessageDialog(null,"��ѡ��ѧУ");
//					return;
//				}
				findStudent(schoolId,1);
				
			}});
		
	}
	/**
	 * ������ҳ��
	 * @param countPage ��ҳ��
	 */
	private void setTotalPage(int countPage) {
		this.jltTotalPage.setText("/"+countPage+"  ҳ");
	}
	
	//--------------------------
	/**
	 * ����ѧУ����õ�ѧУID
	 * @param schoolObj ѧУ����
	 * @return
	 */
	private int getSchoolId(Object schoolObj){
		return 0;
	}
	/**
	 * �����ַ���
	 */
	private void updateAccordion(){
		this.cityAccordion.updateAccordion(new ArrayList());
	}
	/**
	 * ���±���
	 * @param schoolId ѧУID
	 * @param int pageNO ҳ��
	 */
	private void updateStudentTable(int schoolId,int pageNO){
		
		//���±���,����List����
		studentTable.updateLovoTable(null);
		//������ҳ��
		this.setTotalPage(0);
	}
	
	/**
	 * ��ʼ������
	 */
	private void initTable() {
		studentTable = new LovoTable(this,
				new String[]{"ѧ������","�Ա�","�༶","״̬","��ϵ�绰"},
				new String[]{},//ѧ��ʵ������������ new String[]{"studentName","sex"}
				"");//���������� studentId
		studentTable.setSizeAndLocation(180, 90, 550, 300);
		
	}
	
	/**
	 * ��һҳ����¼�
	 */
	private void prevClick(){
		
	}
	/**
	 * ��һҳ����¼�
	 */
	private void nextClick(){
		
	}
	/**
	 * ת��ָ��ҳ��
	 * @param pageNO ҳ��
	 */
	private void goClick(String pageNO){
		
	}
	
	/**
	 * ��ʼ���ַ������
	 *
	 */
	private void initAccordion() {
		//�ڶ�������Ϊ���м���cityList������������Ϊ��������ѧУ���ϵ�������schoolList
		 cityAccordion = new LovoAccordion(this,new ArrayList(),""){
				
				/**
				 * ѧУ�б������¼�
				 * @param schoolObj ѧУ����
				 */
			 @Override
				public void clickEvent(Object schoolObj) {
				 //��¼����ѧУid
				 schoolId = getSchoolId(schoolObj);

					//��ʾ����ѧУѧ��
				 updateStudentTable(schoolId,1);
				}
			};
	}
	
	/**
	 * ɾ��ѧ��
	 * @param studentId ѧ��ID
	 */
	private void delEmployee(int studentId){
		
		
//		��ʾɾ�����
		updateStudentTable(schoolId,1);
	}
	/**
	 * ����ѧ��
	 * @param schoolId ѧУid
	 * @param pageNO ҳ��
	 */
	private void findStudent(int schoolId,int pageNO){
		//�õ�ѡ��
		String item = itemCombox.getItem();
		//�õ�ѡ��ֵ
		String value = valueTxt.getText();
		
		
		
//		���±���,��ʾ��ѯ���
		studentTable.updateLovoTable(null);
		//������ҳ��
		this.setTotalPage(pageNO);
	}

}