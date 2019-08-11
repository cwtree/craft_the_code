package com.chiwei.craft.code.leetcode.algo;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 
 * 示例:
 * 
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 
 * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 * 
 * @author chiwei
 *
 */
public class TwoNumSumFromArray {

	private static int[] fun(int a[], int target) {
		int[] result = new int[2];
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] + a[j] == target) {
					result[0] = i;
					result[1] = j;
				}
			}
		}
		return result;
	}

	/**
	 * 通过map记录每个target-a[i]的下标
	 * 当map中有a[i]了，直接返回i和差的下标
	 * @param a
	 * @param target
	 * @return
	 */
	private static int[] fun2(int a[], int target) {
		int[] result = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			if (map.containsKey(a[i])) {
				result[0] = i;
				result[1] = map.get(a[i]);
			} else {
				map.put(target - a[i], i);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = { 6, 4, 1, 8, 9, 5, 7 };
		int target = 12;
		// 从数组中找出两个数和等于target的，输出两个数的下标
		int[] result = fun2(a, target);
		System.out.println(result[0] + "--" + result[1]);
	}

}
