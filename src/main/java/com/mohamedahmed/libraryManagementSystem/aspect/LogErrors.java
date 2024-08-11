package com.mohamedahmed.libraryManagementSystem.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogErrors {

    @AfterThrowing(pointcut = "execution(* com.mohamedahmed.libraryManagementSystem.service.*.*(..))", throwing = "error")
    public void logException(JoinPoint joinPoint, Throwable error) {
        log.error(error.getClass().getName() +" in method: "+ joinPoint.getSignature().getName() + "() with cause = "
                + error.getCause() + " and message = " + error.getMessage() );
    }


}
