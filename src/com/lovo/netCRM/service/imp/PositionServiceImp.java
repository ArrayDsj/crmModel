package com.lovo.netCRM.service.imp;

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
    public boolean addPosition() {
        return false;
    }

    @Override
    public boolean alterPosition(int positionID) {
        return false;
    }
}
