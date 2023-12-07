package com.oes.dao;

import com.oes.bean.ExamLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExamLogMapper {
    List<ExamLog> allExamLog(String exam_id);
    List<String> findStudent(String exam_id);
    int findLogId(@Param("exam_id")String exam_id, @Param("stu_id") int stu_id);
}
