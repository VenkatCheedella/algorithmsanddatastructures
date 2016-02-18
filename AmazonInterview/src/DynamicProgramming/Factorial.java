package DynamicProgramming;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Factorial {

	public static int factorial(int n)
	{
		if(n == 0)
			return 1;
		else if(n == 1)
			return 1;
		else
		{
			return n*(factorial(n-1));
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		String pattern = "10 this is venkat";		 		
		System.out.println(pattern.indexOf(' '));
		
	}

}
