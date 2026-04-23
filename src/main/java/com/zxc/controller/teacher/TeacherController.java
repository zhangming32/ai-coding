package com.zxc.controller.teacher;

import com.zxc.model.Course;
import com.zxc.model.Teacher;
import com.zxc.service.CourseService;
import com.zxc.service.PageService;
import com.zxc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
@RequestMapping("/teacher")
@SessionAttributes({"username", "teaid"})
public class TeacherController {

    @Resource
    private UserService userService;

    @Resource
    private CourseService courseService;

    @Resource
    private PageService pageService;

    @RequestMapping("/teacherIndex")
    public String teacherIndex() {
        return "teacher/teacherIndex";
    }

    @RequestMapping("/teacherInfo")
    public String teacherInfo(@RequestParam("teaid") int id, Model model) {
        model.addAttribute("teacher", userService.getTeaInfoById(id));
        return "teacher/teacherInfo";
    }

    @RequestMapping("/editTeaPass")
    public String editTeaPass() {
        return "teacher/editTeaPass";
    }

    @RequestMapping("/changeTeaPass")
    public String changeTeaPass(@RequestParam("prepass") String prepass,
                                @RequestParam("nowpass") String nowpass,
                                Model model,
                                HttpServletRequest request) {
        int id = (int) request.getSession().getAttribute("teaid");
        if (userService.checkAccount(id, prepass) == 0) {
            model.addAttribute("msg", "原始密码输入错误!");
            return "teacher/editTeaPass";
        } else {
            Teacher teacher = new Teacher();
            teacher.setTeaId(id);
            teacher.setTeaPass(nowpass);
            userService.changeTeaPass(teacher);
            model.addAttribute("teacher", userService.getTeaInfoById(id));
            return "teacher/teacherInfo";
        }
    }

    @RequestMapping("/courseList")
    public String courseList(@RequestParam("page") int page, Model model, HttpServletRequest request) {
        int teaid = (int) request.getSession().getAttribute("teaid");
        model.addAttribute("paging", pageService.subList(page, courseService.queryAllById(teaid)));
        return "teacher/courseList";
    }

    @RequestMapping("/insertCourse")
    public String insertCourse(Model model) {
        model.addAttribute("insList", courseService.queryAllIns());
        return "teacher/insertCourse";
    }

    @RequestMapping("/editCourse")
    public String editCourse(@RequestParam("courseid") int courseid, Model model) {
        model.addAttribute("courseInfo", courseService.queryInfoById(courseid));
        model.addAttribute("checkIns", courseService.selectCourseLimit(courseid));
        model.addAttribute("insList", courseService.queryAllIns());
        return "teacher/editCourse";
    }

    @RequestMapping("/insertCourseSuccess")
    public String insertCourseSuccess(@RequestParam("content") String content,
                                      @RequestParam("page") int page,
                                      Model model,
                                      HttpServletRequest request) throws UnsupportedEncodingException {
        String[] det = URLDecoder.decode(URLDecoder.decode(content, "utf-8"), "utf-8").split("\\|");
        int teaid = (int) request.getSession().getAttribute("teaid");
        int courseId = courseService.insertCourse(det[0], det[1], teaid);
        courseService.insertInsLimit(det[2], courseId);
        model.addAttribute("paging", pageService.subList(page, courseService.queryAllById(teaid)));
        return "teacher/courseList";
    }

    @RequestMapping("/updateCourseSuccess")
    public String updateCourseSuccess(@RequestParam("content") String content,
                                      @RequestParam("page") int page,
                                      Model model,
                                      HttpServletRequest request) throws UnsupportedEncodingException {
        String[] det = URLDecoder.decode(URLDecoder.decode(content, "utf-8"), "utf-8").split("\\|");
        int teaid = (int) request.getSession().getAttribute("teaid");
        int courseId = courseService.updateCourse(det[0], det[1], teaid);
        courseService.updateInsLimit(det[2], courseId);
        model.addAttribute("paging", pageService.subList(page, courseService.queryAllById(teaid)));
        return "teacher/courseList";
    }

    @RequestMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("courseid") int courseid, Model model, HttpServletRequest request) {
        courseService.deleteCourse(courseid);
        int teaid = (int) request.getSession().getAttribute("teaid");
        model.addAttribute("paging", pageService.subList(1, courseService.queryAllById(teaid)));
        return "teacher/courseList";
    }

    @RequestMapping("/detailCourse")
    public String detailCourse(@RequestParam("courseid") int courseid,
                               @RequestParam("page") int page,
                               Model model) {
        model.addAttribute("paging", pageService.subList(page, courseService.queryStuByCourseId(courseid)));
        return "teacher/courseDetail";
    }

    @RequestMapping("/updateScore")
    public String updateScore(@RequestParam("courseid") int courseid,
                              @RequestParam("stuId") int stuId,
                              @RequestParam("score") int score,
                              @RequestParam("page") Integer page,
                              Model model) {
        courseService.updateScore(courseid, stuId, score);
        model.addAttribute("paging", pageService.subList(page, courseService.queryStuByCourseId(courseid)));
        return "teacher/courseDetail";
    }

    @RequestMapping("/searchStu")
    public String searchStu(@RequestParam("stuid") int stuid,
                            @RequestParam("courseid") int courseid,
                            Model model) {
        int page = 1;
        model.addAttribute("paging", pageService.subList(page, courseService.queryStuByStuId(courseid, stuid)));
        return "teacher/courseDetail";
    }

    @RequestMapping("/deleteStuCourse")
    public String deleteStuCourse(@RequestParam("stuid") int stuid,
                                  @RequestParam("courseid") int courseid,
                                  Model model) {
        courseService.deleteCourseChoose(stuid, courseid);
        model.addAttribute("paging", pageService.subList(1, courseService.queryStuByCourseId(courseid)));
        return "teacher/courseDetail";
    }
}