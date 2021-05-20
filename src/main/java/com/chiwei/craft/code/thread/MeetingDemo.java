package com.chiwei.craft.code.thread;

import java.util.concurrent.CountDownLatch;

public class MeetingDemo {

	private static CountDownLatch latch = new CountDownLatch(5);

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new Director("主持人", latch).start();
		for (int i = 0; i < 5; i++) {
			new Attendee(i + "", latch).start();
		}
	}

}

class Director extends Thread {
	private CountDownLatch latch;
	private String name;

	public Director(String name, CountDownLatch latch) {
		// TODO Auto-generated constructor stub
		this.latch = latch;
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("主持人等待开会");
		try {
			latch.await();
			System.out.println("人员到齐，准备开会");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Attendee extends Thread {

	private CountDownLatch latch;
	private String name;

	public Attendee(String name, CountDownLatch latch) {
		// TODO Auto-generated constructor stub
		this.latch = latch;
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(name + " 到达会议室");
		latch.countDown();
	}

}
