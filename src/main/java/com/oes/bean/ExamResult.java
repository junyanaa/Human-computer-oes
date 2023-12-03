package com.oes.bean;

import lombok.Data;

@Data
public class ExamResult {
    private int exam_id;                 //考试科目id
    private String exam_name;            //考试名称
    private String exam_start_Time;      //考试开始时间
    private String exam_end_Time;        //考试结束时间
    private float score;                 //分数
}
