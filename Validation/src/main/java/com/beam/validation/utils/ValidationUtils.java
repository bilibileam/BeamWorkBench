package com.beam.validation.utils;

import com.beam.validation.utils.model.ValidationObject;
import com.beam.validation.utils.model.ValueObject;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * provide validation functions based on inputed {@link ValidationObject}
 * @author bzhao
 */
public class ValidationUtils {

	private void transformValue(ValidationObject obj){
		transformValue(obj.getActualValue());
		transformValue(obj.getExpectedValue());
	}
	
	private void transformValue(ValueObject obj){
		if(obj.getValue()==null){
			return;
		}
		switch (obj.getType()) {
		case INTEGER:
			obj.setValue(integerValueOfExpression(String.valueOf(obj.getValue())));
			break;
		case STRING:
			obj.setValue(prepareStringValueForExpression(obj.getValue()));
			break;
		default:
			break;
		}
	}
	
	private String prepareStringValueForExpression(Object rawValue){
		StringBuilder sb = new StringBuilder();
		sb.append("\"").append(rawValue).append("\"");
		return sb.toString();
	}
	
	private String buildUpExpression(ValidationObject obj){
		StringBuffer buffer = new StringBuffer();
		
		//two side parameters operation
		buffer.append(obj.getActualValue().getValue()).append(obj.getOperation().getOperationValue()).append(obj.getExpectedValue().getValue());
		
		return buffer.toString();
	}
	
	private Boolean simpleScriptRun(ValidationObject obj){
		Boolean result = false;
		GroovyShell gs = new GroovyShell();
		transformValue(obj);
		result = (Boolean) gs.evaluate(buildUpExpression(obj));
		return result;
	}
	
	private Boolean javaFunctionRun(ValidationObject obj){
		Boolean result = false;
		
		transformValue(obj);
		
		Binding binding = new Binding();
		binding.setVariable("actual", obj.getActualValue().getValue());
		binding.setVariable("expected", obj.getExpectedValue().getValue());
		GroovyShell gs = new GroovyShell(binding);
		result = (Boolean) gs.evaluate(obj.getOperation().getOperationValue());
		return result;
	}
	
	public Boolean validateResultOfValidationObj(ValidationObject obj){
		Boolean result = false;
		switch (obj.getOperation().getType()) {
		case SIMPLE_OPERATION:
			result = simpleScriptRun(obj);
			break;
		case JAVA_FUNCTION:
			result = javaFunctionRun(obj);
			break;
		default:
			break;
		}
		return result;
	}
	
	/**
	 * Get the integer value of a expression.
	 * Use the {@code Integer.valueof(String)} method as default.
	 * Run with groovy script as an alternative method
	 * @param expression
	 * @return the result value as a Integer, 2997/125 will be calculated as 24.
	 */
	private Integer integerValueOfExpression(String expression){
		Integer result = 0;
		boolean finished = false;
		while(!finished){
			try {
				//default method
				result = Integer.valueOf(expression);
				break;
			} catch (NumberFormatException e) {
				try {
					//try with groovy run.
					GroovyShell gs = new GroovyShell();
					String preScript = "Integer result = Math.round(";
					String postScript = ")";
					result = (Integer) gs.evaluate(preScript+expression+postScript);
					break;
				} catch (Exception exInGroovy) {
					throw e;
				}
				//may be other method
			}
		}
		return result;
	}
	
}
