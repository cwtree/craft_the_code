package com.chiwei.craft.code.observer.injava;

import java.util.Observable;

/**
 * JAVA内置观察者模式实现 交通信号灯可观察者-主题
 * 
 * @author chiwei
 *
 */
public class TrafficSignalObservable extends Observable {

	private String currentSignal;// 当前信号灯

	/**
	 * 提供一个拉数据的方法，方便观察者也可以主动过来拉数据
	 * 
	 * @return
	 */
	public String getCurrentSignal() {
		return currentSignal;
	}

	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		this.currentSignal = (String) arg;// 更新当前信号灯
		setChanged();// 这里一定要设置状态改变
		super.notifyObservers(arg);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "交通信号灯主题";
	}

}
