package com.lovo.netCRM.ui.schoolActive.frame;

import com.lovo.netCRM.bean.ActiveBean;
import com.lovo.netCRM.bean.DepartBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.dao.imp.EmployeeDaoImp;
import com.lovo.netCRM.service.imp.ActiveServiceImp;
import com.lovo.netCRM.service.imp.DepartServiceImp;
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
 * @description 添加学校活动对话框
 * 开发日期:2012-10-8
 */
public class SchoolActiveAddDialog extends JDialog{
	/**学校Id*/
	private int schoolId;
	/**活动名称文本框*/
	private LovoTxt nameTxt = new LovoTxt("活动名称",50,50,this);
	/**活动时间文本框*/
	private LovoDate timeTxt = new LovoDate("活动时间",50,100,this);
	/**活动地点文本框*/
	private LovoTxt addressTxt = new LovoTxt("活动地点",50,150,this);
	/**活动主题文本框*/
	private LovoTxt titleTxt = new LovoTxt("活动主题",50,200,this);
	/**负责部门*/
	private LovoComboBox deptTxt;
	/**负责人*/
	private LovoComboBox employeeTxt = new LovoComboBox("负责人",new ArrayList(),50,300,this);;

	// 活动主页
	private SchoolActivePanel activePanel ;
	/**
	 * 添加活动对话框
	 * @param jf 窗体对象
	 *
	 *
	 */
	public SchoolActiveAddDialog(SchoolActivePanel activePanel,JFrame jf,int schoolId){
		super(jf,true);
		this.activePanel = activePanel;
		this.schoolId = schoolId;
		this.setLayout(null);
		this.setTitle("添加新活动");
		
		this.init();
		
		this.setBounds(400, 150, 350, 450);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		this.initComboBox();

		deptTxt.setSelectedIndex(1);
		employeeTxt.setSelectedIndex(0);
		
		LovoButton lbadd = new LovoButton("添加",50,350,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addActive(schoolId);
				if(isOk){
					SchoolActiveAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("取消",180,350,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolActiveAddDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * 初始化负责部门下拉框
	 *
	 */
	private void initComboBox(){
		//添加部门List集合
		ArrayList<Object> departs = new DepartServiceImp().getAllDepts();
		this.deptTxt = new LovoComboBox("负责部门",departs,50,250,this){
			/**
			 * 根据部门ID显示员工集合
			 * @param deptObj 部门对象
			 */
			public void onchange(Object deptObj){
				DepartBean dept = (DepartBean)deptObj;
				//通过deptID找到所有员工
				//设置员工集合
				employeeTxt.setList(new EmployeeDaoImp().getAllEmpByDeptID(dept.getDepartID()));
			}
		};
		

	
	}
	
	/**
	 * 添加活动
	 */
	private boolean addActive(int schoolId){
		ActiveBean active = new ActiveBean();
		//验证数据,验证失败返回false
		String error = "";
		if(nameTxt.getText() == null || nameTxt.getText().equals("")){
			error += "活动名不能为空\n";
		}
		if(timeTxt.getDate() == null ){
			error += "时间不能为空\n";
		}
		if(addressTxt.getText() == null || addressTxt.getText().equals("")){
			error += "地址不能为空\n";
		}
		if(titleTxt.getText() == null || titleTxt.getText().equals("")){
			error += "活动主题不能为空\n";
		}

		if(error.length() != 0){
			JOptionPane.showMessageDialog(null, error);
			return false;
		}else{
			//封装实体
			active.setName(nameTxt.getText());
			active.setAddress(addressTxt.getText());
			active.setTime(timeTxt.getDate());
			active.setTitle(titleTxt.getText());
			active.setEmp(new EmployeeServiceImp().getEmpByName(employeeTxt.getItem().toString()));
		}

		//写入数据库
		new ActiveServiceImp().addActive(schoolId,active);
		//更新数据
		activePanel.initData();
		return true;
	}
}
