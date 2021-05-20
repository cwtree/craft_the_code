package com.chiwei.craft.code.leetcode.algo;

import java.util.ArrayList;
import java.util.List;

public class StrUnique {

	/**
	 * 判断字符串里的字符是否都是唯一的
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param str
	 * @return
	 */
	public static boolean isUnique(String str) {
		List<Character> list = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			if (list.contains(str.charAt(i))) {
				return false;
			}
			list.add(str.charAt(i));
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isUnique("chiwei"));
	}

}
