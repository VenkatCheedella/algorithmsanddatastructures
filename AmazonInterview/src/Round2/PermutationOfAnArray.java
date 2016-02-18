package Round2;

import java.util.Scanner;

public class PermutationOfAnArray {
	private static int count =0;
	public static void permutationOfIntegers(int[] int_arrays, int start, int end)
	{
		int swap_num;
		if(start == end-1)
		{
			for(int i=0; i< end; i++)
			{
				System.out.print(int_arrays[i]);				
			}
			System.out.println();
			++count;
			return;
		}
		for(int i=start; i < end; i++)
		{
			swap_num = int_arrays[start];
			int_arrays[start] = int_arrays[i];
			int_arrays[i] = swap_num;		
			
			permutationOfIntegers(int_arrays, start+1, end);
		}		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Permutation of 1234 : ");
		int[] int_array = {1,2,3,4};
		PermutationOfAnArray.permutationOfIntegers(int_array, 0, 4);
		System.out.println("Total permutations : " + count);		
	}

}
