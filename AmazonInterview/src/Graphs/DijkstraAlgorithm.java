package Graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm<T> {

	public Map<Vertex<T> , Vertex<T>> findShortestDistanceFromVertices(Graph<T> graph, long root_id) {
		Map<Vertex<T> , Vertex<T>> childParentInfo =new HashMap<>();
		Map<Vertex<T>, Integer> distanceFromRoot = new HashMap<>();
		BinaryHeap<Vertex<T>> customizedMinHeap = new BinaryHeap<>();			
		//Creation of MinHeap+Map 
		Vertex<T> root = graph.getVertex(root_id);
		for(Vertex<T> vertex : graph.getAllVertices()){
			if(vertex.equals(root))
				customizedMinHeap.addElementToHeap(vertex, 0);
			else
				customizedMinHeap.addElementToHeap(vertex, Integer.MAX_VALUE);
		}

		childParentInfo.put(root, null);
		distanceFromRoot.put(root, 0);

		while(!customizedMinHeap.isEmpty()){
			Vertex<T> minLengthVertex = customizedMinHeap.extractMin();			
			Set<Edge<T>> connectedEdges =null;
			if(minLengthVertex != null)
				connectedEdges = minLengthVertex.getConnectedEdges();
			if(connectedEdges != null){
				int currentVertexDistance = distanceFromRoot.get(minLengthVertex);
				for(Edge<T> connEdge : connectedEdges){
					Vertex<T> firstVertex = minLengthVertex;
					Vertex<T> secondVeretex = (connEdge.getVertex1().equals(firstVertex)) ? connEdge.getVertex2() : connEdge.getVertex1();
					int currDistanceOfAdjVertex = customizedMinHeap.getValue(secondVeretex);					
					int calculatedDistance = currentVertexDistance + connEdge.getWeight();
					if(currDistanceOfAdjVertex > calculatedDistance){
						customizedMinHeap.decreaseTheWeight(secondVeretex, calculatedDistance);
						distanceFromRoot.put(secondVeretex, calculatedDistance);
						childParentInfo.put(secondVeretex, firstVertex);
					}
				}
			}
		}
		printDistanceForEachNode(distanceFromRoot);
		return childParentInfo;
	}

	public void printDistanceForEachNode(Map<Vertex<T>, Integer> distanceFromRoot){
		Set<Vertex<T>> keys = distanceFromRoot.keySet();
		for(Vertex<T> vertex : keys){
			System.out.println("Minimum distnace from root for vertex : " + vertex.id +  " " + distanceFromRoot.get(vertex));
		}
	}

	public void printShortestPathForEachVertex(Map<Vertex<T>, Vertex<T>> paths){		
		Set<Vertex<T>> nodes = paths.keySet();
		for(Vertex<T> vertex : nodes){
			System.out.println();
			Vertex<T> parent = paths.get(vertex);
			while(parent != null){
				System.out.print(vertex.id + " <- ");
				vertex = parent;
				parent = paths.get(vertex);
			}	
			System.out.print(vertex.id);
		}
	}

	public static void main(String[] args) {
		try{
			File file = new File("weight undirected graph.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String readline;
			Integer vertices_count = null;
			if((readline = reader.readLine()) != null){
				vertices_count = Integer.valueOf(readline);
			}
			readline = null;
			String[] vertexArguments = null;
			Graph<String> graph = new Graph<>(false);
			for(int i=0; i< vertices_count; ++i){
				if((readline = reader.readLine()) != null){
					vertexArguments = readline.split("\\s");
					String id = vertexArguments[0];
					String value = vertexArguments[1];
					Vertex<String> newVertex = new Vertex<>(Long.valueOf(id), value);
					graph.addVertex(newVertex);
				}
				readline = null;
			}										
			Integer edgeCount = null;
			if((readline = reader.readLine())!= null)
				edgeCount = Integer.valueOf(readline);	
			for(int i=0; i< edgeCount.intValue(); ++i){
				if(((readline = reader.readLine()) != null)){
					String[] edgeArguments = readline.split("\\s");
					Long id1 = Long.valueOf(edgeArguments[0]);
					Long id2 = Long.valueOf(edgeArguments[1]);
					int weight = Integer.valueOf(edgeArguments[2]);										
					graph.addEdge(id1, id2, weight);
				}
				readline = null;
			}
			DijkstraAlgorithm<String> dijkstras_algorithm = new DijkstraAlgorithm<>();
			Map<Vertex<String>, Vertex<String>> paths =  dijkstras_algorithm.findShortestDistanceFromVertices(graph, 100001);			
			dijkstras_algorithm.printShortestPathForEachVertex(paths);
			reader.close();			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
