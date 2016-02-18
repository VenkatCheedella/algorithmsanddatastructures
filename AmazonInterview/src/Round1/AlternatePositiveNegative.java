package Round1;

import java.util.ArrayList;

public class AlternatePositiveNegative {
	
	public static int[] ArrayWithAltNegativeNum(int[] array_elem)
	{
		ArrayList<Integer> pos_array = new ArrayList<>();
		ArrayList<Integer> neg_array = new ArrayList<>();
		for(int i=0; i< array_elem.length; i++)
		{
			if(array_elem[i] < 0)
			{
				neg_array.add(array_elem[i]);				
			}
			else
			{
				pos_array.add(array_elem[i]);
			}
		}
		int len_pos_nums = pos_array.size();
		int len_neg_nums = neg_array.size();
		int curr_index =0;
		int iterator= 0;
		while( iterator < len_neg_nums && iterator < len_pos_nums)
		{
			array_elem[curr_index] = pos_array.get(iterator);
			++curr_index;
			array_elem[curr_index] = neg_array.get(iterator);	
			++curr_index;
			++iterator;
		}
		if(iterator < len_neg_nums)
		{
			while(iterator <len_neg_nums)
			{
				array_elem[curr_index] = neg_array.get(iterator);
				++curr_index;
				++iterator;
			}
		}
		if(iterator < len_pos_nums)
		{
			while(iterator < len_pos_nums)
			{
				array_elem[curr_index] = pos_array.get(iterator);
				++curr_index;
				++iterator;
			}
		}
		return array_elem;
	}
	
	public static void main(String[] args) {
		int[] input_array = {1};
		AlternatePositiveNegative.ArrayWithAltNegativeNum(input_array);
		System.out.println("Altered array : ");
		for(int i=0; i< input_array.length; i++)
		{
			System.out.print(input_array[i] + " ");
		}
		int given_number = 1234567891;
		System.out.println(given_number);
	}

}
