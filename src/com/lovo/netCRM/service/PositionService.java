package com.lovo.netCRM.service;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/22.
 */
public interface PositionService {
    //ȡ�����в�����Ϣ
    public ArrayList<Object> getAllPositions();
    //��Ӳ�����Ϣ
    public boolean addPosition();
    //�޸Ĳ�����Ϣ
    public boolean alterPosition(int positionID);
}
