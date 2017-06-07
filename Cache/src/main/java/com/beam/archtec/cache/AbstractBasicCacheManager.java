package com.beam.archtec.cache;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.beam.archtec.dto.CodeEntity;

public abstract class AbstractBasicCacheManager<T extends CodeEntity> implements BasicCacheManager<T> {

    static Logger logger = Logger.getLogger(AbstractBasicCacheManager.class);
    
    protected abstract Class<T> getManagedClass();
    
    protected abstract String getCacheName();
    
    protected Cache getCache() {
        CacheManager cm = CacheManager.getInstance();
        Cache cache = cm.getCache(getCacheName());
        if (cache == null) {
            cm.addCache(getCacheName());
        }
        return cache;
    }
    
    @Override
    public void put(String key, T value) {
        Element element = new Element(key, value);
        getCache().put(element);
    }

    @Override
    public void put(T bean) {
        put(bean.getCode(), bean);
    }
    
    @Override
    public void put(Collection<T> beans) {
        for (T bean : beans) {
            put(bean);
        }
    }
    
    @Override
    public void remove(String key) {
        getCache().remove(key);
    }

    @Override
    public void removeAll() {
        getCache().removeAll();
    }

    @Override
    public T get(String key) {
        Element element = getCache().get(key);
        if (element!=null) {
            return getManagedClass().cast(element.getObjectValue());
            
        }
        return null;
    }
    
    @Override
    public Collection<T> getAll(){
        Collection<T> result = new ArrayList<T>();
        for(Object key : getCache().getKeysWithExpiryCheck()) {
            result.add(getManagedClass().cast(getCache().get(key).getObjectValue()));
        }
        return result;
    }

}
