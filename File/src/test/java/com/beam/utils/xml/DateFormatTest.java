package com.beam.utils.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class DateFormatTest {
    @Test
    public void f() throws FileNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("Y-MM-dd-HH-mm");
        System.out.println(sdf.format(new Date()));
    }
    
    @Test
    public void file() throws FileNotFoundException {
        FileOutputStream os = new FileOutputStream("C:/W/TEST/c.txt");
    }
    
    @Test
    public void file2() {
        File dir = new File("C:/W/TEST2");
        System.out.println(dir.exists());
    }
}
