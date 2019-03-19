package com.chiwei.craft.code.factory;

public class HuachenNissanCar extends Car {

	public HuachenNissanCar() {
		name = "HuaChen Nissan";
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
