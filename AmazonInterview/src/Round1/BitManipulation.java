package Round1;

import java.math.BigInteger;

public class BitManipulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ipaddress = "255.255.255.1";
		String[] ip_parts = ipaddress.split("[. ]");
		for(int i=0; i< ip_parts.length; i++)
			System.out.println(ip_parts[i]);
		String ip_int = "";
		for(int i=0; i< ip_parts.length; i++)
			ip_int = ip_int.concat(ip_parts[i]);
		System.out.println(new BigInteger(ip_int));
	}

}
