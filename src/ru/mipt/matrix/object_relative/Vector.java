package ru.mipt.matrix.object_relative;

import ru.mipt.matrix.exceptions.InvalidSizeException;
import ru.mipt.matrix.object_non_relative.MAct;
import ru.mipt.matrix.object_non_relative.VAct;

public class Vector extends Matrix {
	public Vector(int m) {
		//builds empty vector of size m
		super(1, m);
	}
	
	public Vector (long a1, long a2, long a3){
		//builds custom 3d vector 
		super(1, 3);
		this.data[0][0] = a1;
		this.data[0][1] = a2;
		this.data[0][2] = a3;
	}
	
	public int getSize(){
		return this.getM();
	}
	
	
	public double length() throws InvalidSizeException{
		if (this.getM() == 0) {
			throw new InvalidSizeException("Error: can't calculate length for 0d vector");
		}
		return Math.sqrt(VAct.sMult(this, this));
	}
	
	public long length2(Matrix G) throws InvalidSizeException {		
		if (this.getM() == 0) {
			System.out.println("Error: can't calculate length for 0d vector");
			return 0;
		}
		return MAct.mult(MAct.mult(this.t(), G), this).data[0][0];
	}
	
	public long length2() throws InvalidSizeException {		
		if (this.getM() == 0) {
			System.out.println("Error: can't calculate length for 0d vector");
			return 0;
		}
		return VAct.sMult(this, this);
	}
	public void addColumn(){
		System.out.println("Error: you can\'t operate number of columns of a vector");
	}
	public void deleteColumn(int x){
		System.out.println("Error: you can\'t operate number of columns of a vector");
	}
	public void addColumn(int x){
		System.out.println("Error: you can\'t operate number of columns of a vector");
	}
	public String toString() {
		StringBuilder s = new StringBuilder("("+this.getData(0));
		for (int i=1; i <= this.maxLineNumber(); i ++){
			s.append(", "+this.getData(i));
		}
		s.append(")");
		return s.toString();
	}
	public long getData(int j) {
		return this.data[0][j];
	}
	public long getData(int i, int j) {
		if (i != 0) {
			System.out.println("Warning: trying to access other column of the vector (that don'\t & can\'t exist)");			
		}
		return this.data[0][j];
	}
	public void setData(int i, int j, long newValue){
		if (i != 0) {
			System.out.println("Warning: trying to access other column of the vector (that don'\t & can\'t exist)");			
		}
		this.data[0][j] = newValue;
	}
	public void setData(int j, long newValue){
		this.data[0][j] = newValue;
	}
}
