package com.chiwei.craft.code.observer;

public interface Subject {

	/**
	 * 在该主题上注册观察者
	 * @param o
	 */
	public void registerObserver(Observer o);
	/**
	 * 在该主题上取消该观察者
	 * @param o
	 */
	public void removeObserver(Observer o);
	/**
	 * 主题变化，通知所有观察者
	 */
	public void notifyObservers();
	
}
