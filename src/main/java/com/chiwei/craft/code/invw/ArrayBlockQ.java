package com.chiwei.craft.code.invw;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import cn.hutool.core.util.RandomUtil;

public class ArrayBlockQ {

	private int[] queue;// 数组实现队列
	private int size;// 队列大小
	private int takeIndex;// 消费数据的索引位置
	private int putIndex;// 生产数据的索引位置
	private ReentrantLock lock;
	private Condition isFull;
	private Condition isEmpty;


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<queue.length;i++) {
			sb.append(queue[i]).append("#");
		}
		return sb.toString();
	}

	public ArrayBlockQ(int size) {
		// TODO Auto-generated constructor stub
		queue = new int[size];
		//this.size = size;
		lock = new ReentrantLock();
		isFull = lock.newCondition();
		isEmpty = lock.newCondition();
		putIndex = 0;
		takeIndex = 0;
	}

	/**
	 * 死循环，必须生产成功，否则一直阻塞
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param ele
	 * @throws Exception
	 */
	public void produce(int ele) {
		lock.lock();
		try {
			while (size >= queue.length) {
				isFull.await();
			}
			queue[putIndex++] = ele;
			if (putIndex >= queue.length) {
				putIndex = 0;
			}
			size++;
			isEmpty.signal();
			System.out.println("生产 " + ele + " 后的队列 " + this);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public int consume() {
		lock.lock();
		try {
			while (size <= 0) {
				isEmpty.await();
			}
			int x = queue[takeIndex];
			queue[takeIndex] = -1;
			if (++takeIndex >= queue.length) {
				takeIndex = 0;
			}
			size--;
			isFull.signal();
			System.out.println("消费 " + x + " 后的队列 " + this);
			return x;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayBlockQ abq = new ArrayBlockQ(5);
		Thread p = new Thread(() -> {
			for (;;) {
				abq.produce(RandomUtil.randomInt(100));
				try {
					//Thread.sleep(100L);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Thread c = new Thread(() -> {
			while (true) {
				int x = abq.consume();
				try {
					//Thread.sleep(500L);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		p.start();
		c.start();
	}

}
