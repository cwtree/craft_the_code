package com.chiwei.craft.code.factory;

public abstract class CarFactory2 {

	public Car testCar(String type) {
		Car c = createCar(type);
		c.drive();
		c.brake();
		return c;
	}
	
	/**
	 * 将对象实例化交给子类实现
	 * @param type
	 * @return
	 * abstract Product factoryMethod(String type)
	 */
	public abstract Car createCar(String type);
	
}
