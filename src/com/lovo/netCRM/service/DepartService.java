package com.lovo.netCRM.service;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/22.
 */
public interface DepartService {
    //ȡ�����в�����Ϣ
    public ArrayList<Object> getAllDepts();
    //��Ӳ�����Ϣ
    public boolean addDept();
    //�޸Ĳ�����Ϣ
    public boolean alterDept(int departID);
}
