package com.oes.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Exam {
    private int exam_id;
    private String exam_name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date exam_start_Time;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date exam_end_Time;
    private String exam_description;
}
