package com.lovo.netCRM.service;

import com.lovo.netCRM.bean.PositionBean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/22.
 */
public interface PositionService {
    //ȡ�����в�����Ϣ
    public ArrayList<Object> getAllPositions();
    //��Ӳ�����Ϣ
    public boolean addPosition(Object pos);
    //�޸Ĳ�����Ϣ
    public boolean alterPosition(Object pos);

    //��ID����ְλ��Ϣ
    public PositionBean getPosByID(int posID);
}
