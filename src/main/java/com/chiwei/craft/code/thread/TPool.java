package com.chiwei.craft.code.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						// TODO Auto-generated method stub
						System.out.println("reject#" + Thread.currentThread().getName());
					}
				}
 * 
 * @author phoenix
 * @date 2020年9月12日
 */
public class TPool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(1, 2, 5000, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(1), new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						// TODO Auto-generated method stub
						Thread t = new Thread(r, "TPool-" + r.hashCode());
						t.setDaemon(true);
						return t;
					}
				}, new ThreadPoolExecutor.CallerRunsPolicy());
		for (int i = 0; i < 10; i++) {
			tpe.execute(new MyThread());
		}
		try {
			Thread.sleep(1000000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
