package com.lovo.netCRM.ui.classManager.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;

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
		
	}
	/**
	 * �޸İ༶
	 * @param classId �༶ID
	 */
	private boolean updateClass(int classId){
		//��֤ʧ�ܣ�����false
		
//		���±����ʾ�޸Ľ��
		this.classManagerPanel.initData();
		
		return true;
	}
}
