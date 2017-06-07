package com.beam.archtec.manager.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.beam.CoreException;
import com.beam.archtec.column.BasicConfigColumns;
import com.beam.archtec.dto.CodeEntity;
import com.beam.archtec.utils.ObjectValueUtils;

public abstract class AbstractCommonConfigManager<T extends CodeEntity,C extends BasicConfigColumns> extends AbstractBasicConfigManager<T> implements CommonConfigManager<T,C>{

    protected abstract Class<C> getManagedColumnClass();
    
    public Collection<T> convertToBeans(Collection<Map<String, Object>> configs) throws CoreException{
        Collection<T> result = new ArrayList<T>();
        for (Map<String, Object> data : configs) {
            try {
                T bean = getManagedEntityClass().newInstance();
                ObjectValueUtils.fullFillObjectValue(bean, data, getManagedColumnClass());
                result.add(bean);
            } catch (InstantiationException | IllegalAccessException e1) {
                throw new CoreException(e1);
            }
        }
        return result;
    }
    
    public Collection<Map<String, Object>> convertToMaps(Collection<T> beans) throws CoreException{
        Collection<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
        for(T bean : beans){
            Map<String,Object> value = new HashMap<String, Object>();
            ObjectValueUtils.extractObjectValue(bean, value, getManagedColumnClass());
            result.add(value);
        }
        return result;
    }
    
    @Override
    public void updateCommonConfig(Collection<Map<String,Object>> configs) throws CoreException{
        updateConfig(convertToBeans(configs));
    }
    
    @Override
    public Collection<Map<String,Object>> getCommonConfig() throws CoreException{
        return convertToMaps(getConfig());
    }
    
}
