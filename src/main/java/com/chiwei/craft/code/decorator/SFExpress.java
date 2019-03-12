package com.chiwei.craft.code.decorator;

public class SFExpress implements Express {

	@Override
	public void receive() {
		// TODO Auto-generated method stub
		System.out.println("顺丰收件");
	}

	@Override
	public void deliver() {
		// TODO Auto-generated method stub
		System.out.println("顺丰送件");
	}

}
