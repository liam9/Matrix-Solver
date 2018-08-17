
import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;



/*

	Author: Liam Loucks
	
	Description: This file does the calculations for the Matrix Solver


	Note:	array[x][y]
			x is in the first array and y is in the second array of the two dimensional array

*/



class Matrix{
	
	/*
		empty constructor
	*/
	Matrix(){
		
	}
	
	/*
		Print functions
	*/
	public void print(double[][] Mat, double[][] I){
		System.out.println("");
		System.out.println("Mat");
		for(int i = 0; i < Mat[0].length; i++){
			for(int j = 0; j < Mat.length; j++){
				System.out.print(Mat[j][i]+" ");
			}
			System.out.println(" ");
		}
		System.out.println();
		System.out.println();
		System.out.println("I");
		for(int i = 0; i < Mat[0].length; i++){
			for(int j = 0; j < Mat.length; j++){
				System.out.print(I[j][i]+" ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}
	public void print(double[][] Mat, double[] b){
		System.out.println("");
		for(int i = 0; i < Mat[0].length; i++){
			for(int j = 0; j < Mat.length; j++){
				System.out.print(Mat[j][i]+" ");
			}
			System.out.println(" | "+b[i]);
		}
		System.out.println();
	}
	public void print(double[][] Mat){
		for(int i = 0; i < Mat[0].length; i++){
			for(int j = 0; j < Mat.length; j++){
				System.out.print(Mat[j][i]+" ");
			}
			System.out.println();
		}
		System.out.println("");
		
	}
	
	public void print(double[][] Mat, int l, int h){
		for(int i = 0; i < h; i++){
			for(int j = 0; j < l; j++){
				System.out.print(Mat[j][i]+" ");
			}
			System.out.println();
		}
		System.out.println("");
		
	}
	public void print(double[] b){
		System.out.println("");
		for(int i = 0; i < b.length; i++){
			System.out.println(b[i]);
		}
		System.out.println();
			
	}
	
	
	/*
		replaceColumn(double[][] Mat, double[] column, int c) Replaces a column in Mat with the array passed into the function
	*/
	public double[][] replaceColumn(double[][] Mat, double[] column, int c){
		System.out.println("public double[][] replaceColumn(double[][] Mat, double[] column, int c){");
		for(int i = 0; i < Mat.length; i++){
			Mat[c][i] = column[i];
		}
		return Mat;	
	}
	
	/*
		replaceRow(double[][] Mat, double[] z, int c) trades a row in Mat for a row not contained in mat
	*/
	public double[][] replaceRow(double[][] Mat, double[] z, int c){
		System.out.println("public double[][] replaceRow(double[][] Mat, double[] z, int c)");
		for(int i = 0; i < Mat.length; i++){
			Mat[i][c] = z[i];
		}
		return Mat;	
	}

	/*
		exchangeRows(double[][] Mat, int c, int d) switches the position of two rows in Mat
	*/
	public double[][] exchangeRows(double[][] Mat, int c, int d){
		System.out.println("public double[][] exchangeRows(double[][] Mat, int c, int d){");
		double[] temp = new double[Mat.length];
		for(int i = 0; i < Mat.length; i++){
			temp[i] = Mat[i][c];
			Mat[i][c] = Mat[i][d];
			Mat[i][d] = temp[i];
		}
		return Mat;	
	}
	
	/*
		 reflectOverDiagonal(double[][] Mat) reflects the elements over the diagonal of the matrix Mat
	*/
	public double[][] reflectOverDiagonal(double[][] Mat){
		System.out.println("public double[][] exchangeRows(double[][] Mat, int c, int d){");
		double[] temp = new double[Mat.length];
		
		for(int j = 0; j < Mat.length/2; j++){
			for(int i = 0; i < j; i++){
				temp[i] = Mat[i][j];
				Mat[i][j] = Mat[j][i];
				Mat[j][i] = temp[i];
			}
		}
		return Mat;	
	}
	
	/*
		transpose(double[][] Mat) returns the transpose matrix
	*/
	public double[][] transpose(double[][] Mat){
		System.out.println("public double[][] transpose(double[][] Mat)");
		double[][] transpose = new double[Mat.length][Mat[0].length];
		
		for(int j = 0; j < Mat.length; j++){
			for(int i = 0; i < Mat[0].length; i++){
				transpose[i][j] = Mat[j][i];
			}
		}
		return transpose;	
	}
	
	/*
		copyRow(double[][] Mat, double[] row, int c) copies a row in Mat
	*/
	public double[] copyRow(double[][] Mat, double[] row, int c){
		System.out.println("public double[] copyRow(double[][] Mat, double[] row, int c){");
		for(int i = 0; i < Mat.length; i++){
			row[i] = Mat[i][c];
		}
		return row;	
	}
	
	/*
		copyMat(double[][] mat_1, double[][] mat_2) copies mat_1 to mat_2 
	*/
	public double[][] copyMat(double[][] mat_1, double[][] mat_2){
		System.out.println("public double[][] copyMat(double[][] mat_1, double[][] mat_2){");
		for(int i = 0; i < mat_1.length; i++){
			for(int j = 0; j < mat_1[0].length; j++){
				mat_2[i][j] = mat_1[i][j];
			}
		}
		return mat_2;	
	}
	
	/*
		copyMatToFloat(double[][] mat_1, float[][] mat_2) copies mat_1 to mat_2 and converts to float
	*/
	public float[][] copyMatToFloat(double[][] mat_1, float[][] mat_2){
		System.out.println("public double[][] copyMat(double[][] mat_1, double[][] mat_2){");
		for(int i = 0; i < mat_1.length; i++){
			for(int j = 0; j < mat_1[0].length; j++){
				mat_2[i][j] = (float)mat_1[i][j];
			}
		}
		return mat_2;	
	}
	
	/*
		copyArr(double[] arr_1, double[] arr_2) copies arr_1 to arr_2
	*/
	public double[] copyArr(double[] arr_1, double[] arr_2){
		System.out.println("public double[] copyArr(double[] arr_1, double[] arr_2){");
		for(int i = 0; i < arr_1.length; i++){
			arr_2[i] = arr_1[i];
		}
		return arr_2;	
	}
	
	/*
		copyArrToFloat(double[] arr_1, float[] arr_2) copies arr_1 to arr_2
	*/
	public float[] copyArrToFloat(double[] arr_1, float[] arr_2){
		System.out.println("public double[] copyArr(double[] arr_1, double[] arr_2){");
		for(int i = 0; i < arr_1.length; i++){
			arr_2[i] = (float)arr_1[i];
		}
		return arr_2;	
	}
	
	/*
		addRows(double[][] Mat, int row_1, int row_2) adds row_2 to row_1 in the matrix Mat
	*/
	public double[][] addRows(double[][] Mat, int row_1, int row_2){
		System.out.println("public double[][] addRows(double[][] Mat, int row_1, int row_2){");
		for(int i = 0; i < Mat.length; i++){
			Mat[i][row_1] = Mat[i][row_1] + Mat[i][row_2];
		}
		return Mat;	
	}
	
	/*
		minusRows(double[][] Mat, int row_1, int row_2) minuses row_2 from row_1 in the matrix Mat
	*/
	public double[][] minusRows(double[][] Mat, int row_1, int row_2){
		System.out.println("public double[][] minusRows(double[][] Mat, int row_1, int row_2){");
		for(int i = 0; i < Mat.length; i++){
			Mat[i][row_1] = Mat[i][row_1] - Mat[i][row_2];
		}
		return Mat;	
	}
	
	/*
		multRowByConst(double[][] Mat, int c, double ratio) multiplies a row in Mat by a constant ratio
	*/
	public double[][] multRowByConst(double[][] Mat, int c, double ratio){
		System.out.println("public double[][] multRowByConst(double[][] Mat, int c, double ratio)");
		System.out.println("ratio:"+ratio);
		for(int i = 0; i < Mat.length; i++){
			Mat[i][c] = (Mat[i][c])*ratio;
		}
		return Mat;	
	}
	
	/*
		multMatByArr(double[][] Mat, double[] arr) multiplies a Matrix and an Array together
	*/
	public double[] multMatByArr(double[][] Mat, double[] arr){
		double[] x = new double[arr.length];
		System.out.println("public double[][] multMatByArr(double[][] Mat, int c, double ratio){");
		for(int i = 0; i < Mat.length; i++){
			for(int j = 0; j < Mat.length; j++){
				x[i] +=  (Mat[j][i])*(arr[j]);
			}
		}
		return x;	
	}
	
	/*
		multMatByMat(double[][] Mat, double[][] Mat_2) multiplies a Matrix and another Matrix 
	*/
	public double[][] multMatByMat(double[][] Mat, double[][] Mat_2){
		double[][] result = new double[Mat[0].length][Mat.length]; 
		System.out.println("public double[][] multMatByArr(double[][] Mat, double[] Mat_2)");
		for(int i = 0; i < Mat.length; i++){
			for(int j = 0; j < Mat[0].length; j++){
				result[j][i] = (Mat[j][i])*(Mat_2[i][j]);
			}
		}
		return result;	
	}
	
	
	
	/*
		addMatByMat(double[][] Mat, double[][] Mat_2, boolean sign) Adds a Matrix and another Matrix. 
		If boolean sign is false then the matrices are being minused. 
	*/
	public double[][] addMatByMat(double[][] Mat, double[][] Mat_2, boolean sign){
		int s = 1;
		if(sign == false)
			s = -1;
		
		double[][] result = new double[Mat[0].length][Mat.length]; 
		System.out.println("public double[][] multMatByArr(double[][] Mat, double[] Mat_2)");
		for(int i = 0; i < Mat.length; i++){
			for(int j = 0; j < Mat[0].length; j++){
				result[j][i] = (Mat[j][i])+(s*(Mat_2[j][i]));
			}
		}
		return result;	
	}
	
	/*
		findPivot(double[][] Mat, int c, int d) Finds a suitable pivot in the Matrix
	*/
	public int findPivot(double[][] Mat, int c, int d){
		boolean found;
		for(int i = c; i < Mat[0].length; i++){
			if(Mat[d][c] != 0){
				return c;
			}
		}
		return -1;
	}
	
	/*
		rowEchelonForDet(double[][] Mat) Returns the diagonal of a upper triangular matrix used to find determinant
	*/
	public double[] rowEchelonForDet(double[][] Mat){
		System.out.println("public double[] rowEchelonForDet(double[][] Mat){");
		int pivot = 0;
		double[] tri = new double[Mat.length];
		double[] pivotRow = new double[Mat.length];
		
		
		for(int i = 0; i < Mat.length-1; i++){
			//store the pivot row
			pivotRow = copyRow( Mat, pivotRow, i);
	
			for(int j = i+1; j < Mat[0].length; j++){
				print(Mat);
				if(Mat[i][j] == 0)
					break;
				//swap the pivot row 
				if(Mat[i][i] == 0){
					pivot = findPivot(Mat, j, i);
					Mat = exchangeRows(Mat, i, pivot);
				}
				tri[i] = Mat[i][i];
				
				//convert the pivot to 1
				double reciprocal;
				if(Mat[i][i] > 0)
					reciprocal = (1/(Mat[i][i]));
				else 
					reciprocal = -(1/(Mat[i][i]));
					
				Mat = multRowByConst(Mat, i, reciprocal);
				print(Mat);
				
				double ratio = (Mat[i][j])/(Mat[i][i]);
				//minus rows 
				Mat = minusRows(multRowByConst(Mat, i, ratio), j, i);
				
				//restore row
				replaceRow(Mat, pivotRow, i);
				
			
				print(Mat);
			}
			//restore row
			replaceRow(Mat, pivotRow, i);
			multRowByConst(Mat, i, 1/pivotRow[i]);
		}
		tri[Mat.length-1] = Mat[Mat.length-1][Mat.length-1];
		
		return tri;
	}
	
	
	/*
		rowEchelon(double[][] Mat, double[] b) puts the Mat into row echelon by reducing the lower triangular matrix
	*/
	public double[][] rowEchelon(double[][] Mat, double[] b){
		System.out.println("public double[][] rowEchelon(double[][] Mat, double[] a)");
		int pivot = 0;
		double temp;
		double[] row = new double[Mat.length];
		System.out.println("Mat.length:"+Mat.length);
		for(int i = 0; i < Mat.length-1; i++){
			for(int j = i+1; j < Mat[0].length; j++){
				//The target cell is already 0 so break 
				if(Mat[i][j] == 0)
					break;
				
				//swap the pivot row 
				if(Mat[i][i] == 0){
					pivot = findPivot(Mat, j, i);
					Mat = exchangeRows(Mat, i, pivot);
					temp = b[i];
					b[i] = b[pivot];
					b[pivot] = temp;
				}
				
				//convert the pivot to 1
				double reciprocal;
				if(Mat[i][i] > 0)
					reciprocal = (1/(Mat[i][i]));
				else 
					reciprocal = -(1/(Mat[i][i]));
				
				System.out.println("i:"+i+"	Mat.length:"+Mat.length+"	Mat[0].length"+Mat[0].length);	
				Mat = multRowByConst(Mat, i, reciprocal);
				b[i] = reciprocal*b[i];
				print(Mat, b);
				
				double ratio = (Mat[i][i])/(Mat[i][j]);
				//minus rows 
				Mat = minusRows(multRowByConst(Mat, j, ratio), j, i);
				b[j] = (ratio*b[j]) - b[i];
			
				print(Mat, b);
				
			}
			
		}
		
		print(Mat,b);
		//convert the final pivot to 1
		double reciprocal;
		if(Mat[Mat[0].length-1][Mat[0].length-1] > 0){
			reciprocal = (1/(Mat[Mat[0].length-1][Mat[0].length-1]));
		}
		else{
			reciprocal = -1*(1/(Mat[Mat[0].length-1][Mat[0].length-1]));
		}
			
		Mat = multRowByConst(Mat, Mat[0].length-1, reciprocal);
		b[Mat[0].length-1] = reciprocal*b[Mat[0].length-1];
		print(Mat,b);
		
		return Mat;
	}
	
	
	
	
	/*
		rowEchelonForInverse(double[][] Mat, double[][] I) puts the Mat into upper triangular form for when 
		finding the inverse. Then passes double[][] Mat and double[][] I to the reduceUpperForInverse function
		to convert Mat to the identity and I becomes the inverse matrix
	*/
	public double[][]  rowEchelonForInverse(double[][] Mat, double[][] I){
		System.out.println("public double[][] reduceLower(double[][] Mat, double[] b)");
		int pivot = 0;

		System.out.println("Mat.length:"+Mat.length);
		for(int i = 0; i < Mat.length; i++){
			System.out.println("for(int i = 0; i < Mat.length-1; i++){");
			System.out.println("i:"+i);
			for(int j = 0; j < Mat[0].length; j++){
				System.out.println("for(int j = 1; j < Mat[0].length; j++){");
				System.out.println("j:"+j);
			
				//It is a diagonal element so don't reduce it
				if(i == j){
					if(j == Mat[0].length-1)
						break;
					j++;
				}
				
				System.out.println("Mat.length:"+Mat.length+"	Mat[0].length:"+Mat[0].length);
				System.out.println("i:"+i+"	j:"+j);
				//The target cell is already 0 so break 
				if(Mat[i][j] == 0)
					j++;
				
				
				//swap the pivot row 
				if(Mat[i][i] == 0){
					pivot = findPivot(Mat, j, i);
					Mat = exchangeRows(Mat, i, pivot);
					I = exchangeRows(I, i, pivot);
				}
				
				//convert the pivot to 1
				double reciprocal;
				if(Mat[i][i] > 0)
					reciprocal = (1/(Mat[i][i]));
				else 
					reciprocal = -(1/(Mat[i][i]));
				
				
				System.out.println("reciprocal:"+reciprocal+"	i:"+i+"	Mat[i][i]:"+Mat[i][i]);	
				Mat = multRowByConst(Mat, i, reciprocal);
				I = multRowByConst(I, i, reciprocal);
				print(Mat, I);
				
				
				double ratio = (Mat[i][j])/(Mat[i][i]);
				//minus rows 
				Mat = minusRows(multRowByConst(Mat, i, ratio), j, i);
				//restore row i
				Mat = multRowByConst(Mat, i, 1/ratio);
				//minus rows
				I = minusRows(multRowByConst(I, i, ratio), j, i);
				//restore row i
				I = multRowByConst(I, i, 1/ratio);
			
				print(Mat, I);
				
			}
			
		}
		
		print(Mat,I);
		//convert the final pivot to 1
		double reciprocal;
		if(Mat[Mat[0].length-1][Mat[0].length-1] > 0){
			reciprocal = (1/(Mat[Mat[0].length-1][Mat[0].length-1]));
		}
		else{
			reciprocal = -1*(1/(Mat[Mat[0].length-1][Mat[0].length-1]));
		}
			
		Mat = multRowByConst(Mat, Mat[0].length-1, reciprocal);
		I = multRowByConst(I, Mat[0].length-1, reciprocal);
		print(Mat,I);
		
		return Mat;
	}
	
	/*
		solveUpperTriangular(double[][] Mat, double[] b) performs backsubstitution on the matrix 
	*/
	public double[] solveUpperTriangular(double[][] Mat, double[] b){
		System.out.println("public double[] solveUpperTriangular(double[][] Mat, double[] a)");
		double[] x = new double[b.length];
		for(int i = Mat[0].length-1; i > -1; i--){
			x[i] = b[i];
			for(int j = Mat.length-1; j > i; j--){
				x[i] = x[i] - (x[j]*Mat[j][i]);
			}
			print(Mat, b);
			if( (Mat[i][i]) != 0){
				x[i] = (x[i])/(Mat[i][i]);	
			}
			else{
				x[i] = 0;
			}
			print(x);
				
		}
		return x;
	}
	
	/*
		findRank(double[][] Mat) finds the Rank of the matrix
	*/
	public int findRank(double[][] Mat){
		System.out.println("findRank(double[][] Mat)");
		//Mat = rowEchelonForDet(Mat);
		int rank = 0;

		for(int i = 0; i < Mat.length; i++){
			for(int j = 0; j < Mat.length; j++){
				if(Mat[j][i] != 0){
					rank++;
					break;
				}
			}
		}

		return rank;
	}
	
	/*
		findDet(double[][] Mat) finds the determinant
	*/
	public double findDet(double[][] Mat){
		System.out.println("public double findDet(double[][] Mat){");
		double[] tri = new double[Mat.length];
		tri = rowEchelonForDet(Mat);
		System.out.println("tri:");
		print(tri);
		double det = 1;
		for(int i = 0; i < Mat.length; i++){
			det = det*tri[i];
		}
		System.out.println("det: "+det);
		return det;
	}
	
	/*
		GaussElim(double[][] Mat, double[] b) finds the solution to the system using Gauss-Jordan Elimination 
	*/
	public double[] GaussElim(double[][] Mat, double[] b){
		System.out.println("public double[] GaussElim(double[][] Mat, double[] b)");
		print(Mat, b);
		Mat = rowEchelon(Mat, b);

		System.out.println("b:");
		print(b);
		double[] x = new double[b.length];
		x = solveUpperTriangular(Mat, b);
		print(Mat, b);
		
		System.out.println("x:");
		print(x);

		return x;
	}
	
	
	/*
		GaussInverse(double[][] Mat) finds the inverse matrix using Gauss-Jordan Elimination
	*/
	public double[][] GaussInverse(double[][] Mat){
		double[][] I = new double[Mat.length][Mat[0].length];
		
		//initialize the identity matrix
		for(int j = 0; j < Mat.length; j++){
			for(int i = 0; i < Mat.length; i++){
				if(i == j)
					I[i][j] = 1;
				else
					I[i][j] = 0;
			}
		}
		
		System.out.println("Mat");
		print(Mat);
		System.out.println("I");
		print(I);
		
	
		//Zeroes the lower triangular matrix and upper triangular
		Mat = rowEchelonForInverse(Mat, I);
		
		System.out.println("Mat");
		print(Mat);
		System.out.println("I");
		print(I);
		
		
		//Make sure the diagonal is positive ones
		for(int i = 0; i < Mat.length; i++){
				if(Mat[i][i] < 0){
					multRowByConst(Mat, i, -1);
					multRowByConst(I, i, -1);
				}
				if(Mat[i][i] != 1){
					multRowByConst(Mat, i, 1/Mat[i][i]);
					multRowByConst(I, i, 1/Mat[i][i]);
				}
		}
		
		System.out.println("Mat");
		print(Mat);
		System.out.println("I");
		print(I);
		
		
		return I;
	}
	
	/*
		solveInverse(double[][] Mat, double[] b) solves the system using the inverse matrix using Gauss-Jordan Elimination
	*/
	public double[] solveInverse(double[][] Mat, double[] b){
		double[][] inverse = new double[Mat.length][Mat[0].length];
		double[] x = new double[b.length];
		
		inverse = GaussInverse(Mat);
		x = multMatByArr(inverse, b);
		
		return x;
	}
	

	/*
		Cramers(double[][] Mat, double[] b) finds the solution to the system using Cramers method
	*/
	public double[] Cramers(double[][] Mat, double[] b){
		double det; //holds the regular determinant
		double[] dets = new double[Mat.length]; //holds the determinants with rows replaced by int[] a
		double[] solution = new double[Mat.length];
		double[][] Mat_temp = new double[Mat.length][Mat[0].length];
		double[] b_temp = new double[Mat.length];
		
		b_temp = copyArr(b, b_temp);
		Mat_temp = copyMat(Mat, Mat_temp);
		det = findDet(Mat_temp);
		Mat_temp = copyMat(Mat, Mat_temp);
		for(int i = 0; i < Mat.length; i++){
			Mat_temp = copyMat(Mat, Mat_temp);
			b_temp = copyArr(b, b_temp);
			Mat_temp = copyMat(Mat, Mat_temp);
			b_temp = copyArr(b, b_temp);
			dets[i] = findDet(replaceColumn(Mat_temp, b_temp, i));
			print(Mat_temp, b_temp);
			solution[i] = dets[i]/det;
		}
		print(dets);
		System.out.println("Solution:");
		print(solution);
		return solution;
		
	}
	
	/*
		Power(double[][] mat, double expo) puts matrix Mat to the power of expo
	*/
	public double[][] Power(double[][] mat, double expo){
		for(int j = 0; j < mat.length; j++){
			for(int i = 0; i < mat.length; i++){
				mat[i][j] = exp(mat[i][j], expo);
			}
		}
		return mat;
	}
	
	/*
		fact(int x) calculates the factorial of x
	*/
	public int fact(int x){
		int total = 1;
		for(int i = 1; i < x+1; i++){
			total = total*i;
		}
		return total;
	}
	
	
	/*
		exp(int x, int b) calculates x^b
	*/
	public int exp(int x, int b){
		int total = 1;
		for(int i = 0; i < b; i++){
			total = x*total;
		}
		return total;
	}
	
	/*
		exp(double x, double b) calculates x^b
	*/
	public double exp(double x, double b){
		double total = 1;
		for(int i = 0; i < b; i++){
			total = x*total;
		}
		return total;
	}
	
}
