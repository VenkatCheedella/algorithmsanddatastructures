package Round1;

public class MergeTwoSortedArrays {

	public static int[] mergeArrays(int[] data1, int[] data2)
	{
		int data1_cursor =0;
		int data2_cursor =0;
		int[] new_array = new int[data1.length + data2.length];
		int i=0;
		while(data1_cursor < data1.length && data2_cursor < data2.length)
		{			
			if(data1[data1_cursor] > data2[data2_cursor])
			{
				new_array[i] = data2[data2_cursor];
				++i;
				++data2_cursor;
			}
			else
			{
				new_array[i] = data1[data1_cursor];
				++data1_cursor;
				++i;
			}
		}
		if(data1_cursor < data1.length)
		{
			for(int j=data1_cursor; j< data1.length; ++j)
			{
				new_array[i] = data1[j];
				++i;
			}
		}
		if(data2_cursor < data2.length)
		{
			for(int j= data2_cursor; j <data2.length; ++j)
			{
				new_array[i] = data2[j];
				++i;
			}
		}
		return new_array;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input1 = new int[5];
		int[] input2 = new int[5];
		for(int i=0; i< 5; i++)
		{
			input1[i] = i+1;
			System.out.print(input1[i] + " ");
		}		
		System.out.println("---");
		for(int i=1; i < 5; i++)
		{
			input2[i] = i+1;
			System.out.print(input2[i] + " ");
		}
		int[] output = mergeArrays(input1, input2);
		System.out.println();
		for(int i=0; i< output.length; i++)
		{
			System.out.print(output[i] + " ");
		}
	}

}
