package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.dao.imp.AreaDaoImp;
import com.lovo.netCRM.service.AreaService;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public class AreaServiceImp implements AreaService{
    @Override
    public ArrayList<Object> getAllAreas() {
        return new AreaDaoImp().getAllObjects();
    }

    @Override
    public Object getArea(int areaID) {
        return new AreaDaoImp().getObjectByID(areaID);
    }
}
