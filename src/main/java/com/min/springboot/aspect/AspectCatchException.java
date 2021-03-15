package com.min.springboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/6/17 16:50
 */
@Aspect
@Component
public class AspectCatchException {

    @Pointcut("execution(* com.min.springboot.service.AspectService.*(..))")
    public void exceptionAop(){
    }

    @AfterThrowing(value = "exceptionAop()",throwing = "e")
    public String throwAop(JoinPoint joinPoint, Throwable e){

        System.out.println("aspect 异常处理");
        return AspectCatchException.class.getName();
    }

    /*@Around(value = "exceptionAop()")
    public void throwAop(JoinPoint joinPoint){

        System.out.println("aspect around");

    }*/
}
