package com.oes.dao;


import com.oes.bean.Exam;
import com.oes.bean.ExamResult;
import com.oes.bean.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StuMapper {
    Student stuLogin(@Param("stu_number") String stu_number ,@Param("stu_password") String stu_password);
    Student findStudent(String stu_id);
    List<Student> allStudent(String stu_grade);
    void deleteStudent(String stu_id);

    //学生查成绩记录
    List<ExamResult> stuCheckScore(int stu_id);

    //学生查所有考试
    List<Exam> stuFindAllExam();
}

