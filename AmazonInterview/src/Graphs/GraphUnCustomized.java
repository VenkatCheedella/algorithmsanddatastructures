package Graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class GraphNode
{
	public GraphNode(int value) {
		this.value = value;
	}
	int value;
	GraphNode adjacent;
}

public class GraphUnCustomized {

	private int[] vertices;
	private GraphNode[] edges;
	private int count;	
	
	public GraphUnCustomized(int vertexCount)
	{
		vertices = new int[vertexCount];
		edges = new GraphNode[vertexCount];
		//count =0;
	}
	
	public void addVertex(int value)
	{
		vertices[count] = value;
		edges[count] = new GraphNode(value);
		++count;
	}
	
	private int getIndexOfVertex(int vertex)
	{
		for(int i=0; i< count; ++i)
		{
			if(vertices[i] == vertex)
				return i;
		}
		return -1;
	}
	
	private void addAdjacentVertexToNode(int node1_index, int node2_index)	
	{
		GraphNode temp = edges[node1_index].adjacent;				
		GraphNode new_index_node = new GraphNode(node2_index);
		edges[node1_index].adjacent = new_index_node;
		new_index_node.adjacent = temp;		
	}	
	
	public void addEdge(int value1, int value2)
	{
		int first_val_index = getIndexOfVertex(value1);
		int sec_val_index = getIndexOfVertex(value2);
		if(first_val_index != -1 && sec_val_index != -1)
		{			
			addAdjacentVertexToNode(first_val_index, sec_val_index);
			addAdjacentVertexToNode(sec_val_index, first_val_index);
		}				
		else
		{
			System.out.println("Given vertex is not present in the graph");
		}
	}
	
	public void printGraph()
	{
		for(int i=0; i< edges.length; ++i)
		{
			GraphNode gr_node = edges[i];
			System.out.println();
			System.out.print(gr_node.value + "->");
			while(gr_node.adjacent != null)
			{
				int index_of_vertex = gr_node.adjacent.value;
				System.out.print(edges[index_of_vertex].value + " , ");
				gr_node = gr_node.adjacent;
			}
		}
	}
	
	private void dfsGraph(boolean[] vertex_status, int vertex_index)
	{
		
		for(int i= vertex_index ; i < vertex_status.length; ++i)
		{
			if(!vertex_status[vertex_index])
			{
				vertex_status[vertex_index] = true;
				GraphNode graph_node = edges[vertex_index].adjacent;
				System.out.print(edges[vertex_index].value + " ");
				while(graph_node != null)
				{
					dfsGraph(vertex_status, graph_node.value);
					graph_node = graph_node.adjacent;
				}
			}
		}
	}
	
	public void dfsGraph()
	{
		boolean[] vertex_status = new boolean[count];
		for(int i=0; i< vertex_status.length; ++i)
		{
			dfsGraph(vertex_status, i);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		//Scanner scan;
		File file;			
		if(scan.hasNext())
		{
			file = new File("undirected graph.txt");
			scan.close();
			scan =new Scanner(file);
		}					
		int num_of_verices = 0;
		if(scan.hasNext())
		{
			num_of_verices = scan.nextInt();
		}
		GraphUnCustomized graph = new GraphUnCustomized(num_of_verices);
		while(num_of_verices >0)
		{
			if(scan.hasNext())
			{
				graph.addVertex(scan.nextInt());
				--num_of_verices;
			}
		}
		while(scan.hasNext())
		{
			graph.addEdge(Integer.valueOf(scan.next()), Integer.valueOf(scan.next()));			
		}	
		graph.printGraph();
		System.out.println("Depth first Search :");
		graph.dfsGraph();
		scan.close();
	}

}
