package com.chiwei.craft.code;

import com.chiwei.craft.code.observer.CarLicensePlateSubject;
import com.chiwei.craft.code.observer.ObserverZhangsan;

/**
 * 
 * @author chiwei
 *
 */
public class Bootstrap {

	/**
	 * 观察者模式测试
	 */
	public static void testObserver() {
		CarLicensePlateSubject subject = new CarLicensePlateSubject();
		ObserverZhangsan observer = new ObserverZhangsan();
		subject.registerObserver(observer);
		subject.notifyObservers();
	}
	
	public static void main(String[] args) {
		testObserver();

	}
}
