package com.lovo.netCRM.dao;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/21.
 */
public interface CrmDao {

    //查询所有对象信息
    public ArrayList<Object> getAllObjects();

    //删除信息
    public boolean deleteObject(int objectID);

    //按ID查找信息
    public Object getObjectByID(int objectID);

    //修改数据
    public boolean alterObject(int objectID ,Object object);

    //添加数据
    public boolean addObject(int objectID ,Object object);

    //按条件查找数据
    public ArrayList<Object> getObjectByCon(String item,String value);

    //根据名字查找对象
    public Object getObjectByName(String name);
}
