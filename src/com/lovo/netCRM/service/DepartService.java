package com.lovo.netCRM.service;

import com.lovo.netCRM.bean.DepartBean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/22.
 */
public interface DepartService {
    //ȡ�����в�����Ϣ
    public ArrayList<DepartBean> getAllDepts();
    //��Ӳ�����Ϣ
    public boolean addDept();
    //�޸Ĳ�����Ϣ
    public boolean alterDept(int departID);
}
