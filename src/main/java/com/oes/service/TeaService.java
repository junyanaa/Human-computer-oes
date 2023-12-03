package com.oes.service;

import com.oes.bean.Teacher;

import java.util.Date;

public interface TeaService {
    Teacher teaLogin(String tea_ID , String tea_password);
    Teacher findTeacher(int tea_id);
    void changeName(String tea_name,String tea_id);
    void changeBirthdate(String tea_birthDate,String tea_id);
    void changePassword(String tea_password,String tea_id);
    void changeCourse(String tea_course,String tea_id);
    void changeGrade(String tea_grade,String tea_id);
    void changePhone(String tea_phone,String tea_id);
}
