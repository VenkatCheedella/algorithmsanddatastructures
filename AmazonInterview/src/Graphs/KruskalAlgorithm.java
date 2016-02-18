package Graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class EdgeComparator<T> implements Comparator<Edge<T>>{
	@Override
	public int compare(Edge<T> e1, Edge<T> e2) {		 
		return e1.getWeight()-e2.getWeight();
	}	
}


public class KruskalAlgorithm<T> {
		
	public List<Edge<T>> minimumSpanningTree(Graph<T> graph){
		List<Edge<T>> edges = new ArrayList<>();
		for(Edge<T> edge : graph.getAllEdges())
			edges.add(edge);
		EdgeComparator<T> newComparator =new EdgeComparator<>();
		Collections.sort(edges, newComparator);
		DisJointSet disJointSet = new DisJointSet();
		for(Vertex<T> node : graph.getAllVertices())
			disJointSet.createSet(node.getId());	
		List<Edge<T>> minSpanningTreeContainingEdges = new ArrayList<>();
		for(Edge<T> edge : edges){
			if(disJointSet.createUnion(edge.getVertex1().id, edge.getVertex2().id)){
				minSpanningTreeContainingEdges.add(edge);
			}
		}
		return minSpanningTreeContainingEdges;
 	}	
	
	public static void main(String[] args) {
		try
		{
			File file = new File("kruskaldata.txt");
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
					String[] edgeInfo = edge_info.split("\\s");				
					Long id1 = Long.valueOf(edgeInfo[0]);					
					Long id2 = Long.valueOf(edgeInfo[1]);
					Integer weight = Integer.valueOf(edgeInfo[2]);
					if(id1 != null && id2 != null && weight != null){
						graph.addEdge(id1, id2, weight);
					}					
				}
			}
			reader.close();
			//System.out.println(graph);	
			KruskalAlgorithm<String> minSpanningTree = new KruskalAlgorithm<>();
			List<Edge<String>> listOfEdges = minSpanningTree.minimumSpanningTree(graph);			
			System.out.println("Minimum spanning tree : ");
			for(Edge<String> edge : listOfEdges){
				System.out.println(edge);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

}
