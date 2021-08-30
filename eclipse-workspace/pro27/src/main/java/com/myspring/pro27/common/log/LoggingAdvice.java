package com.myspring.pro27.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;

@Component
@Aspect
public class LoggingAdvice {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	//target �޼ҵ��� �Ķ���� �� ������ ���
	/* @Before("execution(* com.myspring.pro27.member.service*.*(..))or"
	 + "execution(* com.myspring.pro27.*.dao.*.*(..))")  */
	@Before("execution(* com.myspring.pro27.member.service*.*(..)) or "
		 + "execution(* com.myspring.pro27.*.dao.*.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("------------------------------------------------");
		logger.info("------------------------------------------------");
		
		/* ���޵Ǵ� ��� �Ķ���͵��� object�� �迭�� ������ */
		logger.info("1:"+Arrays.toString(jp.getArgs()));
		
		//�ش� advice�� Ÿ���� �˾Ƴ�
		logger.info("2:"+jp.getKind());
		
		//�����ϴ� ��� ��ü�� �޼ҵ忡 ���� ���� �˾Ƴ��� ���
		logger.info("3:"+jp.getSignature().getName());
		
		//target ��ü�� �˾Ƴ��� ���
		logger.info("4:"+jp.getTarget().toString());
		
		//advice�� ���ϴ� ��ü�� �˾Ƴ��� ���
		logger.info("5:"+jp.getThis().toString());
	}
	
	/*@After("execution(* com.myspring.pro27.member.service.*.*(..)) or "
	 + "execution(* com.myspring.pro27.member.*.dao.*.*(..))") */
	@After("execution(* com.myspring.pro27.member.service.*.*(..)) or "
			 + "execution(* com.myspring.pro27.member.*.dao.*.*(..))")
	
	public void after(JoinPoint jp) {
		logger.info("------------------------------------------------");
		logger.info("------------------------------------------------");
		
		/* ���޵Ǵ� ��� �Ķ���͵��� object�� �迭�� ������ */
		logger.info("1:"+Arrays.toString(jp.getArgs()));
		
		//�ش� advice�� Ÿ���� �˾Ƴ�
		logger.info("2:"+jp.getKind());
		
		//�����ϴ� ��� ��ü�� �޼ҵ忡 ���� ���� �˾Ƴ��� ���
		logger.info("3:"+jp.getSignature().getName());
		
		//target ��ü�� �˾Ƴ��� ���
		logger.info("4:"+jp.getTarget().toString());
		
		//advice�� ���ϴ� ��ü�� �˾Ƴ��� ���
		logger.info("5:"+jp.getThis().toString());
	}
	
	//target �޼ҵ��� ���� �ð��� ����
	/* @Around("execution(* com.myspring.pro27.member.service.*.*(..)) or "
	 	+ "execution(* com.myspring.pro27.member.dao.*.*(..))") */
	@Around("execution(* com.myspring.pro27.member.service.*.*(..)) or "
		 	+ "execution(* com.myspring.pro27.member.dao.*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));
		
		//���� Ÿ���� �����ϴ� �κ�. �� �κ��� ������ advice�� ����� �޼ҵ尡 �������� ����
		Object result = pjp.proceed(); //proceed�� exception���� ���� throwable�� ó���ؾ� ��
		
		long endTime = System.currentTimeMillis();
		//target�޼ҵ��� ���� �ð��� ���
		logger.info(pjp.getSignature().getName() + ":" + (endTime -startTime));
		logger.info("==================================");
		
		//around�� ����� ��� �ݵ�� object�� �����ؾ���
		return result;
	}
	}