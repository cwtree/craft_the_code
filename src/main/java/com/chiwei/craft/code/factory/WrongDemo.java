package com.chiwei.craft.code.factory;

public class WrongDemo {

	/**
	 * 当车子增加减少，都需要来这里修改代码，维护成本极大 对修改没有关闭
	 * 
	 * @param type
	 */
	public static void createCar(String type) {
		Car c = null;
		if (type.equals("BMW")) {
			c = new BMWCar();
		} else if (type.equals("Nissan")) {
			c = new NissanCar();
		}
		/**
		 * 以下代码属于车子的共性，不会变化
		 */
		c.drive();
		c.brake();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createCar("type");
	}

}
