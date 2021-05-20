package com.chiwei.craft.code.leetcode.algo;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * 示例：
 * 
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
 * 
 * @author chiwei
 *
 */
public class LinkNumSum {

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode root = new ListNode(0);
		ListNode cursor = root;
		int carry = 0;// 用来处理两个位置数字和大于10的情况
		while (l1 != null || l2 != null || carry != 0) {
			int l1Val = l1 != null ? l1.val : 0;
			int l2Val = l2 != null ? l2.val : 0;
			// 按照每一个对应位置的数字相加
			int sumVal = l1Val + l2Val + carry;
			carry = sumVal / 10;

			ListNode sumNode = new ListNode(sumVal % 10);
			cursor.next = sumNode;
			cursor = sumNode;

			if (l1 != null)
				l1 = l1.next;
			if (l2 != null)
				l2 = l2.next;
		}
		// root是0，去掉
		return root.next;
	}

	public static ListNode addTwoList(ListNode l1, ListNode l2) {
		ListNode root = new ListNode(0);
		ListNode cur = root;
		int temp = 0;
		while (l1 != null || l2 != null || temp != 0) {
			int value1 = l1 != null ? l1.val : 0;
			int value2 = l2 != null ? l2.val : 0;
			int sum = value1 + value2 + temp;// 超过10的只取个位数保留，高位往后进
			temp = sum / 10;// 高位进位，加上去
			ListNode sumNode = new ListNode(sum % 10);// 余数留下
			cur.next = sumNode;
			cur = sumNode;
			if (l1 != null) {
				l1 = l1.next;
			}
			if (l2 != null) {
				l2 = l2.next;
			}
		}
		return root.next;// 返回链表
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode ln1 = new ListNode(2);
		ListNode ln2 = new ListNode(4);
		ListNode ln3 = new ListNode(3);
		ListNode ln31 = new ListNode(9);
		ListNode ln32 = new ListNode(8);
		ListNode ln33 = new ListNode(7);
		// 391
		ln1.next = ln2;
		ln2.next = ln3;
		ln3.next = null;// ln31;
		// ln31.next = ln32;
		// ln32.next = ln33;
		ListNode ln4 = new ListNode(5);
		ListNode ln5 = new ListNode(6);
		ListNode ln6 = new ListNode(4);
		// 516
		// 391+516=907
		// 7->0->9
		ln4.next = ln5;
		ln5.next = ln6;
		ln6.next = null;

		ListNode node = addTwoNumbers(ln1, ln4);
		// addTwoList(ln1,ln4);
		// addTwoNumbers(ln1, ln4);

		while (node != null) {
			System.out.print(node.val);
			node = node.next;
		}

	}

}
