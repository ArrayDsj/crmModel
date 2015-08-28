package com.lovo.netCRM.ui.frame;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoImageLabel;
import com.lovo.netCRM.component.LovoPassWordTxt;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.service.imp.EmployeeServiceImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 登陆窗体
 * 开发日期:2012-10-16
 */
public class LoginFrame extends JFrame{
	/**用户名文本框*/
	private LovoTxt userTxt = new LovoTxt("用户名",100,150,this);
	/**密码文本框*/
	private LovoPassWordTxt pwdTxt = new LovoPassWordTxt("密码",100,200,this);
	
	public LoginFrame(){
		this.setLayout(null);
		LovoImageLabel crmImg = new LovoImageLabel(100,50,246,71,this);
		crmImg.setImage("image/logo_north.jpg");
		
		LovoButton lb = new LovoButton("登陆",230,250,this);
		lb.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			//点击事件,登录
			login();
		}});
		
		this.setSize(450,330);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//居中显示
		this.setLocationRelativeTo(null);
	}
	
	//--------------------
	/**
	 * 登陆方法
	 */
	private void login(){
		//视图层调用业务层login()方法当前登录用户;
		EmployeeBean loginEmp = new EmployeeServiceImp().login(userTxt.getText(), pwdTxt.getText());
		if(loginEmp != null) {
			//Window的dispose方法 释放由此 Window、其子组件及其拥有的所有子组件所使用的所有本机屏幕资源
			//就是销毁login窗口
			this.dispose();
			//传入用户实体对象
			new MainFrame(loginEmp);
		}
	}
}
