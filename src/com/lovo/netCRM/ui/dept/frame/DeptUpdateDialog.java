package com.lovo.netCRM.ui.dept.frame;

import com.lovo.netCRM.bean.DepartBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTxtArea;
import com.lovo.netCRM.service.imp.DepartServiceImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description �����޸ĶԻ���
 * ��������:2012-10-15
 */
public class DeptUpdateDialog extends JDialog{
	/**���ű��*/
	private DeptPanel deptPanel;
	/**����id*/
	private int deptId;
	/**�������Ʊ�ǩ*/
	private LovoLabel nameLabel = new LovoLabel("��������",50,50,this);
	/**����ʱ���ǩ*/
	private LovoLabel timeLabel = new LovoLabel("����ʱ��",50,100,this);
	/**���������ı���*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("��������",50,150,120,60,this);
	public DeptUpdateDialog(JFrame jf,int deptId,DeptPanel deptPanel){
		super(jf,true);
		this.deptPanel = deptPanel;
		this.deptId = deptId;
		this.setLayout(null);
		this.setTitle("�޸Ĳ�����Ϣ");
		
		this.init();
		
		this.setBounds(400, 150, 350, 350);
		this.setVisible(true);
	}
	/**
	 * ��ʼ������
	 *
	 */
	private void init() {
		this.initData(this.deptId);
		
		LovoButton lbupdate = new LovoButton("�޸�",50,250,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = updateDept(deptId);
				if(isOk){
				DeptUpdateDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",180,250,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				DeptUpdateDialog.this.dispose();
			}});
	}
	//---------------------------------------
	/**
	 * ��ʼ����Ϣ
	 */
	private void initData(int deptId) {
		DepartBean theDeapt = new DepartServiceImp().getDeptByID(deptId);
		nameLabel.setText(theDeapt.getDepartName());
		timeLabel.setText(theDeapt.getBuildTime().toString());
		descriptionTxt.setText(theDeapt.getDescribe());
	}
	/**
	 * �޸Ĳ�����Ϣ
	 * @param deptId
	 */
	private boolean updateDept(int deptId){
		String desc = descriptionTxt.getText();
		if(this.descriptionTxt.getText().matches("[\\u4e00-\\u9fa5]{2,10}")) {
			DepartBean willUpdateDept = new DepartServiceImp().getDeptByID(deptId);
			willUpdateDept.setDescribe(descriptionTxt.getText());
			new DepartServiceImp().alterDept(willUpdateDept);
			//���±����ʾ�޸Ľ��
			this.deptPanel.initData();
			return true;
		}else
			return false;
	}
}
