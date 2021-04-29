package com.chiwei.craft.code.thread;

import java.util.Date;

public class MyThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("begin " + new Date() + " MyThread Print " + Thread.currentThread().getName());
		try {
			Thread.sleep(5100L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end " + new Date() + " MyThread Print " + Thread.currentThread().getName());
	}

}
