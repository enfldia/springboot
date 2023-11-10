package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    //특정 어노테이션을 대상 지정
    @Pointcut("@annotation(com.example.firstproject.annotation.RunningTime)")
    private  void enableRunningTime(){} // 어떤 어노테이션을 사용할지


    @Pointcut("execution(* com.example.firstproject..*.*(..))")
    private void cut(){} // 어떤 메소드를 대상으로 할지

    @Around("cut() && enableRunningTime()") //메소드 전후 부가기능을 삽입
    public void loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //메소드 수행 전
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();  //스탑워치 시작
        //메소드 수행
        Object returningObj = joinPoint.proceed();
        //메소드 종료 후
        stopWatch.stop();   //스탑워치 종료
        String methodName = joinPoint.getSignature()
                .getName();
        log.info("{}의 총 수행 시간 => {} sec",methodName,stopWatch.getTotalTimeSeconds());
    }
}
