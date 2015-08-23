package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.bean.PositionBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.dao.imp.PositionDaoImp;
import com.lovo.netCRM.service.PositionService;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/22.
 */
public class PositionServiceImp implements PositionService {
    @Override
    public ArrayList<Object> getAllPositions() {
        CrmDao positions = new PositionDaoImp();
        return positions.getAllObjects();
    }

    @Override
    public boolean addPosition(Object pos) {
        CrmDao positions = new PositionDaoImp();
        return positions.addObject(pos);
    }

    @Override
    public boolean alterPosition(Object pos) {
        return false;
    }

    @Override
    public PositionBean getPosByID(int posID) {
        CrmDao positions = new PositionDaoImp();
        PositionBean thisPos = (PositionBean)positions.getObjectByID(posID);
        return thisPos;
    }
}
