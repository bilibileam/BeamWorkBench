package com.beam.math.matrix.basic.utils;

import com.beam.math.matrix.basic.TwoDimensionMatrix;

public class TwoDimensionMatrixUtils {
	
	public static Integer calculateModularInverse(Integer prime,Integer m){
		Integer a = new Integer(prime);
		Integer b = new Integer(m);
		Integer x;
		Integer remains = 0;
		TwoDimensionMatrix inbase = null;
		TwoDimensionMatrix current;
		for(;remains!=1;){
			x = b/a;
			remains = b%a;
			if(remains == 0){
				// exception here
			}
			b = a;
			a = remains;
			current = buildMatrix(x);
			if(inbase == null){
				inbase = current;
			}else{
				inbase = inbase.multiply(current);
			}
		}
		inbase = inbase.multiply(buildMatrix(b));
		return m-inbase.getA12();
	}
	
	private static TwoDimensionMatrix buildMatrix(Integer a11){
		return new TwoDimensionMatrix(a11,1,1,0);
	}
	
}
