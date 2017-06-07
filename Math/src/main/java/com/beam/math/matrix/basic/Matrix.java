package com.beam.math.matrix.basic;

import java.util.Arrays;

/**
 * @author bzhao024
 * m rows, n column
 * Jun 7, 2017
 */
public class Matrix {
    private Integer m;
    private Integer n;
    private Integer[][] values;
    public static Matrix getNewMatrix(Integer m, Integer n){
        Matrix matrix = new Matrix();
        matrix.m = m;
        matrix.n = n;
        matrix.values = new Integer[m][n];
        Arrays.fill(matrix.values, 0);
        return matrix;
    }
    
    public Matrix mutiply(Matrix other) {
        if(this.n!=other.m){
            //TODO exception
        }
        Matrix result = getNewMatrix(this.m, other.n);
        for (int i = 0; i < result.m; i++) {
            for (int j = 0; j < result.n; j++) {
                result.values[i][j] = mutiply(other, i, j);
            }
        }
        return new Matrix();
    }
    
    public Integer mutiply(Matrix other,int i,int j) {
        return 0;
    }
    
    private Matrix(){
        
    }
}
