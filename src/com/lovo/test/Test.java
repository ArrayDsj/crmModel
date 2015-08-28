package com.lovo.test;

import com.lovo.netCRM.ui.frame.LoginFrame;

import javax.swing.*;
import java.awt.*;


public class Test{
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//new LoginFrame();
		//new MainFrame(new EmployeeBean());
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel(
									"ch.randelshofer.quaqua.QuaquaLookAndFeel");
				} catch (Exception e) {
					e.printStackTrace();
				}
				JFrame w = new LoginFrame();
				w.setBackground(Color.BLACK);
			}
		});
	}

}
