package com.mohamedahmed.libraryManagementSystem.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogTime {

    @Around("execution(* com.mohamedahmed.libraryManagementSystem.service.*.*(..))")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - start;
        log.info("Time taken to finish method: " + joinPoint.getSignature().getName() + "() " + timeTaken + "ms");

        return result;
    }
}
