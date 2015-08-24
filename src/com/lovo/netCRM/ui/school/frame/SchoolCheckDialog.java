package com.lovo.netCRM.ui.school.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxtArea;
import com.lovo.netCRM.dao.imp.AreaDaoImp;
import com.lovo.netCRM.dao.imp.EmployeeDaoImp;
import com.lovo.netCRM.dao.imp.SchoolDaoImp;

/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ��˶Ի���
 * ��������:2012-10-12
 */
public class SchoolCheckDialog extends JDialog{
	/**ѧУ�����*/
	private SchoolPanel schoolPanel;
	/**ѧУid*/
	private int schoolId;
	/**ѧУ���Ʊ�ǩ*/
	private LovoLabel nameLabel = new LovoLabel("ѧУ����",50,40,this);
	/**��������*/
	private LovoLabel cityLabel = new LovoLabel("��������",320,40,this);;
	/**��ַ��ǩ*/
	private LovoLabel addressLabel = new LovoLabel("ѧУ��ַ",50,80,this);
	/**У����ǩ*/
	private LovoLabel masterLabel = new LovoLabel("У    ��",320,80,this);
	/**��ϵ�绰��ǩ*/
	private LovoLabel phoneLabel = new LovoLabel("��ϵ�绰",50,120,this);
	/**ѧ��������ǩ*/
	private LovoLabel studentNumberLabel = new LovoLabel("ѧ������",320,120,this);
	/**��ʦ������ǩ*/
	private LovoLabel teacherNumberLabel = new LovoLabel("��ʦ����",50,160,this);
	/**ip��ַ��ǩ*/
	private LovoLabel ipLabel = new LovoLabel("ip��ַ",320,160,this);
	/**���������ǩ*/
	private LovoLabel netFluxLabel = new LovoLabel("�������",50,200,this);
	/**״̬��ǩ*/
	private LovoLabel stateLabel = new LovoLabel("״    ̬",320,200,this);
	
	/**�����ű�ǩ*/
	private LovoLabel deptLabel = new LovoLabel("������",50,240,this);
	/**������*/
	private LovoLabel employeeLabel = new LovoLabel("������",320,240,this);
	/**¼��ʱ��*/
	private LovoLabel enterTimeLabel = new LovoLabel("¼��ʱ��",50,280,this);
	/**��������ʱ��*/
	private LovoLabel applyTimeLabel = new LovoLabel("��������ʱ��",320,280,this);
	/**˵���ı���*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("˵��",50,320,300,60,this);
	/**��������ı���*/
	private LovoTxtArea mindTxt = new LovoTxtArea("�������",50,400,300,100,this);
	
	public SchoolCheckDialog(JFrame jf, int schoolId,SchoolPanel schoolPanel){
		super(jf,true);
		this.schoolPanel = schoolPanel;
		this.schoolId = schoolId;
		this.setLayout(null);
		this.setTitle("���ѧУ");
		
		this.init();
		
		this.setBounds(300, 50, 650, 600);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		this.initData(this.schoolId);
		this.descriptionTxt.setEnabled(false);


		LovoButton lbpass = new LovoButton("��׼����",100,520,this);
		lbpass.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				passSchool(schoolId);
				SchoolCheckDialog.this.dispose();
			}});
		LovoButton lbback = new LovoButton("��������",250,520,this);
		lbback.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				backSchool(schoolId);
				SchoolCheckDialog.this.dispose();
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",400,520,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolCheckDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * ��ʼ����ǩ����
	 *	@param schoolId ѧУID
	 */
	private void initData(int schoolId){
		SchoolBean sch = (SchoolBean)new SchoolDaoImp().getObjectByID(schoolId);
		nameLabel.setText(sch.getName());
		cityLabel.setText(sch.getArea().getName());
		addressLabel.setText(sch.getAddress());
		masterLabel.setText(sch.getMaster());
		phoneLabel.setText(sch.getPhone());
		studentNumberLabel.setText(sch.getStuNum()+"");
		teacherNumberLabel.setText(sch.getTeaNum()+"");
		ipLabel.setText(sch.getIPAddress());
		netFluxLabel.setText(sch.getFlow());
		stateLabel.setText(sch.getStatus());
		deptLabel.setText(sch.getEmp().getDept());
		employeeLabel.setText(sch.getEmp().getName());
		enterTimeLabel.setText(sch.getFoundTime().toString());
		descriptionTxt.setText(sch.getDescribe());
		mindTxt.setText(sch.getCheckNotic());
		if(sch.getProposeTime() == null){
			applyTimeLabel.setText("");
		}else
			applyTimeLabel.setText(sch.getProposeTime().toString());

	}
	/**
	 * ��׼����
	 * @param schoolId ѧУID
	 * @param cityObj ���ж���
	 */
	private void passSchool(int schoolId){
		//��֤����,��֤ʧ�ܷ���false
		String error = "";
		if(descriptionTxt.getText() == null || descriptionTxt.getText().equals("")){
			error += "û˵��,������\n";
		}if(error.length() != 0) {
			JOptionPane.showMessageDialog(null, error);
			return ;
		} else{
			new SchoolDaoImp().alterSchoolByDescripOn(schoolId, descriptionTxt.getText());
		}
		//���±����ʾ�޸Ľ��
		this.schoolPanel.initData();
	}
	
	/**
	 * ��������
	 * @param schoolId ѧУID
	 * @param cityObj ���ж���
	 */
	private void backSchool(int schoolId){
		String error = "";
		if(descriptionTxt.getText() == null || descriptionTxt.getText().equals("")){
			error += "û˵��,������\n";
		}if(error.length() != 0) {
			JOptionPane.showMessageDialog(null, error);
			return ;
		} else{
			new SchoolDaoImp().alterSchoolByDescripOff(schoolId,descriptionTxt.getText());
		}
		
		//���±����ʾ�޸Ľ��
		this.schoolPanel.initData();
	}
	
}
