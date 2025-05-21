package com.exam.exam_api.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    public LoggingAspect() {
        logger.info("LoggingAspect initialized");
    }

    @Before("execution(* com.exam.exam_api.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", 
            joinPoint.getSignature().getName(), joinPoint.getArgs());
    }
    
    @AfterReturning(pointcut = "execution(* com.exam.exam_api.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result: {}", 
            joinPoint.getSignature().getName(), result);
    }
}