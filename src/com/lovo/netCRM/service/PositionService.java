package com.lovo.netCRM.service;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/22.
 */
public interface PositionService {
    //取得所有部门信息
    public ArrayList<Object> getAllPositions();
    //添加部门信息
    public boolean addPosition();
    //修改部门信息
    public boolean alterPosition(int positionID);
}
