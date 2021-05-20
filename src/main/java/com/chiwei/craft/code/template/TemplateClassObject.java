package com.chiwei.craft.code.template;

public class TemplateClassObject extends TemplateClass {

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		System.out.println("子类具体实现方法2");
	}

	boolean hook() {
		System.out.println("子类钩子方法执行");
		return true;
	}

}
