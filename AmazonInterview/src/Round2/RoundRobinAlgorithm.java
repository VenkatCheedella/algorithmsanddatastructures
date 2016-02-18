package Round2;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Process
{
	public Process(int total_ex_time, int arrival_time, int id)
	{
		this.total_ex_time = total_ex_time;
		this.remaining_time = total_ex_time;
		this.arrival_time = arrival_time;
		this.id = id;
	}
	int total_ex_time;
	int remaining_time;
	int arrival_time;
	int ex_strt_time;
	int ex_end_time;
	int id;

}

public class RoundRobinAlgorithm {

	private Process[] p_array;
	Queue<Process> proc_q;
	int curr_time;

	public RoundRobinAlgorithm(Process[] p_array)
	{
		this.p_array = p_array;		
	}

	private void swapProcessesInArray(int first_index, int second_index)
	{
		Process curr_porc = p_array[first_index];
		p_array[first_index] = p_array[second_index];
		p_array[second_index] = curr_porc;		
	}

	private int seperateNumbers(int start, int end)
	{
		int curr_greater_elem_index = start;		
		for(int i=start; i< end; ++i)
		{			
			if(p_array[i].arrival_time <= p_array[end].arrival_time)
			{
				if(i > curr_greater_elem_index)
				{
					swapProcessesInArray(i, curr_greater_elem_index);					
				}
			}				
			if(p_array[curr_greater_elem_index].arrival_time <= p_array[end].arrival_time && curr_greater_elem_index != end-1)
				++curr_greater_elem_index;					
		}	
		if(p_array[curr_greater_elem_index].arrival_time > p_array[end].arrival_time)
		{
			swapProcessesInArray(curr_greater_elem_index, end);			
		}	
		else
			++curr_greater_elem_index;
		return curr_greater_elem_index;
	}

	private void sortProcessesOnArrivalTime(int start, int end)
	{
		if(start >= end)
			return;
		int pivot = seperateNumbers(start, end);
		sortProcessesOnArrivalTime(start, pivot-1);
		sortProcessesOnArrivalTime(pivot, end);		
	}

	public void runAlgorithm()
	{
		int num_of_process = p_array.length;
		proc_q = new LinkedList<>();
		int i = 0; 	// index of curr_process of p_array
		sortProcessesOnArrivalTime(0, num_of_process-1);
		for(int j=0; j< p_array.length; j++)
		{
			System.out.print(p_array[j].arrival_time + " ");
		}
		System.out.println();
		int time_slice = 2;
		while(i < num_of_process || !proc_q.isEmpty())
		{
			if(i < num_of_process)
			{
				i = checkLoadProcess(i);
			}
			if(!proc_q.isEmpty())
			{
				int curr_proc_exc_time = 0;
				Process curr_proc = proc_q.poll();
				while(curr_proc_exc_time < time_slice)
				{
					if(curr_proc.remaining_time == 0)
						break;
					++curr_time;
					++curr_proc_exc_time;
					--curr_proc.remaining_time;
					if(i < num_of_process)
						i = checkLoadProcess(i);
				}
				if(curr_proc.remaining_time != 0)
				{
					System.out.println(curr_proc.id + " reamining time " + curr_proc.remaining_time);
					proc_q.add(curr_proc);
				}
				else
				{
					System.out.println("Execution of process " + curr_proc.id + " completed at " + curr_time + "units");
				}
			}
			if(i < num_of_process && proc_q.isEmpty())
				++curr_time;
		}
	}

	private int loadProcess(int i)
	{
		proc_q.add(p_array[i]);
		return ++i;
	}

	private int checkLoadProcess(int i)
	{
		while(curr_time == p_array[i].arrival_time)
		{
			i = loadProcess(i);		
			if(i > p_array.length-1)
				break;
			if(curr_time < p_array[i].arrival_time)
			{
				return i;
			}
		}
		return i;
	}


	public static void main(String[] args) {
		Random rand = new Random();
		Process[] proc_array = new Process[5];
		Process new_process1 = new Process(2, 1, 0);
		Process new_process2 = new Process(7, 2, 1);
		Process new_process3 = new Process(1, 3, 2);
		Process new_process4 = new Process(9, 0, 3);
		Process new_process5 = new Process(9, 2, 4);
		System.out.println();
//				proc_array[0] = new_process1;
//				proc_array[1] = new_process2;
//				proc_array[2] = new_process3;
//				proc_array[3] = new_process4;
//				proc_array[4] = new_process5;
		for(int i=0; i< 5; i++)
		{
			Process new_process = new Process(rand.nextInt(i+10), rand.nextInt(i+2), i);
			proc_array[i] = new_process;
			System.out.println(new_process.id + " " + new_process.total_ex_time + "  " + new_process.arrival_time) ;

		}
		RoundRobinAlgorithm rrobin_alg = new RoundRobinAlgorithm(proc_array);
		rrobin_alg.runAlgorithm();
	}

}
