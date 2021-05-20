package com.chiwei.craft.code.decorator;

public abstract class ExpressDecrator implements Express {

	private Express express;

	public ExpressDecrator(Express express) {
		super();
		this.express = express;
	}

	@Override
	public void receive() {
		// TODO Auto-generated method stub
		express.receive();
	}

	@Override
	public void deliver() {
		// TODO Auto-generated method stub
		express.deliver();
	}

}
