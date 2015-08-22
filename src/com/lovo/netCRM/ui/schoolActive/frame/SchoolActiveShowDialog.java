package com.lovo.netCRM.ui.schoolActive.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ѧУ���ʾ�Ի���
 * ��������:2012-10-11
 */
public class SchoolActiveShowDialog  extends JDialog {
	/**�����*/
	private LovoTable activeTable;
	/**ѧУ��*/
	private String schoolName;
	
	public SchoolActiveShowDialog(JFrame jf, String schoolName,int  schoolId){
		super(jf,true);
		this.setLayout(null);
		this.setTitle("ѧУ�һ��");
		this.schoolName = schoolName;
		
		this.init(schoolId);
		
		this.setBounds(350, 150, 650, 500);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 * @param schoolId
	 */
	private void init(int schoolId){
		this.initTable(schoolId);
		String title =  schoolName+"�һ��";
		LovoTitleLabel titleLabel = new LovoTitleLabel(title,this);
		titleLabel.setBounds(20, 40, title.length()*30, 30);
		
		LovoButton lbcancel = new LovoButton("ȷ��",280,420,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolActiveShowDialog.this.dispose();
			}});
		
	}
	//-------------------
	
	/**
	 * ��ʼ�����񷽷�
	 * @param schoolId ѧУID
	 */
	private void initTable(int schoolId) {
		activeTable = new LovoTable(this,
				new String[]{"�����","ʱ��","�ص�","����","������","������"},
				new String[]{},//�ʵ������������ new String[]{"activeName","time"}
				"");//���������� activeId
		activeTable.setSizeAndLocation(20, 90, 600, 300);
		
		//���±���,����ѧУ�List����
		activeTable.updateLovoTable(null);
	}
}