package Round1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class AnagramOfSetOfWords {
		
	
	public static String sortString(String str)	
	{
		str = str.toLowerCase();
		char[] str_chars = str.toCharArray();
		Arrays.sort(str_chars);
		return new String(str_chars);
	}

	public static HashMap<String, Integer> getSameStringCount(String[] words)
	{
		HashMap<String, Integer> anagrams_with_count = new HashMap<>();
		for(int i=0; i< words.length; i++)
		{
			String sorted_str = sortString(words[i]);
			Integer count_anagram = anagrams_with_count.get(sorted_str);			
			if(count_anagram != null)
			{
				anagrams_with_count.put(sorted_str, ++count_anagram);
			}
			else				
			{
				anagrams_with_count.put(sorted_str, 1);
			}
		}
		return anagrams_with_count;
	}
	
	public static void main(String[] args) {
		String[] words = new String[5];
		words[0] = "thisis";
		words[1] = "isthis";
		words[2] = "thiis";
		words[3] = "too";
		words[4] = "oot";
		HashMap<String, Integer> angrams_count = getSameStringCount(words);
		if(angrams_count != null)
		{
			Set<String> keys = angrams_count.keySet();
			for(String key : keys)
			{
				int count_of_words = angrams_count.get(key);
				if( count_of_words == 1)
					System.out.println("Word : " + key + "is not a anagram");
				else
					System.out.println("Word : " + key + "is a anagram and has " + count_of_words + "anagrams");
			}
		}

	}

}
