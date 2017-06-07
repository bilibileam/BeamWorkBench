package com.beam.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BeforeExceptionAspect {
    @Before("call(com.beam.aop.TestClass.*)")
    public void createAlert(){
        System.out.println("Test**********");
    }
    
    @Before("execution(com.beam.aop.TestClass.*)")
    public void createAlert2(){
        System.out.println("Test**********");
    }
}
