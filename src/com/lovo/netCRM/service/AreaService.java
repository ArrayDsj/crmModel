package com.lovo.netCRM.service;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public interface AreaService {
    public ArrayList<Object> getAllAreas();

    public Object getArea(int areaID);

    public Object getAreaByName(String name);
}
