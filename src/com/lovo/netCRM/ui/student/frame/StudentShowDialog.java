package com.lovo.netCRM.ui.student.frame;

import com.lovo.netCRM.bean.StudentBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.dao.imp.RecallRecordDaoImp;
import com.lovo.netCRM.dao.imp.StudentDaoImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * �Ĵ�����CRMϵͳ
 * @author �ųɷ�
 * @version 1.0
 * @see  
 * @description ��ʾѧ����Ϣ�Ի���
 * ��������:2012-10-14
 */
public class StudentShowDialog extends JDialog{
	/**ѧ��id*/
	private int studentId;
	/**�طü�¼���*/
	private LovoTable backTable;
	/**�����ı���*/
	private LovoLabel nameLabel = new LovoLabel("��    ��",50,50,this);
	/**�Ա�ѡť*/
	private LovoLabel sexLabel = new LovoLabel("��    ��",320,50,this);
	/**���������ı���*/
	private LovoLabel birthdayLabel = new LovoLabel("��������",50,100,this);
	/**�����༶*/
	private LovoLabel classLabel = new LovoLabel("�����༶",320,100,this);

	/**��ͥ��ַ�ı���*/
	private LovoLabel addressLabel = new LovoLabel("��ͥ��ַ",50,150,this);
	/**��ϵ�绰�ı���*/
	private LovoLabel phoneLabel = new LovoLabel("��ϵ�绰",320,150,this);
	/**�����ı���*/
	private LovoLabel fatherLabel = new LovoLabel("��    ��",50,200,this);
	/**���׵绰�ı���*/
	private LovoLabel fatherPhoneLabel = new LovoLabel("���׵绰",320,200,this);
	/**ĸ���ı���*/
	private LovoLabel mumLabel = new LovoLabel("ĸ    ��",50,250,this);
	/**ĸ�׵绰�ı���*/
	private LovoLabel mumPhoneLabel = new LovoLabel("ĸ�׵绰",320,250,this);
	
	/**ѧ��״̬��ǩ*/
	private LovoLabel stateLabel = new LovoLabel("ѧ��״̬",50,300,this);
	/**��ע�ı���*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("��    ע",320,300,200,100,this);
	
	
	public StudentShowDialog(JFrame jf,int studentId){
		super(jf,true);
		this.studentId = studentId;
		this.setLayout(null);
		this.setTitle("�鿴ѧ����Ϣ");
		
		this.init();
		
		this.setBounds(300, 10, 650, 700);
		this.setVisible(true);
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		this.descriptionTxt.setEditable(false);
		this.initData(studentId);
		/*�طü�¼����*/
		LovoTitlePanel jp = new LovoTitlePanel("�طü�¼",80, 420, 500, 200,this);
		
		this.initTable(studentId,jp);
		
		LovoButton lbadd = new LovoButton("ȷ��",280,630,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				StudentShowDialog.this.dispose();
			}});
		
	
	}
	
	//----------------------
	/**
	 * ��ʼ����Ϣ
	 * @param studentId ѧ��ID
	 */
	private void initData(int studentId) {
        StudentBean stu = (StudentBean) new StudentDaoImp().getObjectByID(studentId);
        nameLabel.setText(stu.getName());
        sexLabel.setText(stu.getSex());
        birthdayLabel.setText(stu.getBirthday().toString());
        classLabel.setText(stu.getClasses().getName());
        addressLabel.setText(stu.getAddress());
        phoneLabel.setText(stu.getPhone());
        fatherLabel.setText(stu.getFather());
        fatherPhoneLabel.setText(stu.getFatherPhone());
        mumLabel.setText(stu.getMother());
        mumPhoneLabel.setText(stu.getMotherPhone());
        descriptionTxt.setText(stu.getDescribe());
        if(stu.isVip()){
            stateLabel.setText("��Ա");
        }else
            stateLabel.setText("�ǻ�Ա");
    }

    /**
	 * ��ʼ�����
	 * @param studentId ѧ��id
	 */
	private void initTable(int studentId,JPanel jp){
		backTable = new LovoTable(jp,
				new String[]{"�ط�ʱ��","������","�ط���","�ط�����","�ط�����"},
				new String[]{"time","emp.name","recallMan","title","describe"},//ѧУʵ������������ new String[]{"time","man"}
				"");//���������� backId
		backTable.setSizeAndLocation(10, 20, 480, 170);
		
		//���±��,����List����
		backTable.updateLovoTable(new RecallRecordDaoImp().getAllReacllsByStuID(studentId));
	}
	
}
