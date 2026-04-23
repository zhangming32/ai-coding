package com.zxc.service;

import com.zxc.model.Student;
import com.zxc.model.Teacher;

import java.util.List;

public interface UserService {
    int checkAccount(int id, String pass);

    String getStuNameById(int id);

    String getTeaNameById(int id);

    Student getStuInfoById(int id);

    Teacher getTeaInfoById(int id);

    void changeStuPass(Student student);

    void changeTeaPass(Teacher teacher);

    List<Teacher> queryAllTeacher();
}