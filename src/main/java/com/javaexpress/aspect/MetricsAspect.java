package com.javaexpress.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class MetricsAspect {

	@Around(value = "@annotation(com.javaexpress.aspect.LogExecutionTime)")
	public Object methodExecTImeLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch(getClass().getSimpleName());
		try {
			
			stopWatch.start(proceedingJoinPoint.getSignature().getName());
			return proceedingJoinPoint.proceed();
		}finally {
			stopWatch.stop();
			String name = proceedingJoinPoint.getSignature().getName();
			log.info("LogType=metrics {}={} sec",name,stopWatch.getTotalTimeSeconds());
		}
	}
}
