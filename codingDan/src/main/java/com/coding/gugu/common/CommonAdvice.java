package com.coding.gugu.common;

import java.util.Arrays;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CommonAdvice
{
	private static final Logger log = LoggerFactory.getLogger(CommonAdvice.class);
	
	@Before("execution(* com.coding.gugu..*Service.*(..))")
	public void startLog(JoinPoint jp)
	{
		log.info("aspectj Before check parameters");
		log.info(Arrays.toString(jp.getArgs()));
		
		log.info("aspectj Before check kind");
		log.info(jp.getKind());
		
		log.info("aspectj Before check signature");
		log.info(jp.getSignature().toString());
		
		log.info("aspectj Before check target");
		log.info(jp.getTarget().toString());
		
		log.info("aspectj Before check this");
		log.info(jp.getThis().toString());
	}
	
	@Around("execution(* com.coding.gugu..*Service.*(..))")
	public Object aroundLog(ProceedingJoinPoint jp) throws Throwable
	{
		long start = System.currentTimeMillis();
		
		Object result = jp.proceed();
		
		log.info("aspectj Around check proceed result");
		log.info(Optional.ofNullable(result).orElse("").toString());
		
		long end = System.currentTimeMillis();
		
		log.info("aspectj Around check time");
		log.info(jp.getSignature().toString() + " : " + (end-start) + "ms");
		
		return result;
	}

}
