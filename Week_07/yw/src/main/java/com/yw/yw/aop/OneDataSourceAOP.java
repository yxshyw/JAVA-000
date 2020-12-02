package com.yw.yw.aop;

import com.yw.yw.annotation.OneDataSourceAnnotation;
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
public class OneDataSourceAOP {
    @Around("@annotation(one)")
    public Object metric(ProceedingJoinPoint joinPoint, OneDataSourceAnnotation one) throws Throwable {
        try {
            log.info("one");
            DataSourceContext.setDbType(DataSourceContext.DbType.ONE);
            return joinPoint.proceed();
        } finally {
            DataSourceContext.remove();
            log.info("one end");
        }
    }
}