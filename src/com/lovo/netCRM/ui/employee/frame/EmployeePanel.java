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
	
	public EmployeePanel(JFrame jf){
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
		this.updateEmployeeTable(1);
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
				prevClick();
			}});
		
		LovoButton nextButton = new LovoButton("��һҳ",0,0,this);
		nextButton.setBounds(120, 400, 50, 20);
		
		nextButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
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
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"��ѡ����");
//					return;
//				}
				
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
		
		lb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				findEmployee(1);

			}
		});
		
	}
	/**
	 * ������ҳ��
	 * @param countPage ��ҳ��
	 */
	private void setTotalPage(int countPage) {
		this.jltTotalPage.setText("/" + countPage + "  ҳ");
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
		updateEmployeeTable(1);
		employeeTable.setSizeAndLocation(20, 90, 700, 300);
		
	}
	/**
	 * ���±������
	 */
	private void updateEmployeeTable(int pageNO){
		//���±��,��������Ա��List����
		//�����ݿ���ȡ������
		ArrayList<Object> allEmps =new EmployeeServiceImp().getAllStaffs();
		employeeTable.updateLovoTable(allEmps);
		//������ҳ��
		this.setTotalPage(2);
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
	 */
	private void goClick(String pageNO){
		
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
			this.updateEmployeeTable(1);
		}
	}
	/**
	 * ����Ա��
	 *  item ����ѡ��
	 *  value ����ֵ
	 */
	private void findEmployee(int pageNO){
		//�õ�ѡ��(����)
		String item = itemCombox.getItem();
		//�õ�ѡ��ֵ(ģ����ѯ����)
		String value = valueTxt.getText();
		ArrayList<Object> checkEmps = new EmployeeServiceImp().getStaffByCon(item,value);
		//���±��,��ʾ��ѯ���
		employeeTable.updateLovoTable(checkEmps);
		//������ҳ��
		this.setTotalPage(pageNO);
	}
}
