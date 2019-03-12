package com.chiwei.craft.code;

import com.chiwei.craft.code.decorator.CainiaoExpressDecorator;
import com.chiwei.craft.code.decorator.Express;
import com.chiwei.craft.code.decorator.JDExpressDecorator;
import com.chiwei.craft.code.decorator.SFExpress;
import com.chiwei.craft.code.observer.CarLicensePlateSubject;
import com.chiwei.craft.code.observer.ObserverZhangsan;
import com.chiwei.craft.code.observer.injava.DriverObserver;
import com.chiwei.craft.code.observer.injava.TrafficSignalObservable;

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

	/**
	 * JAVA内置观察者模式实现
	 */
	public static void testObserverInJava() {
		TrafficSignalObservable subject = new TrafficSignalObservable();
		DriverObserver observer = new DriverObserver();
		subject.addObserver(observer);
		subject.notifyObservers("红灯");
		System.out.println("观察者主动拉取数据 " + subject.getCurrentSignal());
	}

	/**
	 * 装饰器模式
	 */
	public static void testDecorator() {
		Express express = new JDExpressDecorator(new CainiaoExpressDecorator(new SFExpress()));
		express.receive();
		express.deliver();
	}

	public static void main(String[] args) {
		// testObserver();
		// testObserverInJava();
		testDecorator();
	}
}
