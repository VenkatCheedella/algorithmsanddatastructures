package Graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Vertex<T>
{
	Set<Vertex<T>> adjacentVertices;
	Set<Edge<T>> connectedEdges;
	T value;
	long id;
	
	public Vertex(long id)
	{
		this.id = id;
		this.adjacentVertices = new HashSet<>();
		this.connectedEdges = new HashSet<>();
	}
	
	public Vertex(long id, T value) {
		this.value = value;
		this.id = id;
		this.adjacentVertices = new HashSet<>();
		this.connectedEdges = new HashSet<>();
	}
		
	public long getId()
	{
		return this.id;
	}
	
	public T getValue()
	{
		return this.value;
	}
	
	public boolean addAdjacentVertex(Vertex<T> adjvertex)
	{
		
		return adjacentVertices.add(adjvertex);
	}
	
	public Set<Vertex<T>> getAdjacentVertices(){
		return adjacentVertices;
	}
	
	public Set<Edge<T>> getConnectedEdges(){
		return connectedEdges;
	}
	
	public boolean addEdge(Edge<T> connectededge)
	{
		return connectedEdges.add(connectededge);
	}
	
	@Override
	public int hashCode()
	{
		int prime = 31;
		int result =1;
		result = result*prime + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)		
			return true;		
		if(obj == null)
			return false;
		if(obj.getClass() != getClass())		
			return false;			
		Vertex obj_vertex = (Vertex) obj;
		if(this.id != obj_vertex.getId())
			return false;
		return true;
	}
	
	public void setData(T value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return "Vertex Id : " + this.id +  " Vertex value " + this.value ;
	}
}

class Edge<T>
{
	private Vertex<T> vertex1;
	private Vertex<T> vertex2;
	private int weight;
	private boolean isDirected;
	
	Edge(Vertex<T> vertex1, Vertex<T> vertex2)
	{
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
	}
	
	Edge(Vertex<T> vertex1, Vertex<T> vertex2, int weight)
	{
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}
	
	Edge(Vertex<T> vertex1, Vertex<T> vertex2, int weight, boolean isDirected){
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
		this.isDirected = isDirected;
	}
	
	Vertex<T> getVertex1()
	{
		return vertex1;
	}
	
	Vertex<T> getVertex2()
	{
		return vertex2;
	}
	
	int getWeight()
	{
		return weight;
	}
	
	boolean isDirected()
	{
		return isDirected;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(obj.getClass() != getClass())
			return false;
		Edge<T> userEdge = (Edge<T>) obj;
		if(weight == userEdge.weight && vertex1.equals(userEdge.getVertex1()) && vertex2.equals(userEdge.getVertex2()))
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "Edge [vertex1=" + vertex1 + ", vertex2=" + vertex2 + ", weight=" + weight + "]";
	}

	@Override
	public int hashCode()
	{
		int prime = 31;
		int result =1;
		result = prime* result + ((vertex1 == null) ? 0 : vertex1.hashCode());
		result = prime* result + ((vertex2 == null) ? 0 : vertex2.hashCode());
		return result;
	}
}

public class Graph<T> {
	
	private Collection<Edge<T>> edges;
	private Map<Long, Vertex<T>> vertices;
	boolean isDirected = false;
	
	public Graph(boolean isDirected) {
		edges = new ArrayList<>();
		vertices = new HashMap<>();
		this.isDirected = isDirected;
	}
	
	public Vertex<T> getVertex(long id)
	{
		return vertices.get(id);
	}
	
	public Collection<Edge<T>> getAllEdges()
	{
		return edges;
	}
	
	public Collection<Vertex<T>> getAllVertices()
	{
		return vertices.values();
	}
	
	public void setDataForVertex(long id, T value)
	{
		Vertex<T> vertex = vertices.get(id);
		vertex.setData(value);
	}
	
	public void addVertex(Vertex<T> vertex)
	{
		if(vertices.containsKey(vertex.getId()))
			return;		
		vertices.put(vertex.getId(), vertex);
	}
	
	public void addEdge(long id1, long id2)
	{
		addEdge(id1, id2, 0);
	}
	
	public void addEdge(long id1, long id2, int weight)
	{
		Vertex<T> vertex1 = null;
		if(vertices.containsKey(id1)){
			vertex1 = vertices.get(id1);
		}else{
			vertex1 = new Vertex<>(id1);
		}
		Vertex<T> vertex2 = null;
		if(vertices.containsKey(id2)){
			vertex2 = vertices.get(id2);
		}else{
			vertex2 = new Vertex<>(id2);
		}
		Edge<T> edge = new Edge<T>(vertex1, vertex2, weight);
		edges.add(edge);
		vertex1.addEdge(edge);
		if(!isDirected)
			vertex2.addEdge(edge);			
	}
	
	public String toString()
	{
		StringBuilder str_builder = new StringBuilder(edges.size()+2);
		for(Edge<T> edge : edges)
		{
			str_builder.append( "[" + edge.getVertex1() + " " + edge.getVertex2() + " " + edge.getWeight() + "]");
			str_builder.append("\n");
		}
		return str_builder.toString();
	}
	
	public static void main(String[] args) 
	{
		try
		{
			File file = new File("test data.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String curr_line;
			Integer vertices_count = null;
			if( (curr_line = reader.readLine()) != null)				
				vertices_count = Integer.valueOf(curr_line);
			Graph<String> graph = new Graph<>(false);
			if(vertices_count != null){
				for(int i=0; i< vertices_count; ++i){
					String vertex_data = reader.readLine();
					int getIdEndIndex = vertex_data.indexOf(" ");
					Long id = Long.valueOf(vertex_data.substring(0, getIdEndIndex));
					String value = vertex_data.substring(getIdEndIndex+1);					
					Vertex<String> new_vertex = new Vertex<>(id, value);
					graph.addVertex(new_vertex);
				}								
			}
			Integer edges_count = null;
			curr_line = null;
			if( (curr_line = reader.readLine()) != null)
				edges_count = Integer.valueOf(curr_line);
			if(edges_count != null){
				for(int i=0; i< edges_count ; ++i){
					String edge_info = reader.readLine();
					int seperator_index = edge_info.indexOf(" ");
					String id1_data = edge_info.substring(0, seperator_index);
					Long id1 = Long.valueOf(id1_data);
					String id2_data = edge_info.substring(seperator_index+1);
					Long id2 = Long.valueOf(id2_data);
					if(id1 != null && id2 != null){
						graph.addEdge(id1, id2);
					}					
				}
			}
			reader.close();
			System.out.println(graph);									
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
