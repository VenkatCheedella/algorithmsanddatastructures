package DynamicProgramming;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class LongestIncreasingSubSequence {
	
	// Get the right index through binary search and replace the current index
	private static int getFloorIndex(int[] input, int value, int startIndex, int EndIndex){		
		int midIndex = (startIndex+EndIndex)/2;
		int midValue = input[midIndex];
		if(input[startIndex] == value)
			return startIndex;
		if(input[EndIndex] == value)
			return EndIndex;
		if(midValue == value)
			return midIndex;
		if(input[startIndex] < value && value < input[midIndex])
		{
			int midPrevious = input[midIndex-1];
			if(midPrevious < value && value < midValue)
				return midIndex;
			return getFloorIndex(input, value, startIndex, midIndex);	
		}			
		else
		{
			int midNext = input[midIndex+1];
			if(midValue < value && value < midNext)
				return midIndex+1;
			return getFloorIndex(input, value, midValue, EndIndex);		
		}			
	}
	
	public static int lenOfIncreasingSubSequence(int[] input){		
		int[] resultSequence = new int[input.length];
		Arrays.fill(resultSequence, -1);		
		int[] tempMinimumSequence = new int[input.length];		
		int maxLength = 0;		
		tempMinimumSequence[0] = 0;
		int currLen = 0;
		for(int i=1; i< input.length; ++i){
			if(input[i] < input[tempMinimumSequence[0]]){
				tempMinimumSequence[0] = i;
			}
			else if(input[i] > input[tempMinimumSequence[currLen]]){
				resultSequence[i] = tempMinimumSequence[currLen];
				++currLen;
				++maxLength;
				tempMinimumSequence[currLen] = i;
			}else{
				HashMap<Integer, Integer> indexToValue = new HashMap<>();
				for(int j =0; j< tempMinimumSequence.length; ++j){
					indexToValue.put(j, tempMinimumSequence[j]);
				}
				int[] currMinimumLenSequence = new int[currLen+1];
				for(int j=0; j< currMinimumLenSequence.length; ++j){
					currMinimumLenSequence[j] = input[tempMinimumSequence[j]];
				}
				int findFloorIndex = getFloorIndex(currMinimumLenSequence, input[i], 0, currMinimumLenSequence.length-1);
				resultSequence[i] = tempMinimumSequence[findFloorIndex-1];
				tempMinimumSequence[findFloorIndex] = i;
			}
		}
		System.out.println("\n Print the sequence : ");
		printSequenceOrder(input, resultSequence, tempMinimumSequence[currLen]);
		return maxLength;
	}
	
	private static void printSequenceOrder(int[] input, int[] sequenceOrder, int startIndex){			
		while(startIndex != -1){
			System.out.print(input[startIndex] + " ");
			startIndex = sequenceOrder[startIndex];
		}
	}
	
	public static void main(String[] args) {
		try{
			File file = new File("LISSData.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String readLine;
			String[] inputData = null;
			if((readLine = reader.readLine()) != null)
				inputData = readLine.split("\\s");	
			int[] input = new int[inputData.length];
			if(inputData != null){
				for(int i=0; i< inputData.length; ++i){
					input[i] = Integer.valueOf(inputData[i]);
				}
			}
			for(int i=0; i< input.length; ++i){
				System.out.print(input[i] + " ");
			}	
			lenOfIncreasingSubSequence(input);
			reader.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
