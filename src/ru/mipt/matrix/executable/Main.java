package ru.mipt.matrix.executable;
import ru.mipt.matrix.exceptions.InvalidSizeException;
import ru.mipt.matrix.object_non_relative.MAct;
import ru.mipt.matrix.object_non_relative.VAct;
import ru.mipt.matrix.object_relative.*;

public class Main {

	public static void main(String[] args){		
		//MATRIX
		Matrix A = new Matrix (3, 3);
		Matrix B = new Matrix (1, 0, 0, 0, 2, 0, 0, 0, 3);
		
		try {
		A.fill();
		System.out.println("Matrix A: ");
		A.print();
		System.out.println("Matrix B: ");
		B.print();
		System.out.println("AxB: ");
		MAct.mult(A, B).print();
		System.out.println("det(A): "+MAct.det(A)+" det(B): "+MAct.det(B));
		A.deleteLine(0);
		Matrix C = B.minor(0, B.maxLineNumber());
		C.addColumn(1);
		System.out.println("Matrix A: ");
		A.print();
		System.out.println("Matrix C: ");
		C.print();
		System.out.println("A+C: ");
		MAct.sum(A, C).print();}
		catch (InvalidSizeException e) {
			System.out.println("Something with \"Matrixes\"\'s size just went wrong, maybe this could help you to fix the problem:");
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			System.out.println("Don\'t make me sad with those null pointers");
			e.printStackTrace();
		}
		
		//SHORT VECTOR
		Vector v1 = new Vector (2, 0, 0);
		Vector v2 = new Vector (0, 2, 0);	
		try {
		System.out.println(v1.toString()+'x'+v2.toString()+'='+VAct.vMult(v1, v2).toString());
		System.out.println("Mixed product of those vectors: "+VAct.mMult(v1, v2, VAct.vMult(v1, v2))); }
		catch (InvalidSizeException e) {
			System.out.println("Something with \"Short Vectors\" just went wrong, maybe this could help you to fix the problem:");
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			System.out.println("Don\'t make me sad with those null pointers");
			e.printStackTrace();
		}
		//LONG VECTOR
		Vector vLong1 = new Vector (4);
		Vector vLong2 = new Vector (4);
		Vector vLong3 = new Vector (4);
		try {		
		vLong1.fill();
		vLong2.fill();
		vLong3.fill();
		
		System.out.println('['+vLong1.toString()+'+'+vLong2.toString()+"]*"+vLong3.toString()+'='+VAct.sum(vLong1, vLong2).toString()+'*'+vLong3.toString()+'='+VAct.sMult(VAct.sum(vLong1, vLong2), vLong3)); }
		catch (InvalidSizeException e) {
			System.out.println("Something with \"Long Vectors\" just went wrong, maybe this could help you to fix the problem:");
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			System.out.println("Don\'t make me sad with those null pointers");
			e.printStackTrace();
		}
		//Misc
		try {
		B.fill();
		System.out.println("Matrix B: ");
		B.print();
		B.multimplyBy(4);		
		System.out.println("v1*(4*B)*v2 ="+VAct.form(v1, B, v2));
		
		Matrix D = new Matrix (4, 4);
		D.fill();
		System.out.println("Matrix D: ");
		D.print();
		System.out.println("length2 of " +vLong1.toString()+ " in D metrics: "+vLong1.length2(D));
		} catch (InvalidSizeException e) {
			System.out.println("Something with \"Misc\" just went wrong, maybe this could help you to fix the problem:");
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			System.out.println("Don\'t make me sad with those null pointers");
			e.printStackTrace();
		}
		
	}
}
