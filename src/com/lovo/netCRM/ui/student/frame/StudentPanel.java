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

	/**
	 *
	 * 分页属性
	 */
	//总记录数
	private static int counts ;
	private static int pageNum;
	private final static int pageSize = 3;
	private static int pageNow = 1;

	private boolean onceFind = false;

	//得到选项
	String item = "更新";
	//得到选项值
	String value = "";



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
				this.initAccordion();//初始化手风琴组件
			}
			this.updateAccordion();//更新手风琴组件

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
	 * 初始化按钮
	 *
	 */
	private void initButton() {
		LovoButton prevButton = new LovoButton("上一页",0,0,this);
		prevButton.setBounds(200, 400, 50, 20);
		
		prevButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(pageNow > 1){
					pageNOTxt.setText(--pageNow + "");
					prevClick();
				}
			}});
		
		LovoButton nextButton = new LovoButton("下一页",0,0,this);
		nextButton.setBounds(270, 400, 50, 20);
		
		nextButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(pageNow < pageNum){
					pageNOTxt.setText(++pageNow + "");
					nextClick();
				}
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
				/**
				 * 判断是否翻页
				 */
				//读取总数
				ArrayList<Object> objs = new StudentDaoImp().getObjectByCon(schoolId, 1, "更新", "");
				ArrayList<StudentBean> students = new ArrayList<StudentBean>();
				for(Object stu : objs){
					StudentBean obj2Stu = (StudentBean)stu;
					students.add(obj2Stu);
				}
				//总的记录条数
				counts = Integer.parseInt(students.get(0).getDescribe());
				//得到未添加学生的学生总页数
				int oldPageNum = pageNum;
				int newPageNum = (int) Math.ceil(counts / (pageSize * 1.0));

				if(oldPageNum < newPageNum){//增加了总页数
					pageNum = newPageNum;
				}
				//更新新页数
				ArrayList<Object> limitPage = new StudentDaoImp().getObjectByCon(schoolId, pageNum, "更新", "");
				//设置总页数
				setTotalPage(pageNum);
				//设置当前页为最后一页
				pageNow = pageNum;
				pageNOTxt.setText(pageNow+"");
				studentTable.updateLovoTable(update(limitPage));
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
				if (schoolId == 0) {
					JOptionPane.showMessageDialog(null, "请选择学校");
					return;
				}
				if (itemCombox.getItem().equals("会员") || itemCombox.getItem().equals("非会员")) {
					valueTxt.setText("");
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
		ArrayList<Object> allAreas = new AreaServiceImp().getAllAreas();
		this.cityAccordion.updateAccordion(allAreas);
	}

	/**
	 * 初始化表格
	 */
	private void initTable() {
		studentTable = new LovoTable(this,
				new String[]{"学生姓名","性别","班级","状态","联系电话"},
				new String[]{"stuName","sex","classes.name","vip","phone"},//学生实体属性名数组 new String[]{"studentName","sex"}
				"stuId");//主键属性名 studentId
		studentTable.setSizeAndLocation(180, 90, 550, 300);
	}

	/**
	 * 初始化更新表格
	 * @param schoolId 学校ID
	 * @param pageNOW 页码
	 * 点击手风琴的时候被调用,传入学校id
	 */
	private void updateStudentTable(int schoolId,int pageNOW){
		//在学生表中,根据学校ID查找学生
		//更新表格,插入List集合
		ArrayList<Object> objs = new StudentDaoImp().getObjectByCon(schoolId, pageNOW, "更新", "");
		if(objs != null){
			studentTable.updateLovoTable(this.update(objs));//已近设置了总记录数
			//设置总页数
			pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
			this.setTotalPage(pageNum);
			pageNOTxt.setText(pageNow+"");
		}else{
			//设置总页数
			this.setTotalPage(1);
			pageNOTxt.setText("1");
			studentTable.updateLovoTable(new ArrayList());
		}
	}

	/**
	 * 上一页点击事件
	 */
	private void prevClick(){
		if(onceFind){
			ArrayList<Object> checkSuts = new StudentDaoImp().getObjectByCon(schoolId, pageNow, item, value);
			if(checkSuts != null){
				//更新表格,显示查询结果
				studentTable.updateLovoTable(this.update(checkSuts));
				//设置总页数
				pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
				this.setTotalPage(pageNum);
			}else {
				//如果没有结果则不更新表格
				JOptionPane.showMessageDialog(null,"已近是第一页了");
			}
		}else {
			ArrayList<Object> objs = new StudentDaoImp().getObjectByCon(schoolId, pageNow, "更新", "");
			if(objs != null){
				studentTable.updateLovoTable(this.update(objs));//已近设置了总记录数
				//设置总页数
				pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
				this.setTotalPage(pageNum);
				pageNOTxt.setText(pageNow+"");
			}else{
				//设置总页数
				this.setTotalPage(1);
				studentTable.updateLovoTable(new ArrayList());
			}
		}

	}
	/**
	 * 下一页点击事件
	 */
	private void nextClick(){
		if(onceFind){
			ArrayList<Object> checkSuts = new StudentDaoImp().getObjectByCon(schoolId, pageNow, item, value);
			if(checkSuts != null){
				//更新表格,显示查询结果
				studentTable.updateLovoTable(this.update(checkSuts));
				//设置总页数
				pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
				this.setTotalPage(pageNum);
			}else {
				//如果没有结果则不更新表格
				JOptionPane.showMessageDialog(null,"已近是最后一页了");
			}
		}
		else {
			ArrayList<Object> objs = new StudentDaoImp().getObjectByCon(schoolId, pageNow, "更新", "");
			if(objs != null){
				studentTable.updateLovoTable(this.update(objs));//已近设置了总记录数
				//设置总页数
				pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
				this.setTotalPage(pageNum);
				pageNOTxt.setText(pageNow+"");
			}else{
				//设置总页数
				this.setTotalPage(1);
				studentTable.updateLovoTable(new ArrayList());
			}
		}
	}
	/**
	 * 转向指定页码
	 * @param pageNO 页码
	 */
	private void goClick(String pageNO){
		int page = -1;
		try{
			page = Integer.parseInt(pageNO);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"输入错误");
		}
		if(page <= pageNum) {//如果输入的页数小于等于总页数
			pageNow = Integer.parseInt(pageNO);//跳转到用户输入的页数
			//得到选项
			item = itemCombox.getItem();
			//得到选项值
			value = valueTxt.getText();
			ArrayList<Object> checkSuts = new StudentDaoImp().getObjectByCon(schoolId, pageNow, item, value);
			studentTable.updateLovoTable(this.update(checkSuts));
		}
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
        //将学生的status设置为false 班级人数减一
        if((JOptionPane.showConfirmDialog(null,"是否删除选中学生信息","删除",JOptionPane.YES_NO_OPTION)) == 0){
			if(new StudentServiceImp().deletStudent(studentId)){
				if(!onceFind){//无条件查询
					ArrayList<Object> objs = new StudentDaoImp().getObjectByCon(schoolId, 1, "更新", "");

					ArrayList<StudentBean> students = new ArrayList<StudentBean>();
					for(Object stu : objs){
						StudentBean obj2Stu = (StudentBean)stu;
						students.add(obj2Stu);
					}
					//总的记录条数
					counts = Integer.parseInt(students.get(0).getDescribe());
					pageNum = (int) Math.ceil(counts / (pageSize * 1.0));

					if(pageNow > pageNum){
					//加载上一页信息
						ArrayList<Object> limitPage = new StudentDaoImp().getObjectByCon(schoolId, pageNum, "更新", "");
						pageNow = pageNum;
						this.setTotalPage(pageNum);
						pageNOTxt.setText(pageNow+"");
						studentTable.updateLovoTable(this.update(limitPage));
					}
					else{
						ArrayList<Object> limitPage = new StudentDaoImp().getObjectByCon(schoolId, pageNow, "更新", "");
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
					//总的记录条数
					counts = Integer.parseInt(students.get(0).getDescribe());
					pageNum = (int) Math.ceil(counts / (pageSize * 1.0));

					if(pageNow > pageNum){
						//加载上一页信息
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
	 * 查找学生
	 * @param schoolId 学校id
	 * @param pageNOW 页码
	 */
	private void findStudent(int schoolId,int pageNOW){
		onceFind = true;
		//得到选项
		item = itemCombox.getItem();
		//得到选项值
		value = valueTxt.getText();
        ArrayList<Object> checkSuts = new StudentDaoImp().getObjectByCon(schoolId, pageNOW, item, value);
		if(checkSuts != null){
			//更新表格,显示查询结果
			studentTable.updateLovoTable(this.update(checkSuts));
			//设置总页数
			pageNum = (int) Math.ceil(counts / (pageSize * 1.0));
			pageNow = 1;
			pageNOTxt.setText(pageNow+"");
			this.setTotalPage(pageNum);

		}else {
			//如果没有结果则不更新表格
			JOptionPane.showMessageDialog(null,"无查询结果");
		}
	}

	private ArrayList<Switch> update (ArrayList<Object> objects) {
		ArrayList<StudentBean> students = new ArrayList<StudentBean>();
		for(Object stu : objects){
			StudentBean obj2Stu = (StudentBean)stu;
			students.add(obj2Stu);
		}
		//总的记录条数
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
				change.setVip("会员");
			}else {
				change.setVip("非会员");
			}
			changes.add(change);
		}
		return changes;
	}

}
