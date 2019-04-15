package com.bob.algorithm.LeetCode.collection_test;

public class ListTest {

	public static class ListNode {

		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		public void println() {
			ListNode curr = this;
			StringBuffer sb = new StringBuffer();
			while (curr != null) {
				sb.append("->").append(curr.val);
				curr = curr.next;
			}
			String result = sb.toString().replaceFirst("->", "");
			System.out.println("listNode : " + result);
		}
	}

	/**
	 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
	 * <p>
	 * 示例：
	 * <p>
	 * 输入：1->2->4, 1->3->4
	 * 输出：1->1->2->3->4->4
	 *
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		if (l1 == null && l2 == null) return null;
		else if (l1 == null) return l2;
		else if (l2 == null) return l1;

		ListNode currL1 = l1;
		ListNode currL2 = l2;
		ListNode prevL1 = null;
		while (currL2 != null) {

			while (currL1 != null) {
				if (currL1.val < currL2.val) {
					prevL1 = currL1;
					currL1 = currL1.next;
					continue;
				}
				//currL2 <= currL1
				ListNode tempL2 = currL2;
				currL2 = currL2.next;
				tempL2.next = currL1;

				if (prevL1 == null) {
					l1 = prevL1 = tempL2;
				} else {
					prevL1 = prevL1.next = tempL2;
				}
				break;
			}

			if (currL1 == null) {
				prevL1.next = currL2;
				break;
			}
		}
		return l1;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		// ListNode l1Next = l1.next = new ListNode(2);
		// l1Next = l1Next.next = new ListNode(4);

		ListNode l2 = new ListNode(2);
		// ListNode l2Next = l2.next = new ListNode(3);
		// l2Next = l2Next.next = new ListNode(4);

		ListTest test = new ListTest();
		ListNode listNode = test.mergeTwoLists(l1, l2);
		listNode.println();
	}
}
