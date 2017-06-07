package com.beam.utils.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.testng.annotations.Test;

public class NewTest {
    @Test
    public void f() throws FileNotFoundException {
	File f = new File("C:/T");
	System.out.println(f.listFiles());
	File a = f.listFiles()[0];
	BufferedReader reader = new BufferedReader(new FileReader(a));
	InputStream inputStream = new FileInputStream(a);
	StringBuilder sb = new StringBuilder();
  }
}
