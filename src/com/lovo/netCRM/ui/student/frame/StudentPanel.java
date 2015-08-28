package com.lovo.netCRM.ui.student.frame;

import com.lovo.netCRM.bean.AreaBean;
import com.lovo.netCRM.bean.ClassesBean;
import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.bean.StudentBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.dao.imp.ClassesDaoImp;
import com.lovo.netCRM.dao.imp.StudentDaoImp;
import com.lovo.netCRM.service.imp.AreaServiceImp;
import com.lovo.netCRM.service.imp.EmployeeServiceImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 学生面板
 * 开发日期:2012-10-14
 */
public class StudentPanel extends JPanel{
	/**学生表格组件*/
	private LovoTable studentTable;
	/**窗体对象*/
	private JFrame jf;
	/**城市手风琴组件*/
	private LovoAccordion cityAccordion;
	/**点中的学校id*/
	private int schoolId;
	/**页码文本框*/
	private JTextField pageNOTxt = new JTextField("1");
	/**总页数标签*/
	private JLabel jltTotalPage = new JLabel("/   页");
	/**选项下拉框*/
	private  LovoComboBox<String> itemCombox;
	/**值文本框*/
	private  JTextField valueTxt = new JTextField();
	
	public StudentPanel(JFrame jf){
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		new LovoTitleLabel("学 生 管 理",this);
		this.initTable();
		this.initButton();
		this.initData();
		cityAccordion.setBounds(20,90,150,300);
		this.initFindPanel();
	}
	
	/**
	 * 初始化数据
	 */
	public void initData(){
		if(schoolId==0){
			if(cityAccordion == null){
				this.initAccordion();
			}
			this.updateAccordion();
		}
		else{
			this.updateStudentTable(schoolId,1);
		}
		
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	/**
	 * 初始化按钮
	 *
	 */
	private void initButton() {
		LovoButton prevButton = new LovoButton("上一页",0,0,this);
		prevButton.setBounds(200, 400, 50, 20);
		
		prevButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				prevClick();
			}});
		
		LovoButton nextButton = new LovoButton("下一页",0,0,this);
		nextButton.setBounds(270, 400, 50, 20);
		
		nextButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				nextClick();
			}});
		
		JLabel jld = new JLabel("第");
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
		
		
		LovoButton lbadd = new LovoButton("添加学生",50,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(schoolId == 0){
					JOptionPane.showMessageDialog(null, "请选择学校");
					return;

				}else{
					//统计此学校的班级总数,如果等于0 ,则提示新建班级
					ArrayList<Object> classes = new ClassesDaoImp().getObjectByschID(schoolId);
					//int counts = classes.size();
					if(classes == null){
						JOptionPane.showMessageDialog(null,"此学校还没有班级,请新建班级");
						return;
					}
				}
				
				new StudentAddDialog(jf,schoolId,StudentPanel.this);
			}});
		
		LovoButton lbshow = new LovoButton("查看学生信息",170,500,this);
		lbshow.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				
				new StudentShowDialog(jf,key);
			}});
		
		
		LovoButton lbdel = new LovoButton("删除学生",290,500,this);
		lbdel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				delEmployee(key);
			}});
		
		
		LovoButton lbupdate = new LovoButton("修改学生信息",50,570,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				
				new StudentUpdateDialog(jf,schoolId,key,StudentPanel.this);
			}});
		
		LovoButton lbAddBack = new LovoButton("添加回防记录",170,570,this);
		lbAddBack.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				
				new StudentVisitAddialog(jf,key,schoolId);
			}});
	}
	/**
	 * 初始化查询按钮
	 *
	 */
	private void initFindPanel(){
		LovoTitlePanel jp = new LovoTitlePanel("查询学生",400, 480, 320, 150,this);
	
		this.itemCombox = new LovoComboBox<String>(
				new String[]{"学生姓名","班级","会员",
						"非会员"},30,50,jp);
		
		this.valueTxt.setBounds(160, 50, 120, 20);
		jp.add(this.valueTxt);
		
		LovoButton lb = new LovoButton("查找",180,100,jp);
		lb.setSize(60, 20);
		
		lb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
				if(schoolId == 0){
				JOptionPane.showMessageDialog(null,"请选择学校");
					return;
				}
                findStudent(schoolId, 1);

            }
        });
		
	}
	/**
	 * 设置总页数
	 * @param countPage 总页数
	 */
	private void setTotalPage(int countPage) {
		this.jltTotalPage.setText("/"+countPage+"  页");
	}
	
	//--------------------------
	/**
	 * 根据学校对象得到学校ID
	 * @param schoolObj 学校对象
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
	 * 更新手风琴
	 */
	private void updateAccordion(){

//		this.cityAccordion.updateAccordion(new ArrayList());
	}
	/**
	 * 更新表格
	 * @param schoolId 学校ID
	 * @param int pageNO 页码
	 */
	private void updateStudentTable(int schoolId,int pageNO){
		//在学生表中,根据学校ID查找学生
		//更新表格,插入List集合
		studentTable.updateLovoTable(new StudentDaoImp().getStudentsBySchoolID(schoolId));
		//设置总页数
		this.setTotalPage(0);
	}
	
	/**
	 * 初始化表格
	 */
	private void initTable() {
		studentTable = new LovoTable(this,
				new String[]{"学生姓名","性别","班级","状态","联系电话"},
				new String[]{"name","sex","classes.name","vip","phone"},//学生实体属性名数组 new String[]{"studentName","sex"}
				"id");//主键属性名 studentId
		studentTable.setSizeAndLocation(180, 90, 550, 300);
		
	}
	
	/**
	 * 上一页点击事件
	 */
	private void prevClick(){
		
	}
	/**
	 * 下一页点击事件
	 */
	private void nextClick(){
		
	}
	/**
	 * 转向指定页码
	 * @param pageNO 页码
	 */
	private void goClick(String pageNO){
		
	}
	
	/**
	 * 初始化手风琴组件
	 *
	 */
	public void initAccordion() {
		//第二个参数为城市集合cityList，第三个参数为城市类中学校集合的属性名schoolList
        ArrayList<Object> allAreas = new AreaServiceImp().getAllAreas();
        ArrayList<AreaBean> areas = new ArrayList<AreaBean>();
        for(Object obj : allAreas){
            AreaBean area = (AreaBean)obj;
            areas.add(area);
        }
        cityAccordion = new LovoAccordion(this,areas,"school"){
				
				/**
				 * 学校列表框点击事件
				 * @param schoolObj 学校对象
				 */
			 @Override
				public void clickEvent(Object schoolObj) {
				 //记录点中学校id
				 schoolId = getSchoolId(schoolObj);
					//显示点中学校学生
				 updateStudentTable(schoolId,1);
				}
			};
        cityAccordion.updateAccordion(areas);
	}
	
	/**
	 * 删除学生
	 * @param studentId 学生ID
	 */
	private void delEmployee(int studentId){
        //将学生的status设置为false
        if((JOptionPane.showConfirmDialog(null,"是否删除选中学生信息","删除",JOptionPane.YES_NO_OPTION)) == 0){
            new StudentDaoImp().deleteObject(studentId);
            //对应的班级人数减1;
            StudentBean stu = (StudentBean)new StudentDaoImp().getObjectByID(studentId);
            ClassesBean cla = stu.getClasses();
            cla.setStuNum(cla.getStuNum() -1);
            new ClassesDaoImp().alterObject(cla.getId(),cla);
            updateStudentTable(schoolId, 1);
        }
//		显示删除结果

	}
	/**
	 * 查找学生
	 * @param schoolId 学校id
	 * @param pageNO 页码
	 */
	private void findStudent(int schoolId,int pageNO){
		//得到选项
		String item = itemCombox.getItem();
		//得到选项值
		String value = valueTxt.getText();
        ArrayList<Object> checkSuts = new StudentDaoImp().getObjectByCon(item, value);
//		更新表格,显示查询结果
		studentTable.updateLovoTable(checkSuts);
		//设置总页数
		this.setTotalPage(pageNO);
	}

}
