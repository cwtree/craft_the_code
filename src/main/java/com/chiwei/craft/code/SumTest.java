package com.chiwei.craft.code;

import java.net.Inet4Address;
import java.net.InetAddress;

public class SumTest {

	public static void main(String[] args) throws Exception {
		InetAddress ia = Inet4Address.getByName("https://www.baidu.com");
		System.out.println(ia);
	}
}
