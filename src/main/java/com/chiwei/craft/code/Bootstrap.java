package com.chiwei.craft.code;

import com.chiwei.craft.code.adapter.clazz.AndroidCharge;
import com.chiwei.craft.code.adapter.clazz.Huawei2AndroidChargeAdapter;
import com.chiwei.craft.code.adapter.clazz.Mate10ProHuaweiCharge;
import com.chiwei.craft.code.adapter.object.Huawei2AndroidChargeObjectAdapter;
import com.chiwei.craft.code.command.CreateFileCommand;
import com.chiwei.craft.code.command.LenovoComputerInvoker;
import com.chiwei.craft.code.command.LinuxReceiver;
import com.chiwei.craft.code.command.ProcessorLookCommand;
import com.chiwei.craft.code.command.WindowsReceiver;
import com.chiwei.craft.code.decorator.CainiaoExpressDecorator;
import com.chiwei.craft.code.decorator.Express;
import com.chiwei.craft.code.decorator.JDExpressDecorator;
import com.chiwei.craft.code.decorator.SFExpress;
import com.chiwei.craft.code.facade.CarDoor;
import com.chiwei.craft.code.facade.CarKey;
import com.chiwei.craft.code.facade.DriveCarFacade;
import com.chiwei.craft.code.facade.Engine;
import com.chiwei.craft.code.facade.SafetyBelt;
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

	/**
	 * 命令模式 这里就是命令模式的client客户端 创建具体的命令对象，并设置命令对象的接收者
	 */
	public static void testCmd() {
		// 创建invoker，命令执行对象
		LenovoComputerInvoker lenovo = new LenovoComputerInvoker();
		// 创建命令接收者
		WindowsReceiver windows = new WindowsReceiver();
		LinuxReceiver linux = new LinuxReceiver();
		// 创建命令
		CreateFileCommand createFile = new CreateFileCommand(windows);
		ProcessorLookCommand ps = new ProcessorLookCommand(linux);
		lenovo.setCmd(createFile);
		lenovo.exeCmd();
		lenovo.setCmd(ps);
		lenovo.exeCmd();
	}

	/**
	 * 类适配器模式，继承实现
	 */
	public static void testClassAdapter() {
		AndroidCharge android = new Huawei2AndroidChargeAdapter();
		android.androidCharge();
	}

	/**
	 * 对象适配器模式，组合实现
	 */
	public static void testObjectAdapter() {
		AndroidCharge android = new Huawei2AndroidChargeObjectAdapter(new Mate10ProHuaweiCharge());
		android.androidCharge();
	}

	/**
	 * 外观模式，门面模式
	 */
	public static void testFacade() {
		DriveCarFacade car = new DriveCarFacade(new CarKey(), new CarDoor(), new SafetyBelt(), new Engine());
		car.driveCar();
	}

	public static void main(String[] args) {
		// testObserver();
		// testObserverInJava();
		// testDecorator();
		// simpleFactoryMethod();
		// factoryMethod();
		// abstractFactory();
		// testCmd();
		// testClassAdapter();
		// testObjectAdapter();
		testFacade();
	}
}
