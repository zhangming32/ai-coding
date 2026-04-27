package com.zxc.controller.student;

import com.zxc.model.Course;
import com.zxc.model.Student;
import com.zxc.service.CourseService;
import com.zxc.service.PageService;
import com.zxc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
@SessionAttributes({"username", "stuid"})
public class StudentController {

    @Resource
    private UserService userService;

    @Resource
    private PageService pageService;

    @Resource
    private CourseService courseService;

    @RequestMapping("/studentIndex")
    public String studentIndex() {
        return "student/studentIndex";
    }

    @RequestMapping("/studentInfo")
    public String studentInfo(@RequestParam("stuid") int id, Model model) {
        model.addAttribute("student", userService.getStuInfoById(id));
        return "student/studentInfo";
    }

    @RequestMapping("/editStuPass")
    public String editStuPass() {
        return "student/editStuPass";
    }

    @RequestMapping("/changeStuPass")
    public String changeStuPass(@RequestParam("prepass") String prepass,
                                @RequestParam("nowpass") String nowpass,
                                Model model,
                                HttpServletRequest request) {
        int id = (int) request.getSession().getAttribute("stuid");
        if (userService.checkAccount(id, prepass) == 0) {
            model.addAttribute("msg", "原始密码输入错误!");
            return "student/editStuPass";
        } else {
            Student student = new Student();
            student.setStuId(id);
            student.setStuPass(nowpass);
            userService.changeStuPass(student);
            model.addAttribute("student", userService.getStuInfoById(id));
            return "student/studentInfo";
        }
    }

    @RequestMapping("/courseList")
    public String courseList(@RequestParam("page") int page, Model model, HttpServletRequest request) {
        int stuid = (int) request.getSession().getAttribute("stuid");
        model.addAttribute("paging", pageService.subList(page, courseService.queryAllCourse(stuid)));
        model.addAttribute("teaList", userService.queryAllTeacher());
        model.addAttribute("insList", courseService.queryAllIns());
        return "student/courseList";
    }

    @RequestMapping("/courseDetail")
    public String courseDetail(@RequestParam("classId") int classId, Model model, HttpServletRequest request) {
        int stuid = (int) request.getSession().getAttribute("stuid");
        if (courseService.checkStuIns(classId, stuid)) {
            model.addAttribute("course", courseService.queryCourse(classId));
            return "student/courseDetail";
        } else {
            model.addAttribute("msg", "请注意课程的学院限制");
            model.addAttribute("paging", pageService.subList(1, courseService.queryAllCourse(stuid)));
            model.addAttribute("teaList", userService.queryAllTeacher());
            model.addAttribute("insList", courseService.queryAllIns());
            return "student/courseList";
        }
    }

    @RequestMapping("/chooseSuccess")
    public String chooseSuccess(@RequestParam("stuid") int stuid,
                                @RequestParam("courseid") int courseid,
                                Model model) {
        courseService.chooseSuccess(courseid, stuid);
        model.addAttribute("paging", pageService.subList(1, courseService.queryAllCourse(stuid)));
        model.addAttribute("teaList", userService.queryAllTeacher());
        model.addAttribute("insList", courseService.queryAllIns());
        return "student/courseList";
    }

    @RequestMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("courseid") int courseid, Model model, HttpServletRequest request) {
        int stuid = (int) request.getSession().getAttribute("stuid");
        courseService.deleteCourseChoose(stuid, courseid);
        model.addAttribute("paging", pageService.subList(1, courseService.queryAllCourse(stuid)));
        model.addAttribute("teaList", userService.queryAllTeacher());
        model.addAttribute("insList", courseService.queryAllIns());
        return "student/courseList";
    }

    @RequestMapping("/checkedCourseList")
    public String checkedCourseList(Model model, HttpServletRequest request) {
        int stuid = (int) request.getSession().getAttribute("stuid");
        model.addAttribute("courseList", courseService.queryStuCourse(stuid));
        return "student/checkedCourseList";
    }

    @RequestMapping("/searchCourse")
    public String searchCourse(@RequestParam("courseid") int courseid, Model model, HttpServletRequest request) {
        int stuid = (int) request.getSession().getAttribute("stuid");
        List<Course> cor_list = new ArrayList<>();
        cor_list.add(courseService.queryCourse(courseid));
        model.addAttribute("paging", pageService.subList(1, cor_list));
        model.addAttribute("teaList", userService.queryAllTeacher());
        model.addAttribute("insList", courseService.queryAllIns());
        return "student/courseList";
    }

    @RequestMapping("/searchListByTeaId")
    public String searchListByTeaId(@RequestParam("teaid") int teaid, Model model, HttpServletRequest request) {
        int stuid = (int) request.getSession().getAttribute("stuid");
        List<Course> cor_list = courseService.queryAllById(teaid);
        model.addAttribute("paging", pageService.subList(1, cor_list));
        model.addAttribute("teaList", userService.queryAllTeacher());
        model.addAttribute("insList", courseService.queryAllIns());
        return "student/courseList";
    }

    @RequestMapping("/searchListByInsId")
    public String searchListByInsId(@RequestParam("insid") int insid, Model model, HttpServletRequest request) {
        int stuid = (int) request.getSession().getAttribute("stuid");
        List<Course> cor_list = courseService.queryAllByInsId(insid);
        model.addAttribute("paging", pageService.subList(1, cor_list));
        model.addAttribute("teaList", userService.queryAllTeacher());
        model.addAttribute("insList", courseService.queryAllIns());
        return "student/courseList";
    }
}