package com.lovo.netCRM.bean;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by CodeA on 2015/8/23.
 */
public class SchoolBean {
    private int id;
    private String name;
    private String master;
    private String phone;
    private int stuNum;
    private int teaNum;
    private String address;
    private String IPAddress;
    private String flow;
    private String describe;
    private String status;
    //活动录入时间
    private Date inTime;
    //审批意见
    private String checkNotic;
    private Date foundTime;
    private Date proposeTime;
    private Date permitTime;
    private ArrayList<ActiveBean> active;
    private AreaBean area;
    private ArrayList<ConnectRecordBean> connectRecord;
    private EmployeeBean emp;
    private ArrayList<ClassesBean> classes;

    public ArrayList<ClassesBean> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<ClassesBean> classes) {
        this.classes = classes;
    }

    public SchoolBean() {
    }

    public String getCheckNotic() {
        return checkNotic;
    }

    public void setCheckNotic(String checkNotic) {
        this.checkNotic = checkNotic;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStuNum() {
        return stuNum;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
    }

    public int getTeaNum() {
        return teaNum;
    }

    public void setTeaNum(int teaNum) {
        this.teaNum = teaNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFoundTime() {
        return foundTime;
    }

    public void setFoundTime(Date foundTime) {
        this.foundTime = foundTime;
    }

    public Date getProposeTime() {
        return proposeTime;
    }

    public void setProposeTime(Date proposeTime) {
        this.proposeTime = proposeTime;
    }

    public Date getPermitTime() {
        return permitTime;
    }

    public void setPermitTime(Date permitTime) {
        this.permitTime = permitTime;
    }

    public ArrayList<ActiveBean> getActive() {
        return active;
    }

    public void setActive(ArrayList<ActiveBean> active) {
        this.active = active;
    }

    public AreaBean getArea() {
        return area;
    }

    public void setArea(AreaBean area) {
        this.area = area;
    }

    public ArrayList<ConnectRecordBean> getConnectRecord() {
        return connectRecord;
    }

    public void setConnectRecord(ArrayList<ConnectRecordBean> connectRecord) {
        this.connectRecord = connectRecord;
    }

    public EmployeeBean getEmp() {
        return emp;
    }

    public void setEmp(EmployeeBean emp) {
        this.emp = emp;
    }

    public String toString(){
        return name;
    }
}
