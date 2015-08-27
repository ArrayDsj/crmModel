package com.lovo.netCRM.ui.count.frame;

import com.lovo.netCRM.bean.AreaBean;
import com.lovo.netCRM.component.*;
import com.lovo.netCRM.dao.imp.SchoolCountDaoImp;
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
 * @description 学校统计面板
 * 开发日期:2012-10-14
 */
public class SchoolCountPanel extends JPanel{
	/**学校表格组件*/
	private LovoTable schoolCountTable;
	/**城市id*/
	private int cityId;
	
	/**城市列表框*/
	private LovoList cityList = new LovoList(20,90,150,300,this){
		public void onchange(Object cityObj){
			cityId = getCityId(cityObj);
			showAll(cityId);
		}
	};
	
	public SchoolCountPanel(){
		this.setLayout(null);
		this.init();
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		new LovoTitleLabel("学 校 统 计",this);
		this.initTable();
		this.initButton();
		
		LovoTitlePanel jp = new LovoTitlePanel("按录入时间查询",400, 430, 320, 180,this);
		
		final LovoDate startDate = new LovoDate("起始日期",20,30,jp);
		
		final LovoDate endDate = new LovoDate("结束日期",20,80,jp);
		
		LovoButton lb = new LovoButton("查找",180,130,jp);
		lb.setSize(60, 20);
		
		lb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(startDate.getDate().getTime() < endDate.getDate().getTime()){
					findSchoolCount(cityId, startDate.getText(), endDate.getText());
				}else
					JOptionPane.showMessageDialog(null,"开始时间不能大于结束时间");


			}
		});
		this.initData();
	}
	
	public void initData(){
		if(cityId==0){
			this.initList();
		}
		else{
			showAll(cityId);
		}
	}
	
	/**
	 * 初始化按钮
	 *
	 */
	private void initButton() {
		LovoButton lbadd = new LovoButton("所有统计信息",200,500,this);
		lbadd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(cityId == 0){
					JOptionPane.showMessageDialog(null,"请选择城市");
					return;
				}
				showAll(cityId);
			}});
		
		
	
	}
	
	//--------------------------
	/**
	 * 初始化表格
	 */
	private void initTable() {
		schoolCountTable = new LovoTable(this,
				new String[]{"学校名称","网脉班级数量","会员数量","非会员数量"},
				new String[]{"schoolName","classesNum","vipNum","notVipNum"},//学校统计实体属性名数组 new String[]{"schoolName","netClassNumber"}
				"");//主键属性名 schoolId
		schoolCountTable.setSizeAndLocation(180, 90, 550, 300);
		
		
	}
	/**
	 * 初始化城市列表框
	 *
	 */
	private void initList() {
		ArrayList<Object> allAreas = new AreaServiceImp().getAllAreas();
		cityList.setList(allAreas);
	}
	
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
	 * 显示城市学校所有统计信息
	 * @param cityId 城市id
	 */
	private void showAll(int cityId){
		ArrayList<Object> counts = new SchoolCountDaoImp().getCounts(cityId);
//		更新表格,插入List集合
		schoolCountTable.updateLovoTable(counts);
	}
	/**
	 * 按录入时间查询城市学校统计信息
	 * @param cityId 城市id
	 * @param startTime 起始日期
	 * @param endTime 结束日期
	 */
	private void findSchoolCount(int cityId,String startTime,String endTime){

		ArrayList<Object> timeCounts = new SchoolCountDaoImp().getCounts(cityId,startTime,endTime);
//		更新表格,插入List集合
		schoolCountTable.updateLovoTable(timeCounts);
	}
}
