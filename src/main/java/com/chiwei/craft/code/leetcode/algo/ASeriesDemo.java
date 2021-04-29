package com.chiwei.craft.code.leetcode.algo;

import java.util.Scanner;

/**
 * 
 * 
 * @author phoenix
 * @date 2020年6月4日
 */
public class ASeriesDemo {

	/**
	 * a串数列求和
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param a
	 * @param n
	 * @return
	 */
	public static int seriesSum(int a, int n) {
		int temp = 0, sum = 0;
		for (int i = 0; i < n; i++) {
			temp = temp * 10 + a;
			sum += temp;
		}
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int a = in.nextInt();
			int b = in.nextInt();
			System.out.println("s = " + seriesSum(a, b));
		}
	}

}
