package com.anupam.PL;

/**
 * @author Anupam Gangotia
 * Profile::http://en.gravatar.com/gangotia
 * github::https://github.com/agangotia
 */

/**
 * Gradient descent algorithm
 * Trains a sigmoid unit for binary classification tasks (i.e., each instance will have a class value of 0 or 1)
 * Assumptions:
 * 1.all attributes are binary-valued
 * 2.There are no missing values in the training or test data
 * */

public class PerceptronLearningMain {

	/**
	 * If isDebug=true, program will print support SOP's
	 * elso not.
	 */
	public static boolean isDebug = false;

	/**
	 * Main Function: Execution Starts here.
	 * Input: Program takes 4 command line arguments: (1) a training file, (2) a test file, (3) a learning rate, and (4)the number of iterations to run the algorithm
	 * 
	 * For training and Test file : First line holds the attribute Name,
	 * Each following relevant line defines a single example. 
	 * Each column holds this example’s value of the attribute named at the head of the column.
	 * The last column holds the class label for the examples. 
	 * 
	 * The number of training iterations refers to the number of training instances processed by the learning algorithm
	 * The number of training iterations can be greater than the number of training instances in the training set.
	 * Learning algorithm process the training instances in the same order as they appear in the training set.
	 * once we are finish with all the training instances (if  training iteration is greater than the number of training instances), 
	 * we will start from the beginning of the training set again.
	 * 
	 * Output: Prints on console following 
	 * Accuracy on training set (X instances): Y%
	 * Accuracy on test set (X' instances): Y'%
	 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if (args.length != 4) {// if the user has not provided the 4
			// arguments, then exit and display message
			System.out.println(args.length);
			System.out.println("Please provide 4 arguments");
			System.out.println("Correct Syntax is");
			System.out
					.println("java -cp ./src com.anupam.PL.PerceptronLearningMain data//train.dat data//test.dat 0.05 16");
			return;
		}
		
		/*
		 * Lets read the values of command line parameters.
		 */
		
		String fileNameTraining = args[0];// Training File Name
		String fileNameTesting = args[1];// Test File Name
		String learningRate = args[2];// Learning Rate
		float lRate = Float.valueOf(learningRate);
		String nmIterations = args[3];// Learning Rate
		int numIterations = Integer.parseInt(nmIterations);// Number of
															// iterations, if
															// numIterations is
															// > training file,
															// restart from the
															// beginning.
		int indexTrained = 0;//defines the index of examples trained
		//initial value =0 and final value=numIterations-1

		if (isDebug) {
			System.out.println("fileNameTraining--" + fileNameTraining);
			System.out.println("fileNameTesting--" + fileNameTesting);
			System.out.println("learningRate--" + learningRate);
			System.out.println("numIterations--" + numIterations);
		}

		// Input Data Read from training file
		InputValues objInput = new InputValues(fileNameTraining);
		if (isDebug)
			objInput.showData();

		//Weight Initialization
		Weights objWeight = new Weights(objInput.Headers.size() + 1, 0);
		if (isDebug)
			objWeight.showWeights();

		// Lets Train the model..
		while (indexTrained < numIterations) {
			
			/**
			 * valFetchFromX is an int value,
			 * It represents the index in Input Vector(X), 
			 * which we are referring to for the current iteration.
			 * numIterations can be >objInput.input.size(),
			 * but valFetchFromX always should be <objInput.input.size()
			 */
			int valFetchFromX = indexTrained;
			if (indexTrained >= objInput.input.size())
				valFetchFromX = valFetchFromX % objInput.input.size();//if valFetchFromX overflows, fix it.

			float sumFProd = getSumOfProducts(objWeight.weights,
					objInput.input.get(valFetchFromX));//sumFProd Sigma(WX)
			int gReturn = g(sumFProd);//g(input)
			int tReturn = t(objInput.input.get(valFetchFromX),
					objInput.Headers.size() + 1);// this is the t

			// if gReturn==tvalue classfied in input NO CHANGE IN WEIGHTS, otherwise do the following
			if (gReturn != tReturn) {// Update the weights
				for (int i = 0; i < objInput.input.get(valFetchFromX).length - 1; i++) {
					float deltaW = lRate * (float) (tReturn - gReturn)
							* objInput.input.get(valFetchFromX)[i];//calculating delta acc to formula
					objWeight.weights[i] = objWeight.weights[i] + deltaW;//New weight accto weight update rule
				}
			} else {
				if (isDebug)
					System.out
							.println("\t ****NO CHANGE IN WEIGHTS***  FOR ITERATION "
									+ indexTrained);
			}

			if (isDebug)
				objWeight.showWeights(indexTrained);
			indexTrained++;
		}// Training Finished

		/*
		 * Now the Testing of our trained perceptron unit
		 */

		// First on Training DataSet
		{
			int testIndex = 0;
			int TestPositives = 0;
			String typeOfData = "Training Set";
			while (testIndex < objInput.input.size()) {
				float sumFProd = getSumOfProducts(objWeight.weights,
						objInput.input.get(testIndex));
				int gReturn = g(sumFProd);
				int tReturn = t(objInput.input.get(testIndex),
						objInput.Headers.size() + 1);
				if (gReturn == tReturn)
					TestPositives++;

				testIndex++;
			}
			System.out.println("");
			System.out.println("Accuracy on " + typeOfData + " (" + (testIndex)
					+ " instances )");
			System.out.print(" = " + ((float) (TestPositives) / (testIndex))
					* 100 + " %\n");

		}
		
		// Second on Training Data
		{
			// Input TestData Read
			InputValues objInputTest = new InputValues(fileNameTesting);
			int testIndex = 0;
			int TestPositives = 0;
			String typeOfData = "Test Set";
			while (testIndex < objInputTest.input.size()) {
				float sumFProd = getSumOfProducts(objWeight.weights,
						objInputTest.input.get(testIndex));
				int gReturn = g(sumFProd);
				int tReturn = t(objInputTest.input.get(testIndex),
						objInputTest.Headers.size() + 1);
				if (gReturn == tReturn)
					TestPositives++;

				testIndex++;
			}
			System.out.println("");
			System.out.print("Accuracy on " + typeOfData + " (" + testIndex
					+ " instances )");
			System.out.print(" = " + ((float) (TestPositives) / (testIndex))
					* 100 + " %\n");
		}

	}

	/**
	 * function: getSumOfProducts()
	 * This is the activation Function
	 * Input:float[] wValues, int[] xValues
	 * wValues -> weight array
	 * xValues -> X values array for the given example
	 * Returns sigma(WiXi) 
	 */
	public static float getSumOfProducts(float[] wValues, int[] xValues) {
		float sum = 0f;
		for (int i = 0; i < wValues.length; i++) {
			sum += (wValues[i] * xValues[i]);
		}
		return sum;
	}

	/**
	 * function: g()
	 * This is the activation Function
	 * Input:float value
	 * Returns 1, if provided value activates this function else return 0. 
	 */
	public static int g(float val) {
		if (val >= 0.5f)
			return 1;
		else
			return 0;
	}

	/**
	 * function: t()
	 * Inputs::
	 * int[] xVals, array of values in this example,
	 * int index, index of last coloumn
	 * Returns the class label for this tuple of example. 
	 */
	public static int t(int[] xVals, int index) {
		return xVals[index];
	}

}
