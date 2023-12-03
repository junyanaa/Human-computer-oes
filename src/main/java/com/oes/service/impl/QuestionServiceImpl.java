package com.oes.service.impl;

import com.oes.bean.Question;
import com.oes.dao.QuestionMapper;
import com.oes.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public  List<Question> findAllQuestion(String exam_id){
        return questionMapper.findAllQuestion(exam_id);
    }
    public void deleteQuestion(String exam_id){
        questionMapper.deleteQuestion(exam_id);
    }
    public void addChoiceQuestion(Question question){
        questionMapper.addChoiceQuestion(question);
    }
    public void addBlankQuestion(Question question)
    {
        questionMapper.addBlankQuestion(question);
    }
    public void addYesOrNoQuestion(Question question){
        questionMapper.addYesOrNoQuestion(question);
    }
    public void deleteQuestionById(String question_id){
        questionMapper.deleteQuestionById(question_id);
    }
    @Override
    public List<Question> findQuestion(int exam_id){
        return questionMapper.findQuestion(exam_id);
    }
   public Question findQuestionById(String question_id){
        return questionMapper.findQuestionById(question_id);
   }
}
