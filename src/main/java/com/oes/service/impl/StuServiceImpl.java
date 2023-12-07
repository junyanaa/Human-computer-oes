package com.oes.service.impl;

import com.oes.bean.Exam;
import com.oes.bean.ExamResult;
import com.oes.bean.Student;
import com.oes.dao.StuMapper;
import com.oes.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuMapper StuMapper;

    @Override
    public Student stuLogin(String stu_number ,  String stu_password) {
        return StuMapper.stuLogin(stu_number , stu_password);
    }

    //学生查成绩
    @Override
    public List<ExamResult> stuCheckScore(int stu_id) {
        return StuMapper.stuCheckScore(stu_id);
    }

    //学生查所有考试
    @Override
    public List<Exam> stuFindAllExam(){
        return StuMapper.stuFindAllExam();
    }

    @Override
    public Integer addStudent(Student student) {
        return StuMapper.addStudent(student);
    }

    @Override
    public Integer updateStudent(Student student) {
        return StuMapper.updateStudent(student);
    }

    public Student findStudent(String stu_id){
        return StuMapper.findStudent(stu_id);
    }
    public List<Student> allStudent(String stu_grade){
        return StuMapper.allStudent(stu_grade);
    }
    public void deleteStudent(String stu_id){
        StuMapper.deleteStudent(stu_id);
    }
}

