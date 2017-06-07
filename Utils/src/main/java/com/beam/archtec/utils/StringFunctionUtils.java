package com.beam.archtec.utils;

public class StringFunctionUtils {

    public static String upcaseEachWord(String source) {
        StringBuilder res = new StringBuilder();

        String[] strArr = source.split(" ");
        for (String str : strArr) {
            char[] stringArray = str.trim().toCharArray();
            stringArray[0] = Character.toUpperCase(stringArray[0]);
            str = new String(stringArray);

            res.append(str).append(" ");
        }
        int indexToRemove = res.lastIndexOf(" ");
        if(indexToRemove != -1)
            res.deleteCharAt(indexToRemove);
        return res.toString();
    }
    
}
