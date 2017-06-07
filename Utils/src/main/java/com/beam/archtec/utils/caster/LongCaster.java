package com.beam.archtec.utils.caster;

public class LongCaster {

    public static Long cast(Object value) {
        return Long.valueOf(String.valueOf(value));
    }
    
}
