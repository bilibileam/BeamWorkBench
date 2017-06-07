package com.beam.archtec.utils;

public class TypeUtils {

    public static <F> F castValue(Class<F> clasz,Object value){
        return castValue(clasz, value, null);
    }
    
    public static <F> F castValue(Class<F> clasz,Object value,String format){
        Object result = null;
        try {
            return clasz.cast(value);
        } catch (Exception e) {
            if(clasz == String.class){
                result = String.valueOf(value);
            }else if(clasz == Double.class){
                result = Double.valueOf((String) value);
            }else {
                // can't cast value
            }
        }
        return clasz.cast(result);
    }
    
}
