package com.beam.math.matrix.basic.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TwoDimensionMatrixUtilsTest {

	@Test
	public void calculateModularInverse() {
		Assert.assertEquals(TwoDimensionMatrixUtils.calculateModularInverse(17, 3120).intValue(), 2753);
	}
}
