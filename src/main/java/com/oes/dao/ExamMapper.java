package com.oes.dao;

import com.oes.bean.Exam;
import com.oes.bean.ExamLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExamMapper {
    Exam findExam(@Param("exam_id") String exam_id);
    void addExam(Exam exam);
    List<Exam> allExam();
    void deleteExam(String exam_id);
    void addExamLog(ExamLog examLog);
}
