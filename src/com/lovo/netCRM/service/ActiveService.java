package com.lovo.netCRM.service;

import com.lovo.netCRM.bean.ActiveBean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public interface ActiveService {
    //��ӻ
    public boolean addActive(ActiveBean active, int schoolID);

    //ȡ�����еĻ��Ϣ
    public ArrayList<ActiveBean> getAllActives(int schoolId);
}
