package com.beam.utils.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class ByteTest {

    private byte[] test = {(byte) 0xEF,(byte) 0xBB,(byte) -65};
    
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        byte[] test = {(byte) 0xEF,(byte) 0xBB,(byte) -65};
        byte[] test2 = {(byte) 0xEF,(byte) 0xBB,(byte) 63};
        String str = new String(test, "UTF-8");
        System.out.println(str);
        String str2 = new String(test2, "UTF-8");
        System.out.println(str2);
        File f = new File("C:/W/Test/a.txt");
        FileOutputStream fs = new FileOutputStream(f);
        try {
            byte[] a = str.getBytes("UTF-8");
            System.out.println(a);
            fs.write(a);
        } catch (IOException e) {
        } finally {
            fs.close();
        }
        BufferedReader reader = new BufferedReader(new FileReader(f));
        byte[] b = reader.readLine().getBytes();
        System.out.println(b);
        reader.close();
    }

}
