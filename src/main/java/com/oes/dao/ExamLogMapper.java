package com.oes.dao;

import com.oes.bean.ExamLog;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExamLogMapper {
    List<ExamLog> allExamLog(String exam_id);
    List<String> findStudent(String exam_id);
}
