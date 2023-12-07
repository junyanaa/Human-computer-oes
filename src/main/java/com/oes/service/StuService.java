package com.oes.service;

import com.oes.bean.Exam;
import com.oes.bean.ExamResult;
import com.oes.bean.Student;

import java.util.List;

public interface StuService {

    //学生登录
    Student stuLogin(String stu_number , String stu_password);
    Student findStudent(String stu_id);
    List<Student> allStudent(String stu_grade);
    void deleteStudent(String stu_id);

    //学生查成绩
    List<ExamResult> stuCheckScore(int stu_id);

    //学生查所有考试
    List<Exam> stuFindAllExam();

    Integer addStudent(Student student);

    Integer updateStudent(Student student);
}
