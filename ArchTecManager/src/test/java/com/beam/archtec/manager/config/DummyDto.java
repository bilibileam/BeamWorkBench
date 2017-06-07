package com.beam.archtec.manager.config;

import java.util.Calendar;

import com.beam.archtec.column.BasicConfigColumns;
import com.beam.archtec.dto.CodeEntity;
import com.beam.archtec.dto.IdEntity;

public class DummyDto implements IdEntity<Long>, CodeEntity{

    /**
     */
    private static final long serialVersionUID = 8960314302255977173L;
    
    private Long id;
    
    private String code;

    private String dummyString;
    
    private Calendar dummyCalendar;

    
    /**
     * the get method of dummyString
     * @return the dummyString
     */
    public String getDummyString() {
        return dummyString;
    }



    /**
     * the set method of the dummyString
     * @param dummyString the dummyString to set
     */
    public void setDummyString(String dummyString) {
        this.dummyString = dummyString;
    }



    /**
     * the get method of dummyCalendar
     * @return the dummyCalendar
     */
    public Calendar getDummyCalendar() {
        return dummyCalendar;
    }



    /**
     * the set method of the dummyCalendar
     * @param dummyCalendar the dummyCalendar to set
     */
    public void setDummyCalendar(Calendar dummyCalendar) {
        this.dummyCalendar = dummyCalendar;
    }



    public enum DummyDtoColumns implements BasicConfigColumns {

        ID("Id","dummy id", Long.class),
        CODE("Code","dummy code", String.class),
        DUMMY_STRING("DummyString","dummy string", String.class),
        DUMMY_CALENDAR("DummyCalendar","dummy calendar", Calendar.class)
        ;
        
        private Class<?> columnClass;
        private String columnName;
        private String configName;

        private DummyDtoColumns(String name, String title, Class<?> clazz) {
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



    @Override
    public void setCode(String code) {
        this.code = code;
    }



    @Override
    public String getCode() {
        return code;
    }



    @Override
    public Long getId() {
        return id;
    }



    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    /* (non-Javadoc)
     * @see com.beam.archtec.dto.AbstractIdEntity#hashCode()
     */
    @Override
    public int hashCode() {
        int result = 1;
        int elementHash = (int)(id ^ (id >>> 32));
        result = 31 * result + elementHash;
        return result;
    }
}
