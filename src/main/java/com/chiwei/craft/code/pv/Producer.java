package com.chiwei.craft.code.pv;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

	private BlockingQueue<String> queue;// 内存缓冲区

	private final AtomicInteger produce = new AtomicInteger(0);

	public Producer(BlockingQueue<String> queue) {
		// TODO Auto-generated constructor stub
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				int a = produce.getAndIncrement();
				queue.put(a + "");
				System.out.println("生产产品 " + a);
				Thread.sleep(1000L);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
