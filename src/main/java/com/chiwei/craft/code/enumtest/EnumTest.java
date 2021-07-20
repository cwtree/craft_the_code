package com.chiwei.craft.code.enumtest;

import java.lang.reflect.Method;

import cn.hutool.core.util.StrUtil;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年5月26日
 */
public class EnumTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Object[] objs = OrderTypeEnum.class.getEnumConstants();

		Method method = OrderTypeEnum.class.getMethod("get"+StrUtil.upperFirst("type"));
		for(Object obj:objs) {
			System.out.println("--"+method.invoke(obj));
		}
		
	}

}
