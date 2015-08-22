package com.lovo.test;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.ui.frame.MainFrame;


public class Test{
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//new LoginFrame();
		new MainFrame(new EmployeeBean());
	}

}
