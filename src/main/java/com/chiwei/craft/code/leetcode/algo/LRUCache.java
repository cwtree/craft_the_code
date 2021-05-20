package com.chiwei.craft.code.leetcode.algo;

import java.util.LinkedHashMap;

public class LRUCache<K,V> extends LinkedHashMap<K, V>{
	
	private final int CACHE_SIZE;
	
	public LRUCache(int size) {
		//true 按照访问顺序来排序，最近访问在头部，最老访问的在尾部
		super(size, 0.75F, true);
		CACHE_SIZE = size;
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		// TODO Auto-generated method stub
		//当map中数据量大于指定的缓存个数的时候，自动删除最老的数据
		return size() > CACHE_SIZE;
	}
	
	
	
}
