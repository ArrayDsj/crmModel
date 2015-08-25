package com.lovo.netCRM.ui.classManager.frame;

import com.lovo.netCRM.bean.ClassesBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.dao.imp.ClassesDaoImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 修改班级对话框
 * 开发日期:2012-10-14
 */
public class ClassUpdateDialog extends JDialog{

	/**班级名称文本框*/
	private LovoLabel nameLabel = new LovoLabel("班级名称",50,60,this);
	/**带班老师文本框*/
	private LovoTxt teacherTxt = new LovoTxt("带班老师",50,140,this);
	/**班级id*/
	private int classId;
	/**班级面板*/
	private ClassManagerPanel classManagerPanel;

	public ClassUpdateDialog(JFrame jf,int classId,ClassManagerPanel classManagerPanel){
		super(jf,true);
		this.classId = classId;
		this.classManagerPanel = classManagerPanel;
		
		this.setLayout(null);
		this.setTitle("修改班级");
		
		this.init();
		
		this.setBounds(400, 200, 350, 300);
		this.setVisible(true);
	}
	
	/**
	 * 初始化
	 *
	 */
	private void init() {
		this.initClassData(classId);
		
		LovoButton lbupdate = new LovoButton("修改",60,220,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = updateClass(classId);
				if(isOk){
				ClassUpdateDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("取消",200,220,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				ClassUpdateDialog.this.dispose();
			}});
	}
	
	//---------------------
	/**
	 * 初始化数据
	 * @param classId 班级ID
	 */
	private void initClassData(int classId){
        ClassesBean classes = (ClassesBean)new ClassesDaoImp().getObjectByID(classId);
        nameLabel.setText(classes.getName());
        teacherTxt.setText(classes.getTeaName());
	}
	/**
	 * 修改班级
	 * @param classId 班级ID
	 */
	private boolean updateClass(int classId){
        ClassesBean cla = new ClassesBean();
        //验证数据,验证失败返回false
        String error = "";
        if(teacherTxt.getText() == null || teacherTxt.getText().equals("")){
            error += "没名字，滚犊子\n";
        }
        if(error.length() != 0) {
            JOptionPane.showMessageDialog(null, error);
            return false;
        }
        //封装实体
        cla.setTeaName(teacherTxt.getText());
        //更新表格，显示修改结果
        new ClassesDaoImp().alterObject(classId,cla);
		this.classManagerPanel.initData();
		return true;
	}
}
