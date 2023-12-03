package com.oes.service;

import com.oes.bean.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findAllQuestion(String exam_id);
    void deleteQuestion(String question_id);
    void addChoiceQuestion(Question question);
    void addBlankQuestion(Question question);
    void addYesOrNoQuestion(Question question);
     void deleteQuestionById(String question_id);
    Question findQuestionById(String question_id);
    List<Question> findQuestion(int exam_id);
}
