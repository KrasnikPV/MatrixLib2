package ru.mipt.matrix.object_non_relative;

import ru.mipt.matrix.exceptions.InvalidSizeException;
import ru.mipt.matrix.object_relative.Matrix;

public final class MAct {
	public static Matrix mult(Matrix A, Matrix B) throws InvalidSizeException {
		if ((A == null) || (B == null)) {
			throw new NullPointerException("Error: linking to non-object");
		}
		if ((A.getN() == 0) || (A.getM() == 0) || (B.getN() == 0) || (B.getM() == 0)){
			throw new InvalidSizeException("Error: product can not be calculated 'cause of the previous error");
		}
		if (A.getN() != B.getM()) {
			throw new InvalidSizeException("Error: can\'t multiply these matrixes");
		}
		Matrix result = new Matrix(A.getM(), B.getN());
		
		for (int i=0; i<A.getM(); i++) {
			for (int j=0; j<B.getN(); j++){
				for (int k=0; k<A.getN(); k++){
					result.addData(i, j, A.getData(k, i)*B.getData(j, k));
				}				
			}
		}
		
		return result.t();
	}
	
	public static Matrix sum(Matrix A, Matrix B) throws InvalidSizeException{
		if ((A == null) || (B == null)) {
			throw new NullPointerException("Error: linking to non-object");
		}
		if ((A.getN() != B.getN()) || (A.getM() != B.getM())) {
			throw new InvalidSizeException("Error: can\'t add these matrixes");
		}
		if (A.getN() == 0){
			throw new InvalidSizeException("Error: mtr sum can not be calculated 'cause of the previous error");
		}
		Matrix result = new Matrix(A.getN(), B.getM());
		
		for (int i=0; i<A.getN(); i++) 
			for (int j=0; j<A.getM(); j++){
				result.setData(i, j, A.getData(i ,j) + B.getData(i, j));
			}
		return result;
	}
	
	public static long det(Matrix A) throws InvalidSizeException {
		long det = 0;
		if ((A == null)) {
			throw new NullPointerException("Error: linking to non-object");
		}
		int a = A.getN();
		if (a != A.getM()) {
			throw new InvalidSizeException("Error: determinant can be calculated only if the mtr is squared");
		}
		if (a == 0){
			throw new InvalidSizeException("Error: determinant can not be calculated 'cause of the previous error");
		}
		if (a == 1){
			det = A.getData(0,0);
			return det;
		}
		if (a == 2){
			det = A.getData(0,0)*A.getData(1,1) - A.getData(0,1)*A.getData(1,0);
			return det;
		}
		
		for (int i=0; i<a; i++) {
			if (i%2 == 0){
				det -= A.getData(i, 1)*MAct.det(A.minor(i, 1));
			}
			else{
				det += A.getData(i , 1)*MAct.det(A.minor(i, 1));
			}
		}
		
		return det;
	}
}
