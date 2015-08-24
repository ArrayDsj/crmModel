package com.lovo.netCRM.service;

import com.lovo.netCRM.bean.RecallRecordBean;
import com.lovo.netCRM.bean.StudentBean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public interface StudentService {
    public boolean addRecall(RecallRecordBean re);

    public boolean addStudent(StudentBean stu);

    public boolean alterStudentByID(int stuID);

    public boolean deletStudent(int stuID);

    public ArrayList<StudentBean> getAllStudents();

    public StudentBean getStudentByID(int stuID);

    public StudentBean getStudentByCon(String item,String value);
}
