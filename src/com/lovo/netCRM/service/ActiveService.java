package com.lovo.netCRM.service;

import com.lovo.netCRM.bean.ActiveBean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public interface ActiveService {
    //��ӻ
    public boolean addActive( int schoolID, ActiveBean active);

    //ȡ�����еĻ��Ϣ
    public ArrayList<ActiveBean> getAllActivesByCon(int schoolId);
}
