package com.chiwei.craft.code.template;

public abstract class TemplateClass {

	public void templateMethod() {
		method1();
		if (hook()) {
			method2();
		}
	}

	public void method1() {
		System.out.println("公共实现方法1");
	}

	public abstract void method2();

	boolean hook() {
		return true;
	}

}
