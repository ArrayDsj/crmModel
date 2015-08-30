package com.lovo.netCRM.ui.student.frame;

import com.lovo.netCRM.bean.AreaBean;
import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.bean.StudentBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.dao.imp.ClassesDaoImp;
import com.lovo.netCRM.dao.imp.StudentDaoImp;
import com.lovo.netCRM.service.imp.AreaServiceImp;
import com.lovo.netCRM.service.imp.StudentServiceImp;
import com.lovo.netCRM.util.Switch;

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
 * @description ѧ�����
 * ��������:2012-10-14
 */
public class StudentPanel extends JPanel{
	/**ѧ��������*/
	private LovoTable studentTable;
	/**�������*/
	private JFrame jf;
	/**�����ַ������*/
	private LovoAccordion cityAccordion;
	/**���е�ѧУid*/
	private int schoolId;
	/**ҳ���ı���*/
	private JTextField pageNOTxt = new JTextField("1");
	/**��ҳ����ǩ*/
	private JLabel jltTotalPage = new JLabel("/   ҳ");
	/**ѡ��������*/
	private  LovoComboBox<String> itemCombox;
	/**ֵ�ı���*/
	private  JTextField valueTxt = new JTextField();

	/**
	 *
	 * ��ҳ����
	 */
	//�ܼ�¼��
	private static int counts ;
	private static int pageNum;
	private final static int pageSize = 3;
	private static int pageNow = 1;

	private boolean onceFind = false;

	//�õ�ѡ��
	String item = "����";
	//�õ�ѡ��ֵ
	String value = "";



	public StudentPanel(JFrame jf){
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * ��ʼ��
	 *
	 */
	private void init() {
		new LovoTitleLabel("ѧ �� �� ��",this);

		this.initTable();
		this.initButton();
		this.initData();
		cityAccordion.setBounds(20,90,150,300);
		this.initFindPanel();
	}
	
	/**
	 * ��ʼ������
	 */
	public void initData(){
		if(schoolId==0){
			if(cityAccordion == null){
				this.initAccordion();//��ʼ���ַ������
			}
			this.updateAccordion();//�����ַ������

		}
		else{
			onceFind = false;
			pageNow = 1;
			this.updateStudentTable(schoolId,1);
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
		LovoButton prevButton = new LovoButton("��һҳ",0,0,this);
		prevButton.setBounds(200, 400, 50, 20);
		
		prevButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(pageNow > 1){
					pageNOTxt.setText(--pageNow + "");
					prevClick();
				}
			}});
		
		LovoButton nextButton = new LovoButton("��һҳ",0,0,this);
		nextButton.setBounds(270, 400, 50, 20);
		
		nextButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(pageNow < pageNum){
					pageNOTxt.setText(++pageNow + "");
					nextClick();
				}
			}});
		
		JLabel jld = new JLabel("��");
		jld.setBounds(350, 400, 20, 20);
		this.add(jld);
		
		pageNOTxt.setBounds(365, 400, 20, 20);
		this.add(pageNOTxt);
		
		
		jltTotalPage.setBounds(390, 400, 50, 20);
		this.add(jltTotalPage);
		
		LovoButton goButton = new LovoButton("go",0,0,this);
		goButton.setBounds(445, 400, 25, 20);
		goButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				goClick(pageNOTxt.getText());
			}});
		
		
		LovoButton lbadd = new LovoButton("���ѧ��",50,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(schoolId == 0){
					JOptionPane.showMessageDialog(null, "��ѡ��ѧУ");
					return;

				}else{
					//ͳ�ƴ�ѧУ�İ༶����,�������0 ,����ʾ�½��༶
					ArrayList<Object> classes = new ClassesDaoImp().getObjectByschID(schoolId);
					//int counts = classes.size();
					if(classes == null){
						JOptionPane.showMessageDialog(null,"��ѧУ��û�а༶,���½��༶");
						return;
					}
				}
				new StudentAddDialog(jf,schoolId,StudentPanel.this);
				/**
				 * �ж��Ƿ�ҳ
				 */
				//��ȡ����
				ArrayList<Object> objs = new StudentDaoImp().getObjectByCon(schoolId, 1, "����", "");
				ArrayList<StudentBean> students = new ArrayList<StudentBean>();
				for(Object stu : objs){
					StudentBean obj2Stu = (StudentBean)stu;
					students.add(obj2Stu);
				}
				//�ܵļ�¼����
				counts = Integer.parseInt(students.get(0).getDescribe());
				//�õ�δ���ѧ����ѧ����ҳ��
				int oldPageNum = pageNum;
				int newPageNum = (int) Math.ceil(counts / (pageSize * 1.0));

				if(oldPageNum < newPageNum){//��������ҳ��
					pageNum = newPageNum;
				}
				//������ҳ��
				ArrayList<Object> limitPage = new StudentDaoImp().getObjectByCon(schoolId, pageNum, "����", "");
				//������ҳ��
				setTotalPage(pageNum);
				//���õ�ǰҳΪ���һҳ
				pageNow = pageNum;
				pageNOTxt.setText(pageNow+"");
				studentTable.updateLovoTable(update(limitPage));
			}});
		
		LovoButton lbshow = new LovoButton("�鿴ѧ����Ϣ",170,500,this);
		lbshow.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"��ѡ����");
					return;
				}
				
				new StudentShowDialog(jf,key);
			}});
		
		
		LovoButton lbdel = new LovoButton("ɾ��ѧ��",290,500,this);
		lbdel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"��ѡ����");
					return;
				}
				delEmployee(key);
			}});
		
		
		LovoButton lbupdate = new LovoButton("�޸�ѧ����Ϣ",50,570,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"��ѡ����");
					return;
				}
				
				new StudentUpdateDialog(jf,schoolId,key,StudentPanel.this);
			}});
		
		LovoButton lbAddBack = new LovoButton("��ӻط���¼",170,570,this);
		lbAddBack.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"��ѡ����");
					return;
				}
				
				new StudentVisitAddialog(jf,key,schoolId);
			}});
	}
	/**
	 * ��ʼ����ѯ��ť
	 *
	 */
	private void initFindPanel(){
		LovoTitlePanel jp = new LovoTitlePanel("��ѯѧ��",400, 480, 320, 150,this);
	
		this.itemCombox = new LovoComboBox<String>(
				new String[]{"ѧ������","�༶","��Ա",
						"�ǻ�Ա"},30,50,jp);
		
		this.valueTxt.setBounds(160, 50, 120, 20);
		jp.add(this.valueTxt);
		
		LovoButton lb = new LovoButton("����",180,100,jp);
		lb.setSize(60, 20);
		
		lb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (schoolId == 0) {
					JOptionPane.showMessageDialog(null, "��ѡ��ѧУ");
					return;
				}
				if (itemCombox.getItem().equals("��Ա") || itemCombox.getItem().equals("�ǻ�Ա")) {
					valueTxt.setText("");
				}
				findStudent(schoolId, 1);
			}
		});
		
	}
	/**
	 * ������ҳ��
	 * @param countPage ��ҳ��
	 */
	private void setTotalPage(int countPage) {
		this.jltTotalPage.setText("/"+countPage+"  ҳ");
	}
	
	//--------------------------
	/**
	 * ����ѧУ����õ�ѧУID
	 * @param schoolObj ѧУ����
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
	 * �����ַ���
	 */
	private void updateAccordion(){
		ArrayList<Object> allAreas = new AreaServiceImp().getAllAreas();
		this.cityAccordion.updateAccordion(allAreas);
	}

	/**
	 * ��ʼ�����
	 */
	private void initTable() {
		studentTable = new LovoTable(this,
				new String[]{"ѧ������","�Ա�","�༶","״̬","��ϵ�绰"},
				new String[]{"stuName","sex","classes.name","vip","phone"},//ѧ��ʵ������������ new String[]{"studentName","sex"}
				"stuId");//���������� studentId
		studentTable.setSizeAndLocation(180, 90, 550, 300);
	}

	/**
	 * ��ʼ�����±��
	 * @param schoolId ѧУID
	 * @param pageNOW ҳ��
	 * ����ַ��ٵ�ʱ�򱻵���,����ѧУid
	 */
	private void updateStudentTable(int schoolId,int pageNOW){
		//��ѧ������,����ѧУID����ѧ��
		//���±��,����List����
		ArrayList<Object> objs = new StudentDaoImp().getObjectByCon(schoolId, pageNOW, "����", "");
		if(objs != null){
			studentTable.updateLovoTable(this.update(objs));//�ѽ��������ܼ�¼��
			//������ҳ��
			pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
			this.setTotalPage(pageNum);
			pageNOTxt.setText(pageNow+"");
		}else{
			//������ҳ��
			this.setTotalPage(1);
			pageNOTxt.setText("1");
			studentTable.updateLovoTable(new ArrayList());
		}
	}

	/**
	 * ��һҳ����¼�
	 */
	private void prevClick(){
		if(onceFind){
			ArrayList<Object> checkSuts = new StudentDaoImp().getObjectByCon(schoolId, pageNow, item, value);
			if(checkSuts != null){
				//���±��,��ʾ��ѯ���
				studentTable.updateLovoTable(this.update(checkSuts));
				//������ҳ��
				pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
				this.setTotalPage(pageNum);
			}else {
				//���û�н���򲻸��±��
				JOptionPane.showMessageDialog(null,"�ѽ��ǵ�һҳ��");
			}
		}else {
			ArrayList<Object> objs = new StudentDaoImp().getObjectByCon(schoolId, pageNow, "����", "");
			if(objs != null){
				studentTable.updateLovoTable(this.update(objs));//�ѽ��������ܼ�¼��
				//������ҳ��
				pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
				this.setTotalPage(pageNum);
				pageNOTxt.setText(pageNow+"");
			}else{
				//������ҳ��
				this.setTotalPage(1);
				studentTable.updateLovoTable(new ArrayList());
			}
		}

	}
	/**
	 * ��һҳ����¼�
	 */
	private void nextClick(){
		if(onceFind){
			ArrayList<Object> checkSuts = new StudentDaoImp().getObjectByCon(schoolId, pageNow, item, value);
			if(checkSuts != null){
				//���±��,��ʾ��ѯ���
				studentTable.updateLovoTable(this.update(checkSuts));
				//������ҳ��
				pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
				this.setTotalPage(pageNum);
			}else {
				//���û�н���򲻸��±��
				JOptionPane.showMessageDialog(null,"�ѽ������һҳ��");
			}
		}
		else {
			ArrayList<Object> objs = new StudentDaoImp().getObjectByCon(schoolId, pageNow, "����", "");
			if(objs != null){
				studentTable.updateLovoTable(this.update(objs));//�ѽ��������ܼ�¼��
				//������ҳ��
				pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
				this.setTotalPage(pageNum);
				pageNOTxt.setText(pageNow+"");
			}else{
				//������ҳ��
				this.setTotalPage(1);
				studentTable.updateLovoTable(new ArrayList());
			}
		}
	}
	/**
	 * ת��ָ��ҳ��
	 * @param pageNO ҳ��
	 */
	private void goClick(String pageNO){
		int page = -1;
		try{
			page = Integer.parseInt(pageNO);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"�������");
		}
		if(page <= pageNum) {//��������ҳ��С�ڵ�����ҳ��
			pageNow = Integer.parseInt(pageNO);//��ת���û������ҳ��
			//�õ�ѡ��
			item = itemCombox.getItem();
			//�õ�ѡ��ֵ
			value = valueTxt.getText();
			ArrayList<Object> checkSuts = new StudentDaoImp().getObjectByCon(schoolId, pageNow, item, value);
			studentTable.updateLovoTable(this.update(checkSuts));
		}
	}
	
	/**
	 * ��ʼ���ַ������
	 *
	 */
	public void initAccordion() {
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
				 //��¼����ѧУid
				 schoolId = getSchoolId(schoolObj);
					//��ʾ����ѧУѧ��
				 updateStudentTable(schoolId,1);
				}
			};
        cityAccordion.updateAccordion(areas);
	}
	
	/**
	 * ɾ��ѧ��
	 * @param studentId ѧ��ID
	 */
	private void delEmployee(int studentId){
        //��ѧ����status����Ϊfalse �༶������һ
        if((JOptionPane.showConfirmDialog(null,"�Ƿ�ɾ��ѡ��ѧ����Ϣ","ɾ��",JOptionPane.YES_NO_OPTION)) == 0){
			if(new StudentServiceImp().deletStudent(studentId)){
				if(!onceFind){//��������ѯ
					ArrayList<Object> objs = new StudentDaoImp().getObjectByCon(schoolId, 1, "����", "");

					ArrayList<StudentBean> students = new ArrayList<StudentBean>();
					for(Object stu : objs){
						StudentBean obj2Stu = (StudentBean)stu;
						students.add(obj2Stu);
					}
					//�ܵļ�¼����
					counts = Integer.parseInt(students.get(0).getDescribe());
					pageNum = (int) Math.ceil(counts / (pageSize * 1.0));

					if(pageNow > pageNum){
					//������һҳ��Ϣ
						ArrayList<Object> limitPage = new StudentDaoImp().getObjectByCon(schoolId, pageNum, "����", "");
						pageNow = pageNum;
						this.setTotalPage(pageNum);
						pageNOTxt.setText(pageNow+"");
						studentTable.updateLovoTable(this.update(limitPage));
					}
					else{
						ArrayList<Object> limitPage = new StudentDaoImp().getObjectByCon(schoolId, pageNow, "����", "");
						this.setTotalPage(pageNum);
						studentTable.updateLovoTable(this.update(limitPage));
					}
				}else{
					ArrayList<Object> objs = new StudentDaoImp().getObjectByCon(schoolId, 1, item,value);

					ArrayList<StudentBean> students = new ArrayList<StudentBean>();
					for(Object stu : objs){
						StudentBean obj2Stu = (StudentBean)stu;
						students.add(obj2Stu);
					}
					//�ܵļ�¼����
					counts = Integer.parseInt(students.get(0).getDescribe());
					pageNum = (int) Math.ceil(counts / (pageSize * 1.0));

					if(pageNow > pageNum){
						//������һҳ��Ϣ
						ArrayList<Object> limitPage = new StudentDaoImp().getObjectByCon(schoolId, pageNum, item,value);
						pageNow = pageNum;
						this.setTotalPage(pageNum);
						pageNOTxt.setText(pageNow+"");
						studentTable.updateLovoTable(this.update(limitPage));
					}
					else{
						ArrayList<Object> limitPage = new StudentDaoImp().getObjectByCon(schoolId, pageNow, item,value);
						this.setTotalPage(pageNum);
						studentTable.updateLovoTable(this.update(limitPage));
					}
				}
			}
        }

	}
	/**
	 * ����ѧ��
	 * @param schoolId ѧУid
	 * @param pageNOW ҳ��
	 */
	private void findStudent(int schoolId,int pageNOW){
		onceFind = true;
		//�õ�ѡ��
		item = itemCombox.getItem();
		//�õ�ѡ��ֵ
		value = valueTxt.getText();
        ArrayList<Object> checkSuts = new StudentDaoImp().getObjectByCon(schoolId, pageNOW, item, value);
		if(checkSuts != null){
			//���±��,��ʾ��ѯ���
			studentTable.updateLovoTable(this.update(checkSuts));
			//������ҳ��
			pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
			pageNow = 1;
			pageNOTxt.setText(pageNow+"");
			this.setTotalPage(pageNum);

		}else {
			//���û�н���򲻸��±��
			JOptionPane.showMessageDialog(null,"�޲�ѯ���");
		}
	}

	private ArrayList<Switch> update (ArrayList<Object> objects) {
		ArrayList<StudentBean> students = new ArrayList<StudentBean>();
		for(Object stu : objects){
			StudentBean obj2Stu = (StudentBean)stu;
			students.add(obj2Stu);
		}
		//�ܵļ�¼����
		counts = Integer.parseInt(students.get(0).getDescribe());
		Switch change = null;
		ArrayList<Switch> changes = new ArrayList<Switch>();
		for(StudentBean stu : students){
			change = new Switch();
			change.setStuId(stu.getId());
			change.setPhone(stu.getPhone());
			change.setClasses(stu.getClasses());
			change.setSex(stu.getSex());
			change.setStuName(stu.getName());
			if(stu.isVip()){
				change.setVip("��Ա");
			}else {
				change.setVip("�ǻ�Ա");
			}
			changes.add(change);
		}
		return changes;
	}

}
