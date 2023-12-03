package com.oes.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Teacher {
    private int tea_ID;
    private String tea_name;
    private String tea_gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tea_birthdate;
    private String tea_phone;
    private String tea_password;
    private String tea_course;
    private  String tea_grade;
}
