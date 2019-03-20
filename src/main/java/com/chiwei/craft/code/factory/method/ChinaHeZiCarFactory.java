package com.chiwei.craft.code.factory.method;

import com.chiwei.craft.code.factory.Car;

public class ChinaHeZiCarFactory extends CarFactory2 {

	@Override
	public Car createCar(String type) {
		// TODO Auto-generated method stub
		Car c = null;
		if (type.equals("BMW")) {
			c = new HuachenBMWCar();
		} else if (type.equals("Nissan")) {
			c = new DongfengNissanCar();
		}
		return c;
	}

}
