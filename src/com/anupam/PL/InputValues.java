package com.anupam.PL;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author Anupam Gangotia
 * Profile::http://en.gravatar.com/gangotia
 * github::https://github.com/agangotia
 */

/**
 * Class:InputValues
 * This class holds the Attributes and examples to train the perceptron.
 * */
public class InputValues {

	/**
	 * input: Arraylist of examples
	 * */
	ArrayList<int[]> input;
	
	/**
	 * Headers: Arraylist of attributes names
	 * */
	ArrayList<String> Headers;
	
	
	/**
	 * Parameterized constructor, accepting file name to read the input values from
	 * */
	InputValues(String fileNameTraining){
		
		Headers=new ArrayList<String>();
		input=new ArrayList<int[]>();

		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileNameTraining));
			// Reading Header
			{
				String line = br.readLine();
				StringTokenizer st = new StringTokenizer(line);

				while (st.hasMoreElements()) {
					Headers.add((String) st.nextElement());
				}

			}
			
			{// lets read coloumns
				String line = br.readLine();
				while (line != null) {
					// System.out.print(line);
					StringTokenizer st = new StringTokenizer(line);
					// System.out.println("---- Split by space ------");
					int[] tempCol = new int[Headers.size()+2];
					int tempIndex = 0;
					tempCol[tempIndex++]=1;
					while (st.hasMoreElements()) {
						tempCol[tempIndex++] = Integer.parseInt((String) st
								.nextElement());
						// System.out.println(tempCol[tempIndex-1]);
					}
					input.add(tempCol);
					line = br.readLine();
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * Function:showData()
	 * Prints the examples read to Console
	 * */
	public void showData(){
		System.out.println("");
		for(String temp:Headers){
			System.out.print("\t"+temp);
		}
		System.out.println("");
		for(int[] temp:input){
			for(int i=0;i<temp.length;i++){
				System.out.print("\t"+temp[i]);	
			}System.out.println("");
			
		}
	}

}
