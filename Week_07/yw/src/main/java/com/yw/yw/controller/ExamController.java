package com.yw.yw.controller;

import java.util.List;

import com.yw.yw.annotation.MetricTimeAnnotation;
import com.yw.yw.annotation.OneDataSourceAnnotation;
import com.yw.yw.config.DataSourceContext;
import com.yw.yw.dao.ExamDao;
import com.yw.yw.service.ExamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("metric/")
public class ExamController {
    @Autowired
    private ExamService eService;

    @GetMapping("myvar")
    @MetricTimeAnnotation("name")
    public long s(@RequestParam String myVar) {
        log.info("mid");
        return eService.insertExam(myVar);
    }

    @GetMapping("myvarTran")
    @MetricTimeAnnotation("nameTran")
    public long sTran(@RequestParam String myVar) {
        log.info("midTran");
        return eService.insertExamTran(myVar);
    }

    @GetMapping("one")
    public List<ExamDao> findAllOne() {
        return eService.findAllOne();
    }

    @GetMapping("two")
    public List<ExamDao> findAllTwo() {
        return eService.findAllTwo();
    }
}