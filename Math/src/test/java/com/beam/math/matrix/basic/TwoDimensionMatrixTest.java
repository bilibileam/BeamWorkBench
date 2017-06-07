package com.beam.math.matrix.basic;

import junit.framework.Assert;

import org.testng.annotations.Test;

public class TwoDimensionMatrixTest {
	
	@Test
	public void multiply() {
		TwoDimensionMatrix m1 = new TwoDimensionMatrix(1, 1, 1, 0);
		TwoDimensionMatrix m2 = new TwoDimensionMatrix(1, 1, 1, 0);
		TwoDimensionMatrix m3 = new TwoDimensionMatrix(1, 1, 1, 0);
		TwoDimensionMatrix m4 = new TwoDimensionMatrix(3, 1, 1, 0);
		TwoDimensionMatrix m5 = new TwoDimensionMatrix(4, 1, 1, 0);
		TwoDimensionMatrix expect = new TwoDimensionMatrix(47, 11, 30, 7);
		Assert.assertEquals(expect, m1.multiply(m2).multiply(m3).multiply(m4).multiply(m5));
	}
	
	@Test
	public void multiply2() {
		TwoDimensionMatrix m1 = new TwoDimensionMatrix(183, 1, 1, 0);
		TwoDimensionMatrix m2 = new TwoDimensionMatrix(1, 1, 1, 0);
		TwoDimensionMatrix m3 = new TwoDimensionMatrix(1, 1, 1, 0);
		TwoDimensionMatrix m4 = new TwoDimensionMatrix(8, 1, 1, 0);
		TwoDimensionMatrix result = m1.multiply(m2).multiply(m3).multiply(m4);
		result.print();
	}
}
