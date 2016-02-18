package Round1;

import java.util.ArrayList;
import java.util.List;

class CornerElement
{
	int x_cor;
	int y_cor;
	int value;
	public CornerElement(int x_cor, int y_cor)
	{
		this.x_cor = x_cor;
		this.y_cor = y_cor;
	}
	
	public void putValue(int value)
	{
		this.value = value;
	}
	
	public int getValue(int x_cor, int y_cor)
	{
		if(x_cor == this.x_cor && y_cor == this.y_cor)
			return this.value;
		else
			return -1;
	}
	
}



public class RotateMatrix {
	
	List<CornerElement> list_corner_elements = new ArrayList<>();
	int len_of_row;
	int len_of_col;
	
	private int getIntForGivenCoordinates(int i, int j)
	{
		CornerElement curr_elm;
		for(int iter=0; iter< list_corner_elements.size(); ++iter)
		{
			curr_elm = list_corner_elements.get(iter);
			int value = curr_elm.getValue(i, j);
			if(value != -1)
			{
				return value;
			}
		}		
		return -1;
	}
	
	private void saveCorners(int[][] input_matrix)
	{
		len_of_row = input_matrix.length;
		len_of_col = input_matrix[0].length;
		CornerElement firstElement = new CornerElement(0, 0);
		firstElement.putValue(input_matrix[0][0]);
		CornerElement secondElemen = new CornerElement(0, len_of_col-1);
		secondElemen.putValue(input_matrix[0][len_of_col-1]);
		CornerElement thirdElement = new CornerElement(len_of_row-1, 0);
		thirdElement.putValue(input_matrix[len_of_row-1][0]);
		CornerElement fourthElement = new CornerElement(len_of_row-1, len_of_col-1);
		fourthElement.putValue(input_matrix[len_of_row-1][len_of_col-1]);
		list_corner_elements.add(firstElement);
		list_corner_elements.add(secondElemen);
		list_corner_elements.add(thirdElement);
		list_corner_elements.add(fourthElement);
		
	}
	
	public int[][] returnRotatedMatrix(int[][] input_matrix)
	{		
		int next_elem = input_matrix[0][0];
		saveCorners(input_matrix);
		for(int iter = 1; iter < len_of_col; ++iter)
		{
			int curr_elem = input_matrix[0][iter];
			input_matrix[0][iter] = next_elem;
			next_elem = curr_elem;			
		}
		next_elem = getIntForGivenCoordinates(0, len_of_col-1);		
		for(int iter = 1; iter < len_of_row; ++iter)
		{
			int curr_elem = input_matrix[iter][len_of_col-1];
			input_matrix[iter][len_of_col-1] = next_elem;
			next_elem = curr_elem;
		}
		next_elem = getIntForGivenCoordinates(len_of_row-1, len_of_col-1);
		for(int iter = len_of_col-2; iter >= 0; --iter)
		{
			int curr_elem = input_matrix[len_of_row-1][iter];
			input_matrix[len_of_row-1][iter] = next_elem;
			next_elem = curr_elem;
		}
		next_elem = getIntForGivenCoordinates(len_of_row-1, 0);
		for(int iter = len_of_row-2; iter >=0; --iter)
		{
			int curr_elem = input_matrix[iter][0];
			input_matrix[iter][0] = next_elem;
			next_elem = curr_elem;
		}
		return input_matrix;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int input_matrix[][] = new int[3][4];
		int count =0;
		for(int i=0; i< 3; i++)
		{
			for(int j=0;j< 4; j++)
			{
				input_matrix[i][j] = ++count;
			}
		}
		System.out.println("\nInput");
		for(int i=0; i< 3; ++i)
		{
			for(int j=0; j < 4; ++j)
			{
				System.out.print(input_matrix[i][j] + " ");
			}
			System.out.println();
		}
		RotateMatrix rotate_mat = new RotateMatrix();
		input_matrix = rotate_mat.returnRotatedMatrix(input_matrix);
		System.out.println("\nOutput : ");
		for(int i=0; i< 3; ++i)
		{
			for(int j=0; j < 4; ++j)
			{
				System.out.print(input_matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
