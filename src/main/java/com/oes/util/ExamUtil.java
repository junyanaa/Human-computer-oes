package com.oes.util;

import com.oes.bean.Answer;
import com.oes.bean.Question;

import java.util.*;

public class ExamUtil {
    //阅卷
    public Float marking(List<Question> questions, List<Answer> answers){
        float score=0;
        for (Question question:questions){
            for (Answer answer:answers){
                if (answer.getQuestion_id()==question.getQuestion_id()&& Objects.equals(answer.getStu_answer(), question.getQuestion_answer())){
                    score=+question.getScore();
                }
            }
        }
        return score;
    }
}
