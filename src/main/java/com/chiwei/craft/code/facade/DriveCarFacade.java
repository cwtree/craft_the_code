package com.chiwei.craft.code.facade;

public class DriveCarFacade {

	private CarKey key;
	private CarDoor door;
	private SafetyBelt belt;
	private Engine engine;
	public DriveCarFacade(CarKey key, CarDoor door, SafetyBelt belt, Engine engine) {
		super();
		this.key = key;
		this.door = door;
		this.belt = belt;
		this.engine = engine;
	}
	
	public void driveCar() {
		key.unlock();
		door.openDoor();
		belt.fasten();
		engine.start();
		System.out.println("车子开动了...");
	}
	
}
