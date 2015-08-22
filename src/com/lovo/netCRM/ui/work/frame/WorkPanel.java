package com.lovo.netCRM.ui.work.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 职位管理面板
 * 开发日期:2012-10-6
 */
public class WorkPanel  extends JPanel{
	/**职位表格组件*/
	private LovoTable workTable;
	/**窗体对象*/
	private JFrame jf;
	public WorkPanel(JFrame jf){
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		new LovoTitleLabel("职 位 管 理",this);
		this.initTable();
		this.initButton();
		this.initData();
	}
	/**
	 * 初始化数据
	 */
	public void initData(){
		this.updateWorkTable();
	}
	/**
	 * 初始化按钮
	 *
	 */
	private void initButton() {
		LovoButton lbadd = new LovoButton("添加新职位",200,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				new WorkAddDialog(jf,WorkPanel.this);
			}});
		
		
		LovoButton lbupdate = new LovoButton("修改职位权限",400,500,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = workTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"请选择行");
//					return;
//				}
				
				new WorkUpdateDialog(jf,key,WorkPanel.this);
			}});
	}
	
	//--------------------------
	
	/**
	 * 初始化表格
	 */
	private void initTable() {
		workTable = new LovoTable(this,
				new String[]{"职位名称","职位描述","查询权限","考核权限","销售统计分析","权限管理","后台管理"},
				new String[]{},//职位实体属性名数组 new String[]{"workName","description"}
				"");//主键属性名 workId
		workTable.setSizeAndLocation(20, 90, 700, 300);
		
	}
	/**
	 * 更新表格数据
	 */
	private void updateWorkTable(){
		//更新表格,插入职位List集合
		workTable.updateLovoTable(null);
	}
}
