package com.beam.cryptosystem.utils.modular;

public class MemoryEfficientModular {

	/**
	 * calculate
	 * @param v
	 * @param e
	 * @param m
	 * @return v^e mod m
	 */
	public static Integer calculate(Integer v,Integer e,Integer m){
		return calculate(v,v,e,m);
	}
	
	/**
	 * probably should not using recursive call here
	 * ( a * b ) mod c = ( a * (b mod c) ) mod c
	 * @param v
	 * @param last
	 * @param e
	 * @param m
	 * @return
	 */
	private static Integer calculate(Integer v,Integer last,Integer e,Integer m){
//		Integer c = (last < 0)?(m - (Math.abs(last) % m)):(last % m);
		Integer c = last % m;
		if(e==1){
			return c;
		}
		return calculate(v,c*v,e-1,m);
	}
	
}
