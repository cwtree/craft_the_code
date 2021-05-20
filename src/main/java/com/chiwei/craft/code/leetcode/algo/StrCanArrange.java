package com.chiwei.craft.code.leetcode.algo;

import java.util.Arrays;

public class StrCanArrange {

	/**
	 * 两个字符串可以互为重排
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean canArrange(String str1, String str2) {
		char[] c1 = str1.toCharArray();
		char[] c2 = str2.toCharArray();
		Arrays.sort(c1);
		Arrays.sort(c2);
		return new String(c1).equals(new String(c2));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(canArrange("abc", "bac"));
	}

}
