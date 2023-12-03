package com.oes.controller;

import com.oes.bean.Exam;
import com.oes.service.ExamService;
import com.oes.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller

public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private QuestionService questionService;
    @RequestMapping(value = "/allExam")
    public String allExam(Model model, HttpServletRequest request){
        List<Exam> exams = examService.allExam();
        request.getSession().setAttribute("exams",exams);
        model.addAttribute("exams",exams);
        return "redirect:/jsp/examOperation.jsp";
    }
    @RequestMapping(value = "/addExam")
   @ResponseBody
    public void addExam(HttpServletRequest request,HttpServletResponse response) throws ParseException, IOException {
        Exam exam = new Exam();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String exam_name=request.getParameter("name");
        String exam_start_time=request.getParameter("beginTime");
        exam_start_time=exam_start_time.replaceAll("T"," ");
        String exam_end_time=request.getParameter("endTime");
        exam_end_time=exam_end_time.replaceAll("T"," ");
        exam.setExam_start_Time(simpleDateFormat.parse(exam_start_time));
         exam.setExam_end_Time(simpleDateFormat.parse(exam_end_time));
        String exam_description=request.getParameter("desc");
        System.out.println(exam_start_time);
        System.out.println(exam_end_time);
        exam.setExam_name(exam_name);
        exam.setExam_description(exam_description);
        System.out.println(exam.getExam_start_Time());
        System.out.println(exam.getExam_end_Time());
        examService.addExam(exam);
        response.sendRedirect("/allExam");
    }
    @RequestMapping(value = "/deleteExam")
    @ResponseBody
    public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String exam_id=request.getParameter("exam_id");
        questionService.deleteQuestion(exam_id);
        examService.deleteExam(exam_id);
        response.sendRedirect("/allExam");
    }
}
