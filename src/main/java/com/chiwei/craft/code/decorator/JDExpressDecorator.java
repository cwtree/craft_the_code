package com.chiwei.craft.code.decorator;

public class JDExpressDecorator extends ExpressDecrator {

	public JDExpressDecorator(Express express) {
		super(express);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receive() {
		// TODO Auto-generated method stub
		super.receive();
		System.out.println("收件信息同步到京东");
	}

	@Override
	public void deliver() {
		// TODO Auto-generated method stub
		super.deliver();
		System.out.println("送件信息同步到京东");
	}

	
	
}
