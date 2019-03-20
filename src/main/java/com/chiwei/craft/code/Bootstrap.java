package com.chiwei.craft.code;

import com.chiwei.craft.code.decorator.CainiaoExpressDecorator;
import com.chiwei.craft.code.decorator.Express;
import com.chiwei.craft.code.decorator.JDExpressDecorator;
import com.chiwei.craft.code.decorator.SFExpress;
import com.chiwei.craft.code.factory.abstraction.AmericanCarFactory;
import com.chiwei.craft.code.factory.method.CarFactory2;
import com.chiwei.craft.code.factory.method.ChinaHeZiCarFactory;
import com.chiwei.craft.code.factory.simple.CarFactory;
import com.chiwei.craft.code.factory.simple.SimpleFactory;
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
		System.out.println("观察者模式");
		CarLicensePlateSubject subject = new CarLicensePlateSubject();
		ObserverZhangsan observer = new ObserverZhangsan();
		subject.registerObserver(observer);
		subject.notifyObservers();
	}

	/**
	 * JAVA内置观察者模式实现
	 */
	public static void testObserverInJava() {
		System.out.println("JAVA内置观察者模式");
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
		System.out.println("装饰器模式");
		Express express = new JDExpressDecorator(new CainiaoExpressDecorator(new SFExpress()));
		express.receive();
		express.deliver();
	}
	
	/**
	 * 简单工厂模式
	 */
	public static void simpleFactoryMethod() {
		System.out.println("简单工厂模式");
		CarFactory factory = new CarFactory(new SimpleFactory());
		factory.testCar("Nissan");
	}
	
	/**
	 * 工厂方法模式
	 */
	public static void factoryMethod() {
		System.out.println("工厂方法模式");
		CarFactory2 factory = new ChinaHeZiCarFactory();
		factory.testCar("BMW");
		factory.testCar("Nissan");
	}
	
	/**
	 * 抽象工厂
	 */
	public static void abstractFactory() {
		System.out.println("工厂方法模式");
		CarFactory2 factory = new AmericanCarFactory();
		factory.testCar("Benz");
	}

	public static void main(String[] args) {
		// testObserver();
		// testObserverInJava();
		//testDecorator();
		simpleFactoryMethod();
		factoryMethod();
		abstractFactory();
	}
}
