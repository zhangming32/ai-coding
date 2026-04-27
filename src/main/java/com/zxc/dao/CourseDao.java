package com.zxc.dao;

import com.zxc.model.Course;
import com.zxc.model.Course_choose;
import com.zxc.model.Institution;
import com.zxc.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao {
    List<Course> queryCourseById(@Param("id") int id);

    List<Institution> queryAllIns();

    int insertCourse(Course course);

    void updateCourse(Course course);

    void deleteCourseById(@Param("id") int id);

    void addChooseNum(@Param("id") int id);

    void downChooseNum(@Param("id") int id);

    void addCourseChoose(Course_choose course_choose);

    void deleteCourseChoose(Course_choose course_choose);

    void updateScore(Course_choose course_choose);

    List<Integer> queryCourseIdByStuId(@Param("stuid") int stuid);

    Course queryCourse(@Param("id") int id);

    List<String> queryInsNameByCourse(@Param("id") int id);

    List<Integer> selectCourseLimit(@Param("classId") int classId);

    void insertInsLimit(@Param("classId") int classId, @Param("insId") int insId);

    void deleteInsLimit(@Param("classId") int classId);

    List<Student> queryStuByCourseId(@Param("id") int id);

    List<Student> queryStuByStuId(@Param("classid") int classid, @Param("stuid") int stuid);

    List<Course> queryAllCourse();

    List<Course> queryAllByInsId(@Param("id") int id);

    void deleteCourseChooseByCourseId(@Param("classId") int classId);

    List<Integer> queryInsIdByCourseId(@Param("classId") int classId);

    String selectNameByInsId(@Param("insId") int insId);

    Course queryCourseInfoById(@Param("classId") int classId);

    String selectTeaNameByTeaId(@Param("teaId") int teaId);

    Course selectCourseByClassId(@Param("classId") int classId);

    int selectScore(Course_choose course_choose);

    void deleteStuByClassId(@Param("classId") int classId);

    void deleteLimitByClassId(@Param("classId") int classId);

    List<Integer> selectInsIdByClassId(@Param("classId") int classId);
}