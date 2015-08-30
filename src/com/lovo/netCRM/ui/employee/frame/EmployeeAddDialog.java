package com.lovo.netCRM.ui.employee.frame;

import com.lovo.netCRM.bean.DepartBean;
import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.bean.PositionBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.service.imp.DepartServiceImp;
import com.lovo.netCRM.service.imp.EmployeeServiceImp;
import com.lovo.netCRM.service.imp.PositionServiceImp;
import com.lovo.netCRM.ui.frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 添加员工对话框
 * 开发日期:2012-10-6
 */
public class EmployeeAddDialog extends JDialog{

	/**姓名文本框*/
	private LovoTxt nameTxt = new LovoTxt("姓    名",40,50,this);
	/**性别单选钮*/
	private LovoRadioButton sexTxt = new LovoRadioButton("性    别",new String[]{"男","女"},320,50,this);
	/**出生年月*/
	private LovoDate birthdayTxt = new LovoDate("出生年月",40,100,this);
	/**文化程度*/
	private LovoComboBox<String> eduTxt = new LovoComboBox<String>("文化程度",new String[]{"高中","大专","本科","硕士"},320,100,this);
	/**所属专业文本框*/
	private LovoTxt specialityTxt = new LovoTxt("所属专业",40,150,this);

	/*新建员工用户名标签*/
	private JLabel nameJLabel = new JLabel();
	/*新建员工用户名文本框*/
	private JTextField loginName;
	/*新建员工密码标签*/
	private JLabel passJlabel = new JLabel();
	/*新建员工密码文本框*/
	private JTextField loginPassWord ;



	/**联系方式文本框*/
	private LovoTxt phoneTxt = new LovoTxt("联系方式",320,150,this);
	/**家庭住址文本框*/
	private LovoTxt addressTxt = new LovoTxt("家庭住址",40,200,this);
	/**政治面貌*/
	private LovoComboBox<String> polityFaceTxt = new LovoComboBox<String>("政治面貌",new String[]{"团员","党员","民主党派","无党派人士"},320,200,this);
	/**所在部门*/
	private LovoComboBox deptTxt;
	/**工作职位*/
	private LovoComboBox jobTxt;
	/**头像*/
	//LovoFileChooser(java.awt.Container con, java.lang.String dirPath)
	//String path = fc.getFilePath() 得到图片相对路劲
	private LovoFileChooser faceTxt = new LovoFileChooser(this,"face");
	/**员工主面板*/
	private EmployeePanel emPanel;

	/*主窗体*/
	private JFrame jf;


	public EmployeeAddDialog(JFrame jf,EmployeePanel emPanel){
		super(jf,true);
		this.jf = jf;
		this.emPanel = emPanel;
		this.setLayout(null);
		this.setTitle("添加新员工");
		
		this.init();
		
		this.setBounds(300, 100, 700, 400);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init(){
		this.initComboBox();

		faceTxt.setBounds(580, 70, 100, 150);

		LovoButton lbadd = new LovoButton("添加",150,310,this);
		lbadd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addEmployee();
				if (isOk) {
					EmployeeAddDialog.this.dispose();
				}
			}
		});
		
		LovoButton lbcancel = new LovoButton("取消",400,310,this);
		lbcancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				EmployeeAddDialog.this.dispose();
			}
		});

		MainFrame mainFrame = (MainFrame)jf;

		//设置标签属性
		nameJLabel.setText("用户名");
		nameJLabel.setBounds(40, 275, 80, 20);
		this.add(nameJLabel);
		nameJLabel.setVisible(false);
		passJlabel.setText("密码");
		passJlabel.setBounds(320, 275, 80, 20);
		this.add(passJlabel);
		passJlabel.setVisible(false);

		//设置文本框属性
		loginName = new JTextField();
		loginName.setBounds(140, 275, 120, 20);
		this.add(loginName);
		loginName.setVisible(false);

		loginPassWord = new JTextField();
		loginPassWord.setBounds(420, 275, 120, 20);
		this.add(loginPassWord);
		loginPassWord.setVisible(false);

		if(mainFrame.getUserObj().getPos().isBackRight()){
			passJlabel.setVisible(true);
			nameJLabel.setVisible(true);
			loginName.setVisible(true);
			loginPassWord.setVisible(true);
		}


	}
	
	//--------------------------------------------
	/**
	 * 初始化部门和职位下拉框
	 *
	 */
	private void initComboBox(){
		//添加部门List集合
		ArrayList<Object> departs = new DepartServiceImp().getAllDepts();
		ArrayList<String> departNames = new ArrayList<String>();
		for(Object depat: departs){
			DepartBean d = (DepartBean)depat;
			String deptName = d.getDepartName();
			departNames.add(deptName);
		}
		this.deptTxt = new LovoComboBox("所属部门",departNames,40,250,this);
		//添加职位List集合
		ArrayList<Object> positions = new PositionServiceImp().getAllPositions();
		ArrayList<String> positionNames = new ArrayList<String>();
		for(Object pos: positions){
			PositionBean d = (PositionBean)pos;
			String posName = d.getName();
			positionNames.add(posName);
		}
		this.jobTxt = new LovoComboBox("工作职位",positionNames,320,250,this);
	
	}
	/**
	 * 添加操作
	 *
	 */
	private boolean addEmployee(){
		EmployeeBean newEmp = new EmployeeBean();
		String str = "";
		//验证数据
		if(!this.nameTxt.getText().matches("[a-zA-Z\\u4e00-\\u9fa5]{2,20}")){
			str += "用户名必须为二位以上字母或汉字\n";
		}
		if(!this.phoneTxt.getText().matches("^[1][0-9]{10}$")){
			str += "电话号码格式不正确\n";
		}
		if(!specialityTxt.getText().matches("[\\u4e00-\\u9fa5]{2,20}")){
			str += "专业格式正确\n";
		}
		if(!addressTxt.getText().matches("[a-zA-Z0-9\\u4e00-\\u9fa5]{2,20}")){
			str += "地址格式不正确\n";
		}
		if(birthdayTxt.getText() == null){
			str += "出生日期不能为空\n";
		}
		if(birthdayTxt.getText() == null){
			str += "出生日期不能为空\n";
		}
		if(str.length() != 0){
			JOptionPane.showMessageDialog(null, str);
			return false;
		}else{
			newEmp.setName(nameTxt.getText());
			newEmp.setSex(sexTxt.getItem().toString());
			newEmp.setBirthday(birthdayTxt.getDate());
			newEmp.setPhone(phoneTxt.getText());
			newEmp.setSpeciality(specialityTxt.getText());
			newEmp.setEdu(eduTxt.getItem().toString());
			newEmp.setAddress(addressTxt.getText());
			newEmp.setPolity(polityFaceTxt.getItem().toString());
			newEmp.setDept(deptTxt.getItem().toString());
			newEmp.setPosition(jobTxt.getItem().toString());
			newEmp.setLoginName(loginName.getText());
			newEmp.setPassWord(loginPassWord.getText());
			//设置图片
			if(faceTxt.getFilePath() == null){
				newEmp.setHeadFile("face/4.jpg");
			}else
				newEmp.setHeadFile("face/"+faceTxt.getFilePath());
			//状态status  1为true 在职
			newEmp.setStatus(true);
			//以当前时间作为入职时间
			newEmp.setHireDay(new Date());
		}
		//完成添加操作
		boolean result = new EmployeeServiceImp().addStaff(0,newEmp);
//		更新数据,显示添加结果
		if(result){
			return true;
		}
		return false;
	}
}
