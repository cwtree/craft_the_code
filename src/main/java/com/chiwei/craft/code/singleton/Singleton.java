package com.chiwei.craft.code.singleton;

public class Singleton {

	private static Singleton instance;

	private Singleton() {

	}

	/**
	 * 多线程方式会有问题，可能多个线程获得的对象并不是同一个，而都是自己线程内部实例化的
	 * 
	 * @return
	 */
	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

}
