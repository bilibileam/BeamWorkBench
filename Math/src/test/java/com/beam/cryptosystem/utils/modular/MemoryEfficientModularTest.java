package com.beam.cryptosystem.utils.modular;

import static org.testng.Assert.*;

import org.testng.annotations.Test;


public class MemoryEfficientModularTest {

	@Test
	public void calculate1() {
		Integer c = 1;
		assertEquals(MemoryEfficientModular.calculate(2, 4, 3),c);
	}
	
	@Test
	public void calculate() {
		Integer c = 2790;
		Integer m = 65;
		assertEquals(MemoryEfficientModular.calculate(65, 17, 3233),c);
		assertEquals(MemoryEfficientModular.calculate(2790, 2753, 3233), m);
	}
}
