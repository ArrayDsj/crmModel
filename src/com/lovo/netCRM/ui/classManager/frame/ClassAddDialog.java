package com.lovo.netCRM.ui.classManager.frame;

import com.lovo.netCRM.bean.ClassesBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.dao.imp.ClassesDaoImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ��Ӱ༶�Ի���
 * ��������:2012-10-14
 */
public class ClassAddDialog extends JDialog{
	/**�༶���*/
	private LovoTable classTable;
	/**�༶�����ı���*/
	private LovoTxt nameTxt = new LovoTxt("�༶����",50,60,this);
	/**������ʦ�ı���*/
	private LovoTxt teacherTxt = new LovoTxt("������ʦ",50,140,this);
	/**ѧУid*/
	private int schoolId;
	/**�༶���*/
	private ClassManagerPanel classManagerPanel;
	
	public ClassAddDialog(JFrame jf,int schoolId,ClassManagerPanel classManagerPanel){
		super(jf,true);
		this.schoolId = schoolId;
		this.classManagerPanel = classManagerPanel;
		this.setLayout(null);
		this.setTitle("��Ӱ༶");
		this.init();
		this.setBounds(400, 200, 350, 300);
		this.setVisible(true);
	}
	
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		LovoButton lbadd = new LovoButton("���",60,220,this);
		lbadd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				boolean isOk = addClass(schoolId);
				if(isOk){
				    ClassAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",200,220,this);
		lbcancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ClassAddDialog.this.dispose();
			}});
	}
	
	//---------------------
	/**
	 * ��Ӱ༶
	 * @param schoolId ѧУid
	 */
	private boolean addClass(int  schoolId){
		//��֤���ݣ���֤ʧ�ܣ�����false
        ClassesBean newClasses = new ClassesBean();
        String error = "";
        if(nameTxt.getText() == null || nameTxt.getText().trim().equals("")){
           error += "���Ʋ���Ϊ��";
        }
        if(teacherTxt.getText() == null || nameTxt.getText().trim().equals("")){
            error += "��ʦ���ֲ���Ϊ��";
        }
        if(error.length() != 0) {
            JOptionPane.showMessageDialog(null, error);
            return false;
        }
        newClasses.setName(nameTxt.getText());
        newClasses.setTeaName(teacherTxt.getText());
        newClasses.setBuildTime(new Date());
        if(new ClassesDaoImp().addObject(schoolId,newClasses)){
            //���±����ʾ��ӽ��
            this.classManagerPanel.initData();
            return true;
        }
        return false;
	}
}
