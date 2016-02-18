package Round1;

/**
 * Circular Linked List in ordered way
 */

import java.util.Random;

class LLNode
{
	public LLNode(int value)
	{
		this.value = value;
	}
	LLNode next = null;
	int value;
}

public class InsertIntoCircularLinkedList {
	
	private LLNode root = null;
	private LLNode tail = null;
	
	private void buildSortedCircularLinkedList(LLNode root, int value)
	{
		if(this.root == null)
		{
			this.root = new LLNode(value);
			this.root.next = this.root;
			this.tail = this.root;
		}
		else
		{
			if(root.value <= value)
			{				
				while(true)
				{
					if(root.next == this.root)
					{
						LLNode new_node = new LLNode(value);
						new_node.next = this.root;
						root.next = new_node;
						this.tail = new_node;						
						break;
					}
					LLNode next_node  = root.next;
					if(next_node.value >= value)
					{
						LLNode new_node = new LLNode(value);
						new_node.next = next_node;
						root.next = new_node;
						break;
					}
					else
						root = root.next;
				}
			}
			else
			{
				LLNode new_node = new LLNode(value);
				tail.next = new_node;
				new_node.next = this.root;
				this.root = new_node;
			}
		}
	}
	
	public void buildSortedCircularLinkedList(int value)
	{
		buildSortedCircularLinkedList(this.root, value);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InsertIntoCircularLinkedList circular_ll = new InsertIntoCircularLinkedList();
		Random random_num = new Random();
		for(int i=1; i< 10; i++)
		{
			int num = random_num.nextInt(10);
			circular_ll.buildSortedCircularLinkedList(num);
			System.out.print(num + " ");
		}
		LLNode curr_node = circular_ll.root;
		System.out.println("Print circular LInkedList");
		do{
			System.out.print(curr_node.value + " ");
			curr_node = curr_node.next;
		}while(curr_node != circular_ll.root);
	}
}
