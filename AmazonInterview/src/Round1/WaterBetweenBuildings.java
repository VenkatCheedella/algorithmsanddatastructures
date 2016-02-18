package Round1;

import java.util.Stack;

public class WaterBetweenBuildings {


	private static int getMinimumHeight(int last_index, int curr_index, int[] building_info)
	{
		if( building_info[last_index] < building_info[curr_index])	
			return building_info[last_index];
		return building_info[curr_index];
	}		

	private static int getBetweenMaxHeight(int last_index, int curr_index, int[] building_info)
	{
		int max_buliding_height =0;
		for(int i= last_index+1; i < curr_index; ++i)
		{
			if(building_info[i] > max_buliding_height)
			{
				max_buliding_height = building_info[i];
			}
		}
		return max_buliding_height;
	}

	public static int calculateWaterHeldBetweenBuildings(int[] buildings_info)
	{
		int total_quantity = 0;
		int len_of_row = buildings_info.length;
		int curr_index = 0;
		int last_index =0;
		Stack<Integer> iter_stack = new Stack<>();
		Stack<Integer> prev_best = new Stack<>();	
		prev_best.push(0);
		while(curr_index < len_of_row)
		{
			if(buildings_info[curr_index] != 0)
			{					
				if(!iter_stack.isEmpty())
					last_index = iter_stack.peek();				
				while(last_index >= prev_best.peek() && !iter_stack.isEmpty())
				{									
					if(curr_index-last_index != 1)
					{
						int min_height = getMinimumHeight(last_index, curr_index, buildings_info);
						int max_height_building = getBetweenMaxHeight(last_index, curr_index, buildings_info);
						int curr_capacity = (curr_index - last_index -1)*(min_height - max_height_building);
						total_quantity = total_quantity + curr_capacity;					
					}
					if(curr_index-last_index == 1)
					{
						if(buildings_info[curr_index] < buildings_info[last_index])
							break;
					}										
					last_index= iter_stack.pop();
				}	
				if(buildings_info[last_index] > buildings_info[curr_index])
					iter_stack.push(last_index);
				int last_best = prev_best.peek();
				//iter_stack.push(last_best);				
				if(buildings_info[last_best] < buildings_info[curr_index])
					prev_best.push(curr_index);
				iter_stack.push(curr_index);				
			}
			++curr_index;			
		}
		return total_quantity;
	}

	public static void main(String[] args) {
		int[] buildings_info = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(calculateWaterHeldBetweenBuildings(buildings_info));
	}

}
