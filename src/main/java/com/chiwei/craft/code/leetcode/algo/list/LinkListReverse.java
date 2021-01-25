package com.chiwei.craft.code.leetcode.algo.list;

import com.chiwei.craft.code.leetcode.algo.ListNode;

/**
 * 链表反转
 * 
 * @author phoenix
 * @date 2020年6月5日
 */
public class LinkListReverse {

	public static ListNode reverse(ListNode head) {
		// 链表反转
		// 前一个指针
		ListNode prev = null;
		// 当前指针
		ListNode curr = head;
		while (curr != null) {
			// 下一个指针作为临时变量需要赋值给当前指针
			ListNode tmp = curr.next;
			// 当前指针的下一个是变成前一个
			curr.next = prev;
			// 将当前指针赋值给前一个
			prev = curr;
			// 临时指针赋值给当前指针
			curr = tmp;
		}
		return prev;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode ln1 = new ListNode(1);
		ListNode ln2 = new ListNode(3);
		ListNode ln3 = new ListNode(2);
		ListNode ln31 = new ListNode(6);
		ListNode ln32 = new ListNode(3);
		ln1.next = ln2;
		ln2.next = ln3;
		ln3.next = ln31;
		ln31.next = ln32;
		ListNode node = ln1;
		while (node != null) {
			System.out.print(node.val + "-->");
			node = node.next;
		}
		System.out.println();
		node = reverse(ln1);
		while (node != null) {
			System.out.print(node.val + "-->");
			node = node.next;
		}
	}

}
