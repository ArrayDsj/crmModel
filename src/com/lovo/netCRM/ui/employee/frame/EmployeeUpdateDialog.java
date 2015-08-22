package com.lovo.netCRM.ui.employee.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoImageLabel;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description Ա���޸ĶԻ���
 * ��������:2012-10-6
 */
public class EmployeeUpdateDialog extends JDialog{
	/**Ա�������*/
	private EmployeePanel emPanel;
	/**Ա��ID*/
	private int employeeId;
	/**������ǩ*/
	private LovoLabel nameLabel = new LovoLabel("��    ��",40,50,this);
	/**�Ա��ǩ*/
	private LovoLabel sexLabel = new LovoLabel("��    ��",320,50,this);
	
	/**�������ڱ�ǩ*/
	private LovoLabel birthdayLabel = new LovoLabel("��������",40,100,this);
	/**�Ļ��̶ȱ�ǩ*/
	private LovoLabel eduLabel = new LovoLabel("�Ļ��̶�",320,100,this);
	
	/**����רҵ��ǩ*/
	private LovoLabel specialityLabel = new LovoLabel("����רҵ",40,150,this);
	/**��ϵ��ʽ�ı���*/
	private LovoTxt phoneTxt = new LovoTxt("��ϵ��ʽ",320,150,this);
	/**��ͥסַ��ǩ*/
	private LovoLabel adressLabel = new LovoLabel("��ͥסַ",40,200,this);
	/**������ò*/
	private LovoComboBox<String> polityFaceTxt = new LovoComboBox<String>("������ò",new String[]{"��Ա","��Ա","��������","�޵�����ʿ"},320,200,this);
	/**���ڲ���*/
	private LovoComboBox deptTxt;
	/**����ְλ*/
	private LovoComboBox workTxt;
	/**��ְ���ڱ�ǩ*/
	private LovoLabel enterJobLabel = new LovoLabel("��ְ����",40,300,this);
	/**ͷ���ǩ*/
	private LovoImageLabel faceLabel = new LovoImageLabel(580, 70, 100, 130,this);
	
	
	public EmployeeUpdateDialog(JFrame jf,int employeeId,EmployeePanel emPanel){
		super(jf,true);
		this.emPanel = emPanel;
		this.employeeId = employeeId;
		
		this.setLayout(null);
		this.setTitle("�޸�Ա����Ϣ");
		
		this.init();
		
		this.setBounds(300, 100, 700, 450);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init(){
		this.initComboBox();
		this.initEmployeeData(this.employeeId);
		
		LovoButton lbupdate = new LovoButton("�޸�",150,350,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = updateEmployee(employeeId);
				if(isOk){
					EmployeeUpdateDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",400,350,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				EmployeeUpdateDialog.this.dispose();
			}});
	}
	
	
	//-------------------------------------------
	
	/**
	 * ��ʼ�����ź�ְλ������
	 *
	 */
	private void initComboBox(){
		//��Ӳ���List����
		this.deptTxt = new LovoComboBox("���ڲ���",new ArrayList(),40,250,this);
		//���ְλList����
		this.workTxt = new LovoComboBox("����ְλ",new ArrayList(),320,250,this);
	
	}
	
	/**
	 * ��ʼ������
	 * @param employeeId��Ա��ID
	 */
	private void initEmployeeData(int employeeId){
		
	}
	
	/**
	 * �޸�Ա����Ϣ
	 * @param employeeId Ա��ID
	 */
	private boolean updateEmployee(int employeeId){
		//��֤���ݣ�������֤ʧ�ܷ���false
		
		
		//�������ݣ���ʾ�޸Ľ��
		this.emPanel.initData();
		
		return true;
	}
}
