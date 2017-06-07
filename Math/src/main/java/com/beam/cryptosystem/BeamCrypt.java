package com.beam.cryptosystem;

public class BeamCrypt {

	public void dummy(){
		// prime p
		int p = 61;
		// prime q
		int q = 53;
		// used as public key
		int n = p*q;
		// because p and q are coprime
		int euTotient = (p-1)*(q-1);
		// e and euTotient should be coprime
		// used as public key
		int e = 17;
		// the modular multiplicative inverse of e(mod euTotient)
		// e * d mod euTotient = 1
		int d = 2753;
		
		/*
		 * encrypt
		 * c = m^17 mod n
		 * 
		 * decrypt
		 * m = c^d mod n
		 * 
		 * */
	}
}
