package com.chiwei.craft.code.test;

import java.util.Arrays;
import java.util.List;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Snowflake sf = IdUtil.createSnowflake(31L, 31L);
		for (int i = 0; i < 10; i++) {
			System.out.println(sf.nextId());
		}
	}

}
