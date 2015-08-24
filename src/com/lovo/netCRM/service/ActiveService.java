package com.lovo.netCRM.service;

import com.lovo.netCRM.bean.ActiveBean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public interface ActiveService {
    //添加活动
    public boolean addActive(ActiveBean active, int schoolID);

    //取得所有的活动信息
    public ArrayList<ActiveBean> getAllActives(int schoolId);
}
