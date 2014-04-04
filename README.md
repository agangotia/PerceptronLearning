PerceptronLearning
==================

Gradient descent algorithm Trains a sigmoid unit for binary classification tasks
/**
 * Gradient descent algorithm
 * Trains a sigmoid unit for binary classification tasks (i.e., each instance will have a class value of 0 or 1)
 * Assumptions:
 * 1.all attributes are binary-valued
 * 2.There are no missing values in the training or test data
 * */
 
 /**
	 * Main Function.
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
