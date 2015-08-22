package com.lovo.netCRM.ui.employee.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoImageLabel;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTable;

/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description Ա����Ϣ��ʾ�Ի���
 * ��������:2012-10-15
 */
public class EmployeeShowDialog extends JDialog{

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
	/**��ϵ��ʽ��ǩ*/
	private LovoLabel phoneLabel = new LovoLabel("��ϵ��ʽ",320,150,this);
	/**��ͥסַ��ǩ*/
	private LovoLabel adressLabel = new LovoLabel("��ͥסַ",40,200,this);
	/**������ò*/
	private LovoLabel polityFaceLabel = new LovoLabel("������ò",320,200,this);
	/**���ڲ���*/
	private LovoLabel deptLabel = new LovoLabel("���ڲ���",40,250,this);
	/**����ְλ*/
	private LovoLabel workLabel = new LovoLabel("����ְλ",320,250,this);
	/**��ְ���ڱ�ǩ*/
	private LovoLabel enterJobLabel = new LovoLabel("��ְ����",40,300,this);
	/**ͷ���ǩ*/
	private LovoImageLabel faceLabel = new LovoImageLabel(580, 70, 100, 130,this);
	/**Ա��Id*/
	private int employeeId;
	
	public EmployeeShowDialog(JFrame jf,int employeeId){
		super(jf,true);
		this.employeeId = employeeId;
	
		this.setLayout(null);
		this.setTitle("Ա����Ϣһ��");
		
		this.init();
		
		this.setBounds(300, 100, 700, 450);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		this.initEmployeeData(employeeId);
		LovoButton lbcancel = new LovoButton("ȷ��",300,350,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				EmployeeShowDialog.this.dispose();
			}});
	}
	
	//-------------------------
	/**
	 * ��ʼ������
	 * @param employeeId Ա��ID
	 */
	private void initEmployeeData(int employeeId){
		
	}
}
