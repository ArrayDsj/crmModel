package com.lovo.netCRM.bean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/23.
 */
public class AreaBean {
    private int id;
    private String name;
    private ArrayList<SchoolBean> school;

    public AreaBean() {
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

    public ArrayList<SchoolBean> getSchool() {
        return school;
    }

    public void setSchool(ArrayList<SchoolBean> school) {
        this.school = school;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.name;
    }

}
