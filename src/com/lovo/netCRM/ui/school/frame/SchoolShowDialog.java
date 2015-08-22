package com.lovo.netCRM.ui.school.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
 * @description 学校信息标签
 * 开发日期:2012-10-11
 */
public class SchoolShowDialog extends JDialog{

	/**学校id*/
	private int schoolId;
	/**学校沟通记录表格*/
	private LovoTable communicateTable;
	/**学校名称标签*/
	private LovoLabel nameLabel = new LovoLabel("学校名称",50,40,this);
	/**所属城市*/
	private LovoLabel cityLabel = new LovoLabel("所属城市",320,40,this);;
	/**地址标签*/
	private LovoLabel addressLabel = new LovoLabel("学校地址",50,80,this);
	/**校长标签*/
	private LovoLabel masterLabel = new LovoLabel("校长",320,80,this);
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
	private LovoLabel stateLabel = new LovoLabel("状态",320,200,this);
	
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
	
	public SchoolShowDialog(JFrame jf,int schoolId){
		super(jf,true);
		this.schoolId = schoolId;
		
		this.setLayout(null);
		this.setTitle("学校信息一览");
		
		this.init();
		
		this.setBounds(300, 10, 650, 700);
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
		
		/*沟通记录容器*/
		LovoTitlePanel jp = new LovoTitlePanel("沟通记录",80, 420, 500, 200,this);
		
		this.initTable(this.schoolId,jp);
		
		LovoButton lbcancel = new LovoButton("确定",280,640,this);
		lbcancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				SchoolShowDialog.this.dispose();
			}});
	}
	
	//----------------------
	
	/**
	 * 初始化标签数据
	 * @param schoolId 学校ID
	 */
	private void initData(int schoolId){
		
	}
	/**
	 * 初始化表格
	 * @param schoolId 学校ID
	 */
	private void initTable(int schoolId,JPanel jp){
		communicateTable = new LovoTable(jp,
				new String[]{"时间","校方联系人","职务","沟通人","沟通内容"},
				new String[]{},//学校实体属性名数组 new String[]{"time","schoolConnector"}
				"");//主键属性名 schoolId
		communicateTable.setSizeAndLocation(10, 20, 480, 170);
		
		//更新表格,插入List集合
		communicateTable.updateLovoTable(null);
	}
	

}
