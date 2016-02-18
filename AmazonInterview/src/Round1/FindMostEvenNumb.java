package Round1;

public class FindMostEvenNumb {
	
	public static int returnTopEvenNumb(int[] input, int last_index)
	{
		
		if(last_index < 3)
		{
			return input[1];
		}		
		else
		{
			int next_len = last_index/2;
			int iterator=1;
			int start_index =0 ;
			while(start_index <= next_len && iterator <= last_index)
			{
				input[start_index] =  input[iterator];
				++start_index;
				iterator = iterator + 2;
			}
			return returnTopEvenNumb(input, --start_index);
		}
					
	}
	
	public static void main(String[] args) {
		int[] input =  {1,2,3,4,5, 6, 7, 8};
		System.out.println(returnTopEvenNumb(input, input.length-1));
	}
	
}
