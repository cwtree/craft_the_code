package com.chiwei.craft.code.observer;

public class ObserverZhangsan implements Observer {

	@Override
	public void update(String notify) {
		// TODO Auto-generated method stub
		System.out.println("张三怎么了？主题给我的数据是：" + notify);
	}

}
