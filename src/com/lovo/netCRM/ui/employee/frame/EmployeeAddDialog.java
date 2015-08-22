package com.lovo.netCRM.ui.employee.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoFileChooser;
import com.lovo.netCRM.component.LovoRadioButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 添加员工对话框
 * 开发日期:2012-10-6
 */
public class EmployeeAddDialog extends JDialog{

	/**姓名文本框*/
	private LovoTxt nameTxt = new LovoTxt("姓    名",40,50,this);
	/**性别单选钮*/
	private LovoRadioButton sexTxt = new LovoRadioButton("性    别",new String[]{"男","女"},320,50,this);
	/**出生年月*/
	private LovoDate birthdayTxt = new LovoDate("出生年月",40,100,this);
	/**文化程度*/
	private LovoComboBox<String> eduTxt = new LovoComboBox<String>("文化程度",new String[]{"高中","大专","本科","硕士"},320,100,this);
	/**所属专业文本框*/
	private LovoTxt specialityTxt = new LovoTxt("所属专业",40,150,this);
	/**联系方式文本框*/
	private LovoTxt phoneTxt = new LovoTxt("联系方式",320,150,this);
	/**家庭住址文本框*/
	private LovoTxt addressTxt = new LovoTxt("家庭住址",40,200,this);
	/**政治面貌*/
	private LovoComboBox<String> polityFaceTxt = new LovoComboBox<String>("政治面貌",new String[]{"团员","党员","民主党派","无党派人士"},320,200,this);
	/**所在部门*/
	private LovoComboBox deptTxt;
	/**工作职位*/
	private LovoComboBox jobTxt;
	/**头像*/
	private LovoFileChooser faceTxt = new LovoFileChooser(this,"face");
	/**员工主面板*/
	private EmployeePanel emPanel;
	
	public EmployeeAddDialog(JFrame jf,EmployeePanel emPanel){
		super(jf,true);
		this.emPanel = emPanel;
		this.setLayout(null);
		this.setTitle("添加新员工");
		
		this.init();
		
		this.setBounds(300, 100, 700, 400);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init(){
		this.initComboBox();
		
		faceTxt.setBounds(580, 70, 100, 150);
		
		LovoButton lbadd = new LovoButton("添加",150,310,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addEmployee();
				if(isOk){
					EmployeeAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("取消",400,310,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				EmployeeAddDialog.this.dispose();
			}});
	}
	
	//--------------------------------------------
	/**
	 * 初始化部门和职位下拉框
	 *
	 */
	private void initComboBox(){
		//添加部门List集合
		this.deptTxt = new LovoComboBox("所属部门",new ArrayList(),40,250,this);
		//添加职位List集合
		this.jobTxt = new LovoComboBox("工作职位",new ArrayList(),320,250,this);
	
	}
	/**
	 * 添加操作
	 *
	 */
	private boolean addEmployee(){
		String str = "";
		//验证数据
//		if(!this.nameTxt.getText().matches("[a-zA-Z\\u4e00-\\u9fa5]{2,20}")){
//			str += "用户名必须为二位以上字母或汉字\n";
//		}
		
		//……
		
		if(str.length() != 0){
			JOptionPane.showMessageDialog(null, str);
			return false;
		}
		
		//封装实体
		
		//完成添加操作
		
//		更新数据,显示添加结果
		this.emPanel.initData();
		return true;
	}
}
