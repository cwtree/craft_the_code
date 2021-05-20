package com.chiwei.craft.code.factory.simple;

import com.chiwei.craft.code.factory.BMWCar;
import com.chiwei.craft.code.factory.Car;
import com.chiwei.craft.code.factory.NissanCar;

public class SimpleFactory {

	/**
	 * 简单工厂模式，单纯的将创建对象的方法移至工厂类中 返回用户真正想要的对象
	 * 
	 * @param type
	 * @return
	 */
	public Car createCar(String type) {
		Car c = null;
		if (type.equals("BMW")) {
			c = new BMWCar();
		} else if (type.equals("Nissan")) {
			c = new NissanCar();
		}
		return c;
	}

}
