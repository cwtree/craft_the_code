package com.chiwei.craft.code.factory.method;

import com.chiwei.craft.code.factory.Car;

public class HuachenBMWCar extends Car {

	public HuachenBMWCar() {
		name = "HuaChen BMW";
		color = "white";
	}

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		System.out.println(color + " " + name + " 开动");
	}

	@Override
	public void brake() {
		// TODO Auto-generated method stub
		System.out.println(color + " " + name + " 制动");
	}

}
