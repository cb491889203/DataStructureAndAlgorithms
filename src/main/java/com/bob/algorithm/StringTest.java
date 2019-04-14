package com.bob.algorithm;

import com.bob.algorithm.BinarySearchTree.BinaryNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class StringTest {

	public int lengthOfLongestSubstring(String s) {
		int len = 0;

		List list = new LinkedList();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (list.contains(c)) {
				removeBefore(list, c);
			}
			list.add(c);
			if (list.size() > len) {
				len = list.size();
			}

		}
		return len;
	}

	private void removeBefore(List list, char c) {
		int i = list.indexOf(c);
		for (int j = 0; j <= i; j++) {
			list.remove(0);
		}
	}

	public int reverse(int x) {
		String str = String.valueOf(x);
		boolean isNeg = str.startsWith("-");
		if (isNeg) {
			str = str.substring(1);
		}
		char[] chars = str.toCharArray();
		int midIdx = str.length() / 2;
		for (int i = 0, j = str.length() - 1; i < midIdx && j > 0; i++, j--) {
			char temp = chars[i];
			chars[i] = chars[j];
			chars[j] = temp;
		}

		String resultStr = String.valueOf(chars);
		long l = Long.valueOf(resultStr);
		long l1 = l >>> 31;
		int result;
		if (l1 > 0) {
			result = 0;
		} else {
			if (isNeg) {
				result = (int) -l;
			} else {
				result = (int) l;
			}
		}

		return result;
	}

	public boolean isPalindrome1(int x) {
		if (x < 0) {
			return false;
		}
		int count = 1;
		int temp = x / 10;
		while (temp > 0) {
			count++;
			temp /= 10;
		}

		for (int i = 1, j = count; i < j; i++, j--) {
			int left = (int) (x % Math.pow(10, j) / Math.pow(10, j - 1));
			int right = (int) (x % Math.pow(10, i) / Math.pow(10, i - 1));
			if (left != right) {
				return false;
			}
		}
		return true;
	}

	public boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		} else if (x < 10) {
			return true;
		}

		int num = x;
		int r = 0;
		while (num > 0) {
			r = r * 10 + num % 10;
			num /= 10;
		}

		return r == x;
	}

	/**
	 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
	 * <p>
	 * 字符          数值
	 * I             1
	 * V             5
	 * X             10
	 * L             50
	 * C             100
	 * D             500
	 * M             1000
	 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
	 * <p>
	 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
	 * <p>
	 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
	 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
	 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
	 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
	 */
	public int romanToInt(String s) {
		if (s == null || s.length() == 0) return 0;

		int result = 0;
		char[] chars = s.toCharArray();
		int len = chars.length;
		for (int i = 0; i < len; i++) {
			char curr = chars[i];
			Integer currVal = map.get(curr);

			int nextIdx = i + 1;
			if (nextIdx < len) {
				char next = chars[nextIdx];
				boolean special = special(curr, next);
				if (special) {
					result += (map.get(next) - currVal);

					i++;
					continue;
				}
			}
			result += currVal;
		}

		return result;
	}

	private boolean special(char curr, char next) {
		if (curr == 'I' && (next == 'V' || next == 'X')) {
			return true;
		} else if (curr == 'X' && (next == 'L' || next == 'C')) {
			return true;
		} else if (curr == 'C' && (next == 'D' || next == 'M')) {
			return true;
		}
		return false;
	}

	Map<Character, Integer> map = new HashMap<Character, Integer>() {
		{
			put('I', 1);
			put('V', 5);
			put('X', 10);
			put('L', 50);
			put('C', 100);
			put('D', 500);
			put('M', 1000);
		}
	};

	/**
	 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
	 * <p>
	 * 说明：
	 * <p>
	 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
	 * <p>
	 * 示例 1:
	 * <p>
	 * 输入: [2,2,1]
	 * 输出: 1
	 * 示例 2:
	 * <p>
	 * 输入: [4,1,2,1,2]
	 * 输出: 4
	 */
	public int singleNumber(int[] nums) {

		int temp;
		int index = 0;
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			temp = nums[i];
			if (!set.add(temp)) {
				set.remove(temp);
			}
		}
		if (set.size() != 1) {
			return -1;
		}
		return set.toArray(new Integer[]{})[0];
	}

	/**
	 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
	 * <p>
	 * 说明: 叶子节点是指没有子节点的节点。
	 * <p>
	 * 示例:
	 * 给定如下二叉树，以及目标和 sum = 22，
	 * <p>
	 * 5
	 * / \
	 * 4   8
	 * /   / \
	 * 11  13  4
	 * /  \      \
	 * 7    2      1
	 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
	 */
	public boolean hasPathSum(BinaryNode<Integer> root, int sum) {

		if (root == null) return false;
		return childPathSum(root, 0, sum);
	}

	private boolean childPathSum(BinaryNode<Integer> root, int currSum, int sum) {
		if (root == null) {
			return false;
		}
		currSum += root.val;

		if (root.left == null && root.right == null) {
			return currSum == sum;
		}

		boolean left = childPathSum(root.left, currSum, sum);
		boolean right = childPathSum(root.right, currSum, sum);
		return left || right;
	}

	/**
	 * 编写一个函数来查找字符串数组中的最长公共前缀。
	 * <p>
	 * 如果不存在公共前缀，返回空字符串 ""。
	 * <p>
	 * 示例 1:
	 * <p>
	 * 输入: ["flower","flow","flight"]
	 * 输出: "fl"
	 * 示例 2:
	 * <p>
	 * 输入: ["dog","racecar","car"]
	 * 输出: ""
	 * 解释: 输入不存在公共前缀。
	 * 说明:
	 * <p>
	 * 所有输入只包含小写字母 a-z 。
	 */
	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) return "";

		String str = strs[0];
		char[] chars = str.toCharArray();
		int count = chars.length;
		for (int i = 1; i < strs.length; i++) {

			char[] currChars = strs[i].toCharArray();
			int len = currChars.length;
			count = count > len ? len : count;

			for (int j = 0; j < count; j++) {

				if (chars[j] != currChars[j]) {
					count = j;
					break;
				}

			}

			if (count == 0) return "";
		}

		return str.substring(0, count);
	}

	/**
	 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
	 * <p>
	 * 有效字符串需满足：
	 * <p>
	 * 左括号必须用相同类型的右括号闭合。
	 * 左括号必须以正确的顺序闭合。
	 * 注意空字符串可被认为是有效字符串。
	 * <p>
	 * 示例 1:
	 * <p>
	 * 输入: "()"
	 * 输出: true
	 * 示例 2:
	 * <p>
	 * 输入: "()[]{}"
	 * 输出: true
	 * 示例 3:
	 * <p>
	 * 输入: "(]"
	 * 输出: false
	 * 示例 4:
	 * <p>
	 * 输入: "([)]"
	 * 输出: false
	 * 示例 5:
	 * <p>
	 * 输入: "{[]}"
	 * 输出: true
	 */
	public boolean isValid(String s) {
		if (s == null || s.length() == 0) return true;

		Stack<Character> stack = new Stack<>();
		char curr;
		char prev;
		for (int i = 0; i < s.length(); i++) {
			curr = s.charAt(i);
			if (stack.isEmpty()) {
				stack.push(curr);
				continue;
			}

			prev = stack.peek();
			boolean isPair = (prev == '(' && curr == ')') || (prev == '[' && curr == ']') || (prev == '{' && curr == '}');
			if (isPair) {
				stack.pop();
			} else {
				stack.push(curr);
			}
		}

		return stack.empty();
	}

	public static void main(String[] args) {
		StringTest test = new StringTest();
		/*int i = test.lengthOfLongestSubstring(
				"jhdidvopjhghkgdtreszruyghuuoioiooihujnkjjkyyftddtrdhfjfyfyfytfytfytfyfytfytfytfyfyfytfyfrd" +
				"resrerrzrrzdzzzdszdzrwarestschvjjyuygiugfalsfncicaofwiefnznn,nv,vn,czmvnzbnbvkbvbwfhu" +
				"waihgbvbzkkvbkhowyytytoweifpqpqfahgbvahif" + "qwertyuiopasdfghjklxcvbnmplmoknijbuhvygctfxrdzeswqa" +
				"qazwsxedcrfvtgbyhnujmikolp");
		System.out.println("i = " + i);

		int val = -1111111119;
		System.out.println("input = " + val);
		int reverse = test.reverse(val);
		System.out.println("reverse = " + reverse);*/

		// boolean palindrome = test.isPalindrome(134567891);
		// System.out.println("palindrome = " + palindrome);

		// int i = test.romanToInt("MMDCLXXIV");
		// System.out.println("i = " + i);

		// int num = test.singleNumber(new int[]{1, 2, 3, 4, 2, 3, 1});
		// System.out.println("singleNumber = " + num);

		//add some num;
		// BinarySearchTree tree = new BinarySearchTree();
		//
		// tree.add(5);
		// tree.add(4);
		// tree.add(1);
		// tree.add(2);
		// tree.add(3);
		//
		// tree.add(7);
		//
		// boolean sum = test.hasPathSum(tree.getRootNode(), 12);
		// System.out.println("hasPathSum = " + sum);

		// String commonPrefix = test.longestCommonPrefix(new String[]{"flower", "flow", "flight", "oooo"});
		// System.out.println("commonPrefix = " + commonPrefix);

		System.out.println("isValid = " + test.isValid("{{}[()]}"));
	}
}
