package com.zxc.service.impl;

import com.zxc.dao.CourseDao;
import com.zxc.dao.UserDao;
import com.zxc.model.Course;
import com.zxc.model.Course_choose;
import com.zxc.model.Institution;
import com.zxc.model.Student;
import com.zxc.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseDao courseDao;

    @Resource
    private UserDao userDao;

    @Override
    public List<Course> queryAllById(int id) {
        List<Course> courses = courseDao.queryCourseById(id);
        for (Course course : courses) {
            course.setClassLimitInsName(courseDao.queryInsNameByCourse(course.getClassId()));
        }
        return courses;
    }

    @Override
    public List<String> queryInsNameByCourse(int id) {
        return courseDao.queryInsNameByCourse(id);
    }

    @Override
    public List<Institution> queryAllIns() {
        return courseDao.queryAllIns();
    }

    @Override
    public int insertCourse(String name, String num, int teaid) {
        Course course = new Course();
        course.setClassName(name);
        course.setClassNum(Integer.parseInt(num));
        course.setTeaId(teaid);
        courseDao.insertCourse(course);
        return course.getClassId();
    }

    @Override
    public void insertInsLimit(String det, int classId) {
        courseDao.deleteInsLimit(classId);
        if (det != null && !det.isEmpty()) {
            String[] insIds = det.split(",");
            for (String insId : insIds) {
                if (!insId.isEmpty()) {
                    courseDao.insertInsLimit(classId, Integer.parseInt(insId));
                }
            }
        }
    }

    @Override
    public Course queryInfoById(int id) {
        Course course = courseDao.queryCourse(id);
        if (course != null) {
            course.setClassLimitInsName(courseDao.queryInsNameByCourse(id));
        }
        return course;
    }

    @Override
    public List<Integer> selectCourseLimit(int classId) {
        return courseDao.selectCourseLimit(classId);
    }

    @Override
    public int updateCourse(String name, String num, int teaid) {
        Course course = courseDao.queryCourse(teaid);
        if (course != null) {
            course.setClassName(name);
            course.setClassNum(Integer.parseInt(num));
            courseDao.updateCourse(course);
            return course.getClassId();
        }
        return 0;
    }

    @Override
    public void updateInsLimit(String det, int classId) {
        insertInsLimit(det, classId);
    }

    @Override
    public void deleteCourse(int id) {
        courseDao.deleteCourseChooseByCourseId(id);
        courseDao.deleteInsLimit(id);
        courseDao.deleteCourseById(id);
    }

    @Override
    public List<Student> queryStuByCourseId(int id) {
        return courseDao.queryStuByCourseId(id);
    }

    @Override
    public void updateScore(int classId, int stuId, int score) {
        Course_choose cc = new Course_choose();
        cc.setClassId(classId);
        cc.setStuId(stuId);
        cc.setScore(score);
        courseDao.updateScore(cc);
    }

    @Override
    public List<Student> queryStuByStuId(int classid, int stuid) {
        return courseDao.queryStuByStuId(classid, stuid);
    }

    @Override
    public List<Course> queryAllCourse(int stuid) {
        List<Course> courses = courseDao.queryAllCourse();
        List<Integer> chosenCourseIds = courseDao.queryCourseIdByStuId(stuid);
        for (Course course : courses) {
            course.setClassLimitInsName(courseDao.queryInsNameByCourse(course.getClassId()));
            course.setIsChoose(chosenCourseIds.contains(course.getClassId()) ? 1 : 0);
        }
        return courses;
    }

    @Override
    public Course queryCourse(int id) {
        Course course = courseDao.queryCourse(id);
        if (course != null) {
            course.setClassLimitInsName(courseDao.queryInsNameByCourse(id));
        }
        return course;
    }

    @Override
    public void chooseSuccess(int classId, int stuId) {
        courseDao.addChooseNum(classId);
        Course_choose cc = new Course_choose();
        cc.setStuId(stuId);
        cc.setClassId(classId);
        cc.setScore(0);
        courseDao.addCourseChoose(cc);
    }

    @Override
    public boolean checkStuIns(int classId, int stuId) {
        List<Integer> limitInsIds = courseDao.selectCourseLimit(classId);
        if (limitInsIds == null || limitInsIds.isEmpty()) {
            return true;
        }
        Student student = userDao.selectStuById(stuId);
        if (student == null) {
            return false;
        }
        return limitInsIds.contains(student.getInsId());
    }

    @Override
    public void deleteCourseChoose(int stuId, int classId) {
        courseDao.downChooseNum(classId);
        Course_choose cc = new Course_choose();
        cc.setStuId(stuId);
        cc.setClassId(classId);
        courseDao.deleteCourseChoose(cc);
    }

    @Override
    public List<Course> queryStuCourse(int stuId) {
        List<Integer> courseIds = courseDao.queryCourseIdByStuId(stuId);
        List<Course> courses = new ArrayList<>();
        for (Integer courseId : courseIds) {
            Course course = queryCourse(courseId);
            if (course != null) {
                courses.add(course);
            }
        }
        return courses;
    }

    @Override
    public List<Course> queryAllByInsId(int id) {
        List<Course> courses = courseDao.queryAllByInsId(id);
        for (Course course : courses) {
            course.setClassLimitInsName(courseDao.queryInsNameByCourse(course.getClassId()));
        }
        return courses;
    }
}