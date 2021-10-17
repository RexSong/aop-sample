package org.rex.aop.sample.application.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.rex.aop.sample.application.services.HeartBeatServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

  @Autowired
  private HeartBeatServices heartBeatServices;

  @Pointcut("execution(* org.rex.aop.sample.application.services.Performer.perform(..))")
  public void point(){}

  @Around("point()")
  public void around(ProceedingJoinPoint pj) throws Throwable {
    try {
      pj.proceed();
      heartBeatServices.updateApiComponentToOperational("page id", "component id");
    } catch (Throwable throwable) {
      System.out.println("error occur");
      heartBeatServices.createIncident( throwable.getMessage(), throwable.getStackTrace(), "page id", new String[]{"component id 1", "component id 2"});
      throw throwable;
    }
  }
}
