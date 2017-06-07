package com.beam.archtec.test;


import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import com.beam.archtec.test.puzzle.couples.Person;

/**
 *
 * @author bzhao024
 */
@ContextConfiguration("classpath:application-context.xml") 
@Transactional
public class AbstractSpringContextTest extends AbstractTransactionalTestNGSpringContextTests{
    
	@Test
	public void example(){
		Person person = Mockito.mock(Person.class);
		Mockito.doAnswer(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				Thread.sleep(1000);
				return (Boolean) invocation.callRealMethod();
			}
		}).when(person).equals(null);
	}
	
}
