package com.anupam.PL;

/**
 * @author Anupam Gangotia
 * Profile::http://en.gravatar.com/gangotia
 * github::https://github.com/agangotia
 */

/**
 * Class:Weights
 * This class holds the weights.
 * */
public class Weights {
 float[] weights;
 
 
 /**
  * Parameterized constructor
  * Initializes the number of weights as defined to a defined value
  * */
 Weights(int numOfWeights,int valueAssigned){
	 weights=new float[numOfWeights];
	 for(int i=0;i<numOfWeights;i++)
		 weights[i]=valueAssigned;
 }
 
	/**
	 * Function:showWeights()
	 * Prints the weights values to Console
	 * */
 public void showWeights(){
	 System.out.print("Printing Weights: Initial  [");
	 for(int i=0;i<weights.length;i++)
		 System.out.print("\t"+weights[i]);
	 System.out.print("  ]\n");
 	}

 
 public void showWeights(int x){
	 System.out.print("Printing Weights: iteration"+x+"  [");
	 for(int i=0;i<weights.length;i++)
		 System.out.print("\t"+weights[i]);
	 System.out.print("  ]\n");
 	}
}
