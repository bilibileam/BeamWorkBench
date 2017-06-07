package com.beam.math.utils;

public class MathUtils {

    public static boolean coprime(Integer a, Integer b) {
        return gcd(a, b) == 1;
      }
      public static int gcd(Integer a, Integer b) {
        return b == 0 ? a : gcd(b, a % b);
      }
    
}
