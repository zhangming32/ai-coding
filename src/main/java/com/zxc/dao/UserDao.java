package com.zxc.dao;

import com.zxc.model.Student;
import com.zxc.model.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    Student selectStuById(@Param("id") int id);

    Teacher selectTeaById(@Param("id") int id);

    void updateStuPass(Student student);

    void updateTeaPass(Teacher teacher);

    List<Teacher> queryAllTeacher();
}