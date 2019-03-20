package com.chiwei.craft.code.factory.abstraction;

public class ChiweiAccessoryFactory implements AccessoryFactory {

	@Override
	public void wheel() {
		// TODO Auto-generated method stub
		System.out.println("chiwei 轮胎");
	}

	@Override
	public void engine() {
		// TODO Auto-generated method stub
		System.out.println("chiwei 发动机");
	}

}
