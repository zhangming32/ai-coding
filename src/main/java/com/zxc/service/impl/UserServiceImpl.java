package com.zxc.service.impl;

import com.zxc.dao.UserDao;
import com.zxc.model.Student;
import com.zxc.model.Teacher;
import com.zxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int checkAccount(int id, String pass) {
        if (Integer.toString(id).charAt(4) == '1') {
            Teacher teacher = userDao.selectTeaById(id);
            if (teacher != null && teacher.getTeaPass().equals(pass)) {
                return 2;
            }
        } else {
            Student student = userDao.selectStuById(id);
            if (student != null && student.getStuPass().equals(pass)) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public String getStuNameById(int id) {
        Student student = userDao.selectStuById(id);
        return student != null ? student.getStuName() : null;
    }

    @Override
    public String getTeaNameById(int id) {
        Teacher teacher = userDao.selectTeaById(id);
        return teacher != null ? teacher.getTeaName() : null;
    }

    @Override
    public Student getStuInfoById(int id) {
        return userDao.selectStuById(id);
    }

    @Override
    public Teacher getTeaInfoById(int id) {
        return userDao.selectTeaById(id);
    }

    @Override
    public void changeStuPass(Student student) {
        userDao.updateStuPass(student);
    }

    @Override
    public void changeTeaPass(Teacher teacher) {
        userDao.updateTeaPass(teacher);
    }

    @Override
    public List<Teacher> queryAllTeacher() {
        return userDao.queryAllTeacher();
    }
}