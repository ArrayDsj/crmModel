package com.lovo.netCRM.dao;

import com.lovo.netCRM.bean.EmployeeBean;

/**
 * Created by CodeA on 2015/8/21.
 */
public interface EmployeeDAO {
    //��¼����,����һ���û���Ϣ,��ΪҪʹ�õ�Ȩ�޵���Ϣ
    public EmployeeBean login(String loginName, String passWord);
}
