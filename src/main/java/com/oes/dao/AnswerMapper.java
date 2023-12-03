package com.oes.dao;

import com.oes.bean.Answer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AnswerMapper {
    List<Answer> allAnswer(String log_id);
    //学生提交考试答案后,创建一条学生成绩记录 但暂不记录成绩
    void addExam_log(@Param("exam_id") int exam_id , @Param("stu_id") int stu_id , @Param("submit_Time") String submit_Time);

    //获取刚刚创建的学生成绩记录的id
    int findLog_id(@Param("exam_id") int exam_id , @Param("stu_id") int stu_id);

    //学生提交考试答案后，将答案存入数据库
    void completeExam(@Param("log_id") int log_id , @Param("question_id") int question_id , @Param("stu_answer") String stu_answer);

    /*查找学生的答案集合*/
    List<Answer> findStu_answer(int log_id);

    /*存入学生该门考试的成绩*/
    void addScore(@Param("log_id") int log_id , @Param("score") float score);
}
