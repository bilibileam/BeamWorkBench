package com.beam.validation.utils.model;

import java.util.List;

public enum ValueType {
	INTEGER(Integer.class),
	STRING(String.class),
	OBJECT(Object.class),
	LIST(List.class)
	;
	Class<?> clasz;
	private ValueType(Class<?> clasz){
		this.clasz = clasz;
	}
}
