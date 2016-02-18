package Round1;

import java.util.Stack;

public class CheckValidParanthesis {

	public static boolean checkExpressionIsValid(String input)
	{
		Stack<Character> char_stack = new Stack<>();
		char curr_char = '\0';
		for(int i=0; i< input.length(); i++)
		{
			curr_char = input.charAt(i);
			if(curr_char == '(')
				char_stack.push(')');
			else if(curr_char != ')')
				return false;
			else
			{
				if(char_stack.isEmpty())
					return false;
				else
				{
					char_stack.pop();
				}
			}
		}
		return (char_stack.isEmpty()) ? true : false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "()()(";
		System.out.println(checkExpressionIsValid(input));
	}

}
