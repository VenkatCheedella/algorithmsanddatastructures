package Graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BinaryHeap<T> {
	
	public class HeapNode{
		private int weight;
		private T position;
		
		public HeapNode(T position, int weight){
			this.weight = weight;
			this.position = position;
		}
		
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
		public T getPosition() {
			return position;
		}
		public void setPosition(T position) {
			this.position = position;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj){
			if(obj == null)
				return false;
			if(obj.getClass() != getClass())
				return false;
			HeapNode givenNode = (HeapNode)obj;
			if(givenNode.getPosition() != this.getPosition())
				return false;
			if(givenNode.getWeight() != this.getWeight())
				return false;
			return true;
		}
	}
	
	private Map<T, Integer> nodePosition;
	private ArrayList<HeapNode> minHeap;
	
	public BinaryHeap() {
		nodePosition = new HashMap<>();
		minHeap = new ArrayList<>();
	}
	
	
	public int getIndexOfKey(T key){
		Integer nodePos = nodePosition.get(key);
		return nodePos.intValue();
	}
	
	public HeapNode getNode(int index){
		HeapNode nodeAtIndex = null;
		if(index < minHeap.size())
			nodeAtIndex = minHeap.get(index);
		return nodeAtIndex;
	}
	
	public void heapifyFromTop(int index){			
		int leftChildIndex = 2*index +1;
		int rightChildIndex = 2*index +2;
		HeapNode rChild = null;		
		HeapNode lChild = null;
		if(rightChildIndex < minHeap.size()){
			rChild = minHeap.get(rightChildIndex);
			lChild = minHeap.get(leftChildIndex);
		}		
		else if(leftChildIndex < minHeap.size()){
			lChild = minHeap.get(leftChildIndex);
		}
		HeapNode smallest = null;
		int smallestIndex = -1;
		if(lChild != null && rChild != null){
			if(lChild.weight < rChild.weight){
				smallest = lChild;
				smallestIndex = leftChildIndex;
			}
			else
			{
				smallest = rChild;
				smallestIndex = rightChildIndex;
			}				
		}
		else if (lChild != null){
			smallest = rChild;
			smallestIndex = rightChildIndex;
		}
		HeapNode currentNode = minHeap.get(index);
		if(smallest != null){
			if(currentNode.weight > smallest.weight){
				minHeap.set(index, smallest);
				minHeap.set(smallestIndex, currentNode);
				T smallestNodekey = smallest.getPosition();
				nodePosition.put(smallestNodekey, index);
				T currentNodeKy =currentNode.getPosition();
				nodePosition.put(currentNodeKy, smallestIndex);
				heapifyFromTop(smallestIndex);				
			}
		}
								
	}
	
	public T extractMin(){
		if(!minHeap.isEmpty()){
			int lastIndex = minHeap.size()-1;
			HeapNode lastNode = minHeap.remove(lastIndex);	
			if(minHeap.isEmpty()){
				nodePosition.remove(lastNode.getPosition());
				return lastNode.position;
			}				
			HeapNode minNode = minHeap.set(0, lastNode);			
			nodePosition.remove(minNode.getPosition());
			nodePosition.put(lastNode.getPosition(), 0);
			heapifyFromTop(0);
			return minNode.getPosition();
		}
		return null;
	}
	
	public int getValue(T key){
		Integer pos = nodePosition.get(key);
		if(pos != null){
			HeapNode currNode = minHeap.get(pos);
			return currNode.getWeight();
		}
		else
			return -1;
	}
	
	public void decreaseTheWeight(T key, int newWeight){
		int nodeIndex = nodePosition.get(key);
		HeapNode nodeRef = minHeap.get(nodeIndex);
		nodeRef.setWeight(newWeight);
		heapifyFromBottom(nodeIndex);		
	}
	
	public void heapifyFromBottom(int index){		
		int parent = (index-1)/2;
		if(parent >=0){
			int parentWeight = minHeap.get(parent).getWeight();
			int currentWeight = minHeap.get(index).getWeight();
			if(parentWeight > currentWeight){
				HeapNode parentNode = minHeap.get(parent);
				HeapNode childNode = minHeap.get(index);
				minHeap.set(parent, childNode);
				minHeap.set(index, parentNode);
				T parent_key = parentNode.getPosition();
				nodePosition.put(parent_key, index);
				T child_key = childNode.getPosition();
				nodePosition.put(child_key, parent);
				if(parent > 0)
					heapifyFromBottom(parent);
			}			
		}
		
	}

	public void addElementToHeap(T key, int value){		
		HeapNode newNode = new HeapNode(key, value);
		int size = minHeap.size();
		nodePosition.put(key, size);		
		minHeap.add(size, newNode);	
		heapifyFromBottom(size);
	}
	
	public boolean isEmpty(){
		return nodePosition.isEmpty();
	}
	
	@Override
	public String toString(){
		StringBuffer output = new StringBuffer(minHeap.size());
		for(HeapNode node : minHeap){
			output.append("Node Key : " + node.getPosition() + " Node value : " + node.getWeight() + "\n");
		}
		return output.toString();
	}
	
	public void printKeyPositions(){
		System.out.println(nodePosition);
	}
	
	public static void main(String[] args) {		
		try{			
			File file = new File("BinaryHeap.txt");			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String readLine;
			Integer numOfNodes = null;
			if((readLine = reader.readLine()) != null)
				numOfNodes = Integer.valueOf(readLine);
			BinaryHeap<String> customizedMinHeap =  new BinaryHeap<>();
			if(numOfNodes != null){
				readLine = null;
				for(int i=0; i< numOfNodes.intValue(); ++i){
					if((readLine = reader.readLine()) != null){
						String[] arguments = readLine.split("\\s");
						Integer value = Integer.valueOf(arguments[1]);												
						customizedMinHeap.addElementToHeap(arguments[0], value.intValue());
					}
				}
			}	
			System.out.println("Data in the min Heap");
			System.out.println(customizedMinHeap);
			customizedMinHeap.printKeyPositions();
			customizedMinHeap.decreaseTheWeight("Pramila", 1);
			System.out.println("Data after decrease of weight operation");
			System.out.println(customizedMinHeap);
			customizedMinHeap.printKeyPositions();
			customizedMinHeap.extractMin();
			System.out.println("Data after extraction of minHeap");
			System.out.println(customizedMinHeap);
			customizedMinHeap.printKeyPositions();
			reader.close();			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}				
	}

}
