package com.beam.archtec.dto;

public interface IdEntity<I> extends BasicEntity{

    I getId();
    
    void setId(I id);
}
