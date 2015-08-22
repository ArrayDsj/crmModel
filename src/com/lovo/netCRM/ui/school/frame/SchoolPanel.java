package com.lovo.netCRM.ui.school.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoList;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;

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
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"请选择行");
//					return;
//				}
				
				new SchoolShowDialog(jf,key);
			}});
		
		LovoButton lbupdate = new LovoButton("修改学校信息",500,500,this);
		lbupdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"请选择行");
//					return;
//				}
				
				new SchoolUpdateDialog(jf,key,SchoolPanel.this);
			}});
		
		
		LovoButton lbask = new LovoButton("申请立项",100,580,this);
		lbask.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"请选择行");
//					return;
//				}
				
				apply(key,schoolTable.getSelectColumn(3));
			}});
		
		LovoButton lbcheck = new LovoButton("审核",300,580,this);
		lbcheck.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"请选择行");
//					return;
//				}
				
				checkSchool(key,schoolTable.getSelectColumn(3));
			}});
		
		LovoButton lbAddSpeak = new LovoButton("添加沟通记录",500,580,this);
		lbAddSpeak.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"请选择行");
//					return;
//				}
				
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
		
		return 0;
	}
	/**
	 * 初始化表格
	 */
	private void initTable() {
		schoolTable = new LovoTable(this,
				new String[]{"学校名称","校长","录入时间","状态"},
				new String[]{},//学校实体属性名数组 new String[]{"schoolName","schoolMaster"}
				"");//主键属性名 schoolId
		schoolTable.setSizeAndLocation(180, 90, 550, 300);
	
	}
	/**
	 * 初始化列表框
	 *
	 */
	private void initList() {
		cityList.setList(new ArrayList());
	}
	/**
	 * 显示城市对应的学校
	 * @param cityObj 城市对象
	 */
	private void showSchool(int cityId){
		
//		更新表格,插入List集合
		schoolTable.updateLovoTable(null);
	}
	/**
	 * 申请立项
	 * @param schoolId 学校ID
	 * @param schoolState 学校状态
	 * @param cityObj　城市对象
	 */
	private void apply(int schoolId,String schoolState){
		//验证状态是否为“接洽中”
		
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
		
		new SchoolCheckDialog(jf,schoolId,this);
	}
}
