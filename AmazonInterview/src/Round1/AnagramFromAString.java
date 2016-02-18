package Round1;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnagramFromAString {
	
	private static String formatString(String s)
	{
		s = s.trim();
		s = s.replace(" ", "");
		char[] s1_chars = s.toCharArray();
		Arrays.sort(s1_chars);
		return new String(s1_chars);
	}
	
	public static boolean checKAnagramWithInAString(String s1, String s2)
	{
		s1 = formatString(s1);
		s2 = formatString(s2);
		if(s1.length() < s2.length())
		{
			String temp_str = s1;
			s1 =s2;
			s2= temp_str;
		}
		Pattern p = Pattern.compile(s2);
		Matcher matcher = p.matcher(s1);
		return matcher.find();
	}
	
	public static void main(String[] args) {
		String s1 = "venkat";
		String s2 = "venk";
		System.out.println(checKAnagramWithInAString(s1, s2));
	}

}
