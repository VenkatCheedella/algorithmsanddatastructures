package Round2;


import java.util.*;

public class Encrypt {
    
    private int row_len;
    private int col_len;
    
    public void findRowAndColLength(int len_of_str)
        {
        double d_str_len = (double)len_of_str;
        double _row_len = Math.sqrt(d_str_len);
        row_len = (int)_row_len;
        //System.out.println("square root " +row_len);
        int square_of_row = row_len*row_len;       
        if(square_of_row == len_of_str)
            {
            col_len = row_len;
        }
        else
            col_len = row_len+1;
        if(len_of_str != (col_len * row_len))
        {
        	++row_len;
        }
    }
    
    public void encryptString(String str)
        {
        int len_str = str.length();
        System.out.println(len_str);
        findRowAndColLength(len_str);
        System.out.println(col_len);
        System.out.println(row_len);
        char[][] str_matrix = new char[row_len][col_len];        
        int curr_char =0;
        if(row_len !=0 && col_len !=0)
            {
             for(int i=0;i<row_len; i++)
            {
            for(int j=0; j<col_len; j++)
                {
            	if(curr_char >= str.length())
            	{
            		str_matrix[i][j] = '\0';
            		continue;
            	}            		
                str_matrix[i][j] = str.charAt(curr_char);
                ++curr_char;
            }
        }
            for(int j=0; j < col_len; j++)
                {
                for(int i=0; i< row_len; i++)
                    {
                    System.out.print(str_matrix[i][j]);
                }
                System.out.print(" ");
            }
        }
       
    }
    
    
    public static void main(String[] args) {
    	char c='\u0000';    	
    	System.out.println(c);   
    	String s = new String();
    	int[] integers = new int[10];    	
        Scanner scan = new Scanner(System.in);
        String input_str = scan.nextLine();
        Encrypt sol = new Encrypt();
        sol.encryptString(input_str);
        scan.close();
    }
}