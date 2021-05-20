package com.chiwei.craft.code.invw;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class LRUCache {

	private LinkedHashMap<Integer, Integer> cache;
	private int capacity;
	public LRUCache(int capacity) {
		// TODO Auto-generated constructor stub
		cache = new LinkedHashMap<>(capacity);
		this.capacity = capacity;
	}
	
	public int get(int key) {
		if(!cache.containsKey(key)) {
			return -1;
		}
		//get就相当于最近使用了， 需要更新位置，放到末尾处，最近使用的
		int res = cache.get(key);
		cache.remove(key);//从链表中删除
		cache.put(key, res);//重新放到链表末尾处
		return res;
	}
	
	public void put(int key,int value) {
		if(cache.containsKey(key)) {
			cache.remove(key);//存在，在当前链表删除
		}
		//满了，需要删除最近最久未使用的，表头位置先删除
		if(capacity==cache.size()) {
			//cache满了，删除链表头位置
			Set<Integer> keySet = cache.keySet();
			Iterator<Integer> it = keySet.iterator();
			cache.remove(it.next());//删除表头
		}
		cache.put(key, value);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LRUCache cache = new LRUCache(3);
		cache.put(1, 1);
		cache.put(2, 2);
		cache.put(3, 3);
		cache.put(4, 4);
		cache.put(5, 5);
		System.out.println(cache.get(3)+"--");
	}

}














