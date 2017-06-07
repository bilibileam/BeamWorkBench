package com.beam.validation.utils.model;

import com.beam.validation.utils.ValidationUtils;



/**
 * Enum of supported operation for {@link ValidationUtils}
 * <br>
 * Supported type list for now :
 * <br>
 * {@code SIMPLE_OPERATION} simplified validation method which can easy transform to a groovy script, quick to compile. E.g., a == b, a <= b,etc.
 * <br>
 * {@code SIMPLE_OPERATION} complicated validation method looks like a java/groovy function which always need to use binding to bind variables
 * @author JAVA_FUNCTION
 */
public enum ValidationOperation {
	EQ("==",OperationType.TWO_PARAM_SIMPLE_OPERATION),
	GT(">",OperationType.TWO_PARAM_SIMPLE_OPERATION),
	LT("<",OperationType.TWO_PARAM_SIMPLE_OPERATION),
	GTE(">=",OperationType.TWO_PARAM_SIMPLE_OPERATION),
	LTE("<=",OperationType.TWO_PARAM_SIMPLE_OPERATION),
	IN_COLLECTION("expected.contains(actual)",OperationType.TWO_PARAM_JAVA_FUNCTION),
	LIST_CONTAINS("actual.contains(expected)",OperationType.TWO_PARAM_JAVA_FUNCTION),
	IS_EMPTY("actual.isEmpty()",OperationType.ONE_PARAM_JAVA_FUNCTION),
	IS_NOT_EMPTY("!actual.isEmpty()",OperationType.ONE_PARAM_JAVA_FUNCTION)
	;
	
	public static class OperationType {
		
		static final OperationType TWO_PARAM_SIMPLE_OPERATION = new OperationType(OperationType.Type.SIMPLE_OPERATION,2);
		static final OperationType TWO_PARAM_JAVA_FUNCTION = new OperationType(OperationType.Type.JAVA_FUNCTION,2);
		static final OperationType ONE_PARAM_JAVA_FUNCTION = new OperationType(OperationType.Type.JAVA_FUNCTION,1);
		
		private Type type;
		private Integer paramsCount;
		
		public enum Type{
			SIMPLE_OPERATION,
			JAVA_FUNCTION
		}

		public OperationType(Type type,Integer paramsCount){
			this.type = type;
			this.paramsCount = paramsCount;
		}
		
		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}

		public Integer getParamsCount() {
			return paramsCount;
		}

		public void setParamsCount(Integer paramsCount) {
			this.paramsCount = paramsCount;
		}
		
	}
	
	private String operation;
	private OperationType type;
	
	private ValidationOperation(String operation,OperationType type){
		this.operation=operation;
		this.type=type;
	}

	public String getOperationValue() {
		return operation;
	}
	
	public OperationType.Type getType(){
		return type.getType();
	}
	
	public Integer getParamsCount(){
		return type.getParamsCount();
	}
	
}
