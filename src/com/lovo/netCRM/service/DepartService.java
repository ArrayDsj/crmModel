package com.lovo.netCRM.service;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/22.
 */
public interface DepartService {
    //ȡ�����в�����Ϣ
    public ArrayList<Object> getAllDepts();
    //��Ӳ�����Ϣ
    public boolean addDept(Object newDept);
    //�޸Ĳ�����Ϣ
    public boolean alterDept(Object willUpdateDept);

    //��ID���Ҳ�����Ϣ
    public Object getDeptByID(int departID);
}
