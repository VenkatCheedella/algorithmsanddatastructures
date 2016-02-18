package Graphs;

import java.util.HashMap;

public class DisJointSet {
	
	private HashMap<Long, Node> idToNodeMapping;
	
	public DisJointSet(){
		idToNodeMapping = new HashMap<>();
	}
	
	class Node{			
		public Node(long id) {			
			this.id = id;
			this.parent = this;
		}
		long id;
		int rank;
		Node parent;
	}
	
	public void createSet(long id){
		Node new_node = new Node(id);
		idToNodeMapping.put(new Long(id), new_node);
	}
	
	public boolean createUnion(long id1, long id2){
		Node firstNode = idToNodeMapping.get(id1);
		Node secondNode = idToNodeMapping.get(id2);		
		if(firstNode == null || secondNode == null)
			return false;		
		Node firstNodeRoot = findRoot(firstNode);
		Node secondNodeRoot = findRoot(secondNode);		
		if(firstNodeRoot == secondNodeRoot)
			return false;
		else{
			if(firstNodeRoot.rank >= secondNodeRoot.rank){
				firstNodeRoot.rank = (firstNodeRoot.rank == secondNodeRoot.rank) ? 
						firstNodeRoot.rank+1 : firstNodeRoot.rank;
				secondNodeRoot.parent = firstNodeRoot;
			}
			else{
				firstNodeRoot.parent = secondNodeRoot;
			}
		}
		return true;
	}
	
	public long findRoot(long id){
		Node currNode = idToNodeMapping.get(id);
		if(currNode == null)
			return -1;
		else
			return findRoot(currNode).id;
	}
	
	public Node findRoot(Node currNode){		
		Node currRoot = currNode.parent;
		if(currNode == currRoot)
			return currNode;
		else{
			Node rightRoot = findRoot(currNode.parent);
			currNode.parent = rightRoot;
			return rightRoot;
		}		
	}
	
	public static void main(String[] args) {
		
		
		DisJointSet disJointSet = new DisJointSet();
		disJointSet.createSet(1234567);
		disJointSet.createSet(1234568);
		disJointSet.createSet(1234569);
		disJointSet.createSet(1234566);
		disJointSet.createSet(1234565);
		disJointSet.createSet(1234564);
		disJointSet.createSet(1234563);
		
		disJointSet.createUnion(1234563, 1234566);
		disJointSet.createUnion(1234564, 1234567);
		disJointSet.createUnion(1234565, 1234568);
		disJointSet.createUnion(1234566, 1234567);
		disJointSet.createUnion(1234568, 1234569);
		disJointSet.createUnion(1234564, 1234565);
		
		System.out.println(disJointSet.findRoot(1234563));
		System.out.println(disJointSet.findRoot(1234564));
		System.out.println(disJointSet.findRoot(1234565));
		System.out.println(disJointSet.findRoot(1234566));
		System.out.println(disJointSet.findRoot(1234567));
		System.out.println(disJointSet.findRoot(1234568));
		System.out.println(disJointSet.findRoot(1234569));		
		
	}

}
