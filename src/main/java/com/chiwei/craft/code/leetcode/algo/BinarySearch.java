package com.chiwei.craft.code.leetcode.algo;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年4月29日
 */
public class BinarySearch {

	private static int search(int[] a,int target) {
		int low = 0;
		int high = a.length-1;
		while(low<=high) {
			int middle = (low+high)/2;
			int temp = a[middle];
			if(temp<target) {
				low = middle+1;
			}else if(temp>target) {
				high = middle-1;
			}else {
				return middle;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {1,2,3,4,5,6,7,8,9};
		System.out.println(search(a,5));
	}
	
}


















