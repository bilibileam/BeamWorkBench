package com.beam.archtec.test;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class StringTest {

    @Test
    public void test(){
        String test = "    fisrt*    ";
        String result = StringUtils.substringBefore(test.trim(), " ");
        System.out.println(result);
    }
    
}
