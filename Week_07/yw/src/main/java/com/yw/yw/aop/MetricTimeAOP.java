package com.yw.yw.aop;

import com.yw.yw.annotation.MetricTimeAnnotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class MetricTimeAOP {
    @Around("@annotation(metricTime)")
    public Object metric(ProceedingJoinPoint joinPoint, MetricTimeAnnotation metricTime) throws Throwable {
        String name=metricTime.value();
        long start=System.currentTimeMillis();
        log.info("start");
        try{
            return joinPoint.proceed();
        }finally{
            long t=System.currentTimeMillis()-start;
            log.info(name+":"+t);
        }
    }
}