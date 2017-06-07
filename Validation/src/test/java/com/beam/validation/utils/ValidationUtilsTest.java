package com.beam.validation.utils;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

import com.beam.validation.utils.model.ValidationObject;
import com.beam.validation.utils.model.ValidationOperation;
import com.beam.validation.utils.model.ValueObject;
import com.beam.validation.utils.model.ValueType;

public class ValidationUtilsTest {
	ValidationUtils validationUtils = new ValidationUtils();
	@Test
	public void integerValueOfExpression() throws ScriptException{
		ScriptEngineManager sef = new ScriptEngineManager();
		ScriptEngine engine = sef.getEngineByName("js");
		engine.eval("var a=1;var b=2;var c = a+b;var d = a+c");
		System.out.println(engine.get("c"));
	}
	
	@Test
	public void validationTest(){
		ValueObject actual = new ValueObject();
		actual.setValue("2997/125");
		actual.setType(ValueType.INTEGER);
		ValueObject expect = new ValueObject();
		expect.setValue("24");
		expect.setType(ValueType.INTEGER);
		ValidationObject vo = new ValidationObject();
		vo.setActualValue(actual);
		vo.setExpectedValue(expect);
		vo.setOperation(ValidationOperation.LTE);
		assertTrue(validationUtils.validateResultOfValidationObj(vo));
	}
	
	@Test
	public void validationTest1(){
		ValueObject actual = new ValueObject();
		actual.setValue("16:9");
		actual.setType(ValueType.STRING);
		ValueObject expect = new ValueObject();
		expect.setValue("16:9");
		expect.setType(ValueType.STRING);
		ValidationObject vo = new ValidationObject();
		vo.setActualValue(actual);
		vo.setExpectedValue(expect);
		vo.setOperation(ValidationOperation.EQ);
		assertTrue(validationUtils.validateResultOfValidationObj(vo));
	}
	
	@Test
	public void validationTest2(){
		List<String> codeList = new ArrayList<String>();
		codeList.add("h264");
		codeList.add("aac");
		ValueObject actual = new ValueObject();
		actual.setValue(codeList);
		actual.setType(ValueType.LIST);
		ValueObject expect = new ValueObject();
		expect.setValue("aac");
		expect.setType(ValueType.OBJECT);
		ValidationObject vo = new ValidationObject();
		vo.setActualValue(actual);
		vo.setExpectedValue(expect);
		vo.setOperation(ValidationOperation.LIST_CONTAINS);
		assertTrue(validationUtils.validateResultOfValidationObj(vo));
	}
	
	@Test
	public void validationTest3(){
		List<String> codeList = new ArrayList<String>();
		codeList.add("h264");
		codeList.add("aac");
		ValueObject actual = new ValueObject();
		actual.setValue(codeList);
		actual.setType(ValueType.LIST);
		ValueObject expect = new ValueObject();
		ValidationObject vo = new ValidationObject();
		vo.setActualValue(actual);
		vo.setExpectedValue(expect);
		vo.setOperation(ValidationOperation.IS_NOT_EMPTY);
		assertTrue(validationUtils.validateResultOfValidationObj(vo));
	}
	
}
