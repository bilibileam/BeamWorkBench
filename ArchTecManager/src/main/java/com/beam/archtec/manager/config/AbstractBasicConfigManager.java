package com.beam.archtec.manager.config;

import java.util.Collection;

import com.beam.archtec.cache.BasicCacheManager;
import com.beam.archtec.dao.BasicDao;
import com.beam.archtec.dto.CodeEntity;

public abstract class AbstractBasicConfigManager<T extends CodeEntity> implements BasicConfigManager<T>{

    public abstract BasicDao<T> getDao();
    
    public abstract BasicCacheManager<T> getCacheManager();
    
    protected abstract Class<T> getManagedEntityClass();
    
    @Override
    public void updateConfig(Collection<T> configs) {
        getDao().saveOrUpdate(configs);
        getCacheManager().put(configs);
    }
    
    @Override
    public Collection<T> getConfig() {
        return getCacheManager().getAll();
    }
    
    public void initData() {
        Collection<T> configs = getDao().getAllAvailableBeans();
        getCacheManager().put(configs);
    }
    
}
