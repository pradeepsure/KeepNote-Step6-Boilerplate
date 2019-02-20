package com.stackroute.keepnote.aspectj;

import java.util.Date;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;

/* Annotate this class with @Aspect and @Component */

public class LoggingAspect {
	/*
	 * Write loggers for each of the methods of User controller, any particular
	 * method will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
	@Before("execution(com.stackroute.keepnote.controller.*)")
	public void logBefore() {
		System.out.println("@Before:" + new Date());
	}

	@After("execution(com.stackroute.keepnote.controller.*)")
	public void logAfter() {
		System.out.println("@After:" + new Date());
	}

	@AfterThrowing(pointcut = "execution(com.stackroute.keepnote.controller.*)", throwing = "exception")
	public void logAfterThrowing(Exception exception) {
		System.out.println("@AfterReturning:" + new Date());
		System.out.println("Exception caught:" + exception.getMessage());
	}

	@AfterReturning(pointcut = "execution(com.stackroute.keepnote.controller.*)", returning = "val")
	public void logAfterReturning(Object val) {
		System.out.println("Method return value:" + val);
		System.out.println("@AfterReturning:" + new Date());
	}
}
