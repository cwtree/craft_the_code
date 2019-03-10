package com.chiwei.craft.code.observer.injava;

import java.util.Observable;
import java.util.Observer;

public class DriverObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("主题 " + o + " 给我更新数据了，数据是 " + arg);
	}

}
