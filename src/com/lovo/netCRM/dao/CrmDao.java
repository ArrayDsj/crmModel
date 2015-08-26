package com.lovo.netCRM.dao;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/21.
 */
public interface CrmDao {

    //��ѯ���ж�����Ϣ
    public ArrayList<Object> getAllObjects();

    //ɾ����Ϣ
    public boolean deleteObject(int objectID);

    //��ID������Ϣ
    public Object getObjectByID(int objectID);

    //�޸�����
    public boolean alterObject(int objectID ,Object object);

    //�������
    public boolean addObject(int objectID ,Object object);

    //��������������
    public ArrayList<Object> getObjectByCon(String item,String value);

    //�������ֲ��Ҷ���
    public Object getObjectByName(String name);
}
