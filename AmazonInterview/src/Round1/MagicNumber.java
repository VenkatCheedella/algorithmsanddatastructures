package Round1;

import java.util.Scanner;

public class MagicNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int given_input = scan.nextInt();	
		int magic_num = 0;
		for(int i=0; i< given_input; i++)
		{
			 magic_num=0;
			double power_of_5 = Math.pow(5, i) + Math.pow(5, i+1);
			magic_num = magic_num + (int)power_of_5;
		}
		System.out.println("Magic Number : " + magic_num);
	}

}
