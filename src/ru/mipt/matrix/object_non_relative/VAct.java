package ru.mipt.matrix.object_non_relative;

import ru.mipt.matrix.exceptions.InvalidSizeException;
import ru.mipt.matrix.object_relative.Matrix;
import ru.mipt.matrix.object_relative.Vector;


public class VAct {
	public static long sMult(Vector a, Vector b) throws InvalidSizeException{ //scalar
		if ((a == null) || (b == null)) {
			throw new NullPointerException();
		}
		if (a.getM() != b.getM()) {
			throw new InvalidSizeException("Error: vector\'s lengths do not equal each other");
		}
		Matrix simpleMatrix = MAct.mult(a.t(), b);
		return simpleMatrix.getData(0, 0);
	}
	public static Vector vMult(Vector a, Vector b) throws InvalidSizeException{ // vector
		if ((a == null) || (b == null)) {
			throw new NullPointerException();
		}		
		if ((a.getM() == b.getM()) && (b.getM() == 3)) {
			Vector res = new Vector(3);
			res.setData(0, 0, a.getData(0, 1)*b.getData(0, 2) - a.getData(0, 2)*b.getData(0, 1));
			res.setData(0, 1, a.getData(0, 0)*b.getData(0, 2) - a.getData(0, 2)*b.getData(0, 0));
			res.setData(0, 2, a.getData(0, 0)*b.getData(0, 1) - a.getData(0, 1)*b.getData(0, 0));
			return res;
		}
		else {
			throw new InvalidSizeException("Error: vector\'s number of dementions do not equal 3");
		}
	}
	
	public static long form(Vector a, Matrix G, Vector b) throws InvalidSizeException {	//sqr form
		if ((a == null) || (b == null) || (G ==null)) {
			throw new NullPointerException();
		}	
		if (a.getSize() != G.getN()) {
			throw new InvalidSizeException("Error: can\'t multiply the first vector on the matrix");
		}
		if (b.getSize() != G.getM()) {
			throw new InvalidSizeException("Error: can\'t multiply the matrix on the second vector");	
		}
		return MAct.mult(MAct.mult(a.t(), G), b).getData(0, 0);
	}
	public static Vector sum(Vector a, Vector b) throws InvalidSizeException{
		if ((a == null) || (b == null)) {
			throw new NullPointerException();
		}
		if ((a.getN() != 1) || (b.getN() != 1)) {
			throw new InvalidSizeException("Error: the vectors you trying to sum up are not vectors at all");
		}
		if (a.getSize() != b.getSize()) {
			throw new InvalidSizeException("Error: the vectors you trying to sum up have different dementional size");
		}
		Vector res = new Vector (a.getSize());
		
		for (int j=0; j<a.getSize(); j++) {
			res.setData(j, a.getData(j)+b.getData(j));
		}
		
		return res;
	}
	
	public static long mMult(Vector v1, Vector v2, Vector v3){ //mixed
		if ((v1 == null) || (v2 == null) || (v3==null)) {
			throw new NullPointerException();
		}	
		return v1.getData(0)*v2.getData(1)*v3.getData(2) - v1.getData(0)*v3.getData(1)*v2.getData(2) +
				+ v2.getData(0)*v3.getData(1)*v1.getData(2) - v2.getData(0)*v2.getData(1)*v3.getData(2) +
				+ v3.getData(0)*v1.getData(1)*v2.getData(2) - v3.getData(0)*v2.getData(1)*v1.getData(2);
	}

	
}
