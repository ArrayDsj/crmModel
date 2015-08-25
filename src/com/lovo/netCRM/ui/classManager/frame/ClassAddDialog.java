package com.lovo.netCRM.ui.classManager.frame;

import com.lovo.netCRM.bean.ClassesBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.dao.imp.ClassesDaoImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 添加班级对话框
 * 开发日期:2012-10-14
 */
public class ClassAddDialog extends JDialog{
	/**班级表格*/
	private LovoTable classTable;
	/**班级名称文本框*/
	private LovoTxt nameTxt = new LovoTxt("班级名称",50,60,this);
	/**带班老师文本框*/
	private LovoTxt teacherTxt = new LovoTxt("带班老师",50,140,this);
	/**学校id*/
	private int schoolId;
	/**班级面板*/
	private ClassManagerPanel classManagerPanel;
	
	public ClassAddDialog(JFrame jf,int schoolId,ClassManagerPanel classManagerPanel){
		super(jf,true);
		this.schoolId = schoolId;
		this.classManagerPanel = classManagerPanel;
		this.setLayout(null);
		this.setTitle("添加班级");
		this.init();
		this.setBounds(400, 200, 350, 300);
		this.setVisible(true);
	}
	
	/**
	 * 初始化
	 *
	 */
	private void init() {
		LovoButton lbadd = new LovoButton("添加",60,220,this);
		lbadd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				boolean isOk = addClass(schoolId);
				if(isOk){
				    ClassAddDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("取消",200,220,this);
		lbcancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ClassAddDialog.this.dispose();
			}});
	}
	
	//---------------------
	/**
	 * 添加班级
	 * @param schoolId 学校id
	 */
	private boolean addClass(int  schoolId){
		//验证数据，验证失败，返回false
        ClassesBean newClasses = new ClassesBean();
        String error = "";
        if(nameTxt.getText() == null || nameTxt.getText().trim().equals("")){
           error += "名称不能为空";
        }
        if(teacherTxt.getText() == null || nameTxt.getText().trim().equals("")){
            error += "老师名字不能为空";
        }
        if(error.length() != 0) {
            JOptionPane.showMessageDialog(null, error);
            return false;
        }
        newClasses.setName(nameTxt.getText());
        newClasses.setTeaName(teacherTxt.getText());
        newClasses.setBuildTime(new Date());
        if(new ClassesDaoImp().addObject(schoolId,newClasses)){
            //更新表格，显示添加结果
            this.classManagerPanel.initData();
            return true;
        }
        return false;
	}
}
