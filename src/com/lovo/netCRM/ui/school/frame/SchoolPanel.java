package com.lovo.netCRM.ui.school.frame;

import com.lovo.netCRM.bean.AreaBean;
import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoList;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
import com.lovo.netCRM.dao.imp.SchoolDaoImp;
import com.lovo.netCRM.service.imp.AreaServiceImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 学校显示面板
 * 开发日期:2012-10-11
 */
public class SchoolPanel extends JPanel{
	/**学校表格组件*/
	private LovoTable schoolTable;
	/**窗体对象*/
	private JFrame jf;
	/**城市id*/
	private  int cityId;
	/**城市列表框*/
	private LovoList cityList = new LovoList(20,90,150,300,this){
		public void onchange(Object t){
			cityId = getCityId(t);
			showSchool(cityId);
		}
	};
	public SchoolPanel(JFrame jf){
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		new LovoTitleLabel("学 校 管 理",this);
		this.initTable();
		this.initButton();
		this.initData();
	}
	/**
	 * 初始化数据
	 */
	public void initData(){
		if(cityId==0){
			this.initList();
		}
		else{
			showSchool(cityId);
		}
	}
	
	/**
	 * 初始化按钮
	 *
	 */
	private void initButton() {
		LovoButton lbadd = new LovoButton("录入新学校",100,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				new SchoolAddDialog(jf,SchoolPanel.this);
			}});
		
		
		LovoButton lbshow = new LovoButton("查看学校信息",300,500,this);
		lbshow.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null, "请选择行");
					return;
				}
				
				new SchoolShowDialog(jf,key);
			}});
		
		LovoButton lbupdate = new LovoButton("修改学校信息",500,500,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				
				new SchoolUpdateDialog(jf,key,SchoolPanel.this);
			}});
		
		
		LovoButton lbask = new LovoButton("申请立项",100,580,this);
		lbask.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				
				apply(key,schoolTable.getSelectColumn(3));
			}});
		
		LovoButton lbcheck = new LovoButton("审核",300,580,this);
		lbcheck.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				
				checkSchool(key,schoolTable.getSelectColumn(3));
			}});
		
		LovoButton lbAddSpeak = new LovoButton("添加沟通记录",500,580,this);
		lbAddSpeak.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				
				new SchoolCommunicateAddDialog(jf,key);
			}});
	}
	
	//--------------------------
	/**
	 * 根据城市对象，得到城市id
	 * @param cityObj 城市对象
	 * @return
	 */
	private int getCityId(Object cityObj){
		if(cityObj instanceof AreaBean){
			AreaBean area = (AreaBean)cityObj;
			return area.getId();
		}
		return 0;
	}
	/**
	 * 初始化表格
	 */
	private void initTable() {
		schoolTable = new LovoTable(this,
				new String[]{"学校名称","校长","录入时间","状态"},
				new String[]{"name","master","foundTime","status"},//学校实体属性名数组 new String[]{"schoolName","schoolMaster"}
				"id");//主键属性名 schoolId
		schoolTable.setSizeAndLocation(180, 90, 550, 300);
	}
	/**
	 * 初始化列表框
	 *
	 */
	private void initList() {
		//查找所有地区
		ArrayList<Object> allAreas = new AreaServiceImp().getAllAreas();
		cityList.setList(allAreas);
	}
	/**
	 * 显示城市对应的学校
	 * @param cityObj 城市对象
	 */
	private void showSchool(int cityId){
		ArrayList<SchoolBean> schools = new ArrayList<SchoolBean>();
		schools = new SchoolDaoImp().getSchoolByAreaID(cityId);
		//更新表格,插入List集合
		schoolTable.updateLovoTable(schools);
	}
	/**
	 * 申请立项
	 * @param schoolId 学校ID
	 * @param schoolState 学校状态
	 * @param cityObj　城市对象
	 */
	private void apply(int schoolId,String schoolState){
		//验证状态是否为“接洽中”
		//SchoolBean sch = (SchoolBean)new SchoolDaoImp().getObjectByID(schoolId);
		if(schoolState.equals("接洽中")){
			new SchoolDaoImp().alterSchoolByStatus(schoolId, "待审");
			JDialog ShowMessage = new JDialog(jf,"申请立项");
			ShowMessage.setBounds(450, 250, 300, 200);
			JLabel t = new JLabel();
			t.setText(schoolTable.getSelectColumn(0) + "申请立项成功,等待审核");
			ShowMessage.add(t);
			ShowMessage.setVisible(true);
		}
		//显示申请立项结果
		this.initData();
	}
	/**
	 * 审核
	 * @param schoolId 学校ID
	 * @param schoolState 学校状态
	 * @param cityObj 城市对象
	 */
	private void checkSchool(int schoolId,String schoolState){
//		验证状态是否为“待审”或“审核未通过”
		if(schoolState.equals("待审") || schoolState.equals("审核未通过")){
			new SchoolCheckDialog(jf,schoolId,this);
		}
	}
}
