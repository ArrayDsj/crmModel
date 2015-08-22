package com.lovo.netCRM.ui.school.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;

import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.component.LovoTxtArea;


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
		this.cityTxt = new LovoComboBox("所属城市",new ArrayList(),320,50,this);
		
		//添加部门List集合
		this.deptTxt = new LovoComboBox("负责部门",new ArrayList(),50,300,this){
			/**
			 * 根据部门ID显示员工集合
			 * @param deptObj 部门对象
			 */
			public void onchange(Object deptObj){
				
				//设置员工集合
				employeeTxt.setList(null);
			}
		};
		
	}
	
	/**
	 * 添加学校
	 */
	private boolean addSchool(){
		//验证数据，验证失败返回false
		
		//封装实体
		
		//更新表格，显示添加结果
		this.schoolPanel.initData();
		
		return true;
	}
}
