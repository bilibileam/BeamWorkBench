package com.beam.archtec.column;

public enum EmptyBasicColumns implements BasicColumns {

    EMPTY_COLUMN(null, null);
    
    private Class<?> columnClass;
    private String columnName;

    private EmptyBasicColumns(String name, Class<?> clazz) {
        this.columnName = name;
        this.columnClass = clazz;
    }
    
    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public Class<?> getColumnClass() {
        return columnClass;
    }

}
