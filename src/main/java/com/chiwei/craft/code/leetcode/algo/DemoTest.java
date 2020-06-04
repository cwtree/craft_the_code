package com.chiwei.craft.code.leetcode.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class DemoTest {

	/**
	 * 找出独立出现一次的数字
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param nums
	 * @return
	 */
	public static int singleNumber(int[] nums) {
		int a = 0;
		for (int i : nums) {
			a ^= i;
		}
		return a;
	}

	/**
	 * 找出出现大于 N/2向下取整次数的数字
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param nums
	 * @return
	 */
	public static int majorityElement(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		int res = -1;
		if (nums.length == 1) {
			return nums[0];
		}
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i]) + 1);
				if (map.get(nums[i]) > Math.floor(nums.length / 2.0)) {
					res = nums[i];
				}
			} else {
				map.put(nums[i], 1);
			}
		}
		return res;
	}

	/**
	 * NXN矩阵旋转90度
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param matrix
	 */
	public static void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		int mid = n >> 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < mid; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][n - j - 1];
				matrix[i][n - j - 1] = temp;
				;
			}
		}
	}

	/**
	 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param matrix
	 */
	public static void setZeroes(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] tmp = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tmp[i][j] = matrix[i][j];
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (tmp[i][j] == 0) {
					for (int m = 0; m < row; m++) {
						matrix[m][j] = 0;
					}
					for (int n = 0; n < col; n++) {
						matrix[i][n] = 0;
					}
				}
			}
		}
	}

	/**
	 * 字符串旋转 输入：s1 = "waterbottle", s2 = "erbottlewat" 输出：True
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean isFlipedString(String s1, String s2) {
		return s1.equals(s2) || (s1.length() == s2.length() && (s1 + s1).contains(s2));
	}

	/**
	 * 链表去重
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param head
	 * @return
	 */
	public static ListNode removeDuplicateNodes(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode pre = head;
		ListNode later = null;// later指针跟这pre往后移动，遇到重复往后移
		List<Integer> list = new ArrayList<>();
		while (pre != null) {
			if (!list.contains(pre.val)) {
				//不包含这个数字
				list.add(pre.val);
				//later指针记录当前节点
				later = pre;
				//指针后移
				pre = pre.next;
			} else {
				//数字重复，指针直接后移
				pre = pre.next;
				//later指向后移后的指针
				later.next = pre;
			}
		}
		return head;
	}
	
	/**
	 * 返回链表倒数第K个节点
	 * 双向指针  p q 距离K且p.next为空，q即为结果
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param head
	 * @param k
	 * @return
	 */
	public static int kthToLast(ListNode head, int k) {
		if(head==null)
			return -1;
		ListNode p = head;
		for(int i=0;i<k;i++) {
			p = p.next;
		}
		while(p!=null) {
			head = head.next;
			p = p.next;
		}
		return head.val;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 00001000 00000100
		 * 
		 * 00001100 00000111
		 * 
		 * 00001011 00000011
		 * 
		 * 00001000 00000011
		 * 
		 * 00001011 00000100
		 * 
		 * 00001111 00000111
		 * 
		 * 00001000
		 */
		// System.out.println(singleNumber(new int[] { 8, 4, 7, 3, 3, 4, 7 }));
		// System.out.println(4 ^ 4);
		// System.out.println(majorityElement(new int[] { 3, 3, 4 }));
		/*
		 * int[][] a = new int[][] {{1,2,3},{0,5,2},{9,3,6}}; for(int
		 * i=0;i<a.length;i++) { System.out.println(Arrays.toString(a[i])); }
		 * System.out.println("-------------"); setZeroes(a); for(int
		 * i=0;i<a.length;i++) { System.out.println(Arrays.toString(a[i])); }
		 */
		/*
		 * String s1 = "waterbottle"; String s2 = "erbottlewat";
		 * System.out.println(isFlipedString(s1, s2));
		 */
		/*ListNode ln1 = new ListNode(1);
		ListNode ln2 = new ListNode(1);
		ListNode ln3 = new ListNode(2);
		ListNode ln31 = new ListNode(1);
		ListNode ln32 = new ListNode(3);
		ListNode ln33 = new ListNode(0);
		// 391
		ln1.next = ln2;
		ln2.next = ln3;
		ln3.next = ln31;
		ln31.next = ln32;
		ln32.next = ln33;
		ListNode node = removeDuplicateNodes(ln1);
		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}*/
		
		ListNode ln1 = new ListNode(1);
		ListNode ln2 = new ListNode(1);
		ListNode ln3 = new ListNode(2);
		ListNode ln31 = new ListNode(1);
		ListNode ln32 = new ListNode(3);
		ListNode ln33 = new ListNode(0);
		// 391
		ln1.next = ln2;
		ln2.next = ln3;
		ln3.next = ln31;
		ln31.next = ln32;
		ln32.next = ln33;
		int a = kthToLast(ln1,6);
		System.out.println(a);
		
		
		
	}

}
