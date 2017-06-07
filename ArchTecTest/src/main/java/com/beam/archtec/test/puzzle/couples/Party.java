package com.beam.archtec.test.puzzle.couples;

import java.util.Map;

public class Party {
	public Map<Person, Integer> bash;
	
	public void letsShakeHands(){
		for(Map.Entry<Person, Integer> person : bash.entrySet()){
			for(Map.Entry<Person, Integer> toShake : bash.entrySet()){
				if(!person.getKey().equals(toShake.getKey())&&person.getKey().knownList.contains(toShake)){
					person.setValue(person.getValue()+1);
				}
			}
		}
	}
	
}
