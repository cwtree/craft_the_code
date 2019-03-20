package com.chiwei.craft.code.factory.simple;

import com.chiwei.craft.code.factory.Car;

public class CarFactory {

	SimpleFactory factory;
	
	public CarFactory(SimpleFactory factory) {
		super();
		this.factory = factory;
	}

	/**
	 * 在实际需要创建对象的类中，引入工厂类，通过工厂类实例化对象
	 * 对象的复杂创建工作在工厂类中完成
	 * @param type
	 * @return
	 */
	public Car testCar(String type) {
		Car c = factory.createCar(type);
		c.drive();
		c.brake();
		return c;
	}
	
}
