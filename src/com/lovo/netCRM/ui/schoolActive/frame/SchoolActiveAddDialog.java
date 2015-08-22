package com.lovo.netCRM.ui.schoolActive.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ���ѧУ��Ի���
 * ��������:2012-10-8
 */
public class SchoolActiveAddDialog extends JDialog{
	/**ѧУId*/
	private int schoolId;
	/**������ı���*/
	private LovoTxt nameTxt = new LovoTxt("�����",50,50,this);
	/**�ʱ���ı���*/
	private LovoDate timeTxt = new LovoDate("�ʱ��",50,100,this);
	/**��ص��ı���*/
	private LovoTxt addressTxt = new LovoTxt("��ص�",50,150,this);
	/**������ı���*/
	private LovoTxt titleTxt = new LovoTxt("�����",50,200,this);
	/**������*/
	private LovoComboBox deptTxt;
	/**������*/
	private LovoComboBox employeeTxt = new LovoComboBox("������",new ArrayList(),50,300,this);;

	/**
	 * ��ӻ�Ի���
	 * @param jf �������
	 * @param schoolTable ѧУ���
	 * @param cityObj ���г���
	 */
	public SchoolActiveAddDialog(JFrame jf,int schoolId){
		super(jf,true);
		this.schoolId = schoolId;
		this.setLayout(null);
		this.setTitle("����»");
		
		this.init();
		
		this.setBounds(400, 150, 350, 450);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		this.initComboBox();
		
		LovoButton lbadd = new LovoButton("���",50,350,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addActive(schoolId);
				if(isOk){
					SchoolActiveAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",180,350,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolActiveAddDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * ��ʼ��������������
	 *
	 */
	private void initComboBox(){
		//��Ӳ���List����
		this.deptTxt = new LovoComboBox("������",new ArrayList(),50,250,this){
			/**
			 * ���ݲ���ID��ʾԱ������
			 * @param deptObj ���Ŷ���
			 */
			public void onchange(Object deptObj){
				
				//����Ա������
				employeeTxt.setList(null);
			}
		};

	
	}
	
	/**
	 * ��ӻ
	 */
	private boolean addActive(int schoolId){
		//��֤����,��֤ʧ�ܷ���false
		
		//��װʵ��
		
		return true;
		
	}
}
