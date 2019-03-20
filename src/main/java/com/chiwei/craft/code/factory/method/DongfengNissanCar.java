package com.chiwei.craft.code.factory.method;

import com.chiwei.craft.code.factory.Car;

public class DongfengNissanCar extends Car {

	public DongfengNissanCar() {
		name = "DongFeng Nissan";
		color = "black";
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
