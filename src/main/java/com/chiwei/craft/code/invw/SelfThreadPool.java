package com.chiwei.craft.code.invw;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SelfThreadPool {

	private static final int THREAD_NUM = 5;
	private static final int TASK_NUM = 50;
	private int threadNum;
	private int taskNum;
	private final Set<WorkThread> workThreads;
	private final BlockingQueue<Runnable> taskQueue;

	public SelfThreadPool() {
		// TODO Auto-generated constructor stub
		this(THREAD_NUM, TASK_NUM);
	}

	public SelfThreadPool(int threadNum, int taskNum) {
		// TODO Auto-generated constructor stub
		if (threadNum <= 0) {
			threadNum = THREAD_NUM;
		}
		if (taskNum <= 0) {
			taskNum = TASK_NUM;
		}
		taskQueue = new ArrayBlockingQueue<>(taskNum);
		this.threadNum = threadNum;
		this.taskNum = taskNum;
		workThreads = new HashSet<>();
		for (int i = 0; i < taskNum; i++) {
			WorkThread wt = new WorkThread("thread-" + i);
			wt.start();
			workThreads.add(wt);
		}
	}

	public void execute(Runnable r) {
		try {
			taskQueue.put(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		System.out.println("destroy ...");
		if (workThreads == null || workThreads.size() <= 0) {
			return;
		}
		for (WorkThread wt : workThreads) {
			wt.stopWork();
			wt = null;
		}
		workThreads.clear();
	}
	
	class WorkThread extends Thread {
		public WorkThread(String name) {
			super();
			setName(name);
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(!interrupted()) {
				try {
					Runnable r = taskQueue.take();
					r.run();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

		public void stopWork() {
			interrupt();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SelfThreadPool stp = new SelfThreadPool();
		for (int i = 0; i < 100; i++) {
			stp.execute(new MyTask("thread-" + i));
		}
	}
}


class MyTask implements Runnable {
	String name;

	public MyTask(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("name-" + name);
	}

}
