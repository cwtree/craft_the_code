package com.chiwei.craft.code.ddd;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年4月30日
 */
public class DddTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account a = new Account(222L, "phoenix");
		
		System.out.println(a.biz());
	}

}
