package com.beam.aop;

import org.springframework.stereotype.Component;

@Component
public class TestClass {

    public void test() throws Exception{
        Exception ex =  new Exception();
        throw ex;
    }
    
}
