package com.beam.utils.xml;

public class DummyClass {

	private InnerDummy diaobao;
	
	public String output(){
		return diaobao.wocao;
	}
	
	private static final class InnerDummy{
		private String wocao;
	}
	
}
