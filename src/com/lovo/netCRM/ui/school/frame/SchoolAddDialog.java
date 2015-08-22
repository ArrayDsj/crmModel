package com.lovo.netCRM.ui.school.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;

import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.component.LovoTxtArea;


/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ���ѧУ�Ի���
 * ��������:2012-10-11
 */
public class SchoolAddDialog extends JDialog{
	/**ѧУ�����*/
	private SchoolPanel schoolPanel;
	/**ѧУ�����ı���*/
	private LovoTxt nameTxt = new LovoTxt("ѧУ����",50,50,this);
	/**��������*/
	private LovoComboBox cityTxt;
	/**��ַ�ı���*/
	private LovoTxt addressTxt = new LovoTxt("ѧУ��ַ",50,100,this);
	/**У���ı���*/
	private LovoTxt masterTxt = new LovoTxt("У��",320,100,this);
	/**��ϵ�绰�ı���*/
	private LovoTxt phoneTxt = new LovoTxt("��ϵ�绰",50,150,this);
	/**ѧ�������ı���*/
	private LovoTxt studentNumberTxt = new LovoTxt("ѧ������",320,150,this);
	/**��ʦ�����ı���*/
	private LovoTxt teacherNumberTxt = new LovoTxt("��ʦ����",50,200,this);
	/**ip��ַ�ı���*/
	private LovoTxt ipTxt = new LovoTxt("ip��ַ",320,200,this);
	/**��������ı���*/
	private LovoTxt netFluxTxt = new LovoTxt("�������",50,250,this);
	
	/**������*/
	private LovoComboBox deptTxt;
	/**������ְλ*/
	private LovoComboBox employeeTxt = new LovoComboBox("������",new ArrayList(),50,350,this);;
	
	/**˵���ı���*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("˵��",320,250,200,120,this);

	public SchoolAddDialog(JFrame jf,SchoolPanel schoolPanel){
		super(jf,true);
		this.schoolPanel = schoolPanel;

		this.setLayout(null);
		this.setTitle("¼����ѧУ");
		
		this.init();
		
		this.setBounds(300, 150, 650, 500);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		this.initComboBox();
		
		LovoButton lbadd = new LovoButton("���",200,400,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addSchool();
				if(isOk){
					SchoolAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",400,400,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolAddDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * ��ʼ�����С������ź͸�����������
	 *
	 */
	private void initComboBox(){
//		��ӳ��м���
		this.cityTxt = new LovoComboBox("��������",new ArrayList(),320,50,this);
		
		//��Ӳ���List����
		this.deptTxt = new LovoComboBox("������",new ArrayList(),50,300,this){
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
	 * ���ѧУ
	 */
	private boolean addSchool(){
		//��֤���ݣ���֤ʧ�ܷ���false
		
		//��װʵ��
		
		//���±����ʾ��ӽ��
		this.schoolPanel.initData();
		
		return true;
	}
}
