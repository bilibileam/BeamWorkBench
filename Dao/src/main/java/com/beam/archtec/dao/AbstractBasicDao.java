package com.beam.archtec.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.beam.archtec.dto.BasicEntity;

public abstract class AbstractBasicDao<T extends BasicEntity> extends HibernateDaoSupport implements BasicDao<T>{

    public abstract Class<T> getClasz();

    protected Class<T> entityClass;
    
    public AbstractBasicDao() {
        Type superClassType = getClass().getGenericSuperclass();

        if (superClassType instanceof ParameterizedType) {
            Type[] paramTypes = ((ParameterizedType) superClassType).getActualTypeArguments();
            this.entityClass = (Class<T>) paramTypes[0];
        }
    }
    
    /**
     * @param entityClass
     */
    public AbstractBasicDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /* (non-Javadoc)
     * @see com.beam.archtec.dao.BasicDao#saveOrUpdate(com.beam.archtec.dto.BasicEntity)
     */
    @Override
    public void saveOrUpdate(T bean) {
        
    }

    /* (non-Javadoc)
     * @see com.beam.archtec.dao.BasicDao#saveOrUpdate(java.util.Collection)
     */
    @Override
    public void saveOrUpdate(Collection<T> beans) {
        
    }

    /* (non-Javadoc)
     * @see com.beam.archtec.dao.BasicDao#getAllAvailableBeans()
     */
    @Override
    public Collection<T> getAllAvailableBeans() {
        return null;
    }

    
}
