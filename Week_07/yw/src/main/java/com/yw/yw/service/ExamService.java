package com.yw.yw.service;

import java.util.List;

import com.yw.yw.annotation.OneDataSourceAnnotation;
import com.yw.yw.annotation.TwoDataSourceAnnotation;
import com.yw.yw.config.DataSourceContext;
import com.yw.yw.dao.ExamDao;
import com.yw.yw.mapper.ExamMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExamService {
    @Autowired
    private ExamMapper examMapper;

    public long insertExam(String myVar) {
        examMapper.insertExam(myVar + "212");
        String a = null;
        return examMapper.insertExam(myVar + a.equals("kk"));
    }

    @Transactional
    public long insertExamTran(String myVar) {
        examMapper.insertExam(myVar + "212");
        String a = null;
        return examMapper.insertExam(myVar + a.equals("kk"));
    }

    @OneDataSourceAnnotation
    public List<ExamDao> findAllOne() {
        return examMapper.findAall();
    }

    @TwoDataSourceAnnotation
    public List<ExamDao> findAllTwo() {
        return examMapper.findAall();
    }
}