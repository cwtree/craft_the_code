package com.chiwei.craft.code.pv;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PVMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		Producer p = new Producer(queue);
		Consumer c = new Consumer(queue);
		new Thread(p).start();
		new Thread(c).start();
	}

}
