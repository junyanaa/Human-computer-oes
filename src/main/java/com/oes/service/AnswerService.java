package com.oes.service;

import com.oes.bean.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> allAnswer(String log_id);
    //学生提交考试答案后,创建一条学生成绩记录 但暂不记录成绩
    void addExam_log(int exam_id , int stu_id , String submit_Time);

    //获取刚刚创建的学生成绩记录的id
    int findLog_id(int exam_id , int stu_id);

    //学生提交考试答案后，将答案存入数据库
    void completeExam(int log_id , int question_id , String stu_answer);

    /*查找学生的答案集合*/
    List<Answer> findStu_answer(int log_id);

    /*存入学生该门考试的成绩*/
    void addScore(int log_id , float score);
}
