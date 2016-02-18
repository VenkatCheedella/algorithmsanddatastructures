package Round1;


import java.util.LinkedList;
import java.util.Queue;

// Level Order search with count variable, it represents the number of times a node is touched

class Node
{
	public Node(int value)
	{
		this.value = value;
	}
	Node left = null;
	Node right = null;
	int value;
	int count = 0;
}

public class BinaryTree {

	Node root;
	Queue<Node> tree_queue = new LinkedList<>();
	
	public void buildTree(int value)
	{
		buildTree(this.root, value);
	}
	
	public void buildTree(Node root, int value)
	{
		if(root == null)
		{
			this.root = new Node(value);					// This is important in Java, references are passed by values
			tree_queue.add(this.root);
		}
		else
		{
			Node curr_node = tree_queue.peek();
			if(curr_node.left == null)
			{
				curr_node.left = new Node(value);
				tree_queue.add(curr_node.left);
				++curr_node.count;
			}
			else if(curr_node.right == null)
			{
				curr_node.right = new Node(value);
				tree_queue.add(curr_node.right);
				++curr_node.count;
				tree_queue.poll();
			}			
		}
	}
	
	public void printTree(Node root)
	{
		Queue<Node> temp_q = new LinkedList<>();
		temp_q.add(root);
		temp_q.add(null);
		while(!temp_q.isEmpty())
		{
			Node curr_node = temp_q.poll();
			if(curr_node == null)
			{
				System.out.println();
				if(temp_q.isEmpty())
					return;
				temp_q.add(null);
			}				
			else
			{
				System.out.print(curr_node.value);
				if(curr_node.left != null)
					temp_q.add(curr_node.left);
				if(curr_node.right != null)
					temp_q.add(curr_node.right);
			}			
		}
	}
	
	public int postOrderTraversal(Node curr_root)
	{
		int left_height=0, right_height=0;
		if(curr_root.left != null)
		{
			left_height = postOrderTraversal(curr_root.left);
		}
		if(curr_root.right != null)
		{
			right_height = postOrderTraversal(curr_root.right); 
		}
		return (left_height > right_height) ? ++left_height : ++right_height;
	}
	
	public static void main(String[] args) {		
		BinaryTree bt = new BinaryTree();
		for(int i=0; i< 10; ++i)
			bt.buildTree(i);
		System.out.println("Print the level order : ");
		bt.printTree(bt.root);
		System.out.println(bt.postOrderTraversal(bt.root));
	}

}
