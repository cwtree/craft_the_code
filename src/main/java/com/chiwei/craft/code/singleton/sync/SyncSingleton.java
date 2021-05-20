package com.chiwei.craft.code.singleton.sync;

public class SyncSingleton {

	private static SyncSingleton instance;

	private SyncSingleton() {

	}

	/**
	 * 存在性能问题，因为调用getInstance方法的地方都会同步阻塞一下
	 * 
	 * @return
	 */
	public static synchronized SyncSingleton getInstance() {
		if (instance == null) {
			instance = new SyncSingleton();
		}
		return instance;
	}

}
