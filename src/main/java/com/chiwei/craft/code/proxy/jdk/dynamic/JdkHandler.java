package com.chiwei.craft.code.proxy.jdk.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.chiwei.craft.code.proxy.MyService;

public class JdkHandler implements InvocationHandler {

	private MyService ms;

	public JdkHandler(MyService ms) {
		this.ms = ms;
	}

	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), ms.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		// 等同切面before方法
		System.out.println("真正方法调用之前");
		Object o = method.invoke(ms, args);
		System.out.println("打印真正的方法调用结果—— " + o);
		// 等同切面after方法
		System.out.println("真正方法调用之后");
		return o;
	}

}
