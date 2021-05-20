package com.chiwei.craft.code.invw;

import java.util.ArrayList;
import java.util.List;

public class PV {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PVQueue pvq = new PVQueue();
		Producer p1 = new Producer(pvq,1);
		Producer p2 = new Producer(pvq,6);
		Producer p3 = new Producer(pvq,2);
		Producer p4 = new Producer(pvq,9);
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		Consumer c1 = new Consumer(pvq);
		Consumer c2 = new Consumer(pvq);
		Consumer c3 = new Consumer(pvq);
		Consumer c4 = new Consumer(pvq);
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		
	}

}

class PVQueue {
	private static int MAX = 100;
	private List<Integer> list = new ArrayList<>();

	public void produce(int element) {
		synchronized (list) {
			while (list.size() + 1 >= MAX) {
				// 超出限制
				try {
					list.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			list.add(element);
			System.out.println("生产完" + element + "后的队列 " + list);
			list.notifyAll();
		}
	}

	public void consume() {
		synchronized (list) {
			while (list.size() <= 0) {
				// 没有元素可消费
				try {
					list.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			int res = list.remove(0);// 移除头部元素
			System.out.println("消费完" + res + "后的队列 " + list);
			list.notifyAll();
		}
	}
}

class Producer extends Thread {
	private int ele;
	private PVQueue pvq;
	public Producer(PVQueue pvq,int ele) {
		// TODO Auto-generated constructor stub
		this.pvq = pvq;
		this.ele = ele;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		pvq.produce(ele);
	}
}

class Consumer extends Thread {
	private PVQueue pvq;
	public Consumer(PVQueue pvq) {
		// TODO Auto-generated constructor stub
		this.pvq = pvq;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		pvq.consume();
	}
}










