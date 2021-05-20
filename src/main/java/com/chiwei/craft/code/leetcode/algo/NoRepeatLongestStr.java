package com.chiwei.craft.code.leetcode.algo;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 
 * 示例 1:
 * 
 * 输入: "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 
 * @author chiwei
 *
 */
public class NoRepeatLongestStr {

	public static int lengthOfLongestSubstring(String s) {
		int size, i = 0, j = 0, k = 0, max = 0;
		size = s.length();
		// 第一层循环，从0开始
		for (j = 0; j < size; j++) {
			for (k = i; k < j; k++) {
				if (s.charAt(k) == s.charAt(j)) {
					i = k + 1;// 相等，跳过k，并且退出循环
					break;
				}
			}
			if (j - i + 1 > max)
				max = j - i + 1;
		}
		return max;
	}

	public static int lengthOfLongestSubstring2(String s) {
		String str = "";
		int len = 0;
		for (int i = 0; i < s.length(); i++) {
			String temp = s.charAt(i) + "";
			int index = str.indexOf(temp);
			if (index <= -1) {
				str += temp;
				len = len < str.length() ? str.length() : len;
			} else {
				// 存在重复
				str = str.substring(index + 1) + temp;
				len = str.length();
			}
		}
		System.out.println("---" + str);
		return len;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(lengthOfLongestSubstring2("pwwkew"));
	}

}
