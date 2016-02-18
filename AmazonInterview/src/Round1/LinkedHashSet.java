package Round1;

import java.util.Map;



public class LinkedHashSet {
		
	private Map<String, Integer> dictionary = new java.util.LinkedHashMap<>();	
	int curr_pos;
	
	public LinkedHashSet() {
		curr_pos =0;
	}
	
	public void putWord(String word)
	{
		dictionary.put(word, ++curr_pos);
	}
	
	public boolean findString(String input)
	{
		String[] words = input.split(" ");	
		int next_word_pos = -1;
		for(int i=0; i < words.length; i++)
		{
			Integer curr_word_position = (dictionary.get(words[i]));
			int curr_word_pos;
			if(curr_word_position == null)
				curr_word_pos = -1;
			else
				curr_word_pos = curr_word_position;
				
			if( curr_word_pos > next_word_pos)
			{
				next_word_pos =  curr_word_pos;
			}
			else
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedHashSet dictionary_srch = new LinkedHashSet();
		dictionary_srch.putWord("Venkat");
		dictionary_srch.putWord("graduate");
		dictionary_srch.putWord("student");
		dictionary_srch.putWord("is");
		dictionary_srch.putWord("experience");
		dictionary_srch.putWord("3");
		dictionary_srch.putWord("has");
		
		String input_str = "Venkat experience 3 ";
		System.out.println(dictionary_srch.findString(input_str));
	}

}
