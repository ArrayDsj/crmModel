package com.lovo.netCRM.bean;

/**
 * Created by CodeA on 2015/8/23.
 */
public class AreaBean {
    private int id;
    private String name;
    private SchoolBean school;

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

    public SchoolBean getSchool() {
        return school;
    }

    public void setSchool(SchoolBean school) {
        this.school = school;
    }
}
