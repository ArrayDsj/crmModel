package com.lovo.netCRM.ui.employee.frame;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoImageLabel;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.service.imp.EmployeeServiceImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 员工信息显示对话框
 * 开发日期:2012-10-15
 */
public class EmployeeShowDialog extends JDialog{

	/**姓名标签*/
	private LovoLabel nameLabel = new LovoLabel("姓    名",40,50,this);
	/**性别标签*/
	private LovoLabel sexLabel = new LovoLabel("性    别",320,50,this);
	
	/**出生日期标签*/
	private LovoLabel birthdayLabel = new LovoLabel("出生日期",40,100,this);
	/**文化程度标签*/
	private LovoLabel eduLabel = new LovoLabel("文化程度",320,100,this);
	
	/**所属专业标签*/
	private LovoLabel specialityLabel = new LovoLabel("所属专业",40,150,this);
	/**联系方式标签*/
	private LovoLabel phoneLabel = new LovoLabel("联系方式",320,150,this);
	/**家庭住址标签*/
	private LovoLabel adressLabel = new LovoLabel("家庭住址",40,200,this);
	/**政治面貌*/
	private LovoLabel polityFaceLabel = new LovoLabel("政治面貌",320,200,this);
	/**所在部门*/
	private LovoLabel deptLabel = new LovoLabel("所在部门",40,250,this);
	/**工作职位*/
	private LovoLabel workLabel = new LovoLabel("工作职位",320,250,this);
	/**入职日期标签*/
	private LovoLabel enterJobLabel = new LovoLabel("入职日期",40,300,this);
	/**头像标签*/
	private LovoImageLabel faceLabel = new LovoImageLabel(580, 70, 100, 130,this);
	/**员工Id*/
	private int employeeId;
	
	public EmployeeShowDialog(JFrame jf,int employeeId){
		super(jf,true);
		this.employeeId = employeeId;
	
		this.setLayout(null);
		this.setTitle("员工信息一览");
		
		this.init();
		
		this.setBounds(300, 100, 700, 450);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		this.initEmployeeData(employeeId);
		LovoButton lbcancel = new LovoButton("确定",300,350,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				EmployeeShowDialog.this.dispose();
			}});
	}
	
	//-------------------------
	/**
	 * 初始化数据
	 * @param employeeId 员工ID
	 */
	private void initEmployeeData(int employeeId){
		EmployeeBean theEmp = new EmployeeServiceImp().getStaffByID(employeeId);
		nameLabel.setText(theEmp.getName());
		sexLabel.setText(theEmp.getSex());
		birthdayLabel.setText(theEmp.getBirthday().toString());
		eduLabel.setText(theEmp.getEdu());
		specialityLabel.setText(theEmp.getSpeciality());
		phoneLabel.setText(theEmp.getPhone());
		adressLabel.setText(theEmp.getAddress());
		enterJobLabel.setText(theEmp.getHireDay().toString());
		polityFaceLabel.setText(theEmp.getPolity());
		deptLabel.setText(theEmp.getDept());
		workLabel.setText(theEmp.getPosition());
		faceLabel.setImage(theEmp.getHeadFile());
	}
}
