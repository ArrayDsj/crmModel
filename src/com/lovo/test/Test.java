package com.lovo.test;

import com.lovo.netCRM.ui.frame.LoginFrame;

import java.sql.SQLException;

public class Test{
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SQLException {
//		Connection con = ConnectionSQL.createConnectionSQL();
//		DatabaseMetaData md = con.getMetaData();
//		boolean batch = md.supportsBatchUpdates();
//		if(batch){
//			JOptionPane.showMessageDialog(null,"支持批量处理SQL语句");
//		}
		new LoginFrame();
		//new MainFrame(new EmployeeBean());
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UIManager
//							.setLookAndFeel(
//									"ch.randelshofer.quaqua.QuaquaLookAndFeel");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				JFrame w = new LoginFrame();
//				w.setBackground(Color.BLACK);
//			}
//		});
	}

}
