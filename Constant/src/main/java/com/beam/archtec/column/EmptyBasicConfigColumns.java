package com.beam.archtec.column;

public enum EmptyBasicConfigColumns implements BasicConfigColumns {
    EMPTY_COLUMN(null, null,null);

    private Class<?> columnClass;
    private String columnName;
    private String configName;

    private EmptyBasicConfigColumns(String name, String title, Class<?> clazz) {
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
