package com.lovo.netCRM.ui.schoolActive.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
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

	/**
	 * 添加活动对话框
	 * @param jf 窗体对象
	 * @param schoolTable 学校表格
	 * @param cityObj 点中城市
	 */
	public SchoolActiveAddDialog(JFrame jf,int schoolId){
		super(jf,true);
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
		this.deptTxt = new LovoComboBox("负责部门",new ArrayList(),50,250,this){
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
	 * 添加活动
	 */
	private boolean addActive(int schoolId){
		//验证数据,验证失败返回false
		
		//封装实体
		
		return true;
		
	}
}
