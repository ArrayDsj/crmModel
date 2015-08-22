package com.lovo.netCRM.ui.student.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitlePanel;
import com.lovo.netCRM.component.LovoTxtArea;
/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 显示学生信息对话框
 * 开发日期:2012-10-14
 */
public class StudentShowDialog extends JDialog{
	/**学生id*/
	private int studentId;
	/**回访记录表格*/
	private LovoTable backTable;
	/**姓名文本框*/
	private LovoLabel nameLabel = new LovoLabel("姓    名",50,50,this);
	/**性别单选钮*/
	private LovoLabel sexLabel = new LovoLabel("性    别",320,50,this);
	/**出生日期文本框*/
	private LovoLabel birthdayLabel = new LovoLabel("出生日期",50,100,this);
	/**所属班级*/
	private LovoLabel classLabel = new LovoLabel("所属班级",320,100,this);

	/**家庭地址文本框*/
	private LovoLabel addressLabel = new LovoLabel("家庭地址",50,150,this);
	/**联系电话文本框*/
	private LovoLabel phoneLabel = new LovoLabel("联系电话",320,150,this);
	/**父亲文本框*/
	private LovoLabel fatherLabel = new LovoLabel("父    亲",50,200,this);
	/**父亲电话文本框*/
	private LovoLabel fatherPhoneLabel = new LovoLabel("父亲电话",320,200,this);
	/**母亲文本框*/
	private LovoLabel mumLabel = new LovoLabel("母    亲",50,250,this);
	/**母亲电话文本框*/
	private LovoLabel mumPhoneLabel = new LovoLabel("母亲电话",320,250,this);
	
	/**学生状态标签*/
	private LovoLabel stateLabel = new LovoLabel("学生状态",50,300,this);
	/**备注文本域*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("备    注",320,300,200,100,this);
	
	
	public StudentShowDialog(JFrame jf,int studentId){
		super(jf,true);
		this.studentId = studentId;
		this.setLayout(null);
		this.setTitle("查看学生信息");
		
		this.init();
		
		this.setBounds(300, 10, 650, 700);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		this.descriptionTxt.setEditable(false);
		this.initData(studentId);
		/*回访记录容器*/
		LovoTitlePanel jp = new LovoTitlePanel("回访记录",80, 420, 500, 200,this);
		
		this.initTable(studentId,jp);
		
		LovoButton lbadd = new LovoButton("确定",280,630,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				StudentShowDialog.this.dispose();
			}});
		
	
	}
	
	//----------------------
	/**
	 * 初始化信息
	 * @param studentId 学生ID
	 */
	private void initData(int studentId){
		
	}
	/**
	 * 初始化表格
	 * @param studentId 学生id
	 */
	private void initTable(int studentId,JPanel jp){
		backTable = new LovoTable(jp,
				new String[]{"回访时间","负责人","回访人","回访主题","回访内容"},
				new String[]{},//学校实体属性名数组 new String[]{"time","man"}
				"");//主键属性名 backId
		backTable.setSizeAndLocation(10, 20, 480, 170);
		
		//更新表格,插入List集合
		backTable.updateLovoTable(null);
	}
	
}
