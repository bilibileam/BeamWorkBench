package com.beam.archtec.test.puzzle.couples;

import java.util.ArrayList;
import java.util.List;

public class Person {

	public String name;
	public List<Person> knownList = new ArrayList<Person>();
	
	public Person(String name){
		this.name = name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(((Person)obj).name);
	}
	
	
}
