package com.oes.dao;

import com.oes.bean.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionMapper {
    List<Question> findAllQuestion(String exam_id);
    void deleteQuestion(String question_id);
    void addChoiceQuestion(Question question);
    void addBlankQuestion(Question question);
    void addYesOrNoQuestion(Question question);
     void deleteQuestionById(String question_id);
     Question findQuestionById(String question_id);
    //考试页面出现题目
    List<Question> findQuestion(int exam_id);
}
