package com.oes.service.impl;

import com.oes.bean.ExamLog;
import com.oes.dao.ExamLogMapper;
import com.oes.service.ExamLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamLogServiceImpl implements ExamLogService {
    @Autowired
    private ExamLogMapper examLogMapper;
    @Override
    public List<ExamLog> allExamLog(String exam_id) {
        return examLogMapper.allExamLog(exam_id);
    }
    public List<String> findStudent(String exam_id){
        return examLogMapper.findStudent(exam_id);
    }
}
