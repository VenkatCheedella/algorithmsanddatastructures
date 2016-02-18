package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestCommonSubSequence {
			
	private static int maxOfTwoNumbers(int int1, int int2)
	{
		if(int1 > int2)
			return int1;
		else
			return int2;
		
	}
	
	public static List<Character> getLCSFromMatrix(int[][] lcs, String S2)
	{
		int i = lcs[0].length-1;
		int j = lcs.length-1;
		int index_char = S2.length()-1;
		ArrayList<Character> match_pos = new ArrayList<>();		
		while( i > 1 && j > 1)
		{
			if(lcs[i-1][j-1] < lcs[i][j])
			{
				match_pos.add(S2.charAt(index_char));
				--i;
				--j;
				--index_char;
			}
			else
			{				
					if(lcs[i][j] == lcs[i][j-1])
					{
						--j;
						--index_char;
					}
					else
					{
						--i;
						--index_char;
					}												
			}
		}	
		if(lcs[0][0] < lcs[1][1])
			match_pos.add(S2.charAt(index_char));
		return match_pos;
	}
	
	public static void executiveLCS(String S1, String S2)
	{
		int[][] lcs_matrix = lengthOfLCS(S1, S2);
		List<Character> list_indeces_match = getLCSFromMatrix(lcs_matrix, S2);				
		for(int j=0; j< list_indeces_match.size() ; ++j)
		{
			System.out.print(list_indeces_match.get(j));
		}
	}
	
	public static int[][] lengthOfLCS(String S1, String S2)
	{
		int len_of_str1 = S1.length();
		int len_of_str2 = S2.length();
		
		int[][] lcs = new int[len_of_str1+1][len_of_str2+1];
		
		for(int i=1; i <= len_of_str1; ++i)
		{
			char s1_char = S1.charAt(i-1);
			for(int j=1; j<= len_of_str2; ++j)
			{				
				char s2_char = S2.charAt(j-1);
				if(s1_char == s2_char)
				{
					int prev_match_count = lcs[i-1][j-1];
					lcs[i][j] = prev_match_count+1;
				}
				else
				{
					lcs[i][j] =  maxOfTwoNumbers(lcs[i-1][j], lcs[i][j-1]);
				}
			}
		}
		
//		for(int i=1 ; i< len_of_str1+1; ++i)
//		{
//			for(int j=1 ; j < len_of_str2+1; ++j)
//			{
//				System.out.print(lcs[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		return lcs;
	}
	
	
	public static void main(String[] args) {		
		executiveLCS("abcdf", "abedf");
	}
	
}
