package com.lovo.netCRM.ui.school.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.component.LovoTxtArea;
/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ѧУ��Ϣ�޸ĶԻ���
 * ��������:2012-10-15
 */
public class SchoolUpdateDialog extends JDialog{
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
	/**У���ı���*/
	private LovoTxt masterTxt = new LovoTxt("У    ��",320,80,this);
	/**��ϵ�绰��ǩ*/
	private LovoLabel titleLabel = new LovoLabel("��ϵ�绰",50,120,this);
	/**ѧ�������ı���*/
	private LovoTxt studentNumberTxt = new LovoTxt("ѧ������",320,120,this);
	/**��ʦ�����ı���*/
	private LovoTxt teacherNumberTxt = new LovoTxt("��ʦ����",50,160,this);
	/**ip��ַ�ı���*/
	private LovoTxt ipTxt = new LovoTxt("ip��ַ",320,160,this);
	/**���������ı���*/
	private LovoTxt netFluxTxt = new LovoTxt("��������",50,200,this);
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
	/**������׼ʱ��*/
	private LovoLabel passTimeLabel = new LovoLabel("������׼ʱ��",50,320,this);
	/**˵���ı���*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("˵��",50,360,150,60,this);
	/**��������ı���*/
	private LovoTxtArea mindTxt = new LovoTxtArea("�������",320,360,150,60,this);
	
	
	public SchoolUpdateDialog(JFrame jf,int schoolId,SchoolPanel schoolPanel){
		super(jf,true);
		this.schoolId = schoolId;
		this.schoolPanel = schoolPanel;
		this.setLayout(null);
		this.setTitle("�޸�ѧУ��Ϣ");
		
		this.init();
		
		this.setBounds(300, 100, 650, 550);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		this.initData(this.schoolId);
		this.descriptionTxt.setEditable(false);
		this.mindTxt.setEditable(false);
		
		LovoButton lbupdate = new LovoButton("�޸�",200,450,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = updateSchool(schoolId);
				if(isOk){
				SchoolUpdateDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("ȡ��",400,450,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolUpdateDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * ��ʼ����ǩ����
	 * @param schoolId ѧУID
	 */
	private void initData(int schoolId){
		
	}
	/**
	 * �޸�ѧУ
	 * @param schoolId ѧУID
	 * @param cityObj ���ж���
	 */
	private boolean updateSchool(int schoolId){
		//��֤���ݣ���֤ʧ�ܷ���false
		
		//��װʵ��
		
		//���±�����ʾ�޸Ľ��
		this.schoolPanel.initData();
		return true;
	}
	

}