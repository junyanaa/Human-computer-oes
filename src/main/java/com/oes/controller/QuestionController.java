package com.oes.controller;

import com.oes.bean.Exam;
import com.oes.bean.Question;
import com.oes.service.ExamService;
import com.oes.service.QuestionService;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.oes.util.FileUtil;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class QuestionController extends HttpServlet {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ExamService examService;
    @RequestMapping(value = "back")
    public String back(){
        return "redirect:/jsp/questionOperation.jsp";
    }

    @RequestMapping(value = "/allQuestion")
    @ResponseBody
    public void allQuestion(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String exam_id;
        if ((exam_id=request.getParameter("exam_id"))==null){
            Exam exam= (Exam) request.getSession().getAttribute("exam");
            exam_id= String.valueOf(exam.getExam_id());
        }
        Exam exam=examService.findExam(exam_id);
        List<Question> questions=questionService.findAllQuestion(exam_id);
        request.getSession().setAttribute("exam",exam);
        request.getSession().setAttribute("exam_id",exam_id);
        request.getSession().setAttribute("questions",questions);
        response.sendRedirect("/back");
    }

    @RequestMapping(value = "/FileUpload")
    public void FileUpload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String realPath1 = "E:\\学习\\human-computer-oes\\FileUpload";
        String realPath2 ="E:\\学习\\human-computer-oes\\tmp";
        /*String realPath1 = "F:\\Undergraduate\\Junior\\The first semester of Junior year\\HumanComputer\\Code\\SSM\\FileUpload";
        String realPath2 ="F:\\Undergraduate\\Junior\\The first semester of Junior year\\HumanComputer\\Code\\SSM\\tmp";*/
        File saveFilePath = new File(realPath1);
        File tempFilePath = new File(realPath2);
        boolean flag=false;
        if (!saveFilePath.exists()) {
            saveFilePath.mkdir();
        }
        if (!tempFilePath.exists()) {
            tempFilePath.mkdir();
        }
        FileUtil fileUtil=new FileUtil();
        try {
            DiskFileItemFactory diskFileItemFactory =fileUtil.getDiskFileItemFactory(tempFilePath);
            ServletFileUpload servletFileUpload = fileUtil.getServletFileUpload(diskFileItemFactory);
            flag =fileUtil.uploadParseRequest(servletFileUpload, req, realPath1);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("flag",flag);
        String fileName=fileUtil.filePath;
        File file=new File(fileName);
        FileInputStream fileInputStream=new FileInputStream(file);
        List<Question> questions=new FileUtil().getQuestion(fileInputStream);
        Exam exam= (Exam) req.getSession().getAttribute("exam");
        for (Question question:questions){
            question.setExam_id(Integer.parseInt(String.valueOf(exam.getExam_id())));
            if (Objects.equals(question.getQuestion_type(), "单选题")){
                questionService.addChoiceQuestion(question);
            } else if (Objects.equals(question.getQuestion_type(),"多选题")) {
                questionService.addChoiceQuestion(question);
            } else if (Objects.equals(question.getQuestion_type(), "判断题")) {
                questionService.addYesOrNoQuestion(question);
            }else {
                questionService.addBlankQuestion(question);
            }
        }
        resp.sendRedirect("/allQuestion");
    }

    @RequestMapping(value = "deleteQuestion")
    @ResponseBody
    public void deleteQuestion(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String question_id = request.getParameter("deleteQuestion");
        System.out.println(question_id);
        questionService.deleteQuestionById(question_id);
        response.sendRedirect("/allQuestion");
    }
    @RequestMapping(value = "/stuExamining", method = RequestMethod.POST)
    public String stuExamining(HttpServletRequest request){
        //获取选择的考试id
        int exam_id = Integer.parseInt(request.getParameter("exam_id"));
        //将获取的考试id存入session
        request.getSession().setAttribute("exam_id" , exam_id);

        //根据考试id获取这门考试的题目
        List<Question> questions = questionService.findQuestion(exam_id);

        //将题目存入session中
        request.getSession().setAttribute("questions_session" , questions);

        return "redirect:/jsp/stuExamining.jsp";              //重定向到考试页面
    }
}
