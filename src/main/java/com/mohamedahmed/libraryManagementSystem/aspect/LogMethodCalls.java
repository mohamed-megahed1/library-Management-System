package com.mohamedahmed.libraryManagementSystem.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogMethodCalls {


    @Before("execution(* com.mohamedahmed.libraryManagementSystem.service.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("Entering method: " + joinPoint.getSignature().getName()+ "()");
    }

    @After("execution(* com.mohamedahmed.libraryManagementSystem.service.*.*(..))")
    public void logAfterMethod(JoinPoint joinPoint) {
        log.info("Exiting method: " + joinPoint.getSignature().getName()+ "()");
    }





}
