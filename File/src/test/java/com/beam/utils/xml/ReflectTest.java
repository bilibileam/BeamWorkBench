package com.beam.utils.xml;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.testng.annotations.Test;

public class ReflectTest {

	@Test
	public void dummy() throws Exception{
		DummyClass dummyClass = new DummyClass();
		
		Class<?>[] claszs = DummyClass.class.getDeclaredClasses();
		Class<?> clasz = claszs[0];
		Class<?> innerClasz = Class.forName("com.beam.utils.xml.DummyClass$InnerDummy");
		
		Constructor<?> constructor = innerClasz.getDeclaredConstructors()[0];
		constructor.setAccessible(true);
		Object obj = constructor.newInstance();
		
		Field inner = DummyClass.class.getDeclaredField("diaobao");
		inner.setAccessible(true);
		inner.set(dummyClass, obj);
		
		Field field = obj.getClass().getDeclaredField("wocao");
		field.setAccessible(true);
		field.set(obj, "test");
		System.out.println(dummyClass.output());
	}
	
}
