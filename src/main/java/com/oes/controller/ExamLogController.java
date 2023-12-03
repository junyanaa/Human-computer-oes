package com.oes.controller;

import com.oes.bean.Exam;
import com.oes.bean.ExamLog;
import com.oes.bean.Student;
import com.oes.service.ExamLogService;
import com.oes.service.ExamService;
import com.oes.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExamLogController {
    @Autowired
    private ExamLogService examLogService;
    @Autowired
    private ExamService examService;
    @Autowired
    private StuService stuService;
    @RequestMapping(value = "/allExamLog")
    @ResponseBody
    public void examLogs(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String examId =request.getParameter("logExamId");
        List<ExamLog> examLogs=examLogService.allExamLog(examId);
        List<String> stu_ids=examLogService.findStudent(examId);
        List<Student> students =new ArrayList<>();
        for (String stu_id:stu_ids){
            Student student=stuService.findStudent(stu_id);
            students.add(student);
        }
        Exam logExam=examService.findExam(examId);
        if (examLogs!=null) {
            request.getSession().setAttribute("examLogs",examLogs);
            request.getSession().setAttribute("logExam",logExam);
            request.getSession().setAttribute("logStudent",students);
            response.sendRedirect("jsp/recordQuery.jsp");
        }else if (logExam==null){
            request.setAttribute("message","考试信息错误！");
            response.sendRedirect("jsp/stuMain.jsp");
        }else{
            request.setAttribute("message","无考试记录！");
            response.sendRedirect("jsp/stuMain.jsp");
        }
    }
}
