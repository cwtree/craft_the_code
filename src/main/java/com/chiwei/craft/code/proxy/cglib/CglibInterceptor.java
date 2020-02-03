package com.chiwei.craft.code.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		// TODO Auto-generated method stub
		// 等同切面before方法
		System.out.println("真正方法调用之前");
		Object obj = methodProxy.invokeSuper(o, objects);
		System.out.println("打印真正的方法调用结果—— " + obj);
		// 等同切面after方法
		System.out.println("真正方法调用之后");
		return obj;
	}

}
