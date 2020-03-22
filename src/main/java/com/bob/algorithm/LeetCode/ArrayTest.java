package com.bob.algorithm.LeetCode;

import java.util.HashSet;
import java.util.Set;

public class ArrayTest {

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
	 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
	 * <p>
	 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
	 * <p>
	 * 示例 1:
	 * <p>
	 * 给定数组 nums = [1,1,2],
	 * <p>
	 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
	 * <p>
	 * 你不需要考虑数组中超出新长度后面的元素。
	 * 示例 2:
	 * <p>
	 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
	 * <p>
	 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
	 * <p>
	 * 你不需要考虑数组中超出新长度后面的元素。
	 * 说明:
	 * <p>
	 * 为什么返回数值是整数，但输出的答案是数组呢?
	 * <p>
	 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
	 * <p>
	 * 你可以想象内部操作如下:
	 * <p>
	 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
	 * int len = removeDuplicates(nums);
	 * <p>
	 * // 在函数里修改输入数组对于调用者是可见的。
	 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
	 * for (int i = 0; i < len; i++) {
	 * print(nums[i]);
	 * }
	 */
	public int removeDuplicates(int[] nums) {
		if (nums == null || nums.length == 0) return 0;

		int anchor = 0;
		for (int i = 1; i < nums.length; i++) {

			if (nums[anchor] != nums[i]) {
				nums[++anchor] = nums[i];
			}
		}
		return anchor + 1;
	}

	/**
	 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
	 * <p>
	 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
	 * <p>
	 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
	 * <p>
	 * 示例 1:
	 * <p>
	 * 给定 nums = [3,2,2,3], val = 3,
	 * <p>
	 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
	 * <p>
	 * 你不需要考虑数组中超出新长度后面的元素。
	 * 示例 2:
	 * <p>
	 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
	 * <p>
	 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
	 * <p>
	 * 注意这五个元素可为任意顺序。
	 * <p>
	 * 你不需要考虑数组中超出新长度后面的元素。
	 * 说明:
	 * <p>
	 * 为什么返回数值是整数，但输出的答案是数组呢?
	 * <p>
	 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
	 * <p>
	 * 你可以想象内部操作如下:
	 * <p>
	 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
	 * int len = removeElement(nums, val);
	 * <p>
	 * // 在函数里修改输入数组对于调用者是可见的。
	 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
	 * for (int i = 0; i < len; i++) {
	 * print(nums[i]);
	 * }
	 */
	public int removeElement(int[] nums, int val) {
		if (nums == null || nums.length == 0) return 0;

		int anchor = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				nums[anchor++] = nums[i];
			}
		}
		return anchor;
	}

	/**
	 * 在由若干 0 和 1  组成的数组 A 中，有多少个和为 S 的非空子数组。
	 * <p>
	 * <p>
	 * <p>
	 * 示例：
	 * <p>
	 * 输入：A = [1,0,1,0,1], S = 2
	 * 输出：4
	 * 解释：
	 * 如下面黑体所示，有 4 个满足题目要求的子数组：
	 * [1,0,1,0,1]
	 * [1,0,1,0,1]
	 * [1,0,1,0,1]
	 * [1,0,1,0,1]
	 * <p>
	 * <p>
	 * 提示：
	 * <p>
	 * A.length <= 30000
	 * 0 <= S <= A.length
	 * A[i] 为 0 或 1
	 */
	public int numSubarraysWithSum(int[] A, int S) {
		if (A == null) return 0;

		int tail = 0;
		int head = 0;
		int count = 0;
		int sum = 0;

		//子数组的head向后移动
		while (head < A.length) {

			if (A[head] == 0) {//当前头部是0的情况
				if (sum == S) {//如果当前和已经等于S则直接加一
					count++;
				}
			} else {//头部是1
				sum += 1;
				if (sum > S) {//如果此时sum已超过了S，

					//这是就需要将tail向前移动，直到使得sum-1<S的那个1的位置。且过程中遇到的0都使count加1。
					while (tail < A.length) {
						sum -= A[tail];
						if (sum == S) {
							count++;
							tail++;
						} else if (sum < S) {
							tail++;
							break;
						}
					}

				} else if (sum == S) {
					//正好到了S， 计数加1
					count++;
				}
			}

			//头部前进1位
			head++;
		}

		while (tail < A.length) {
			sum -= A[tail];
			if (sum == S) {
				count++;
				tail++;
			} else if (sum < S) {
				break;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		ArrayTest test = new ArrayTest();
		// int i = test.removeDuplicates(new int[]{1, 1/*, 2, 2, 2, 3, 3, 3, 3*/});
		// System.out.println("removeDuplicates = " + i);

		/*int[] nums = {1, 2, 2, 3, 2, 1};
		int len = test.removeElement(nums, 2);
		System.out.println("removeElement = " + len);
		for (int i = 0; i < len; i++) {
			System.out.println(nums[i]);
		}*/

		int[] A = new int[]{0,0};
		int i = test.numSubarraysWithSum(A, 0);
		System.out.println("numSubarraysWithSum = " + i);
	}
}
