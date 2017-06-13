package com.mycompany.myapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@Aspect
public class LoginCheckAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginCheckAspect.class);
	
	//pointcut
	@Pointcut("execution(public * com.mycompany.myapp.controller.Exam14AopController.exam02*(..))")
	private void loginCheckMethod(){}
	
	//Advice
	@Around("loginCheckMethod()")
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		//before code
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		String mid = (String) requestAttributes.getAttribute("mid", RequestAttributes.SCOPE_SESSION);
		
		LOGGER.info(mid);
		
		if(mid == null){
			requestAttributes.setAttribute("loginNeed", "로그인이 필요합니다.", RequestAttributes.SCOPE_REQUEST);
			return "aop/exam02LoginForm";
		}else{
		
			Object result = joinPoint.proceed();
			LOGGER.info(String.valueOf(result));
			return result;
		}
		//after code
		
		
		
	}
	
}
