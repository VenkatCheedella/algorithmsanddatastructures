package Round1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

class LRUNode
{
	public LRUNode(int key, String value)
	{
		this.key = key;
		this.value = value;
	}
	int key;
	String value;
}

public class LRUCache {
	
	HashMap<Integer, LRUNode> LRU_Hash = new HashMap<>();
	LinkedList<LRUNode> LRUCacheList = new LinkedList<>();
	int limit = 10;	
	
	
	public void setPage(int key, String value)
	{
		LRUNode new_node = new LRUNode(key, value);
		setPage(new_node);
	}
	
	public void setPage(LRUNode page_node)
	{		
		if(LRUCacheList.size() > limit)
		{					
			LRUNode del_node = LRUCacheList.removeLast();
			System.out.println("LRUCache is full, Deleting node : "+ del_node.key);
			LRU_Hash.remove(del_node.key);
			
		}
		LRUCacheList.addFirst(page_node);
		LRU_Hash.put(page_node.key, page_node);
	}
	
	public String getPage(int key)
	{
		LRUNode get_node = LRU_Hash.get(key);
		if(get_node == null)
		{
			System.out.println("Inserting Page to Cache");
			setPage(key, String.valueOf(key));			
		}
		get_node = LRU_Hash.get(key);
		LRUCacheList.remove(get_node);
		LRUCacheList.addFirst(get_node);
		return get_node.value;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand_num = new Random();	
		LRUCache lru_cache = new LRUCache();
		for(int i=0; i< 15; i++)
		{
			System.out.println(lru_cache.getPage(rand_num.nextInt(100)));
		}
	}

}
