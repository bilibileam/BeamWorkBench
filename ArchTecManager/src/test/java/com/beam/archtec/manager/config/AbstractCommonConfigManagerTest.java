package com.beam.archtec.manager.config;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.beam.CoreException;
import com.beam.archtec.cache.AbstractBasicCacheManager;
import com.beam.archtec.cache.BasicCacheManager;
import com.beam.archtec.dao.AbstractBasicDao;
import com.beam.archtec.dao.BasicDao;
import com.beam.archtec.manager.config.DummyDto.DummyDtoColumns;
import com.beam.archtec.test.AbstractSpringContextTest;

public class AbstractCommonConfigManagerTest extends AbstractSpringContextTest{

    AbstractCommonConfigManager<DummyDto,DummyDto.DummyDtoColumns> dummyManager;
    List<DummyDto> list = new ArrayList<DummyDto>();
    Collection<Map<String , Object>> maps = new ArrayList<>();
    
    @BeforeTest
    public void setUp() {
        final AbstractBasicDao<DummyDto> dummyDao = spy(new AbstractBasicDao<DummyDto>() {

            @Override
            public Class<DummyDto> getClasz() {
                return DummyDto.class;
            }
        });
        final AbstractBasicCacheManager<DummyDto> dummyCacheManager = spy(new AbstractBasicCacheManager<DummyDto>() {

            @Override
            protected Class<DummyDto> getManagedClass() {
                return DummyDto.class;
            }

            @Override
            protected String getCacheName() {
                return null;
            }
        });
        dummyManager = new AbstractCommonConfigManager<DummyDto, DummyDto.DummyDtoColumns>() {
            
            @Override
            protected Class<DummyDto> getManagedEntityClass() {
                return DummyDto.class;
            }
            
            @Override
            public BasicDao<DummyDto> getDao() {
                return dummyDao;
            }
            
            @Override
            public BasicCacheManager<DummyDto> getCacheManager() {
                return dummyCacheManager;
            }
            
            @Override
            protected Class<DummyDtoColumns> getManagedColumnClass() {
                return DummyDtoColumns.class;
            }
        };
        Calendar dummyCalendar1 = Calendar.getInstance();
        DummyDto dummy1 = new DummyDto();
        dummy1.setId(Long.valueOf(1l));
        dummy1.setCode("1");
        dummy1.setDummyString("1");
        dummy1.setDummyCalendar(dummyCalendar1);
        
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put(DummyDtoColumns.CODE.getConfigName(), "1");
        map1.put(DummyDtoColumns.DUMMY_STRING.getConfigName(), "1");
        map1.put(DummyDtoColumns.ID.getConfigName(), Long.valueOf(1l));
        map1.put(DummyDtoColumns.DUMMY_CALENDAR.getConfigName(), dummyCalendar1);
        maps.add(map1);
        
        list.add(dummy1);
    }
    
    @Test
    public void testConvertToMaps() throws CoreException {
        Collection<Map<String, Object>> maps = dummyManager.convertToMaps(list);
        assertEquals(1, maps.size());
    }
    
    @Test
    public void testConvertToMapsException() throws CoreException {
        Collection<Map<String, Object>> maps = dummyManager.convertToMaps(new ArrayList<DummyDto>());
        assertEquals(0, maps.size());
    }
    
    @Test
    public void testConvertToBeans() throws CoreException {
        Collection<DummyDto> list = dummyManager.convertToBeans(maps);
        assertEquals(1, list.size());
    }
    
    @Test
    public void testConvertToBeansException() throws CoreException {
        Collection<DummyDto> list = dummyManager.convertToBeans(new ArrayList<Map<String,Object>>());
        assertEquals(0, list.size());
        AbstractCommonConfigManager<DummyDto,DummyDto.DummyDtoColumns> spiedManager = spy(dummyManager);
    }
    
}
