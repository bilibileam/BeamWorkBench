package com.beam.file.utils;

import java.io.UnsupportedEncodingException;

public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
        // TODO Auto-generated method stub
        byte[] a = EncodingUtils.getBOM("UTF-8").getBytes();
        System.out.println();
    }

}
