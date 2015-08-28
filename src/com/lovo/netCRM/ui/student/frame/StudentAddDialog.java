package com.lovo.netCRM.ui.student.frame;

import com.lovo.netCRM.bean.ClassesBean;
import com.lovo.netCRM.bean.StudentBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.dao.imp.ClassesDaoImp;
import com.lovo.netCRM.dao.imp.StudentDaoImp;
import com.lovo.netCRM.ui.frame.MainFrame;

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
 * @description 添加学生面板
 * 开发日期:2012-10-14
 */
public class StudentAddDialog extends JDialog{
	/**学生主面板*/
	private StudentPanel studentPanel;
	/**学校id*/
	private int schoolId;
	/**姓名文本框*/
	private LovoTxt nameTxt = new LovoTxt("姓    名",50,50,this);
	/**性别单选钮*/
	private LovoRadioButton sexTxt = new LovoRadioButton("性    别",new String[]{"男","女"},320,50,this);
	/**出生日期文本框*/
	private LovoDate birthdayTxt = new LovoDate("出生日期",50,100,this);
	/**所属班级*/
	private LovoComboBox classTxt;
	/**家庭地址文本框*/
	private LovoTxt addressTxt = new LovoTxt("家庭地址",50,150,this);
	/**联系电话文本框*/
	private LovoTxt phoneTxt = new LovoTxt("联系电话",320,150,this);
	/**父亲文本框*/
	private LovoTxt fatherTxt = new LovoTxt("父    亲",50,200,this);
	/**父亲电话文本框*/
	private LovoTxt fatherPhoneTxt = new LovoTxt("父亲电话",320,200,this);
	/**母亲文本框*/
	private LovoTxt mumTxt = new LovoTxt("母    亲",50,250,this);
	/**母亲电话文本框*/
	private LovoTxt mumPhoneTxt = new LovoTxt("母亲电话",320,250,this);
	
	/**备注文本域*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("备    注",50,300,400,120,this);

	MainFrame mainFrame ;
	public StudentAddDialog(JFrame jf,int schoolId,StudentPanel studentPanel){
		super(jf,true);

		this.mainFrame = (MainFrame)jf;

		this.studentPanel = studentPanel;

		this.schoolId = schoolId;
		this.setLayout(null);
		this.setTitle("添加学生信息");
		
		this.init();
		
		this.setBounds(300, 150, 650, 530);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		this.initComboBox(schoolId);
		LovoButton lbadd = new LovoButton("添加",200,450,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addStudent(schoolId);
				if(isOk){
					mainFrame.getStudentPanel().initAccordion();
					StudentAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("取消",400,450,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				StudentAddDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * 初始化班级下拉框
	 *
	 */
	private void initComboBox(int schoolId){
//		添加班级 集合
        ArrayList<Object> allClassBySchool = new ClassesDaoImp().getObjectByschID(schoolId);
		this.classTxt = new LovoComboBox("所属班级",allClassBySchool,320,100,this);
        classTxt.getItemAt(0);
	}
	
	/**
	 * 添加学生
	 */
	private boolean addStudent(int schoolId){
		//验证数据，验证失败返回false
        StudentBean stu = new StudentBean();
        //验证数据,验证失败返回false
        String error = "";
        if(nameTxt.getText() == null || nameTxt.getText().equals("")){
            error += "姓名不能为空\n";
        }
        if(sexTxt.getItem() == null || sexTxt.getItem().equals("")){
            error += "请选性别\n";
        }
        if(birthdayTxt.getDate() == null || birthdayTxt.getDate().equals("")){
            error += "没校长,滚犊子\n";
        }
        if(phoneTxt.getText() == null || phoneTxt.getText().equals("")){
            error += "没手机,滚犊子\n";
        }
        if(classTxt.getItem() == null || classTxt.getItem().equals("")){
            error += "没班级,滚犊子\n";
        }
        if(addressTxt.getText() == null || addressTxt.getText().equals("")){
            error += "没地址,滚犊子\n";
        }
        if(fatherTxt.getText() == null || fatherTxt.getText().equals("")){
            error += "没老汉儿,滚犊子\n";
        }
        if(fatherPhoneTxt.getText() == null || fatherPhoneTxt.getText().equals("")){
            error += "没老汉儿电话,滚犊子\n";
        }
        if(mumTxt.getText() == null || mumTxt.getText().equals("")){
            error += "没老娘,滚犊子\n";
        }if(descriptionTxt.getText() == null || descriptionTxt.getText().equals("")){
            error += "没说明,滚犊子\n";
        }if(mumPhoneTxt.getText() == null || mumPhoneTxt.getText().equals("")){
            error += "没老娘电话,滚犊子\n";
        }
        if(error.length() != 0) {
            JOptionPane.showMessageDialog(null, error);
            return false;
        }
        //封装实体
        stu.setName(nameTxt.getText());
        stu.setSex(sexTxt.getItem());
        stu.setBirthday(birthdayTxt.getDate());
        stu.setPhone(phoneTxt.getText());
        ClassesBean cla = new ClassesDaoImp().getClassesByName(classTxt.getItem().toString());
        stu.setClasses(cla);
        stu.setAddress(addressTxt.getText());
        stu.setFather(fatherTxt.getText());
        stu.setFatherPhone(fatherPhoneTxt.getText());
        stu.setMother(mumTxt.getText());
        stu.setMotherPhone(mumPhoneTxt.getText());
        stu.setDescribe(descriptionTxt.getText());
        stu.setStatus(true);
        stu.setVip(false);

        //写入数据库
		new StudentDaoImp().addObject(schoolId, stu);
        //这个班级的人数加一

        cla.setStuNum(cla.getStuNum()+1);
		JOptionPane.showMessageDialog(null,cla.getStuNum());
        new ClassesDaoImp().alterObject(cla.getId(), cla);
		//更新表格，显示添加结果
		this.studentPanel.initData();
		
		return true;
	}
}
