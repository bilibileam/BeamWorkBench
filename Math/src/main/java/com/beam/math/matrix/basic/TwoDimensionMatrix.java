package com.beam.math.matrix.basic;


public class TwoDimensionMatrix {

	private Integer a11;
	private Integer a12;
	private Integer a21;
	private Integer a22;
	
	/* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TwoDimensionMatrix)) {
            return false;
        }
        final TwoDimensionMatrix other = (TwoDimensionMatrix) obj;
        return (this.a11==other.a11)&&(this.a12==other.a12)&&(this.a21==other.a21)&&(this.a22==other.a22);
    }
	
	public TwoDimensionMatrix(Integer a11,Integer a12,Integer a21,Integer a22){
		this.a11 = a11;
		this.a12 = a12;
		this.a21 = a21;
		this.a22 = a22;
	}
	
	/**
	 * multiply
	 * ABij = Ain*Bnj
	 * @param other
	 * @return
	 */
	public TwoDimensionMatrix multiply(TwoDimensionMatrix other){
		Integer a11 = this.a11*other.a11+this.a12*other.a21;
		Integer a12 = this.a11*other.a12+this.a12*other.a22;
		Integer a21 = this.a21*other.a11+this.a22*other.a21;
		Integer a22 = this.a21*other.a12+this.a22*other.a22;
		return new TwoDimensionMatrix(a11,a12,a21,a22);
	}
	
	public Integer getA12() {
		return a12;
	}

	public void print(){
		System.out.println("***********");
		System.out.println(" "+a11+"    "+a12);
		System.out.println(" "+a21+"    "+a22);
		System.out.println("***********");
	}
	
}
