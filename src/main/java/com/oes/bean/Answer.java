package com.oes.bean;

import lombok.Data;

@Data
public class Answer {
    private int answer_id;
    private int log_id;
    private int question_id;
    private  String  stu_answer;
}
