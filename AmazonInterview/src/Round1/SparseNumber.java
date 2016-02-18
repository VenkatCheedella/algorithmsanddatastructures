package Round1;

public class SparseNumber {
	
	public static int nextSparseNumber(int num)
	{
		int next_num;
		while(true)
		{
			boolean is_inc = false;
			++num;
			next_num = num;			
			while(next_num !=0)
			{
				int unit_place = 1 & next_num;
				int tenth_palce = 2 & next_num;
				 if( unit_place == 1 && tenth_palce == 2)
				 {		
					 is_inc = true;
					 break;
				 }
				 next_num = next_num >> 1;				 
			}
			if(!is_inc)
			{				
				return num;		
			}				
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(nextSparseNumber(10));
	}

}
