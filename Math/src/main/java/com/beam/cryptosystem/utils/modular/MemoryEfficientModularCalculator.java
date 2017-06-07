package com.beam.cryptosystem.utils.modular;

/**
 * calculate v^e mod m
 * 
 * @author bzhao024
 * 
 * Nov 4, 2014
 */
public class MemoryEfficientModularCalculator {

	private Integer v;
	private Integer e;
	private Integer m;
	private Integer current;
	
	public MemoryEfficientModularCalculator(Integer v,Integer e,Integer m){
		this.v = v;
		this.e = e;
		this.m = m;
	}
	
	public Integer excute(){
		current = current % m;
		if(e==1){
			return current;
		}
		current = current * v;
		e--;
		return excute();
	}
}
