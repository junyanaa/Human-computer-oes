package com.oes.service;

import com.oes.bean.ExamLog;

import java.util.List;

public interface ExamLogService {
    List<ExamLog> allExamLog(String exam_id);
    List<String> findStudent(String exam_id);
    int findLogId(String exam_id,int stu_id);
}
