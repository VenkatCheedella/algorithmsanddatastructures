package DynamicProgramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MinimumCoinsSelection {
	
	
	private static ArrayList<Integer> trackNumbers(int[][] selection_array, int[] denominations,int row_len, int col_len){	
		int i = row_len-1;
		int j = col_len-1;
		ArrayList<Integer> selected_coins = new ArrayList<>(selection_array.length);
		while(selection_array[i][j] != 0){
			int curr_count = selection_array[i][j] ;
			int top_count = selection_array[i-1][j];
			if(curr_count == top_count){
				--i;
				continue;
			}
			selected_coins.add(denominations[i]);
			j = j - denominations[i];
		}
		return selected_coins;
	}
	
	public static ArrayList<Integer> getLeastNumberOfCoinsForSum(int[] denominations, int sum){
		Arrays.sort(denominations);
		int[] sum_array = new int[sum+1];
		for(int i=0; i< sum_array.length; ++i){
			sum_array[i] = i;
		}
		int[][] selection_array = new int[denominations.length][sum+1];
		
		for(int i=0; i < denominations.length; ++i){				// no selection for zero sum
			selection_array[i][0] = 0;
		}
		int i=0;
		while(i < denominations.length){
			int j=1;
			while(j < selection_array[0].length){
				if(denominations[i] > j){
					if(i!=0)
						selection_array[i][j] = selection_array[i-1][j];
					else
						selection_array[i][j] = 0;
				}else{
					int cal_min = selection_array[i][j - denominations[i]];
					if(i == 0){
						selection_array[i][j] = 1 + cal_min;
						++j;
						continue;
					}						
					selection_array[i][j] = Math.min(selection_array[i-1][j], 1+ cal_min);
				}	
				++j;
			}
			++i;
		}
		ArrayList<Integer> selected_coins = trackNumbers(selection_array, denominations, selection_array.length, selection_array[0].length);		
		return selected_coins;
	}
	

	public static void main(String[] args) {
		try{
			File file = new File("coinsdata.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String readline;
			Integer num_of_coins = 0;
			Integer sum = 0;
			String[] data = null;
			if((readline = reader.readLine()) != null)
				data = readline.split("\\s");				
			if(data.length == 2){
				num_of_coins = Integer.valueOf(data[0]);
				sum = Integer.valueOf(data[1]);
			}
			int[] denominations = new int[num_of_coins.intValue()];
			readline = null;
			String[] coin_denom = null;
			if((readline = reader.readLine()) != null)
				coin_denom = readline.split("\\s");			
			for(int i=0; i< denominations.length; ++i){
				denominations[i] = Integer.valueOf(coin_denom[i]);				
			}
			reader.close();
			ArrayList<Integer> selected_coins  = getLeastNumberOfCoinsForSum(denominations, sum);
			for(Integer curr_coin : selected_coins){
				System.out.println(curr_coin + " ");
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
