package ru.mipt.matrix.object_relative;

import java.util.Random;

import ru.mipt.matrix.exceptions.InvalidSizeException;

public class Matrix {
	protected long[][] data;
	protected int n;
	protected int m;
	
	public Matrix(int n, int m){
		//builds empty axb matrix 
		if ((n<1) || (m<1)) {
			this.n = 0;
			this.m = 0;
			data = null;
			throw new IllegalArgumentException("Error: can't build matrix "+n+'x'+m);
		}
		else {
			this.n = n;
			this.m = m;
			data = new long[n][m];
		}
	}
	
	public Matrix(long a11, long a12, long a13,
				  long a21, long a22, long a23,
				  long a31, long a32, long a33){
		//builds custom 3x3 matrix
		this.n = this.m = 3;
		data = new long[3][3];
		data[0][0] = a11;		data[1][0] = a12;		data[2][0] = a13;
		data[0][1] = a21;		data[1][1] = a22;		data[2][1] = a23;
		data[0][2] = a31;		data[1][2] = a32;		data[2][2] = a33;
	}
	
	public Matrix minor(int xx, int xy) {		
		//returns minor 
		Matrix result = new Matrix(this.getN() - 1, this.getM() - 1);
		
		int jumpx = 0;
		int jumpy = 0;
				
		for (int i=0; i<n-1; i++){
			if (i == xx) {
				jumpx++;
			}
			for (int j=0; j<m-1; j++){
				if (j == xy) {
					jumpy++;
				}
				result.data[i][j] = this.data[i+jumpx][j+jumpy];	
			}
			jumpy = 0;
		}
		
		return result;
	}
	
	public int getN(){
		return n;
	}
	
	public int getM(){
		return m;
	}
	
	public int maxColumnNumber(){
		return n - 1;
	}
	public int maxLineNumber(){
		return m - 1;
	}
	
	
	public long getData(int i, int j){
		if ((i>=n) || (j>=m))
			throw new ArrayIndexOutOfBoundsException("Can\'t give a field out of matrix");
		return this.data[i][j];
	}
	public void setData(int i, int j, long newValue){
		if ((i>=n) || (j>=m))
			throw new ArrayIndexOutOfBoundsException("Can\'t set a field out of matrix");
		this.data[i][j] = newValue;
	}
	public void addData(int x, int y, long diff) {
		if ((x>=n) || (y>=m))
			throw new ArrayIndexOutOfBoundsException("Can\'t edit a field out of matrix");
		this.data[x][y] += diff;
	}
	public void editField(int x, int y, long newValue){
		if ((x>=n) || (y>=m))
			throw new ArrayIndexOutOfBoundsException("Can\'t edit a field out of matrix");
		this.data[x][y] = newValue;
	}

	public void print() throws InvalidSizeException {
		if ((n == 0)||(m==0)) {
			throw new InvalidSizeException("Matrix to print is empty");
		}
		for(int j=0; j<m; j++) {
			for(int i=0; i<n; i++)
				System.out.print(data[i][j]+" ");
			System.out.println();
		}
	}
	
	public void fill(int r) {
		//fills with random ints of order r
		Random x = new Random();
		for(int j=0; j<m; j++)
			for(int i=0; i<n; i++)
				data[i][j] = Math.round(Math.pow(10, r-1)) + x.nextInt((int) Math.round(Math.pow(10, r)-Math.pow(10, r-1)));
	}
	
	public void fill() {
		//fills with random ints of order 1
		Random x = new Random();
		for(int j=0; j<m; j++)
			for(int i=0; i<n; i++)
				data[i][j] = x.nextInt(10);
	}
	
	public Matrix t() {
		Matrix result = new Matrix(m, n);
		
		for(int j=0; j<m; j++)
			for(int i=0; i<n; i++)
				result.setData(j, i, data[i][j]);
		
		return result;
	}
	public long det() throws InvalidSizeException{
		return ru.mipt.matrix.object_non_relative.MAct.det(this);
	}
	
	public void addLine(){
		long[][] newData = new long[this.n][this.m+1];
		for(int j=0; j<this.m; j++)
			for(int i=0; i<this.n; i++)
				newData[i][j] = this.getData(i, j);
		this.m++;
		this.data = newData;
	}
	public void addColumn(){
		long[][] newData = new long[this.n+1][this.m];
		for(int j=0; j<this.m; j++)
			for(int i=0; i<this.n; i++)
				newData[i][j] = this.getData(i, j);
		this.n++;
		this.data = newData;
	}
	public void deleteLine(int y){
		long[][] newData = new long[this.n][this.m-1];
		int skip = 0;
		for(int j=0; j<this.m-1; j++) {
			if (j==y) {
				skip = 1;
			}
			for(int i=0; i<this.n; i++)
				newData[i][j] = this.getData(i, j+skip);
		}
		this.m--;
		this.data = newData;
	}
	public void deleteColumn(int x){
		long[][] newData = new long[this.n-1][this.m];
		int skip = 0;
		for(int j=0; j<this.m; j++) {
			skip = 0;
			for(int i=0; i<this.n-1; i++){
				if (i==x) {
					skip = 1;
				}
				newData[i][j] = this.getData(i + skip, j);
			}
		}
		this.n--;
		this.data = newData;
	}
	public void addLine(int y){ //y i the position of the new line
		long[][] newData = new long[this.n][this.m+1];
		int skip = 0;

		for(int j=0; j<this.m; j++){
			if (j==y) {
				skip = 1;
			}
			for(int i=0; i<this.n; i++)
				newData[i][j+skip] = this.getData(i, j);
		}
		this.m++;
		this.data = newData;
	}
	public void addColumn(int x){//x is the position of the new column
		long[][] newData = new long[this.n+1][this.m];
		int skip = 0;
		
		for(int i=0; i<this.n; i++){
			if (i==x) {
				skip = 1;
			}
			for(int j=0; j<this.m; j++)			
				newData[i+skip][j] = this.getData(i, j);
		}
		this.n++;
		this.data = newData;
	}

	public void multimplyBy(int mult) throws InvalidSizeException {
		if ((n == 0)||(m==0)) {
			throw new InvalidSizeException("Matrix is empty");
		}
		else {
			for(int j=0; j<m; j++) {
				for(int i=0; i<n; i++)
					data[i][j] *= mult;
				}
		}
	}
	
}
