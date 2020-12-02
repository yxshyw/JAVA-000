package com.yw.yw.aop;

import com.yw.yw.annotation.TwoDataSourceAnnotation;
import com.yw.yw.config.DataSourceContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(1)
public class TwoDataSourceAOP {
    @Around("@annotation(two)")
    public Object metric(ProceedingJoinPoint joinPoint, TwoDataSourceAnnotation two) throws Throwable {
        try {
            log.info("two");
            DataSourceContext.setDbType(DataSourceContext.DbType.TWO);
            return joinPoint.proceed();
        } finally {
            DataSourceContext.remove();
            log.info("two end");
        }
    }
}