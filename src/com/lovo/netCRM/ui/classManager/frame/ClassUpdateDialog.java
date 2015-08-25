package com.lovo.netCRM.ui.classManager.frame;

import com.lovo.netCRM.bean.ClassesBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.dao.imp.ClassesDaoImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description �޸İ༶�Ի���
 * ��������:2012-10-14
 */
public class ClassUpdateDialog extends JDialog{

	/**�༶�����ı���*/
	private LovoLabel nameLabel = new LovoLabel("�༶����",50,60,this);
	/**������ʦ�ı���*/
	private LovoTxt teacherTxt = new LovoTxt("������ʦ",50,140,this);
	/**�༶id*/
	private int classId;
	/**�༶���*/
	private ClassManagerPanel classManagerPanel;

	public ClassUpdateDialog(JFrame jf,int classId,ClassManagerPanel classManagerPanel){
		super(jf,true);
		this.classId = classId;
		this.classManagerPanel = classManagerPanel;
		
		this.setLayout(null);
		this.setTitle("�޸İ༶");
		
		this.init();
		
		this.setBounds(400, 200, 350, 300);
		this.setVisible(true);
	}
	
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		this.initClassData(classId);
		
		LovoButton lbupdate = new LovoButton("�޸�",60,220,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = updateClass(classId);
				if(isOk){
				ClassUpdateDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",200,220,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				ClassUpdateDialog.this.dispose();
			}});
	}
	
	//---------------------
	/**
	 * ��ʼ������
	 * @param classId �༶ID
	 */
	private void initClassData(int classId){
        ClassesBean classes = (ClassesBean)new ClassesDaoImp().getObjectByID(classId);
        nameLabel.setText(classes.getName());
        teacherTxt.setText(classes.getTeaName());
	}
	/**
	 * �޸İ༶
	 * @param classId �༶ID
	 */
	private boolean updateClass(int classId){
        ClassesBean cla = new ClassesBean();
        //��֤����,��֤ʧ�ܷ���false
        String error = "";
        if(teacherTxt.getText() == null || teacherTxt.getText().equals("")){
            error += "û���֣�������\n";
        }
        if(error.length() != 0) {
            JOptionPane.showMessageDialog(null, error);
            return false;
        }
        //��װʵ��
        cla.setTeaName(teacherTxt.getText());
        //���±����ʾ�޸Ľ��
        new ClassesDaoImp().alterObject(classId,cla);
		this.classManagerPanel.initData();
		return true;
	}
}
