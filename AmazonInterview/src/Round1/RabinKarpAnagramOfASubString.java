package Round1;

public class RabinKarpAnagramOfASubString {
	
	static final int MAX = 256;
	
	private  static boolean areCharactersSame(int[] pattern_count, int[] curr_text_count)
	{
		for(int i=0; i< MAX; i++)
		{
			if(pattern_count[i] != curr_text_count[i])
				return false;
		}
		return true;
	}
	
	public static void checkAnagramInAString(String text, String pattern)
	{
		int[] pattern_count = new int[MAX];
		int[] curr_text_count = new int[MAX];
		int len_of_pattern = pattern.length();
		int len_of_text = text.length();
		for(int i=0; i< len_of_pattern ; i++)
		{
			int curr_char_val = pattern.charAt(i);
			int curr_char_of_text = text.charAt(i);
			pattern_count[curr_char_val] = ++pattern_count[curr_char_val];
			curr_text_count[curr_char_of_text] = ++curr_text_count[curr_char_of_text];
		}
		
		for(int i=len_of_pattern; i< len_of_text; i++)
		{
			int curr_index = (i-len_of_pattern);
			if(areCharactersSame(pattern_count, curr_text_count))
			{
				System.out.println("There is an anagram at index : " + (curr_index+1));
			}
			int next_char = text.charAt(i);
			int prev_char = text.charAt(curr_index);
			curr_text_count[next_char] = ++curr_text_count[next_char];
			curr_text_count[prev_char] = --curr_text_count[prev_char];
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		checkAnagramInAString("this is venkat", "si ");
	}

}
