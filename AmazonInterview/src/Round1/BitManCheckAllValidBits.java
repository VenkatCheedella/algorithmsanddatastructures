package Round1;

import java.util.Scanner;

public class BitManCheckAllValidBits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int given_input = scan.nextInt();	
		//Math.sqrt(99);
		System.out.println("Given number has all one's : " + (((given_input & given_input-1) == 0) ? true : false));
	}

}
