package com.oes.bean;

import lombok.Data;

@Data
public class Student {
    private int stu_id;                 //学生id
    private String stu_number;          //学号（登陆账号）
    private String stu_password;        //密码
    private String stu_name;            //学生姓名
    private String stu_sex;             //性别
    private String stu_birth;           //生日
    private String stu_nation;          //民族
    private String stu_grade;           //年级
    private String stu_college;         //学院
    private String stu_major;           //专业
}
