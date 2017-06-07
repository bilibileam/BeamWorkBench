package com.beam.archtec.dao;

import org.testng.annotations.Test;

import com.beam.archtec.dto.AbstractVersionControlEntity;

public class AbstractBasicDaoTest {
	
	AbstractBasicDao<AbstractVersionControlEntity<Integer>> dao = new AbstractBasicDao<AbstractVersionControlEntity<Integer>>() {
		
		@Override
		public Class<AbstractVersionControlEntity<Integer>> getClasz() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	AbstractVersionControlEntity<Integer> e = new AbstractVersionControlEntity<Integer>() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 3089052362350691786L;

		@Override
		public void setId(Integer id) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public Integer getId() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void setCode(String code) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public String getCode() {
			// TODO Auto-generated method stub
			return null;
		}
	};
  @Test
  public void f() {
	  System.out.println(dao.entityClass);
  }
}
