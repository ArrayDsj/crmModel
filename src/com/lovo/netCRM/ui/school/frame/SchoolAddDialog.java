package com.lovo.netCRM.ui.school.frame;

import com.lovo.netCRM.bean.DepartBean;
import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.component.LovoTxtArea;
import com.lovo.netCRM.dao.imp.AreaDaoImp;
import com.lovo.netCRM.dao.imp.EmployeeDaoImp;
import com.lovo.netCRM.dao.imp.SchoolDaoImp;
import com.lovo.netCRM.service.imp.AreaServiceImp;
import com.lovo.netCRM.service.imp.DepartServiceImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;


/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 添加学校对话框
 * 开发日期:2012-10-11
 */
public class SchoolAddDialog extends JDialog{
	/**学校主面板*/
	private SchoolPanel schoolPanel;
	/**学校名称文本框*/
	private LovoTxt nameTxt = new LovoTxt("学校名称",50,50,this);
	/**所属城市*/
	private LovoComboBox cityTxt;
	/**地址文本框*/
	private LovoTxt addressTxt = new LovoTxt("学校地址",50,100,this);
	/**校长文本框*/
	private LovoTxt masterTxt = new LovoTxt("校长",320,100,this);
	/**联系电话文本框*/
	private LovoTxt phoneTxt = new LovoTxt("联系电话",50,150,this);
	/**学生人数文本框*/
	private LovoTxt studentNumberTxt = new LovoTxt("学生人数",320,150,this);
	/**老师人数文本框*/
	private LovoTxt teacherNumberTxt = new LovoTxt("老师人数",50,200,this);
	/**ip地址文本框*/
	private LovoTxt ipTxt = new LovoTxt("ip地址",320,200,this);
	/**宽带流量文本框*/
	private LovoTxt netFluxTxt = new LovoTxt("宽带流量",50,250,this);
	
	/**负责部门*/
	private LovoComboBox deptTxt;
	/**负责人职位*/
	private LovoComboBox employeeTxt = new LovoComboBox("负责人",new ArrayList(),50,350,this);;
	
	/**说明文本域*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("说明",320,250,200,120,this);

	public SchoolAddDialog(JFrame jf,SchoolPanel schoolPanel){
		super(jf,true);
		this.schoolPanel = schoolPanel;

		this.setLayout(null);
		this.setTitle("录入新学校");
		
		this.init();
		
		this.setBounds(300, 150, 650, 500);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		this.initComboBox();
		
		LovoButton lbadd = new LovoButton("添加",200,400,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addSchool();
				if(isOk){
					SchoolAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("取消",400,400,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolAddDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * 初始化城市、负责部门和负责人下拉框
	 *
	 */
	private void initComboBox(){
//		添加城市集合
		ArrayList<Object> areas = new AreaServiceImp().getAllAreas();
		this.cityTxt = new LovoComboBox("所属城市",areas,320,50,this);
		
		//添加部门List集合
		ArrayList<Object> departs = new DepartServiceImp().getAllDepts();
		this.deptTxt = new LovoComboBox("负责部门",departs,50,300,this){
			/**
			 * 根据部门ID显示员工集合
			 * @param deptObj 部门对象
			 */
			public void onchange(Object deptObj){
				DepartBean dept = (DepartBean)deptObj;
				//设置员工集合
				employeeTxt.setList(new EmployeeDaoImp().getAllEmpByDeptID(dept.getDepartID()));
			}
		};
		
	}
	
	/**
	 * 添加学校
	 */
	private boolean addSchool(){
		//验证数据，验证失败返回false
		SchoolBean sch = new SchoolBean();
		//验证数据,验证失败返回false
		String error = "";
		if(nameTxt.getText() == null || nameTxt.getText().equals("")){
			error += "学校名不能为空\n";
		}
		if(addressTxt.getText() == null || addressTxt.getText().equals("")){
			error += "地址不能为空\n";
		}
		if(masterTxt.getText() == null || masterTxt.getText().equals("")){
			error += "没校长,滚犊子\n";
		}
		if(phoneTxt.getText() == null || phoneTxt.getText().equals("")){
			error += "没手机,滚犊子\n";
		}
		if(studentNumberTxt.getText() == null || studentNumberTxt.getText().equals("")){
			error += "没学生,滚犊子\n";
		}
		if(teacherNumberTxt.getText() == null || teacherNumberTxt.getText().equals("")){
			error += "没老师,滚犊子\n";
		}
		if(ipTxt.getText() == null || ipTxt.getText().equals("")){
			error += "没IP,滚犊子\n";
		}
		if(netFluxTxt.getText() == null || netFluxTxt.getText().equals("")){
			error += "没流量,滚犊子\n";
		}
		if(descriptionTxt.getText() == null || descriptionTxt.getText().equals("")){
			error += "没说明,滚犊子\n";
		}
		if(error.length() != 0) {
			JOptionPane.showMessageDialog(null, error);
			return false;
		} else{
			//封装实体
			sch.setName(nameTxt.getText());
			sch.setAddress(addressTxt.getText());
			sch.setMaster(masterTxt.getText());
			sch.setPhone(phoneTxt.getText());
			sch.setStuNum(Integer.parseInt(studentNumberTxt.getText()));
			sch.setTeaNum(Integer.parseInt(teacherNumberTxt.getText()));
			sch.setIPAddress(ipTxt.getText());
			sch.setFlow(netFluxTxt.getText());
			sch.setDescribe(descriptionTxt.getText());
			sch.setArea(new AreaDaoImp().getAreaByName(cityTxt.getItem().toString()));
			sch.setEmp(new EmployeeDaoImp().getEmpByName(employeeTxt.getItem().toString()));
			sch.setStatus("接洽中");
			sch.setFoundTime(new Date());

		}

		//写入数据库
		new SchoolDaoImp().addObject(sch);
		//更新表格，显示添加结果
		this.schoolPanel.initData();
		
		return true;
	}
}
