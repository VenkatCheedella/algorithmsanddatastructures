package Round1;
// Here the sequence in the array is increasing order and then in decreasing order

public class FirstLargestInAnArray {

	public static int findMaxElement(int[] input, int start, int end)
	{
		if(start >= end)
			return input[start];
		else			
		{
			int mid = (end+start)/2;
			if(start == mid || mid == end)
			{
				return (input[start] > input[end]) ? input[start] : input[end];	
			}
			else
			{
				if(input[mid] > input[mid-1] && input[mid] > input[mid+1])
					return input[mid];
				else if(input[mid] < input[mid+1])
					return findMaxElement(input, mid, end);				
				else 
					return findMaxElement(input, start, mid);																			
			}			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input_array =new int[7];
		input_array[0] = 1;
		input_array[1] = 2;
		input_array[2] = 3;
		input_array[3] = 4;
		input_array[4] = 3;
		input_array[5] = 2;
		input_array[6] = 1;
		int lar_elem = FirstLargestInAnArray.findMaxElement(input_array, 0, input_array.length-1);
		System.out.println(lar_elem);
	}

}
