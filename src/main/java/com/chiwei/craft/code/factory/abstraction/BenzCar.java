package com.chiwei.craft.code.factory.abstraction;

import com.chiwei.craft.code.factory.Car;

public class BenzCar extends Car {

	AccessoryFactory af;

	public BenzCar(AccessoryFactory af) {
		super();
		this.af = af;
	}

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		af.engine();
		af.wheel();
		System.out.println("Benz开动前准备发动机、车轮");
	}

	@Override
	public void brake() {
		// TODO Auto-generated method stub
		af.engine();
		af.wheel();
		System.out.println("Benz制动前准备发动机、车轮");
	}

}
