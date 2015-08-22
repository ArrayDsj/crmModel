package com.lovo.netCRM.ui.employee.frame;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.service.imp.DepartServiceImp;
import com.lovo.netCRM.service.imp.EmployeeServiceImp;
import com.lovo.netCRM.service.imp.PositionServiceImp;

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
 * @description 员工修改对话框
 * 开发日期:2012-10-6
 */
public class EmployeeUpdateDialog extends JDialog{
	/**员工主面板*/
	private EmployeePanel emPanel;
	/**员工ID*/
	private int employeeId;
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
	/**联系方式文本框*/
	private LovoTxt phoneTxt = new LovoTxt("联系方式",320,150,this);
	/**家庭住址标签*/
	private LovoLabel adressLabel = new LovoLabel("家庭住址",40,200,this);
	/**政治面貌*/
	private LovoComboBox<String> polityFaceTxt = new LovoComboBox<String>("政治面貌",new String[]{"团员","党员","民主党派","无党派人士"},320,200,this);
	/**所在部门*/
	private LovoComboBox deptTxt;
	/**工作职位*/
	private LovoComboBox workTxt;
	/**入职日期标签*/
	private LovoLabel enterJobLabel = new LovoLabel("入职日期",40,300,this);
	/**头像标签*/
	private LovoImageLabel faceLabel = new LovoImageLabel(580, 70, 100, 130,this);
	
	
	public EmployeeUpdateDialog(JFrame jf,int employeeId,EmployeePanel emPanel){
		super(jf,true);
		this.emPanel = emPanel;
		this.employeeId = employeeId;
		
		this.setLayout(null);
		this.setTitle("修改员工信息");
		
		this.init();
		
		this.setBounds(300, 100, 700, 450);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init(){
		this.initComboBox();
		this.initEmployeeData(this.employeeId);
		
		LovoButton lbupdate = new LovoButton("修改",150,350,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = updateEmployee(employeeId);
				if(isOk){
					//销毁UpdateDialog窗口
					EmployeeUpdateDialog.this.dispose();
				}else
					JOptionPane.showMessageDialog(null,"修改失败");
			}});
		
		LovoButton lbcancel = new LovoButton("取消",400,350,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				EmployeeUpdateDialog.this.dispose();
			}});
	}
	
	
	//-------------------------------------------
	
	/**
	 * 初始化部门和职位下拉框
	 *
	 */
	private void initComboBox(){
		//添加部门List集合
		//从数据库中找出所有部门名称
		ArrayList<Object> departNames = new DepartServiceImp().getAllDepts();
		this.deptTxt = new LovoComboBox("所在部门",departNames,40,250,this);
		//默认选择员工所在的部门


		//添加职位List集合
		//从数据库中找出所有职位名称
		ArrayList<Object> positionNames = new PositionServiceImp().getAllPositions();
		this.workTxt = new LovoComboBox("工作职位",positionNames,320,250,this);
		//默认选择员工所处的职业


	
	}
	
	/**
	 * 初始化数据
	 * @param employeeId　员工ID
	 */
	private void initEmployeeData(int employeeId){
		//当点击修改员工信息的时候,将选中的员工信息显示到UpdateDialog面板上
		EmployeeBean theEmp = new EmployeeServiceImp().getStaffByID(employeeId);
		nameLabel.setText(theEmp.getName());
		sexLabel.setText(theEmp.getSex());
		birthdayLabel.setText(theEmp.getBirthday().toString());
		eduLabel.setText(theEmp.getEdu());
		specialityLabel.setText(theEmp.getSpeciality());
		phoneTxt.setText(theEmp.getPhone());
		adressLabel.setText(theEmp.getAddress());
		enterJobLabel.setText(theEmp.getHireDay().toString());
		faceLabel.setImage(theEmp.getHeadFile());
	}
	
	/**
	 * 修改员工信息
	 * @param employeeId 员工ID
	 */
	private boolean updateEmployee(int employeeId) {
		//验证数据，数据验证失败返回false
		//使用正则表达式验证电话号码是否正确
		String phone = phoneTxt.getText();
		if (phone.matches("^[1][0-9]{10}$")) {
			//读取面板上的数据,写入到数据库中
			//1.根据ID查找用户
			EmployeeBean willUpdateEmp = new EmployeeServiceImp().getStaffByID(employeeId);
			//修改手机号
			willUpdateEmp.setPhone(phone);
			//System.out.println("选中:" + deptTxt.getItem().toString());
			//修改部门
			willUpdateEmp.setDept(deptTxt.getItem().toString());
			//修改职位
			//System.out.println("选中:"+workTxt.getItem().toString());
			willUpdateEmp.setPosition(workTxt.getItem().toString());
			//修改政治面貌
			willUpdateEmp.setPolity(polityFaceTxt.getItem().toString());
			//修改数据库
			new EmployeeServiceImp().alterStaff(willUpdateEmp);
			//更新数据，显示修改结果
			this.emPanel.initData();
			return true;
		}else
			return false;
	}
}
