package com.lovo.netCRM.ui.employee.frame;

import com.lovo.netCRM.component.*;
import com.lovo.netCRM.service.imp.EmployeeServiceImp;

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
 * @description Ա���������
 * ��������:2012-10-5
 */
public class EmployeePanel extends JPanel{
	/**Ա��������*/
	private LovoTable employeeTable;
	/**�������*/
	private JFrame jf;
	/**ҳ���ı���*/
	private JTextField pageNOTxt = new JTextField("1");
	/**��ҳ����ǩ*/
	private JLabel jltTotalPage = new JLabel("/   ҳ");
	/**ѡ��������*/
	private  LovoComboBox<String> itemCombox;
	/**ֵ�ı���*/
	private  JTextField valueTxt = new JTextField();

	//��ǰҳ
	private static int pageNow = 1;
	//��ҳ��С
	private final int pageSize = 10;
	//�ܼ�¼��
	private  static int counts;
	//��ҳ��
	private static int pageNum;

	public EmployeePanel(JFrame jf){
		ArrayList<Object> allEmps =new EmployeeServiceImp().getAllStaffs();
		//�ܼ�¼��
		this.counts = allEmps.size();
		this.pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		new LovoTitleLabel("Ա �� �� ��",this);
		//��ʼ�����
		this.initTable();
		//��ʼ����ť
		this.initButton();
		//��ѯ��ťҳ
		this.initFindPanel();
		//��ʼ������
		this.initData();
	}
	/**
	 * ��ʼ������
	 */
	public void initData(){
		this.updateEmployeeTable(pageNow);
	}
	/**
	 * ��ʼ����ť
	 *
	 */
	private void initButton() {
		LovoButton prevButton = new LovoButton("��һҳ",0,0,this);
		prevButton.setBounds(50, 400, 50, 20);
		
		prevButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(pageNow > 1){
					pageNOTxt.setText(--pageNow + "");
				}
				prevClick();
			}});
		
		LovoButton nextButton = new LovoButton("��һҳ",0,0,this);
		nextButton.setBounds(120, 400, 50, 20);
		
		nextButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(pageNow < pageNum){
					pageNOTxt.setText(++pageNow + "");
				}
				nextClick();
			}});
		
		JLabel jld = new JLabel("��");
		jld.setBounds(200, 400, 20, 20);
		this.add(jld);
		
		pageNOTxt.setBounds(215, 400, 20, 20);
		this.add(pageNOTxt);
		
		
		jltTotalPage.setBounds(240, 400, 50, 20);
		this.add(jltTotalPage);
		
		LovoButton goButton = new LovoButton("go",0,0,this);
		goButton.setBounds(295, 400, 25, 20);
		goButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				goClick(pageNOTxt.getText());
			}});
		
		
		LovoButton lbadd = new LovoButton("�����Ա��",50,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				new EmployeeAddDialog(jf,EmployeePanel.this);
			}});
		
		LovoButton lbdel = new LovoButton("ɾ��Ա��",250,500,this);
		lbdel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = employeeTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"��ѡ����");
					return;
				}
				delEmployee(key);
			}});
		
		
		LovoButton lbupdate = new LovoButton("�޸�Ա����Ϣ",50,570,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = employeeTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"��ѡ����");
					return;
				}
				new EmployeeUpdateDialog(jf,key,EmployeePanel.this);
			}});
		
		
		LovoButton lbshow = new LovoButton("�鿴Ա����Ϣ",250,570,this);
		lbshow.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = employeeTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"��ѡ����");
					return;
				}
				new EmployeeShowDialog(jf,key);
			}});
	}
	/**
	 * ��ʼ����ѯ��ť
	 *
	 */
	private void initFindPanel(){

		LovoTitlePanel jp = new LovoTitlePanel("��ѯԱ��",400, 480, 320, 150,this);

		this.itemCombox = new LovoComboBox<String>(
				new String[]{"����Ա��","Ա������","��������",
						"�Ļ��̶�","����ְλ"},30,50,jp);

		valueTxt.setBounds(160, 50, 120, 20);
		jp.add(valueTxt);
		LovoButton lb = new LovoButton("����",180,100,jp);
		lb.setSize(60, 20);

		//�����ѯ�¼�
		lb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findEmployee(pageNow);
			}
		});
		
	}
	/**
	 * ������ҳ��
	 *
	 */
	private void setTotalPage(int pageNum) {
		this.jltTotalPage.setText("/" + pageNum + "  ҳ");
	}
	
	
	//-------------------------------------------------
	/**
	 * ��ʼ�����
	 *
	 */
	private void initTable() {
		employeeTable = new LovoTable(this,
				new String[]{"����","�Ա�","��������","�Ļ��̶�","��ϵ��ʽ","���ڲ���", "����ְλ"},
				//Ա��ʵ������������ new String[]{"employeeName","sex"}
				new String[]{"name","sex","birthday","edu","phone","dept","position"},
				//���������� employeeId
				"ID");
		//����һ�����ݿ�����
		updateEmployeeTable(pageNow);
		employeeTable.setSizeAndLocation(20, 90, 700, 300);
	}
	/**
	 * ���±������
	 */
	private void updateEmployeeTable(int pageNow){

		//Ĭ�ϲ���ȫ��Ա��
		ArrayList<Object> limitAllEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, "����Ա��", "");
		employeeTable.updateLovoTable(limitAllEmps);
		//������ҳ��
		this.setTotalPage(pageNum);
	}
	
	/**
	 * ��һҳ����¼�
	 */
	private void prevClick(){
		ArrayList<Object> limitEmps = null;
		//�õ�ѡ��(����)
		String item = itemCombox.getItem();
		//�õ�ѡ��ֵ(ģ����ѯ����)
		String value = valueTxt.getText();

		limitEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, item, value);
		if(limitEmps != null){//���Ϊ��,�Ͳ����±��
			employeeTable.updateLovoTable(limitEmps);
		}
	}
	/**
	 * ��һҳ����¼�
	 */
	private void nextClick(){
		ArrayList<Object> limitEmps = null;
		//�õ�ѡ��(����)
		String item = itemCombox.getItem();
		//�õ�ѡ��ֵ(ģ����ѯ����)
		String value = valueTxt.getText();
		//�õ���ҳ��

		limitEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, item, value);
		if(limitEmps != null){
			employeeTable.updateLovoTable(limitEmps);
		}
	}
	/**
	 * ת��ָ��ҳ��
	 */
	private void goClick(String pageNO){
		ArrayList<Object> limitEmps = null;
		//�õ�ѡ��(����)
		String item = itemCombox.getItem();
		//�õ�ѡ��ֵ(ģ����ѯ����)
		String value = valueTxt.getText();
		int page = -1;
		try{
			page = Integer.parseInt(pageNO);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"�������");
		}
		if(page <= pageNum){//��������ҳ��С�ڵ�����ҳ��
			pageNow = Integer.parseInt(pageNO);//��ת���û������ҳ��
			limitEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, item, value);
		}
		if(limitEmps != null){
			employeeTable.updateLovoTable(limitEmps);
		}
	}
	
	/**
	 * ɾ��Ա��
	 * @param employeeId Ա��ID
	 */
	private void delEmployee(int employeeId){
		//���±��,��ʾɾ�����
		//�������ݿ����,ɾ��ѡ�е��û�(ֻ�Ǹ���status״̬,ԭ��Ϊ0,��Ϊ1)
		if((JOptionPane.showConfirmDialog(null,"�Ƿ�ɾ��ѡ��Ա����Ϣ","ɾ��",JOptionPane.YES_NO_OPTION)) == 0){
			boolean dele = new EmployeeServiceImp().deleteStaff(employeeId);
			if(dele){
				this.updateEmployeeTable(pageNow);//���ɾ���ɹ�,���±��
				counts--;
			}
		}
	}
	/**
	 * 	����Ա��
	 *  item ����ѡ��
	 *  value ����ֵ
	 */
	private void findEmployee(int pageNow){
		//�õ�ѡ��(����)
		String item = itemCombox.getItem();
		//�õ�ѡ��ֵ(ģ����ѯ����)
		String value = valueTxt.getText();
		JOptionPane.showMessageDialog(null,item   + "   " + value);
		ArrayList<Object> checkEmps = new EmployeeServiceImp().getAllStaffs(1, pageSize, item, value);
		//���±��,��ʾ��ѯ���
		if(checkEmps != null){
			JOptionPane.showMessageDialog(null,checkEmps.size());
			employeeTable.updateLovoTable(checkEmps);
			this.setTotalPage(pageNum);
		}
	}
}
