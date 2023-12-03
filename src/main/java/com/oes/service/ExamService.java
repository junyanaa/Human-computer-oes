package com.oes.service;

import com.oes.bean.Exam;

import java.util.List;

public interface ExamService {
    Exam findExam(String exam_id);
    void addExam(Exam exam);
    List<Exam> allExam();
    void deleteExam(String exam_id);
}
