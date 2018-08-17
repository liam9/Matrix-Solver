

import java.util.*;

/*
/*

	Author: Liam Loucks
	
	Description: This file just tests Matrix.java. It is not included in the app


	Note:	array[x][y]
			x is in the first array and y is in the second array of the two dimensional array

*/



class Matrix_Tester{
	
	
	public static void main(String args[]){
		int[] c = {2, 4, 7, 2, 6};
		int[] e = {1, 3, 2, 7, 1};
		int t = 5;
		/*
		Polynomial P = new Polynomial(c, e, t);
		TaylorApprox T = new TaylorApprox(P);
		System.out.println(T.evaluatePolynomial(P, 4));
		System.out.println(T.evaluatePolynomial(P, 7));
		//System.out.println(T.recursiveDerivative(P, t, 1, 0, 99, 99, 0, 7));
		System.out.println(T.iterativeDerivative(P, 2, 33168, 1648857, 4, 7));
		*/
		double[][] mat = {
			{ 1, 1, 1},
			{ 0, 2, 5},
			{ 2, 5, -1},
		};
		double[][] mat_1 = {
			{ 3, 8},
			{ 4, 6},
		};
		double[][] mat_2 = {
			{ 6, 1, 1},
			{ 4, -2, 5},
			{ 2, 8, 7},
		};
		double[][] mat_3 = {
			{ 6, 1, 1},
			{ 4, -2, 5},
			{ 2, 8, 7},
		};
		double[] a = {6, -4, 27};
		double[][] multi = new double[][]{
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
		};
		
		//Some Testing
		
		Matrix L = new Matrix();
		//double b[] = L.GaussElim(mat_2, a);
		//System.out.println(L.findDet(mat));
		//double b[] = L.GaussElim(mat_3, a);
		//double b[] = L.Cramers(mat_2, a);
		//System.out.println(L.findDet(mat_2));
		//double[][] temp = new double[mat_2.length][mat_2[0].length];
		//temp = L.reflectOverDiagonal(mat_2);
		//L.print(temp);
		 //L.print(L.GaussInverse(mat_3));
		//System.out.println((L.findRank(mat)));
		/*
		double[][] temp = new double[mat_2.length][mat_2[0].length];
		L.print(mat_2);		
		System.out.println("");
		temp = L.transpose(mat_2);
		L.print(temp);		
		System.out.println("");
		temp = L.transpose(temp);
		L.print(temp);	
		*/
		L.print(L.Power(mat_2, 4));
	
	}

}