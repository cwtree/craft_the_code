package com.chiwei.craft.code.factory.abstraction;

import com.chiwei.craft.code.factory.Car;
import com.chiwei.craft.code.factory.method.CarFactory2;

public class AmericanCarFactory extends CarFactory2 {

	@Override
	public Car createCar(String type) {
		// TODO Auto-generated method stub
		Car c = null;
		AccessoryFactory af = new ChiweiAccessoryFactory();
		if (type.equals("Benz")) {
			c = new BenzCar(af);
		}
		return c;
	}

}
