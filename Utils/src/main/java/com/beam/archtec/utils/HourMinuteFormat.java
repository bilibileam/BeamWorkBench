package com.beam.archtec.utils;

import java.text.DecimalFormat;

public class HourMinuteFormat {

	DecimalFormat df = new DecimalFormat("0000");
	
	public String format(String toformat){
		StringBuilder sb = new StringBuilder();
		int negativeOperatorIndex = toformat.indexOf("-");
		String tmp = "";
		try {
			tmp = df.format(Integer.valueOf(toformat.substring(negativeOperatorIndex+1).trim()));
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		
		if(negativeOperatorIndex!=-1){
			sb.append("-");
		}
		sb.append(tmp.substring(0, 2)).append(":").append(tmp.substring(2));
		return sb.toString();
	}
	
}
