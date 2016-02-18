package GenericQuestions;

import java.util.ArrayList;

public class WordCount {

	public static int findWords(char[] inputStr){
		
		int i=0;
		int firstValidCharIndex = 0;
		while(true){
			char currChar = inputStr[i];
			if(currChar == ' '){
				++firstValidCharIndex;
				++i;
			}		
			else
				break;
		}
		i = inputStr.length-1;
		int lastValidCharIndex = inputStr.length-1;
		while(true){
			char currChar = inputStr[i];
			if(currChar == ' '){
				--lastValidCharIndex;
				--i;
			}
			else
				break;
		}
		char[] newCharArray = new char[lastValidCharIndex - firstValidCharIndex+1];
		int k=0;
		for(int j=firstValidCharIndex; j <= lastValidCharIndex; ++j){
			newCharArray[k] = inputStr[j];
			++k;
		}
		ArrayList<Integer> arryOfIndices = new ArrayList<>();
		for(int l=1; l < newCharArray.length; ++l){
			int currChar = newCharArray[l];
			if(currChar == ' ')
				arryOfIndices.add(l);
		}
		return arryOfIndices.size()+1;
	}
	
	public static void main(String[] args) {
			WordCount.findWords("Let us try to find the number of words".toCharArray());
	}

}
