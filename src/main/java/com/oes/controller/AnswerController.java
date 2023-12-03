package com.oes.controller;

import com.oes.bean.Answer;
import com.oes.bean.Question;
import com.oes.bean.Student;
import com.oes.service.AnswerService;
import com.oes.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;
    @RequestMapping(value = "allAnswer")
    @ResponseBody
    public void allAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String log_id=request.getParameter("logIdButton");
        List<Answer> answers=answerService.allAnswer(log_id);
        List<Question> questions =new ArrayList<>();
        for (Answer answer:answers){
            Question question= questionService.findQuestionById(String.valueOf(answer.getQuestion_id()));
            questions.add(question);
        }
        request.getSession().setAttribute("questionSession",questions);
        request.getSession().setAttribute("allAnswerSession",answers);
        response.sendRedirect("jsp/answerQuery.jsp");
    }

    public String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int d = cal.get(Calendar.DATE);
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int mi = cal.get(Calendar.MINUTE);
        int s = cal.get(Calendar.SECOND);

        return y + "-" + m + "-" + d + " " + h + ":" + mi + ":" + s;
    }

    //系统直接批改打分
    public float marking(List<Question> questions, List<Answer> answers) {
        float score = 0;
        for (Question question : questions) {
            for (Answer answer : answers) {
                if (answer.getQuestion_id() == question.getQuestion_id() && answer.getStu_answer().equals(question.getQuestion_answer())) {
                    score = score + question.getScore();
                }
            }
        }
        return score;
    }


    @RequestMapping("/completeExam")
    public String completeExam(HttpServletRequest request) {
        List<Question> questions = (List<Question>) request.getSession().getAttribute("questions_session");

        //获取当堂考试的id
        int exam_id = (int) request.getSession().getAttribute("exam_id");

        //获取学生
        Student student = (Student) request.getSession().getAttribute("stu_session");

        //获取当前时间作为提交时间
        String submit_Time = getCurrentTime();

        //先为该学生创建这堂考试的记录
        answerService.addExam_log(exam_id, student.getStu_id(), submit_Time);
        //再获取新创建的记录的id
        int log_id = answerService.findLog_id(exam_id, student.getStu_id());

        for (int i = 0; i < questions.size(); i++) {
            //获取题号
            String question_id = String.valueOf(questions.get(i).getQuestion_id());

            //获取存入session的多选题答案
            Object attribute = request.getSession().getAttribute("check" + i);
            if (attribute != null) {
                ArrayList arrayList = new ArrayList<String>();
                arrayList.add("A");
                arrayList.add("B");
                arrayList.add("C");
                arrayList.add("D");
                String check = "";
                for (Object answer : arrayList) {
                    check += request.getParameter(question_id + answer);
                }
                String replace = check.replace("null", "");
                answerService.completeExam(log_id, questions.get(i).getQuestion_id(), replace);
            } else {    //除了多选题的其他题
                //根据题号获取学生提交的相对应的答案
                String stu_answer = request.getParameter(question_id);

                if (stu_answer != null) {
                    if ("true".equals(stu_answer)) {
                        answerService.completeExam(log_id, questions.get(i).getQuestion_id(), "对");
                    } else if ("false".equals(stu_answer)) {
                        answerService.completeExam(log_id, questions.get(i).getQuestion_id(), "错");
                    } else {
                        answerService.completeExam(log_id, questions.get(i).getQuestion_id(), stu_answer);
                    }
                } else {
                    answerService.completeExam(log_id, questions.get(i).getQuestion_id(), "无");
                }
            }
        }

        //获取该学生的答案集合
        List<Answer> answers = answerService.findStu_answer(log_id);
        //算出得分
        float score = marking(questions, answers);
        //将得分存入数据库
        answerService.addScore(log_id, score);

        return "redirect:/jsp/stuMain.jsp";
    }
}
