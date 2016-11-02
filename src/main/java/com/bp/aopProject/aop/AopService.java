package com.bp.aopProject.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/**
 * 
 * @author current_bp
 * @createTime 20161102
 *
 */
@Aspect
@Component
public class AopService {
	
	@Pointcut("execution(* com.bp.aopProject.service.impl.DosomethhingImpl.sayName(..))")
	public void pointCut(){
		System.out.println("===>pointCut");
	}
	
	@Before("pointCut()")
	public void beforeSay(){
		System.out.println("===>beforeSay");
	}
	
	@After("execution(* com.bp.aopProject.service.impl.DosomethhingImpl.sayName(..))")
	public void afterSay(){
		System.out.println("===>afterSay");
	}

}
