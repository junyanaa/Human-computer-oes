package com.oes.bean;

import lombok.Data;

@Data
public class Question {
    private  int question_id;
    private int exam_id;
    private String question_type;//选择题类型（单选、多选、填空、判断）
    private String question_content;
    private String question_optionA;
    private String question_optionB;
    private String question_optionC;
    private String question_optionD;
    private String question_answer;
    private Float score;
}
