package com.lovo.netCRM.ui.classManager.frame;

import com.lovo.netCRM.bean.AreaBean;
import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.component.LovoAccordion;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
import com.lovo.netCRM.dao.imp.ClassesDaoImp;
import com.lovo.netCRM.service.imp.AreaServiceImp;

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
 * @description �༶�������
 * ��������:2012-10-14
 */
public class ClassManagerPanel extends JPanel{
	/**�༶������*/
	private LovoTable classTable;
	/**�������*/
	private JFrame jf;
	/**�����ַ������*/
	private LovoAccordion cityAccordion;
	/**ѧУid*/
	private int schoolId;
	
	
	public ClassManagerPanel(JFrame jf){
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		new LovoTitleLabel("�� �� �� ��",this);
		this.initTable();
		this.initButton();
		this.initData();
		cityAccordion.setBounds(20, 90, 150, 300);
	}
	
	/**
	 * ��ʼ������
	 */
	public void initData(){
		if(schoolId==0){
			if(cityAccordion == null){
				this.initAccordion();
			}
			this.updateAccordion();
		}
		else{
			this.updateClassTable(schoolId);
		}
		
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	/**
	 * ��ʼ����ť
	 *
	 */
	private void initButton() {
		LovoButton lbadd = new LovoButton("��Ӱ༶",200,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(schoolId == 0){
					JOptionPane.showMessageDialog(null,"��ѡ��ѧУ");
					return;
				}
				new ClassAddDialog(jf,schoolId,ClassManagerPanel.this);
			}});
		
		
		LovoButton lbupdate = new LovoButton("�޸İ༶",400,500,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = classTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"��ѡ����");
					return;
				}
				new ClassUpdateDialog(jf,key,ClassManagerPanel.this);
			}});
	}
	
	//--------------------------
	/**
	 * ����ѧУ����õ�ѧУID
	 * @param schoolObj
	 * @return
	 */
	private int getSchoolId(Object schoolObj){
        if(schoolObj instanceof SchoolBean){
            SchoolBean schoolBean = (SchoolBean)schoolObj;
            return schoolBean.getId();
        }
		return 0;
	}
	/**
	 * ��ʼ�����
	 */
	private void initTable() {
		classTable = new LovoTable(this,
				new String[]{"�༶����","����ʱ��","�༶����","������ʦ"},
				new String[]{"name","buildTime","stuNum","teaName"},//�༶ʵ������������ new String[]{"className","time"}
				"id");//���������� classId
		classTable.setSizeAndLocation(180, 90, 550, 300);
	}
	/**
	 * ��ʼ���ַ������
	 *
	 */
	private void initAccordion() {
	//�ڶ�������Ϊ���м���cityList������������Ϊ��������ѧУ���ϵ�������schoolList
        ArrayList<Object> allAreas = new AreaServiceImp().getAllAreas();
        ArrayList<AreaBean> areas = new ArrayList<AreaBean>();
        for(Object obj : allAreas){
            AreaBean area = (AreaBean)obj;
            areas.add(area);
        }
        cityAccordion = new LovoAccordion(this,areas,"school"){
				
				/**
				 * ѧУ�б�����¼�
				 * @param schoolObj ѧУ����
				 */
			 @Override
				public void clickEvent(Object schoolObj) {
				 	schoolId = getSchoolId(schoolObj);
                    //��ʾ����ѧУ�༶
				 	updateClassTable(schoolId);
				}
			};
        cityAccordion.updateAccordion(areas);
	}
	
	/**
	 * �����ַ���
	 */
	private void updateAccordion(){
		//this.cityAccordion.updateAccordion(new ArrayList());
	}
	/**
	 * ���±��
	 * @param schoolId
	 */
	private void updateClassTable(int schoolId){
		//���±��,����List����
        //����ѧУID���Ұ༶,һ��ѧУ�ж���༶
        ArrayList<Object> allClasses = new ClassesDaoImp().getObjectByschID(schoolId);
		classTable.updateLovoTable(allClasses);
	}

}
