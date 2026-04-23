package com.zxc.service;

import com.zxc.model.Course;
import com.zxc.model.Institution;
import com.zxc.model.Student;

import java.util.List;

public interface CourseService {
    List<Course> queryAllById(int id);

    List<String> queryInsNameByCourse(int id);

    List<Institution> queryAllIns();

    int insertCourse(String name, String num, int teaid);

    void insertInsLimit(String det, int classId);

    Course queryInfoById(int id);

    List<Integer> selectCourseLimit(int classId);

    int updateCourse(String name, String num, int teaid);

    void updateInsLimit(String det, int classId);

    void deleteCourse(int id);

    List<Student> queryStuByCourseId(int id);

    void updateScore(int classId, int stuId, int score);

    List<Student> queryStuByStuId(int classid, int stuid);

    List<Course> queryAllCourse(int stuid);

    Course queryCourse(int id);

    void chooseSuccess(int classId, int stuId);

    boolean checkStuIns(int classId, int stuId);

    void deleteCourseChoose(int stuId, int classId);

    List<Course> queryStuCourse(int stuId);

    List<Course> queryAllByInsId(int id);
}