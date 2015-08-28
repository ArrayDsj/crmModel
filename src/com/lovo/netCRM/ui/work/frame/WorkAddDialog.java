package com.lovo.netCRM.ui.work.frame;

import com.lovo.netCRM.bean.PositionBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoCheckBox;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.component.LovoTxtArea;
import com.lovo.netCRM.service.imp.PositionServiceImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 添加职位对话框
 * 开发日期:2012-10-6
 */
public class WorkAddDialog extends JDialog {
	/**
	 * 职位主面板
	 */
	private WorkPanel workPanel;
	/**
	 * 职位名称文本框
	 */
	private LovoTxt nameTxt = new LovoTxt("职位名称", 50, 50, this);
	/**
	 * 职位描述文本域
	 */
	private LovoTxtArea descriptionTxt = new LovoTxtArea("职位描述", 50, 100, 300, 80, this);
	/**
	 * 权限复选框
	 */
	private LovoCheckBox gradeTxt = new LovoCheckBox("权限",
			new String[]{"查询权限", "考核权限", "销售统计分析", "权限管理", "后台管理"}, 50, 200, this);

	public WorkAddDialog(JFrame jf, WorkPanel workPanel) {
		super(jf, true);
		this.workPanel = workPanel;
		this.setLayout(null);
		this.setTitle("添加新职位");

		this.init();

		this.setBounds(350, 150, 550, 400);
		this.setVisible(true);
	}

	/**
	 * 初始化
	 */
	private void init() {
		LovoButton lbadd = new LovoButton("添加", 150, 300, this);
		lbadd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addWork();
				if (isOk) {
					WorkAddDialog.this.dispose();
				}
			}
		});

		LovoButton lbcancel = new LovoButton("取消", 330, 300, this);
		lbcancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				WorkAddDialog.this.dispose();
			}
		});
	}

	//----------------------

	/**
	 * 添加职位
	 */
	private boolean addWork() {
		//验证数据，数据验证失败返回false
		PositionBean newPos = new PositionBean();
		String errorStr = "";
		if (!this.nameTxt.getText().matches("[\\u4e00-\\u9fa5]{2,10}")) {
			errorStr += "职位名称格式错误(2到10个汉字)\n";
		}
		if (!this.descriptionTxt.getText().matches("[a-zA-Z0-9\\u4e00-\\u9fa5]{3,50}")) {
			errorStr += "描述格式错误\n";
		}
		if (errorStr.length() != 0) {
			JOptionPane.showMessageDialog(null, errorStr);
			return false;
		}
		//封装实体
		else {
			newPos.setName(nameTxt.getText());
			newPos.setDescribe(descriptionTxt.getText());
			//默认为false
			newPos.setCheckRight(false);
			newPos.setQueryRight(false);
			newPos.setSaleRight(false);
			newPos.setManagerRight(false);
			newPos.setBackRight(false);
			//得到选中项数组
			String[] items = gradeTxt.getItem();
			for (int i = 0; i < items.length; i++) {
				if (items[i].toString().equals("查询权限")) {
					newPos.setCheckRight(true);
				}
				if (items[i].toString().equals("考核权限")) {
					newPos.setQueryRight(true);
				}
				if (items[i].toString().equals("销售统计分析")) {
					newPos.setSaleRight(true);
				}

				if (items[i].toString().equals("权限管理")) {
					newPos.setManagerRight(true);
				}
				if (items[i].toString().equals("后台管理")) {
					newPos.setBackRight(true);
				}
			}
		}
		JOptionPane.showMessageDialog(null, newPos.isCheckRight());
		JOptionPane.showMessageDialog(null, newPos.isQueryRight());
		JOptionPane.showMessageDialog(null, newPos.isSaleRight());
		JOptionPane.showMessageDialog(null, newPos.isManagerRight());
		JOptionPane.showMessageDialog(null, newPos.isBackRight());
		new PositionServiceImp().addPosition(newPos);
		//更新表格，显示添加职位结果
		workPanel.initData();
		return true;
	}
}
