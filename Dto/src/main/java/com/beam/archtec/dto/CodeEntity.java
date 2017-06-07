package com.beam.archtec.dto;

/**
 * CodeEntity provide the business code operation for version control and cache management
 *
 * @author bzhao024
 */
public interface CodeEntity extends BasicEntity {

    void setCode(String code);
    
    String getCode();
}
