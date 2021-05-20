package com.chiwei.craft.code.invw;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BlockQ {

	private List<Integer> container = new ArrayList<>();
	private volatile int size;
	private volatile int capacity;
	private Lock lock = new ReentrantLock();
	private final Condition isNull = lock.newCondition();
	private final Condition isFull = lock.newCondition();
	public BlockQ(int capacity) {
		// TODO Auto-generated constructor stub
		this.capacity = capacity;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
