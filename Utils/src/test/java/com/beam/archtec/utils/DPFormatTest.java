package com.beam.archtec.utils;

import org.testng.annotations.Test;

public class DPFormatTest {

	@Test
	public void format() {
		System.out.println(new HourMinuteFormat().format("1228"));
	}
}
