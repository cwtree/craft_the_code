package com.chiwei.craft.code.leetcode.algo;

/**
 * 
 * 121 回文数
 * 
 * @author chiwei
 *
 */
public class Huiwen {

	public static boolean isPalindrome(int x) {
		// /10 %10
		StringBuilder s = new StringBuilder(x + "");
		return s.reverse().toString().equals(x+"");
	}
	
	public static boolean isPalindrome2(int x) {
		// /10 %10
		int temp = x;
		int sum = 0;
		while(temp>0) {
			sum=sum*10+temp%10;
			temp=temp/10;
		}
		return sum==x;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPalindrome2(-121));
	}

}
