package com.lovo.netCRM.service;

import com.lovo.netCRM.bean.PositionBean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/22.
 */
public interface PositionService {
    //取得所有部门信息
    public ArrayList<Object> getAllPositions();
    //添加部门信息
    public boolean addPosition(Object pos);
    //修改部门信息
    public boolean alterPosition(Object pos);

    //按ID查找职位信息
    public PositionBean getPosByID(int posID);
}
