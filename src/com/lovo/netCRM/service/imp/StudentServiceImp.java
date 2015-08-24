package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.bean.RecallRecordBean;
import com.lovo.netCRM.bean.StudentBean;
import com.lovo.netCRM.service.StudentService;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public class StudentServiceImp implements StudentService{
    @Override
    public boolean addRecall(RecallRecordBean re) {
        return false;
    }

    @Override
    public boolean addStudent(StudentBean stu) {
        return false;
    }

    @Override
    public boolean alterStudentByID(int stuID) {
        return false;
    }

    @Override
    public boolean deletStudent(int stuID) {
        return false;
    }

    @Override
    public ArrayList<StudentBean> getAllStudents() {
        return null;
    }

    @Override
    public StudentBean getStudentByID(int stuID) {
        return null;
    }

    @Override
    public StudentBean getStudentByCon(String item, String value) {
        return null;
    }
}
