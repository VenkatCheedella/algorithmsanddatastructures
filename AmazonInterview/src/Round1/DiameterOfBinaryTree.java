package Round1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class BSTNode
{
	public BSTNode(int value) {
		this.value = value;
	}
	BSTNode left;
	BSTNode right;
	int value;
}


public class DiameterOfBinaryTree {
	
	BSTNode root;
	int diameter_tree;
	
	public void buildTree(int value)	
	{
		buildTree(this.root, value);
	}
	
	//building tree
	private void buildTree(BSTNode curr_root, int value)
	{
		if(curr_root == null)
		{
			this.root = new BSTNode(value);
		}
		else
		{
			if(curr_root.value >= value)
			{
				if(curr_root.left !=null)
				{
					buildTree(curr_root.left, value);
					return;
				}
				else
				{
					curr_root.left = new BSTNode(value);
					return;
				}
			}
			else
			{
				if(curr_root.right != null)
				{
					buildTree(curr_root.right, value);
					return;
				}
				else
				{
					curr_root.right = new BSTNode(value);
					return;
				}
			}
		}
	}
	
	//Inorder
	public void printInorderTree(BSTNode curr_root)
	{
		if(curr_root.left != null)
		{
			printInorderTree(curr_root.left);
		}
		System.out.print(curr_root.value + " ");
		if(curr_root.right != null)
		{
			printInorderTree(curr_root.right);
		}
	}
	
	// Level Order
	public void printLevelOrderTraversal(BSTNode curr_root)
	{
		Queue<BSTNode> level_queue = new LinkedList<>();
		level_queue.add(curr_root);		
		level_queue.add(null);
		while(!level_queue.isEmpty())
		{
			BSTNode curr_node = level_queue.poll();
			if(curr_node == null)
			{
				if(level_queue.isEmpty())
					break;
				System.out.println();
				level_queue.add(null);
			}
			else
			{
				System.out.print(curr_node.value + " ");
				if(curr_node.left != null)
					level_queue.add(curr_node.left);
				if(curr_node.right != null)
					level_queue.add(curr_node.right);
			}
		}
	}
	
	//Diameter of tree
	public int diameterOfTree()
	{
		diameter_tree = 0;
		diameterOfTree(this.root);
		return diameter_tree;
	}
	
	// Calculation of diameter of trees
	private int diameterOfTree(BSTNode curr_root)
	{
		int curr_node_diameter, left_height = 0, right_height = 0;
		if(curr_root.left == curr_root.right)
			return 0;
		if(curr_root.left != null)
		{
			left_height = diameterOfTree(curr_root.left);
			++ left_height;
		}
		if(curr_root.right != null)
		{
			right_height = diameterOfTree(curr_root.right);
			++ right_height;
		}
		curr_node_diameter = left_height + right_height + 1;
		if(curr_node_diameter > diameter_tree)
			diameter_tree = curr_node_diameter;
		return (left_height > right_height ? left_height : right_height);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DiameterOfBinaryTree tree = new DiameterOfBinaryTree();
		Random rand_num = new Random();		
		int[] input_value = {9, 1, 16, 8, 16, 3,3,4, 15};
		for(int i=0; i< 9; i++)
		{
			tree.buildTree(input_value[i]);
			//tree.buildTree(rand_num.nextInt(20));
		}
		System.out.println( "\n" + "Inorder :");
		tree.printInorderTree(tree.root);
		System.out.println("\n" + "Level Order : ");
		tree.printLevelOrderTraversal(tree.root);
		System.out.println("\nDiameter of tree : " + tree.diameterOfTree());
	}

}
