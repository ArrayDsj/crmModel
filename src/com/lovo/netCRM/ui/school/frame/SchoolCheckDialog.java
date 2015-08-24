package com.lovo.netCRM.ui.school.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxtArea;
import com.lovo.netCRM.dao.imp.AreaDaoImp;
import com.lovo.netCRM.dao.imp.EmployeeDaoImp;
import com.lovo.netCRM.dao.imp.SchoolDaoImp;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 审核对话框
 * 开发日期:2012-10-12
 */
public class SchoolCheckDialog extends JDialog{
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
	/**校长标签*/
	private LovoLabel masterLabel = new LovoLabel("校    长",320,80,this);
	/**联系电话标签*/
	private LovoLabel phoneLabel = new LovoLabel("联系电话",50,120,this);
	/**学生人数标签*/
	private LovoLabel studentNumberLabel = new LovoLabel("学生人数",320,120,this);
	/**老师人数标签*/
	private LovoLabel teacherNumberLabel = new LovoLabel("老师人数",50,160,this);
	/**ip地址标签*/
	private LovoLabel ipLabel = new LovoLabel("ip地址",320,160,this);
	/**宽带流量标签*/
	private LovoLabel netFluxLabel = new LovoLabel("宽带流量",50,200,this);
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
	/**说明文本域*/
	private LovoTxtArea descriptionTxt = new LovoTxtArea("说明",50,320,300,60,this);
	/**审批意见文本域*/
	private LovoTxtArea mindTxt = new LovoTxtArea("审批意见",50,400,300,100,this);
	
	public SchoolCheckDialog(JFrame jf, int schoolId,SchoolPanel schoolPanel){
		super(jf,true);
		this.schoolPanel = schoolPanel;
		this.schoolId = schoolId;
		this.setLayout(null);
		this.setTitle("审核学校");
		
		this.init();
		
		this.setBounds(300, 50, 650, 600);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		this.initData(this.schoolId);
		this.descriptionTxt.setEnabled(false);


		LovoButton lbpass = new LovoButton("批准立项",100,520,this);
		lbpass.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				passSchool(schoolId);
				SchoolCheckDialog.this.dispose();
			}});
		LovoButton lbback = new LovoButton("驳回立项",250,520,this);
		lbback.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				backSchool(schoolId);
				SchoolCheckDialog.this.dispose();
			}});
		
		LovoButton lbcancel = new LovoButton("取消",400,520,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolCheckDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * 初始化标签数据
	 *	@param schoolId 学校ID
	 */
	private void initData(int schoolId){
		SchoolBean sch = (SchoolBean)new SchoolDaoImp().getObjectByID(schoolId);
		nameLabel.setText(sch.getName());
		cityLabel.setText(sch.getArea().getName());
		addressLabel.setText(sch.getAddress());
		masterLabel.setText(sch.getMaster());
		phoneLabel.setText(sch.getPhone());
		studentNumberLabel.setText(sch.getStuNum()+"");
		teacherNumberLabel.setText(sch.getTeaNum()+"");
		ipLabel.setText(sch.getIPAddress());
		netFluxLabel.setText(sch.getFlow());
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

	}
	/**
	 * 批准立项
	 * @param schoolId 学校ID
	 * @param cityObj 城市对象
	 */
	private void passSchool(int schoolId){
		//验证数据,验证失败返回false
		String error = "";
		if(descriptionTxt.getText() == null || descriptionTxt.getText().equals("")){
			error += "没说明,滚犊子\n";
		}if(error.length() != 0) {
			JOptionPane.showMessageDialog(null, error);
			return ;
		} else{
			new SchoolDaoImp().alterSchoolByDescripOn(schoolId, descriptionTxt.getText());
		}
		//更新表格，显示修改结果
		this.schoolPanel.initData();
	}
	
	/**
	 * 驳回立项
	 * @param schoolId 学校ID
	 * @param cityObj 城市对象
	 */
	private void backSchool(int schoolId){
		String error = "";
		if(descriptionTxt.getText() == null || descriptionTxt.getText().equals("")){
			error += "没说明,滚犊子\n";
		}if(error.length() != 0) {
			JOptionPane.showMessageDialog(null, error);
			return ;
		} else{
			new SchoolDaoImp().alterSchoolByDescripOff(schoolId,descriptionTxt.getText());
		}
		
		//更新表格，显示修改结果
		this.schoolPanel.initData();
	}
	
}
