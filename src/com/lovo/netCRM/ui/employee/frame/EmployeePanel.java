package com.lovo.netCRM.ui.employee.frame;

import com.lovo.netCRM.bean.EmployeeBean;
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
	private final int pageSize = 3;
	//�ܼ�¼��
	private  static int counts;
	//��ҳ��
	private static int pageNum;

	private String item = null;

	private String value = "";

	private boolean onceFind = false;

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
		onceFind = false;
		pageNow = 1;
		this.updateEmployeeTable();//pageNow=1
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
					prevClick();
				}

			}});
		
		LovoButton nextButton = new LovoButton("��һҳ",0,0,this);
		nextButton.setBounds(120, 400, 50, 20);
		
		nextButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(pageNow < pageNum){
					pageNOTxt.setText(++pageNow + "");
					nextClick();
				}

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
				//���ó�ʼ��
				onceFind = false;
				/**
				 * �ж��Ƿ�ҳ
				 */
				EmployeeBean empCounts = (EmployeeBean) new EmployeeServiceImp().getAllStaffs("����Ա��", "").get(0);
				//�õ������������ܼ�¼��
				int allCounts = empCounts.getID();
				counts = allCounts;
				//�õ�δ���ѧ����ѧ����ҳ��
				int oldPageNum = pageNum;
				int newPageNum = (int) Math.ceil(counts / (pageSize * 1.0));

				if(oldPageNum < newPageNum){//��������ҳ��
					pageNum = newPageNum;
				}
				//��ת�����µ���һҳ
				ArrayList<Object> checkEmps = new EmployeeServiceImp().getAllStaffs(pageNum, pageSize, "����Ա��", "");
				//������ҳ��
				setTotalPage(pageNum);
				//���õ�ǰҳΪ���һҳ
				pageNow = pageNum;
				pageNOTxt.setText(pageNow+"");
				employeeTable.updateLovoTable(checkEmps);
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
				onceFind = false;
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
				onceFind = false;
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
				onceFind = false;
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
				findEmployee();
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
		updateEmployeeTable();//pageNow=1
		employeeTable.setSizeAndLocation(20, 90, 700, 300);
	}
	/**
	 * ���±������
	 */
	private void updateEmployeeTable(){//ֻ�г�ʼ�������Ա���ɹ���Ż����
		//Ĭ�ϲ���ȫ��Ա��
		ArrayList<Object> limitAllEmps = new EmployeeServiceImp().getAllStaffs(1, pageSize, "����Ա��", "");
		ArrayList<Object> allEmps =new EmployeeServiceImp().getAllStaffs();
		//���������ܼ�¼��
		counts = allEmps.size();
		pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
		//������ҳ��
		pageNOTxt.setText(pageNow + "");
		this.setTotalPage(pageNum);//��ҳ�������������µ�ȫ����¼�������Է�ҳ��С
		employeeTable.updateLovoTable(limitAllEmps);
	}
	
	/**
	 * ��һҳ����¼�
	 */
	private void prevClick(){
		if(!onceFind){//���û�е����ѯ ���ǰ�ȫ��������ʾ
			ArrayList<Object> limitEmps = null;
			limitEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, "����Ա��", "");
			if(limitEmps != null){//���Ϊ��,�Ͳ����±��
				employeeTable.updateLovoTable(limitEmps);
			}
		}else{
			ArrayList<Object> limitEmps = null;
			limitEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, item, value);
			if(limitEmps != null){//���Ϊ��,�Ͳ����±��
				employeeTable.updateLovoTable(limitEmps);
			}
		}

	}
	/**
	 * ��һҳ����¼�
	 */
	private void nextClick(){
		if(!onceFind){

			ArrayList<Object> limitEmps = null;
			limitEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, "����Ա��", "");
			if(limitEmps != null){//���Ϊ��,�Ͳ����±��
				employeeTable.updateLovoTable(limitEmps);
			}
		}else{
			ArrayList<Object> limitEmps = null;
			limitEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, item, value);
			if(limitEmps != null){//���Ϊ��,�Ͳ����±��
				employeeTable.updateLovoTable(limitEmps);
			}
		}
	}
	/**
	 * ת��ָ��ҳ��
	 */
	private void goClick(String pageNO){
		ArrayList<Object> limitEmps = null;
		if(item == null){
			item = "����Ա��";
		}
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
				if(!onceFind){//û�е����ѯʱ,��������ѯ
					//�ܼ�¼��
					ArrayList<Object> allEmps =new EmployeeServiceImp().getAllStaffs();
					counts = allEmps.size();
					pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
					if(pageNow > pageNum){//���ɾ�������һ������һҳ�ĵ�һ��
						ArrayList<Object> limitAllEmps = new EmployeeServiceImp().getAllStaffs(pageNum, pageSize, "����Ա��", "");
						pageNow = pageNum;
						this.setTotalPage(pageNum);
						pageNOTxt.setText(pageNum+"");
						employeeTable.updateLovoTable(limitAllEmps);
					}else{
						ArrayList<Object> limitAllEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, "����Ա��", "");
						this.setTotalPage(pageNow);
						employeeTable.updateLovoTable(limitAllEmps);
					}
				}else{
					EmployeeBean empCounts = (EmployeeBean) new EmployeeServiceImp().getAllStaffs(item, value).get(0);
					//�õ������������ܼ�¼��
					int allCounts = empCounts.getID();
					counts = allCounts;
					pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
					if(pageNow > pageNum){//���ɾ�������һ������һҳ�ĵ�һ��
						ArrayList<Object> limitAllEmps = new EmployeeServiceImp().getAllStaffs(pageNum, pageSize,  item, value);
						pageNow = pageNum;
						this.setTotalPage(pageNum);
						pageNOTxt.setText(pageNum+"");
						employeeTable.updateLovoTable(limitAllEmps);
					}else{
						ArrayList<Object> limitAllEmps = new EmployeeServiceImp().getAllStaffs(pageNow, pageSize, item, value);
						this.setTotalPage(pageNow);
						employeeTable.updateLovoTable(limitAllEmps);
					}
				}

			}
		}
	}
	/**
	 * 	����Ա��
	 *  item ����ѡ��
	 *  value ����ֵ
	 */
	private void findEmployee(){
		onceFind = true;
		//�õ�ѡ��(����)
		item = itemCombox.getItem().toString();
		//�õ�ѡ��ֵ(ģ����ѯ����)
		value = valueTxt.getText();
		JOptionPane.showMessageDialog(null, item + "   " + value);
		EmployeeBean empCounts = (EmployeeBean) new EmployeeServiceImp().getAllStaffs(item, value).get(0);
		//�õ������������ܼ�¼��
		int allCounts = empCounts.getID();
		counts = allCounts;
		if(allCounts != 0){
			ArrayList<Object> checkEmps = new EmployeeServiceImp().getAllStaffs(1, pageSize, item, value);
			//�������������м�¼���ܺ�
			//���±��,��ʾ��ѯ���
			if(checkEmps != null){
				//JOptionPane.showMessageDialog(null,checkEmps.size());
				employeeTable.updateLovoTable(checkEmps);
				pageNum = (int) Math.ceil(allCounts / (pageSize * 1.0));
				this.setTotalPage(pageNum);
				pageNow = 1;
				pageNOTxt.setText("1");
			}
		}
	}
}
