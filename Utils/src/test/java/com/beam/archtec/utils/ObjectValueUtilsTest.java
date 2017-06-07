package com.beam.archtec.utils;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.beam.CoreException;
import com.beam.archtec.column.BasicConfigColumns;

public class ObjectValueUtilsTest {

    private static final String TEST_STRING = "test";
    private static final Long TEST_LONG = 2l;
    private static final Integer TEST_INTEGER = 1;
    private Map<String, Object> values;
    private TestingObject testingObject;
    
    private static class TestingObject {
        private String stringField;
        private Long longField;
        private Integer integerField;
        /**
         * the get method of stringField
         * @return the stringField
         */
        public String getStringField() {
            return stringField;
        }
        /**
         * the set method of the stringField
         * @param stringField the stringField to set
         */
        public void setStringField(String stringField) {
            this.stringField = stringField;
        }
        /**
         * the get method of longField
         * @return the longField
         */
        public Long getLongField() {
            return longField;
        }
        /**
         * the set method of the longField
         * @param longField the longField to set
         */
        public void setLongField(Long longField) {
            this.longField = longField;
        }
        /**
         * the get method of integerField
         * @return the integerField
         */
        public Integer getIntegerField() {
            return integerField;
        }
        /**
         * the set method of the integerField
         * @param integerField the integerField to set
         */
        public void setIntegerField(Integer integerField) {
            this.integerField = integerField;
        }
        
    }
    
    private enum TestingBasicConfigColumns implements BasicConfigColumns {
        LONG_FIELD("LongField", "Long Column",Long.class),
        INTEGER_FIELD("integerField", "Integer Column",Integer.class),
        STRING_FIELD("StringField", "String Column",String.class);

        private Class<?> columnClass;
        private String columnName;
        private String configName;

        private TestingBasicConfigColumns(String name, String title, Class<?> clazz) {
            this.columnName = name;
            this.columnClass = clazz;
            this.configName = title;
        }

        @Override
        public String getColumnName() {
            return columnName;
        }

        @Override
        public Class<?> getColumnClass() {
            return columnClass;
        }

        @Override
        public String getConfigName() {
            return configName;
        }

    }
    
    @BeforeTest
    public void init() {
        values = new HashMap<String, Object>();
        values.put("String Column", TEST_STRING);
        values.put("Long Column", TEST_LONG);
        values.put("Integer Column", TEST_INTEGER);
        
        testingObject = new TestingObject();
        testingObject.setIntegerField(TEST_INTEGER);
        testingObject.setLongField(TEST_LONG);
        testingObject.setStringField(TEST_STRING);
    }
    
    @Test
    public void shouldFullFillValue() throws CoreException {
        TestingObject readyToFullFillObject = new TestingObject();
        ObjectValueUtils.fullFillObjectValue(readyToFullFillObject, values, TestingBasicConfigColumns.class);
        assertEquals(readyToFullFillObject.getIntegerField(), TEST_INTEGER);
        assertEquals(readyToFullFillObject.getStringField(), TEST_STRING);
        assertEquals(readyToFullFillObject.getLongField(), TEST_LONG);
    }
    
    @Test
    public void shouldExtractObjectValue() throws CoreException {
        Map<String, Object> readyToExtract = new HashMap<>();
        ObjectValueUtils.extractObjectValue(testingObject, readyToExtract, TestingBasicConfigColumns.class);
        assertEquals(readyToExtract.get("Integer Column"), TEST_INTEGER);
        assertEquals(readyToExtract.get("String Column"), TEST_STRING);
        assertEquals(readyToExtract.get("Long Column"), TEST_LONG);
    }
}
