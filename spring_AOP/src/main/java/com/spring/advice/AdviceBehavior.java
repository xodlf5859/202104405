
package com.spring.advice;

import org.aspectj.lang.ProceedingJoinPoint;

public class AdviceBehavior {

	//joinpoint : before/ after
	public void chikachika() {
		System.out.println("양치 하자 영 차 영 차");
	}
	
	
	//joinpoint : around
	public void chikachikaAround(ProceedingJoinPoint joinpoint) throws Throwable{
		System.out.println("함닦았다");
		
		joinpoint.proceed();
		
		System.out.println("또 닦아라 마");
	}
	
	
}
