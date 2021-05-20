package com.chiwei.craft.code.observer;

import java.util.ArrayList;

public class CarLicensePlateSubject implements Subject {

	private ArrayList<Observer> list;

	public CarLicensePlateSubject() {
		// TODO Auto-generated constructor stub
		list = new ArrayList<Observer>();
	}

	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		list.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		list.remove(o);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (Observer o : list) {
			o.update("摇到车牌了");
		}
	}

}
