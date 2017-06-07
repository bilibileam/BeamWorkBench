package com.beam.validation.utils.model;


public class ValueObject {

	private Object value;
	private ValueType type;
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public ValueType getType() {
		return type;
	}
	public void setType(ValueType type) {
		this.type = type;
	}
}
