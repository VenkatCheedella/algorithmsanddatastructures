
// BigInteger is used to handle large numbers
package Round1;

import java.math.BigInteger;

public class FibonacciNumber {

	BigInteger[] fibonacci_series;
			
	public BigInteger getNthFibonacciNumber(int n)
	{
		fibonacci_series = new BigInteger[n];
		int curr_size =0;
		while(n != curr_size)
		{
			if(curr_size == 0)
			{
				fibonacci_series[curr_size] = new BigInteger(String.valueOf(0));
				++curr_size;
			}
			else if(curr_size == 1)
			{
				fibonacci_series[curr_size] = new BigInteger(String.valueOf(1));
				++curr_size;
			}
			else
			{
				fibonacci_series[curr_size]= fibonacci_series[curr_size-1].add(fibonacci_series[curr_size-2]);				
				++curr_size;
			}
		}
		return fibonacci_series[curr_size-1];
	}
	
	public static void main(String[] args) {
		FibonacciNumber fib_num = new FibonacciNumber();
		System.out.println(fib_num.getNthFibonacciNumber(150));

	}

}
