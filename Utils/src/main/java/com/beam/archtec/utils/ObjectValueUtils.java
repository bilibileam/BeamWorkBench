package com.beam.archtec.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.beam.CoreException;
import com.beam.archtec.column.BasicConfigColumns;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author bzhao024
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ObjectValueUtils {

    private static String getMethod(String field) {
        return "get"+StringUtils.capitalize(field);
    }
    
    private static String setMethod(String field) {
        return "set"+StringUtils.capitalize(field);
    }
    
    public static <C extends BasicConfigColumns> void fullFillObjectValue(Object object,Map<String,Object> values,Class<C> columnClass) throws CoreException{
        for (BasicConfigColumns column : columnClass.getEnumConstants()) {
            String fieldName = column.getColumnName();
            String configName = column.getConfigName();
            Object valueToPut = values.get(configName);
            setFieldValue(object, fieldName, valueToPut, column.getColumnClass());
        }
    }
    
    public static <C extends BasicConfigColumns> void extractObjectValue(Object object,Map<String,Object> values,Class<C> columnClass) throws CoreException{
        for (BasicConfigColumns column : columnClass.getEnumConstants()) {
            String fieldName = ((BasicConfigColumns)column).getColumnName();
            String configName = ((BasicConfigColumns)column).getConfigName();
            Object valueToPut = getFieldValue(object, fieldName);
            values.put(configName,valueToPut);
        }
    }
    
    public static Object getFieldValue(Object object,String fieldName) throws CoreException{
        try {
            Method method = object.getClass().getMethod(getMethod(fieldName));
            return method.invoke(object);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            try {
                Field field = object.getClass().getField(fieldName);
                field.get(object);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
                throw new CoreException(e1);
            }
        } catch (InvocationTargetException e) {
            throw new CoreException(e);
        }
        return null;
    }
    
    public static void setFieldValue(Object object,String fieldName, Object value, Class<?> clasz) throws CoreException {
        try {
            Method method = object.getClass().getMethod(setMethod(fieldName), clasz);
            method.invoke(object, value);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException e) {
            try {
                Field field = object.getClass().getField(fieldName);
                field.set(object, value);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
                throw new CoreException(e1);
            }
        } catch (InvocationTargetException e) {
            throw new CoreException(e);
        }
    }
}
