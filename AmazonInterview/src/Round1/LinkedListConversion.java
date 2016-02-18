package Round1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LinkedListConversion {
	
	private static Stack<Integer> saveElementsFromReverse = new Stack<>();
	private static int sum_of_numbers = 0;
	
	public static LinkedList<Integer> getSumLinkedLinkedofLLs(List<LinkedList<Integer>> list)
	{
		for(LinkedList<Integer> curr_ll : list)
		{
			convertLinkedListsToNumber(curr_ll);
		}
		return convertSumIntoLL(sum_of_numbers);
	}
	
	private static void convertLinkedListsToNumber(LinkedList<Integer> list_of_ll)
	{		
		for(Integer curr_value : list_of_ll)
		{
			saveElementsFromReverse.add(curr_value);
		}	
		int local_sum =0;
		int dec_pos =1;
		while(!saveElementsFromReverse.isEmpty())
		{
			local_sum = local_sum + dec_pos*saveElementsFromReverse.pop();
			dec_pos = dec_pos*10;
		}			
		sum_of_numbers = sum_of_numbers + local_sum;
	}

	private static LinkedList<Integer> convertSumIntoLL(int sum_of_nums)
	{
		LinkedList<Integer> storedigits_of_num = new LinkedList<>();
		int quotient = 0;
		int remainder= 0;
		do{					
			quotient = sum_of_nums/10;
			remainder = sum_of_nums % 10;
			storedigits_of_num.addFirst(remainder);			
		}while(quotient >= 10);		
		storedigits_of_num.addFirst(quotient);
		return storedigits_of_num;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<LinkedList<Integer>> list_of_ll = new ArrayList<>();
		for(int i=0; i< 3; i++)
		{
			LinkedList<Integer> curr_list = new LinkedList<>();			
			for(int j=1; j< 3; j++)
			{
				curr_list.add(j);
			}
			list_of_ll.add(curr_list);
		}		
		System.out.println(getSumLinkedLinkedofLLs(list_of_ll));
	}

}
