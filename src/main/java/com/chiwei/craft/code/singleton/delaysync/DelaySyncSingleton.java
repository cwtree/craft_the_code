package com.chiwei.craft.code.singleton.delaysync;

public class DelaySyncSingleton {

	/**
	 * volatile修饰确保该变量的变化对任何线程可见
	 */
	private static volatile DelaySyncSingleton instance;

	private DelaySyncSingleton() {

	}

	/**
	 * 延迟同步，只有第一次调用实例化的时候会同步阻塞
	 * 双重检测
	 * @return
	 */
	public static DelaySyncSingleton getInstance() {
		if (instance == null) {
			synchronized (DelaySyncSingleton.class) {
				if (instance == null) {
					instance = new DelaySyncSingleton();
				}
			}
		}
		return instance;
	}

}
