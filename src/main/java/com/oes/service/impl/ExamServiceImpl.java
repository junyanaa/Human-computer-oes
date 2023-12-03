package com.oes.service.impl;

import com.oes.bean.Exam;
import com.oes.dao.ExamMapper;
import com.oes.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;
    @Override
    public Exam findExam(String exam_id){
        return examMapper.findExam(exam_id);
    }
    public void addExam(Exam exam){
        examMapper.addExam(exam);
    }
    public List<Exam> allExam(){
        return examMapper.allExam();
    }
    public void deleteExam(String exam_id){
        examMapper.deleteExam(exam_id);
    }
}
