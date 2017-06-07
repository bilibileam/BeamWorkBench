package com.beam.archtec.cache;

import java.util.Collection;

import com.beam.archtec.dto.CodeEntity;

public interface BasicCacheManager<T extends CodeEntity> {

    /**
     * put element into cache
     * 
     * @param key
     * @param value
     */
    void put(String key, T value);

    void put(Collection<T> beans);
    
    void put(T bean);
    
    /**
     * remove element from cache〉
     * 
     * @param key
     */
    void remove(String key);

    /**
     * remove all elements from cache
     * 
     */
    void removeAll();

    /**
     * get cached element
     * 
     * @param key
     * @return
     */
    T get(String key);

    Collection<T> getAll();
    
}
