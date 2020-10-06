package com.mercury.SpringBootRESTDemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@Aspect  // joint point + advice
public class WhichWhenTestMathodAspect {
    @Pointcut("execution(* com.mercury.SpringBootRESTDemo.test.*.*(..))")
    public void getJoinPoint() {

    }

    @Before("getJoinPoint()")
    public void printAdvice(JoinPoint joinPoint) {
        System.out.println("current method: " +
                joinPoint.getSignature().getName()
                + ", current time: " +
                ZonedDateTime.now(ZoneId.of("Asia/Shanghai")));
    }
}
