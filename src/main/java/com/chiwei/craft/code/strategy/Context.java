package com.chiwei.craft.code.strategy;

public class Context {

	private TravelStrategy ts;

	public Context(TravelStrategy ts) {
		this.ts = ts;
	}

	public void executeTravel() {
		System.out.println(ts.travel() + " 去旅行");
	}

}
