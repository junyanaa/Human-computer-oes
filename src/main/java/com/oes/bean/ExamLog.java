package com.oes.bean;

import lombok.Data;

import java.util.Date;

@Data
public class ExamLog {
    private int log_id;
    private int exam_id;
    private int stu_id;
    private float score;
    private Date submit_time;
}
