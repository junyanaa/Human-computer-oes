package com.oes.controller;

import com.oes.bean.*;
import com.oes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class StuController {
    //注入StuService对象
    @Autowired
    private StuService stuService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ExamLogService examLogService;
    @Autowired
    private AnswerService answerService;

    @Autowired
    private ExamService examService;
    //学生登录
    @RequestMapping(value = "/stuLogin",method = RequestMethod.POST)
    public String StuLogin(HttpServletRequest request) {
        //获取登录页面发送请求的数据
        String stu_number = request.getParameter("stu_number");
        String stu_password = request.getParameter("stu_password");

        //在数据库中查找符合条件的学生用户
        Student student = stuService.stuLogin(stu_number, stu_password);

        if (student != null) {
            //将查到的用户存入Session
            request.getSession().setAttribute("stu_session" , student);
            return "redirect:/jsp/stuMain.jsp";             //重定向到学生主页
        }else {
            request.setAttribute("msg" , "用户名或密码错误");
            return "forward:login.jsp";                     //返回到登录页面
        }
    }

    //学生退出登录
    @RequestMapping(value = "/stuLogout")
    public String stuLogOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //销毁session
        session.invalidate();
        return "forward:login.jsp";                     //返回到登录页面
    }
    @RequestMapping(value = "/allStudent")
    @ResponseBody
    public void allStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
         HttpSession session= request.getSession();
         Teacher teacher= (Teacher) session.getAttribute("tea_session");
         String stu_grade=teacher.getTea_grade();
        List<Student> students=stuService.allStudent(stu_grade);
        request.getSession().setAttribute("allStudent",students);
        response.sendRedirect("jsp/studentManagement.jsp");
    }
    @RequestMapping(value = "/deleteStudent")
    @ResponseBody
    public void deleteStudent(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String stu_id=request.getParameter("deleteStudentButton");
        stuService.deleteStudent(stu_id);
        response.sendRedirect("/allStudent");
    }
    //学生查成绩
    @RequestMapping("/stuCheckScore")
    public String stuCheckScore(HttpServletRequest request) {
        //获取session域中当前用户的id
        Student student = (Student) request.getSession().getAttribute("stu_session");

        int stu_id = student.getStu_id();
        //利用id进行成绩查询
        List<ExamResult> examResults = stuService.stuCheckScore(stu_id);

        //将查询结果存入session
        request.getSession().setAttribute("examResult_session" , examResults);

        return "redirect:/jsp/stuRecordQuery.jsp";         //重定向到成绩查询页面
    }

    //学生参加考试，进入考试列表
    @RequestMapping("/stuExamList")
    public String stuAttendExam(HttpServletRequest request){
        //获取session域中当前用户的id
        Student student = (Student) request.getSession().getAttribute("stu_session");
        int stu_id = student.getStu_id();
        //利用id进行成绩查询
        List<ExamResult> examResults = stuService.stuCheckScore(stu_id);
        //查询所有考试
        List<Exam> exams = stuService.stuFindAllExam();
        //将查询结果存入session
        request.getSession().setAttribute("examResult_session" , examResults);
        request.getSession().setAttribute("exam_session" , exams);
        return "redirect:/jsp/stuExamList.jsp";               //重定向到查看考试列表页面
    }

    // 学生查看试卷
    @RequestMapping("/stuCheckPaper")
    public String stuCheckPaper(HttpServletRequest request){
        Student student = (Student)request.getSession().getAttribute("stu_session");
        int stu_id = student.getStu_id();

        String exam_id= request.getParameter("exam_id");
        List <Question> questions = questionService.findAllQuestion(exam_id);

        int log_id = examLogService.findLogId(exam_id,stu_id);
        List<Answer> answers=answerService.findStu_answer(log_id);

        Exam exam = examService.findExam(exam_id);
        request.getSession().setAttribute("exam_session" , exam);

        request.getSession().setAttribute("paperQuestions",questions);
        request.getSession().setAttribute("paperAnswers",answers);

        return "redirect:/jsp/stuCheckPaper.jsp";               //重定向到查看试卷页面
    }
}