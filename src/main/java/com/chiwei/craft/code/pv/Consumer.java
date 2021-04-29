package com.chiwei.craft.code.pv;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private BlockingQueue<String> queue;// 内存缓冲区

	public Consumer(BlockingQueue<String> queue) {
		// TODO Auto-generated constructor stub
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				String produce = queue.take();
				System.out.println("消费产品 " + produce);
				Thread.sleep(1000L);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
