package com.lovo.netCRM.ui.schoolActive.frame;

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
 * @description 学校活动面板
 * 开发日期:2012-10-6
 */
public class SchoolActivePanel extends JPanel{
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
	public SchoolActivePanel(JFrame jf){
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		new LovoTitleLabel("学 校 活 动",this);
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
		LovoButton lbadd = new LovoButton("添加新活动",200,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				new SchoolActiveAddDialog(SchoolActivePanel.this,jf,key);
			}});
		
		
		LovoButton lbshow = new LovoButton("查看活动信息",400,500,this);
		lbshow.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = schoolTable.getKey();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				//schoolTable.getSelectColumn(0) 意思是选正学校列表中的第一个列,就是学校名字
				new SchoolActiveShowDialog(jf,schoolTable.getSelectColumn(0),key);

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
				new String[]{"学校名称","校长","录入时间","联系电话"},
				//学校实体属性名数组 new String[]{"schoolName","schoolMaster"}
				new String[]{"name","master","inTime","phone"},
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
	 *
	 */
	private void showSchool(int cityId){
		ArrayList<SchoolBean> schools = new ArrayList<SchoolBean>();
		schools = new SchoolDaoImp().getSchoolByAreaID(cityId);
		//更新表格,插入List集合
		schoolTable.updateLovoTable(schools);
	}
}
