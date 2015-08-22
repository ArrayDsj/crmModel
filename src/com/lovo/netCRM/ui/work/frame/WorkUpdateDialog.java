package com.lovo.netCRM.ui.work.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoCheckBox;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxtArea;
/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 修改职位对话框
 * 开发日期:2012-10-6
 */
public class WorkUpdateDialog extends JDialog{
	/**职位主面板*/
	private WorkPanel workPanel;
	/**职位名称文本框*/
	private LovoLabel nameLabel = new LovoLabel("职位名称",50,50,this);
	/**职位描述文本域*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("职位描述",50,100,300,80,this);
	/**权限复选框*/
	private LovoCheckBox gradeTxt = new LovoCheckBox("权限",new String[]{"查询权限","考核权限","销售统计分析","权限管理","后台管理"},50,200,this);
	/**职位id*/
	private int workId;
	public WorkUpdateDialog(JFrame jf,int workId,WorkPanel workPanel){
		super(jf,true);
		this.workPanel = workPanel;
		this.workId = workId;
		
		this.setLayout(null);
		this.setTitle("添加新职位");
		
		this.init();
		
		this.setBounds(350, 150, 550, 400);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		descriptionTxt.setEditable(false);
		this.initData(workId);
		LovoButton lbupdate = new LovoButton("修改",150,300,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				updateWork(workId);
				WorkUpdateDialog.this.dispose();
			}});
		
		LovoButton lbcancel = new LovoButton("取消",330,300,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				WorkUpdateDialog.this.dispose();
			}});
	}
	
	
	//----------------------

	/**
	 * 初始化数据
	 * @param workId 工作职位ID
	 */
	private void initData(int workId) {
		//设置选中项
		gradeTxt.setItem(new String[]{});
		
	}
	/**
	 * 修改职位
	 * @param workId 工作职位id
	 */
	private void updateWork(int workId){
		
		
		//更新表格，显示修改职位结果
		this.workPanel.initData();
	}
}
