package Round1;

public class LargestPaliInString {

	public String largestPalindrome(String input_str)
	{		
		int len_of_str = input_str.length();
		String pali_str = "";
		for(int i=0; i< len_of_str; i++)
		{
			for(int j=i+1; j < len_of_str; j++)
			{
				String curr_str = input_str.substring(i, j+1);
				if(isStringPalindrome(curr_str))
				{
					if(curr_str.length() > pali_str.length()){
						pali_str = curr_str;
					}					
					
				}
			}
		}
		return pali_str;
	}
	
	private boolean isStringPalindrome(String S)
	{
		int i = 0;			
		int j= S.length()-1;		
		while( i != j && i<j)
		{
			if(S.charAt(i) != S.charAt(j))
				return false;
			++i;
			--j;
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new LargestPaliInString().largestPalindrome("abbcaabbcacbb"));
	}

}
