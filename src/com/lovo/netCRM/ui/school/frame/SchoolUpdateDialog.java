package com.lovo.netCRM.ui.school.frame;

import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.netCRM.component.LovoTxtArea;
import com.lovo.netCRM.dao.imp.SchoolDaoImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 学校信息修改对话框
 * 开发日期:2012-10-15
 */
public class SchoolUpdateDialog extends JDialog{
	/**学校主面板*/
	private SchoolPanel schoolPanel;
	/**学校id*/
	private int schoolId;
	/**学校名称标签*/
	private LovoLabel nameLabel = new LovoLabel("学校名称",50,40,this);
	/**所属城市*/
	private LovoLabel cityLabel = new LovoLabel("所属城市",320,40,this);;
	/**地址标签*/
	private LovoLabel addressLabel = new LovoLabel("学校地址",50,80,this);
	/**校长文本框*/
	private LovoTxt masterTxt = new LovoTxt("校    长",320,80,this);
	/**联系电话标签*/
	private LovoLabel titleLabel = new LovoLabel("联系电话",50,120,this);
	/**学生人数文本框*/
	private LovoTxt studentNumberTxt = new LovoTxt("学生人数",320,120,this);
	/**老师人数文本框*/
	private LovoTxt teacherNumberTxt = new LovoTxt("老师人数",50,160,this);
	/**ip地址文本框*/
	private LovoTxt ipTxt = new LovoTxt("ip地址",320,160,this);
	/**宽带流量文本框*/
	private LovoTxt netFluxTxt = new LovoTxt("宽带流量",50,200,this);
	/**状态标签*/
	private LovoLabel stateLabel = new LovoLabel("状    态",320,200,this);
	
	/**负责部门标签*/
	private LovoLabel deptLabel = new LovoLabel("负责部门",50,240,this);
	/**负责人*/
	private LovoLabel employeeLabel = new LovoLabel("负责人",320,240,this);
	/**录入时间*/
	private LovoLabel enterTimeLabel = new LovoLabel("录入时间",50,280,this);
	/**申请立项时间*/
	private LovoLabel applyTimeLabel = new LovoLabel("申请立项时间",320,280,this);
	/**立项批准时间*/
	private LovoLabel passTimeLabel = new LovoLabel("立项批准时间",50,320,this);
	/**说明文本域*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("说明",50,360,150,60,this);
	/**审批意见文本域*/
	private LovoTxtArea mindTxt = new LovoTxtArea("审批意见",320,360,150,60,this);
	
	
	public SchoolUpdateDialog(JFrame jf,int schoolId,SchoolPanel schoolPanel){
		super(jf,true);
		this.schoolId = schoolId;
		this.schoolPanel = schoolPanel;
		this.setLayout(null);
		this.setTitle("修改学校信息");
		
		this.init();
		
		this.setBounds(300, 100, 650, 550);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		this.initData(this.schoolId);
		this.descriptionTxt.setEditable(false);
		this.mindTxt.setEditable(false);
		
		LovoButton lbupdate = new LovoButton("修改",200,450,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = updateSchool(schoolId);
				if(isOk){
				SchoolUpdateDialog.this.dispose();
				}
			}});
		
		LovoButton lbcancel = new LovoButton("取消",400,450,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolUpdateDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * 初始化标签数据
	 * @param schoolId 学校ID
	 */
	private void initData(int schoolId){
		SchoolBean sch = (SchoolBean)new SchoolDaoImp().getObjectByID(schoolId);
		nameLabel.setText(sch.getName());
		cityLabel.setText(sch.getArea().getName());
		addressLabel.setText(sch.getAddress());
		masterTxt.setText(sch.getMaster());
		titleLabel.setText(sch.getPhone());
		studentNumberTxt.setText(sch.getStuNum()+"");
		teacherNumberTxt.setText(sch.getTeaNum()+"");
		ipTxt.setText(sch.getIPAddress());
		netFluxTxt.setText(sch.getFlow());
		stateLabel.setText(sch.getStatus());
		deptLabel.setText(sch.getEmp().getDept());
		employeeLabel.setText(sch.getEmp().getName());
		enterTimeLabel.setText(sch.getFoundTime().toString());
		descriptionTxt.setText(sch.getDescribe());
		mindTxt.setText(sch.getCheckNotic());
		if(sch.getProposeTime() == null){
			applyTimeLabel.setText("");
		}else
			applyTimeLabel.setText(sch.getProposeTime().toString());
		if(sch.getPermitTime() == null){
			passTimeLabel.setText("");
		} else
			passTimeLabel.setText(sch.getPermitTime().toString());
	}
	/**
	 * 修改学校
	 * @param schoolId 学校ID
	 * @param cityObj 城市对象
	 */
	private boolean updateSchool(int schoolId){
		//验证数据，验证失败返回false
		SchoolBean sch = new SchoolBean();
		//验证数据,验证失败返回false
		String error = "";
		if(masterTxt.getText() == null || masterTxt.getText().equals("")){
			error += "没校长,滚犊子\n";
		}if(studentNumberTxt.getText() == null || studentNumberTxt.getText().equals("")){
			error += "没学生,滚犊子\n";
		}
		if(teacherNumberTxt.getText() == null || teacherNumberTxt.getText().equals("")){
			error += "没老师,滚犊子\n";
		}if(ipTxt.getText() == null || ipTxt.getText().equals("")){
			error += "没IP,滚犊子\n";
		}
		if(netFluxTxt.getText() == null || netFluxTxt.getText().equals("")) {
			error += "没流量,滚犊子\n";
		}
		if(error.length() != 0) {
			JOptionPane.showMessageDialog(null, error);
			return false;
		} else{
			//封装实体
			sch.setMaster(masterTxt.getText());
			sch.setStuNum(Integer.parseInt(studentNumberTxt.getText()));
			sch.setTeaNum(Integer.parseInt(teacherNumberTxt.getText()));
			sch.setIPAddress(ipTxt.getText());
			sch.setFlow(netFluxTxt.getText());
		}
		
		//更新表格，显示修改结果
		new SchoolDaoImp().alterObject(schoolId,sch);
		this.schoolPanel.initData();
		return true;
	}
	

}
