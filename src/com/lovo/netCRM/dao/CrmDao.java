package com.lovo.netCRM.dao;

import com.lovo.netCRM.bean.EmployeeBean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/21.
 */
public interface CrmDao {
    //��¼����,����һ���û���Ϣ,��ΪҪʹ�õ�Ȩ�޵���Ϣ
    public EmployeeBean login(String loginName, String passWord);

    //��ʼ�����ݲ���,����¼����ʾ����Ա����Ϣ
    public ArrayList<Object> getAllObjects();

    //ɾ��Ա����Ϣ
    public boolean deleteObject(int ObjectID);
}
