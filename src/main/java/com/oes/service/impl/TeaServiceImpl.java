package com.oes.service.impl;

import com.oes.bean.Teacher;
import com.oes.dao.TeaMapper;
import com.oes.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TeaServiceImpl implements TeaService {
    @Autowired
    private TeaMapper teaMapper;

    @Override
    public Teacher teaLogin(String tea_ID , String tea_password){
        return teaMapper.teaLogin(tea_ID , tea_password);
    }

    @Override
    public Teacher findTeacher(int tea_id) {
        return teaMapper.findTeacher(tea_id);
    }

    @Override
    public void changeName(String tea_name,String tea_id) {
        teaMapper.changeName(tea_name,tea_id);
    }

    @Override
    public void changeBirthdate(String tea_birthDate,String tea_id) {
teaMapper.changeBirthdate(tea_birthDate,tea_id);
    }

    @Override
    public void changePassword(String tea_password,String tea_id) {
teaMapper.changePassword(tea_password,tea_id);
    }

    @Override
    public void changeCourse(String tea_course,String tea_id) {
teaMapper.changeCourse(tea_course,tea_id);
    }

    @Override
    public void changeGrade(String tea_grade,String tea_id) {
teaMapper.changeGrade(tea_grade,tea_id);
    }
    @Override
    public void changePhone(String tea_phone,String tea_id) {
teaMapper.changePhone(tea_phone,tea_id);
    }
}
