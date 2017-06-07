package com.beam.bmp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        File f1 = new File("C:/W2/tmp/test2c.bmp");
        FileInputStream fis1 = new FileInputStream(f1);
//        File f2 = new File("C:/W2/tmp/test2.bmp");
//        File f3 = new File("C:/W2/tmp/test2c.bmp");
//        BufferedReader reader1 = null;
//        BufferedReader reader2 = null;
//        BufferedReader reader3 = null;
        try {
//            reader1 = new BufferedReader(new FileReader(f1));
//            reader2 = new BufferedReader(new FileReader(f2));
//            reader3 = new BufferedReader(new FileReader(f3));
//            byte[] b1 = reader1.readLine().getBytes();
//            byte[] b2 = reader2.readLine().getBytes();
//            byte[] b3 = reader3.readLine().getBytes();
//            System.out.println(reader1.lines().count());
//            System.out.println(b1.length);
//            System.out.println(reader2.lines().count());
//            System.out.println(b2.length);
//            System.out.println(reader3.lines().count());
//            System.out.println(b3.length);
              int c;
              while((c=fis1.read())!=-1){
                  System.out.println(c);
              }
//            for (int i = 0; i < b1.length; i++) {
//                System.out.println(b1[i]);
////                if(b1[i]!=b2[i]){
////                    System.out.println(i);
////                    break;
////                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        } finally{
//            reader1.close();
//            reader2.close();
//            reader3.close();
        }
    }
    
}
