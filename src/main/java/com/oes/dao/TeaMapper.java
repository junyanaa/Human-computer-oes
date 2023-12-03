package com.oes.dao;

import com.oes.bean.Teacher;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface TeaMapper {
    Teacher teaLogin(@Param("tea_ID") String stu_number , @Param("tea_password") String stu_password);
    Teacher findTeacher(int tea_Id);

    void changeName(@Param("tea_name") String tea_name,@Param("tea_id") String tea_id);
    void changeBirthdate(@Param("tea_birthDate")String tea_birthDate,@Param("tea_id")String tea_id);
    void changePassword(@Param("tea_password")String tea_password,@Param("tea_id")String tea_id);
    void changeCourse(@Param("tea_course")String tea_course,@Param("tea_id")String tea_id);
    void changeGrade(@Param("tea_grade")String tea_grade,@Param("tea_id")String tea_id);
    void changePhone(@Param("tea_phone")String tea_phone,@Param("tea_id")String tea_id);
}
