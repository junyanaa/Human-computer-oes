package com.oes.service.impl;

import com.oes.bean.Answer;
import com.oes.dao.AnswerMapper;
import com.oes.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;
    public List<Answer> allAnswer(String log_id){
        return answerMapper.allAnswer(log_id);
    }
    //学生提交考试答案后,创建一条学生成绩记录 但暂不记录成绩
    @Override
    public void addExam_log(int exam_id, int stu_id, String submit_Time) {
        answerMapper.addExam_log(exam_id , stu_id , submit_Time);
    }

    //获取刚刚创建的学生成绩记录的id
    @Override
    public int findLog_id(int exam_id , int stu_id){
        return answerMapper.findLog_id(exam_id , stu_id);
    }

    //学生提交考试答案后，将答案存入数据库
    @Override
    public void completeExam(int log_id , int question_id , String stu_answer){
        answerMapper.completeExam(log_id , question_id , stu_answer);
    }

    /*查找学生的答案集合*/
    @Override
    public List<Answer> findStu_answer(int log_id){
        return answerMapper.findStu_answer(log_id);
    }

    /*存入学生该门考试的成绩*/
    @Override
    public void addScore(int log_id , float score){
        answerMapper.addScore(log_id , score);
    }
}
