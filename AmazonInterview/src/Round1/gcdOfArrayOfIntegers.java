package Round1;

import java.util.Random;

public class gcdOfArrayOfIntegers {

	public static int gcdOfTwoNumbers(int num1, int num2)
	{
		if(num1 > num2)
		{
			int temp = num1;
			num1 = num2;
			num2 = temp;
		}
		if(num1 !=0)
		{
			int modOfTwonumbers = num2%num1;
			return gcdOfTwoNumbers(modOfTwonumbers, num1);
		}
		else
			return num2;
	}
	
	public static int getGCDOfIntegers(int[] input_nums)
	{
		int gcd = input_nums[0];
		for(int i=1; i< input_nums.length; i++)
		{
			gcd = gcdOfTwoNumbers(gcd, input_nums[i]);
		}
		return gcd;
	}
	
	public static void main(String[] args) {		
		int[] input_array = new int[10];
		Random rnd = new Random();
		for(int i=1; i< 10; i++)
		{
			input_array[i] = rnd.nextInt(50);
		}	
		System.out.println(getGCDOfIntegers(input_array));
	}

}
