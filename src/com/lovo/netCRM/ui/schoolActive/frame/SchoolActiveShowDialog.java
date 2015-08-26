package com.lovo.netCRM.ui.schoolActive.frame;

import com.lovo.netCRM.bean.ActiveBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
import com.lovo.netCRM.service.imp.ActiveServiceImp;

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
 * @description 学校活动显示对话框
 * 开发日期:2012-10-11
 */
public class SchoolActiveShowDialog  extends JDialog {
	/**活动表格*/
	private LovoTable activeTable;
	/**学校名*/
	private String schoolName;
	
	public SchoolActiveShowDialog(JFrame jf, String schoolName,int  schoolId){
		super(jf,true);
		this.setLayout(null);
		this.setTitle("学校活动一览");
		this.schoolName = schoolName;
		
		this.init(schoolId);
		
		this.setBounds(350, 150, 650, 500);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 * @param schoolId
	 */
	private void init(int schoolId){
		this.initTable(schoolId);
		String title =  schoolName+"活动一览";
		LovoTitleLabel titleLabel = new LovoTitleLabel(title,this);
		titleLabel.setBounds(20, 40, title.length()*30, 30);
		
		LovoButton lbcancel = new LovoButton("确定",280,420,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolActiveShowDialog.this.dispose();
			}});
		
	}
	//-------------------
	
	/**
	 * 初始化表格方法
	 * @param schoolId 学校ID
	 */
	private void initTable(int schoolId) {
		activeTable = new LovoTable(this,
				new String[]{"活动名称","时间","地点","主题","负责部门","负责人"},
				new String[]{"name","time","address","title","emp.dept","emp.name"},//活动实体属性名数组 new String[]{"activeName","time"}
				"id");//主键属性名 activeId
		activeTable.setSizeAndLocation(20, 90, 600, 300);
		//更新表格,插入学校活动List集合
		ArrayList<ActiveBean> allActives = new ActiveServiceImp().getAllActivesByCon(schoolId);
        if(allActives != null){
            activeTable.updateLovoTable(allActives);
        }

	}
}
