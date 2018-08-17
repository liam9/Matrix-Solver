import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.lang.Character;
import java.lang.Integer;
import java.lang.Double;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/*

	This class is the GUI for the Matrix.java class

*/

public class MatrixLayout {

    static JFrame frame;
    static JPanel panel, matrixForm, answerForm;
    static JMenuBar menuBar;
    static JMenu menuCalc, menuVars, menuEquats, menuPower;
    static JMenuItem itemGauss, itemCramer, itemGaussInverse, itemRank, itemDet, itemTranspose, itemPower, itemOption, itemClose, itemView, itemReports, itemMultiply, itemAdd, itemMinus;
	static JMenuItem[] variables = new JMenuItem[20];
	static JMenuItem[] equations = new JMenuItem[20];
    static JToolBar toolBar;
    static Icon iconMenu = UIManager.getIcon("html.pendingImage");
    static JButton barRun, solution;
    static ButtonGroup group, groupPower;
    static JRadioButtonMenuItem subFont1, subFont2, subFont3, subFont4, subFont5, subPower2, subPower3, subPower4, subPower5, subPower6;
    static JLabel label;
	static boolean answer;
	
	//The options from myMenuBar
	 static int currentJMenu; //integers between 1-3
	 static int calc;//integers between 1-10
	 static int numOfVars; //integers between 2-20
	 static int numOfEquats;//integers between 2-10
	 static int pow;
	
	static final String[] calcTypes = {"Matrix Solve", "1)Gaussian Elimination", "2)Cramers Rule", "3)Gaussian Inverse", "6)Transpose",
			"5)Find Rank", "4)Determinant", "7)Power", "8)Add Matrices", "9)Minus Matrices", "10)Multiply Matrices"};
	static double[][] aMat;
	static float[] xMat;
	static double[] bMat;
	static double[][] dMat;
	static float[][] eMat;
	static double det;
	static double rank;
	

    public static void main(String[] args) {
		currentJMenu = 0;
		calc = 0;
		numOfVars = 0;
		numOfVars = 0; 
		pow = 0;
		
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                gui();
            }
        });
    }

    public static void gui() {
	  
	  frame = new JFrame(calcTypes[calc]+" | V:"+numOfVars+" | E:"+numOfEquats);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(300, 300);

        MatrixLayout myMenu = new MatrixLayout();
        myMenu.myMenuBar();
		
		//initialize backend matrices
		aMat = new double[numOfVars][numOfEquats];
		dMat = new double[numOfVars][numOfEquats];
		bMat = new double[numOfEquats];
		
		
        myMenu.myToolBar();
        frame.setJMenuBar(menuBar);
		
		
		if(answer == false){
			if((calc == 1)||(calc==2))
				frame.add(formMatrixWithB(), BorderLayout.CENTER);
			else if(((calc == 3)||(calc == 4)) && (numOfVars == numOfEquats))
				frame.add(formMatrix(), BorderLayout.CENTER);
			else if(((calc==5)||(calc == 6)||(calc == 7)))
				frame.add(formMatrix(), BorderLayout.CENTER);
			else if(((calc==8)||(calc == 9)||(calc == 10)))
				frame.add(formTwoMatrices(), BorderLayout.CENTER);
		}
		else{
			frame.add(formAnswer(), BorderLayout.CENTER);
			answer = false;
		}
		
		
		frame.add(toolBar, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
		
		
    }
	
	/*
		formAnswer() creates the ouput Field where the answer to the Matrix equation is displayed
	*/
	public static JPanel formAnswer() {
        answerForm = new JPanel();
        label = new JLabel("Answer: ");
		
		
		if((calc == 1)||(calc==2)){
			solution = new JButton("Solution: ");
			answerForm.add(solution);
			JFormattedTextField[] ans = new JFormattedTextField[numOfVars];
			for(int z = 0; z < numOfVars; z++){
				System.out.print("\n(xMat[z]):"+(xMat[z]));
				ans[z] = new JFormattedTextField(String.valueOf(xMat[z]));				
			}
			 answerForm.setLayout(new GridLayout(1, numOfVars));

			for(int j = 0; j < numOfVars; j++){
				answerForm.add(ans[j]);
			}
				
			return answerForm;
		}
		
		if((calc == 3)||(calc==4)||(calc==7)||(calc==8)||(calc==9)||(calc==10)){
			answerForm.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			 JFormattedTextField[][] ans =  new JFormattedTextField[numOfVars][numOfEquats];
			 solution = new JButton("Solution: ");
			 c.gridx = 0;
			 c.gridy = 0;
			answerForm.add(solution, c);
			for(int j = 0; j < numOfEquats; j++){
				for(int i = 0; i < numOfVars; i++){
					ans[i][j] = new JFormattedTextField((String.valueOf(eMat[i][j])));	
					c.gridx = i+1;
					c.gridy = j+1;
					answerForm.add(ans[i][j], c);
				}				
			}
			return answerForm;
		}
		
		if((calc == 5)||(calc==6)){
			solution = new JButton("Solution: ");
			answerForm.add(solution);
			double sol;
			if(calc == 5)
				sol = rank;
			else 
				sol = det;
			JFormattedTextField ans =  new JFormattedTextField(String.valueOf(sol));
			answerForm.add(ans);
		
			return answerForm;
		}
		
		
	
		return answerForm;
	}
	
	/*
		formTwoMatrices() forms the input Field where two Matrices are displayed
		used for Adding Matrices, Multiplying Matrices, and a couple other functions
	*/
	public static JPanel formTwoMatrices() {
		matrixForm = new JPanel();
        label = new JLabel("First Name: ");
		JTextField[][] a = new JTextField[numOfVars][numOfEquats];
		JButton[] x = new JButton[numOfEquats];
		JTextField[][] d = new JTextField[numOfVars][numOfEquats];
		
        for(int i = 0; i < numOfVars; i++){
			for(int j = 0; j < numOfEquats; j++){
				final int iFinal = i;
				final int jFinal = j;
				a[i][j] = new JTextField();
				a[i][j].getDocument().addDocumentListener(new DocumentListener() {
					  public void changedUpdate(DocumentEvent e) {
						System.out.println("\nUser is editing something in TextField");
						System.out.println("a[i][j]:" + a[iFinal][jFinal].getText()+"	i:"+iFinal+"		j:"+jFinal);
						aMat[iFinal][jFinal] = Double.parseDouble((a[iFinal][jFinal].getText()));
					  }
					  public void removeUpdate(DocumentEvent e) {
						System.out.println("\naMat[i][j] = 0");
						aMat[iFinal][jFinal] = 0;
					  }
					  public void insertUpdate(DocumentEvent e) {
						System.out.println("\nUser is editing something in TextField");
						System.out.println("a[i][j]" + a[iFinal][jFinal].getText()+"	i:"+iFinal+"		j:"+jFinal);
						aMat[iFinal][jFinal] = Double.parseDouble((a[iFinal][jFinal].getText()));
					  }

					  public void warn() {
						 if (a[iFinal][jFinal].getText().length() > 100){
						   JOptionPane.showMessageDialog(null,
							  "Error: Please enter a number with less digits", "Error Massage",
							  JOptionPane.ERROR_MESSAGE);
						 }
					  }
				});
										
			}
		}
		
		for(int i = 0; i < numOfEquats; i++){
			x[i] = new JButton("|");
		}
	
		for(int i = 0; i < numOfVars; i++){
			for(int j = 0; j < numOfEquats; j++){
				final int iFinal = i;
				final int jFinal = j;
				d[i][j] = new JTextField();
				d[i][j].getDocument().addDocumentListener(new DocumentListener() {
					  public void changedUpdate(DocumentEvent e) {
						System.out.println("\nUser is editing something in TextField");
						System.out.println("d[i][j]:" + d[iFinal][jFinal].getText()+"	i:"+iFinal+"		j:"+jFinal);
						dMat[iFinal][jFinal] = Double.parseDouble((d[iFinal][jFinal].getText()));
					  }
					  public void removeUpdate(DocumentEvent e) {
						System.out.println("\naMat[i][j] = 0");
						dMat[iFinal][jFinal] = 0;
					  }
					  public void insertUpdate(DocumentEvent e) {
						System.out.println("\nUser is editing something in TextField");
						System.out.println("d[i][j]" + d[iFinal][jFinal].getText()+"	i:"+iFinal+"		j:"+jFinal);
						dMat[iFinal][jFinal] = Double.parseDouble((d[iFinal][jFinal].getText()));
					  }

					  public void warn() {
						 if (d[iFinal][jFinal].getText().length() > 100){
						   JOptionPane.showMessageDialog(null,
							  "Error: Please enter a number with less digits", "Error Massage",
							  JOptionPane.ERROR_MESSAGE);
						 }
					  }
				});
										
			}
		}
		
        matrixForm.setLayout(new GridLayout(numOfEquats, numOfVars+2));
		  for(int i = 0; i < numOfEquats; i++){
			for(int j = 0; j < numOfVars; j++){
				matrixForm.add(a[j][i]);
			}
			matrixForm.add(x[i]);
			for(int j = 0; j < numOfVars; j++){
				matrixForm.add(d[j][i]);
			}
		}	
		
        return matrixForm;
	}

	
   /*
		formMatrixWithB() creates an input field with a matrix an array
		used for Gaussian Elimination and other functions
   */
	public static JPanel formMatrixWithB() {
        matrixForm = new JPanel();
        label = new JLabel("First Name: ");
		JTextField[][] a = new JTextField[numOfVars][numOfEquats];
		JButton[] x = new JButton[numOfEquats];
		JTextField[] b = new JTextField[numOfEquats];
		
        for(int i = 0; i < numOfVars; i++){
			for(int j = 0; j < numOfEquats; j++){
				final int iFinal = i;
				final int jFinal = j;
				a[i][j] = new JTextField();
				a[i][j].getDocument().addDocumentListener(new DocumentListener() {
					  public void changedUpdate(DocumentEvent e) {
						System.out.println("\nUser is editing something in TextField");
						System.out.println("a[i][j]:" + a[iFinal][jFinal].getText()+"	i:"+iFinal+"		j:"+jFinal);
						aMat[iFinal][jFinal] = Double.parseDouble((a[iFinal][jFinal].getText()));
					  }
					  public void removeUpdate(DocumentEvent e) {
						System.out.println("\naMat[i][j] = 0");
						aMat[iFinal][jFinal] = 0;
					  }
					  public void insertUpdate(DocumentEvent e) {
						System.out.println("\nUser is editing something in TextField");
						System.out.println("a[i][j]" + a[iFinal][jFinal].getText()+"	i:"+iFinal+"		j:"+jFinal);
						aMat[iFinal][jFinal] = Double.parseDouble((a[iFinal][jFinal].getText()));
					  }

					  public void warn() {
						 if (a[iFinal][jFinal].getText().length() > 100){
						   JOptionPane.showMessageDialog(null,
							  "Error: Please enter a number with less digits", "Error Massage",
							  JOptionPane.ERROR_MESSAGE);
						 }
					  }
				});
										
			}
		}
		
		for(int i = 0; i < numOfEquats; i++){
			x[i] = new JButton("|");
		}
	
		for(int i = 0; i < numOfEquats; i++){
			final int iFinal = i;
			b[i] = new JTextField();
			b[i].getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					System.out.println("\nUser is editing something in TextField");
					System.out.println("b[i]:" + b[iFinal].getText()+"	i:"+iFinal);
					bMat[iFinal] = Double.parseDouble((b[iFinal].getText()));
			    }
				  public void removeUpdate(DocumentEvent e) {
					System.out.println("\nbMat[i] = 0");
					bMat[iFinal] = 0;
				  }
				  public void insertUpdate(DocumentEvent e) {
					 System.out.println("\nUser is editing something in TextField");
					System.out.println("b[i]:" + b[iFinal].getText()+"	i:"+iFinal);
					bMat[iFinal] = Double.parseDouble((b[iFinal].getText()));
				  }

				  public void warn() {
					 if (b[iFinal].getText().length() > 100){
					   JOptionPane.showMessageDialog(null,
						  "Error: Please enter a number with less digits", "Error Massage",
						  JOptionPane.ERROR_MESSAGE);
					 }
				  }
				
			});
		}
		
        matrixForm.setLayout(new GridLayout(numOfEquats, numOfVars+2));
		  for(int i = 0; i < numOfEquats; i++){
			for(int j = 0; j < numOfVars; j++){
				matrixForm.add(a[j][i]);
			}
			matrixForm.add(x[i]);
			matrixForm.add(b[i]);
		}	
        return matrixForm;
    }
	
	
	/*
		formMatrix() creates an input field with a single Matrix
		Used for Gaussian Inverse and other functions
	*/
	public static JPanel formMatrix() {
		if(numOfVars > numOfEquats)
			System.out.println("\nError: numOfVars > numOfEquats");
        matrixForm = new JPanel();
        label = new JLabel("First Name: ");
		JTextField[][] a = new JTextField[numOfVars][numOfEquats];
		
		
        for(int i = 0; i < numOfVars; i++){
			for(int j = 0; j < numOfEquats; j++){
				final int iFinal = i;
				final int jFinal = j;
				a[i][j] = new JTextField();
				a[i][j].getDocument().addDocumentListener(new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						System.out.println("\nUser is editing something in TextField");
						System.out.println("value:" + a[iFinal][jFinal].getText()+"	i:"+iFinal+"		j:"+jFinal);
						aMat[iFinal][jFinal] = Double.parseDouble((a[iFinal][jFinal].getText()));
					  }
					  public void removeUpdate(DocumentEvent e) {
						System.out.println("\naMat[iFinal][jFinal] = 0");
						aMat[iFinal][jFinal] = 0;
					  }
					  public void insertUpdate(DocumentEvent e) {
						System.out.println("\nUser is editing something in TextField");
						System.out.println("a[i][j]:" + a[iFinal][jFinal].getText()+"	i:"+iFinal+"		j:"+jFinal);
						aMat[iFinal][jFinal] = Double.parseDouble((a[iFinal][jFinal].getText()));
					  }

					  public void warn() {
						 if (a[iFinal][jFinal].getText().length() > 100){
						   JOptionPane.showMessageDialog(null,
							  "Error: Please enter a number with less digits", "Error Massage",
							  JOptionPane.ERROR_MESSAGE);
						 }
					  }
					 
				});
			}
		}
		
        matrixForm.setLayout(new GridLayout(numOfEquats, numOfVars+2));
		  for(int i = 0; i < numOfEquats; i++){
			for(int j = 0; j < numOfVars; j++){
				matrixForm.add(a[j][i]);
			}
		}	
        return matrixForm;
    }
	
	/*
		myToolBar() holds the run button
		other buttons could be added like save or email
	*/
    public void myToolBar() {

        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        frame.add(toolBar);

        barRun = new JButton("Run", iconMenu);
		barRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				print();
				run();
			}
		});
		toolBar.add(barRun);

		
    }

	/*
		myMenuBar() holds the (Calculation), the (# of Variables), and (# of Equations) menu options
		
	*/
    public void myMenuBar() {
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
		
		
        //Menus
        menuCalc = new JMenu("(Calculation)");
		menuCalc.setMnemonic(KeyEvent.VK_M);
        menuBar.add(menuCalc);

        itemGauss = new JMenuItem("1)Gaussian Elimination", iconMenu);
		itemGauss.addActionListener(new MenuActionListener());
        menuCalc.add(itemGauss);

        itemCramer = new JMenuItem("2)Cramers Rule", iconMenu);
		itemCramer.addActionListener(new MenuActionListener());
        menuCalc.add(itemCramer);
		menuCalc.addSeparator();

        itemGaussInverse = new JMenuItem("3)Gaussian Inverse", iconMenu);
		itemGaussInverse.addActionListener(new MenuActionListener());
        menuCalc.add(itemGaussInverse);
		
		itemTranspose = new JMenuItem("4)Transpose", iconMenu);
		itemTranspose.addActionListener(new MenuActionListener());
        menuCalc.add(itemTranspose);

        menuCalc.addSeparator();

        itemRank = new JMenuItem("5)Find Rank", iconMenu);
		itemRank.addActionListener(new MenuActionListener());
        menuCalc.add(itemRank);
		
		itemDet = new JMenuItem("6)Determinant", iconMenu);
		itemDet.addActionListener(new MenuActionListener());
        menuCalc.add(itemDet);
		
		 menuPower = new JMenu("7)Power");
		 menuPower.setMnemonic(KeyEvent.VK_M);
        menuCalc.add(menuPower);
        groupPower = new ButtonGroup();
		
		ActionListener sliceActionListener = new ActionListener() {
		  public void actionPerformed(ActionEvent actionEvent) {
			AbstractButton aButton = (AbstractButton) actionEvent.getSource();
			String textBox = aButton.getText();
			calc = 7;
			if(textBox.charAt(0) == 'P'){
					pow = Character.getNumericValue(textBox.charAt(2));
					System.out.println("\nPower:"+pow+" calculation:"+calc);
					
			}
		  }
		};

        subPower2 = new JRadioButtonMenuItem("P:2");
        menuPower.add(subPower2);
		subPower2.addActionListener(sliceActionListener);
        groupPower.add(subPower2);

        subPower3 = new JRadioButtonMenuItem("P:3");
        menuPower.add(subPower3);
		subPower3.addActionListener(sliceActionListener);
        groupPower.add(subPower3);

        subPower4 = new JRadioButtonMenuItem("P:4");
        menuPower.add(subPower4);
		subPower4.addActionListener(sliceActionListener);
        groupPower.add(subPower4);

        subPower5 = new JRadioButtonMenuItem("P:5");
        menuPower.add(subPower5);
		subPower5.addActionListener(sliceActionListener);
        groupPower.add(subPower5);

        subPower6 = new JRadioButtonMenuItem("P:6");
        menuPower.add(subPower6);
		subPower6.addActionListener(sliceActionListener);
        groupPower.add(subPower6);
        menuPower.addSeparator();
		
        menuCalc.addSeparator();
		
		itemAdd = new JMenuItem("8)Add Matrices", iconMenu);
		itemAdd.addActionListener(new MenuActionListener());
        menuCalc.add(itemAdd);

		itemMinus = new JMenuItem("9)Minus Matrices", iconMenu);
		itemMinus.addActionListener(new MenuActionListener());
        menuCalc.add(itemMinus);
       
		itemMultiply = new JMenuItem("10)Multiply Matrices", iconMenu);
		itemMultiply.addActionListener(new MenuActionListener());
        menuCalc.add(itemMultiply);
        menuCalc.addSeparator();

     

        menuVars = new JMenu("(# of Variables)");
		menuVars.setMnemonic(KeyEvent.VK_M);
        menuBar.add(menuVars);
		
		for(int i = 2; i < variables.length; i++){
			  variables[i] = new JMenuItem("V:"+i, iconMenu);
			  menuVars.add((variables[i]));
			  variables[i].addActionListener(new MenuActionListener());	
		}
		
		menuEquats = new JMenu("(# of Equations)");
		menuEquats.setMnemonic(KeyEvent.VK_M);
        menuBar.add(menuEquats);
		
		for(int i = 2; i < equations.length; i++){
			  equations[i] = new JMenuItem("E:"+i, iconMenu);
			  menuEquats.add((equations[i]));
			  equations[i].addActionListener(new MenuActionListener());
			
		}
       
    }
	
	/*
		 MenuActionListener listens for user input on the menu 
	*/
	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String event = e.getActionCommand();
			
			if(event.charAt(0) == 'V'){
				
				if(event.length() == 4){
					numOfVars = (Character.getNumericValue(event.charAt(2)) * 10) + Character.getNumericValue(event.charAt(3));
					if((calc!=5)&&(calc!=7)){
						numOfEquats = numOfVars; //The matrix has to be square unless it is a power or rank operation
					}
				}
				else if(event.length() == 3){
					numOfVars = Character.getNumericValue(event.charAt(2));
					if((calc!=5)&&(calc!=7)){
						numOfEquats = numOfVars;
					}
				}	
			}
			
			else if(event.charAt(0) == 'E'){
				if(event.length() == 4){
					numOfEquats = (Character.getNumericValue(event.charAt(2)) * 10) + Character.getNumericValue(event.charAt(3)); 
					if((calc!=5)&&(calc!=7)){
						numOfVars = numOfEquats; 
					}
				}
				else if(event.length() == 3){
					numOfEquats = Character.getNumericValue(event.charAt(2));
					if((calc!=5)&&(calc!=7)){
						numOfVars = numOfEquats; 
					}
				}
			}
			
			else{
				if(event.equals("1)Gaussian Elimination"))
					calc = 1;
				else if(event.equals("2)Cramers Rule"))
					calc = 2;
				else if(event.equals("3)Gaussian Inverse"))
					calc = 3;
				else if(event.equals("4)Transpose"))
					calc = 4;
				else if(event.equals("5)Find Rank"))
					calc = 5;
				else if(event.equals("6)Determinant"))
					calc = 6;
				
				else if(event.equals("8)Add Matrices"))
					calc = 8;
				else if(event.equals("9)Minus Matrices"))
					calc = 9;
				else if(event.equals("10)Multiply Matrices"))
					calc = 10;
				
			}
			System.out.println("\nSelected: " + e.getActionCommand());
			System.out.println("Calculation: " + calc);
			System.out.println("Number of Variables: " + numOfVars);
			System.out.println("Number of Equations: " + numOfEquats);
			if(numOfVars > numOfEquats)
				System.out.println("Error: Number of Variables > Number of Equations");
			//update formMatrix
			if(((numOfVars > 1)&&(numOfEquats>1))&&(numOfVars < numOfEquats+1))
				gui();
				
			

		}
		
	}
	
	/*
		Print functions for printing what is going on in the App
	*/
	public static void print(){
		if(calc == 1){
			for(int j = 0; j < numOfEquats; j++){
				for(int i = 0; i < numOfVars; i++){
					System.out.print(aMat[i][j]+" ");
				}
				System.out.print("| "+bMat[j]);
				System.out.println();
			}	
		}
		if((calc == 2)||(calc == 3)||(calc == 4)||(calc == 5)||(calc == 6)||(calc == 7)){
			for(int j = 0; j < numOfEquats; j++){
				for(int i = 0; i < numOfVars; i++){
					System.out.print(aMat[i][j]+" ");
				}
				System.out.println();
			}	
		}
	}
	
	public static void print(float[] resultant){
		for(int j = 0; j < numOfEquats; j++){
				System.out.println(resultant[j]+" ");
		}	
	}
	public static void print(float[][] resultant){
		for(int j = 0; j < numOfEquats; j++){
			for(int i = 0; i < numOfVars; i++){
				System.out.print(resultant[i][j]+" ");
			}
			System.out.println();
		}		
	}
	
	/*
		run() call Matrix.java and saves the answers 
	*/
	public static void run(){
		xMat = new float[numOfVars];
		eMat = new float[numOfVars][numOfEquats];
		Matrix L = new Matrix();
		if(calc == 1){
			xMat = L.copyArrToFloat(L.GaussElim(aMat, bMat), xMat);
			System.out.println("\n\n\n\n\n\n\n");
			System.out.println("Gaussian Elimination Answer:");
			print(xMat);
		}
		else if(calc == 2){
			xMat = L.copyArrToFloat(L.Cramers(aMat, bMat), xMat); 
			System.out.println("\n\n\n\n\n\n\n");
			System.out.println("Cramers Method Answer:");
			print(xMat);
		}
		else if(calc == 3){
			eMat = L.copyMatToFloat((L.GaussInverse(aMat)), eMat); 
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Gaussian Inverse Answer:");
			print(eMat);
		}
		else if(calc == 4){
			eMat = L.copyMatToFloat((L.transpose(aMat)), eMat); 
			//eMat = L.copyMat((L.transpose(eMat)), eMat);
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Transpose Answer:");
			print(eMat);
		}
		else if(calc == 5){
			rank = L.findRank(aMat); 
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Answer:");
			System.out.println("Rank Answer:"+rank);
		}
		else if(calc == 6){
			det = L.findDet(aMat); 
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Answer:");
			System.out.println("Determinant Answer: "+det);
		}
		else if(calc == 7){
			eMat = L.copyMatToFloat((L.Power((aMat), 4)), eMat); 
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Power Answer:");
			print(eMat);
		}
		else if(calc == 8){
			eMat = L.copyMatToFloat((L.addMatByMat(aMat, dMat,true)), eMat); 
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Addition Answer:");
			print(eMat);
		}
		else if(calc == 9){
			eMat = L.copyMatToFloat((L.addMatByMat(aMat, dMat, false)), eMat); 
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Subraction Answer:");
			print(eMat);
		}
		else if(calc == 10){
			eMat = L.copyMatToFloat((L.multMatByMat(aMat, dMat)), eMat); 
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Multiplication Answer:");
			print(eMat);
		}
		answer = true;
		gui();
		return;
	
	}
	
	
	
}
