package com.beam.archtec.dao;

import java.util.Collection;

import com.beam.archtec.dto.BasicEntity;

public interface BasicDao<T extends BasicEntity> {

    void saveOrUpdate(T bean);
    
    void saveOrUpdate(Collection<T> beans);
    
    Collection<T> getAllAvailableBeans();
    
}
