package com.beam.validation.utils.model;


/**
 * Base Validation Object to execute common validation function and get result
 * actualValue and validation operation is always required, expectedValue is required if operation have two parameters
 * @author bzhao
 */
public class ValidationObject {

	private String name;
	private ValueObject actualValue;
	private ValueObject expectedValue;
	private ValidationOperation operation;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ name : ");
		builder.append(name);
		builder.append(" , actualValue : ");
		builder.append(actualValue.getValue());
		builder.append(" , operation :");
		builder.append(operation.getOperationValue());
		builder.append(" , expectedValue =");
		builder.append(expectedValue.getValue());
		builder.append(" }");
		return builder.toString();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ValueObject getActualValue() {
		return actualValue;
	}
	public void setActualValue(ValueObject actualValue) {
		this.actualValue = actualValue;
	}
	public ValueObject getExpectedValue() {
		return expectedValue;
	}
	public void setExpectedValue(ValueObject expectedValue) {
		this.expectedValue = expectedValue;
	}
	public ValidationOperation getOperation() {
		return operation;
	}
	public void setOperation(ValidationOperation operation) {
		this.operation = operation;
	}
	
}
