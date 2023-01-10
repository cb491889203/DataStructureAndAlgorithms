package com.bob.algorithm.test;

public class NumberTest {

	public int reverse(int x) {
		String str = String.valueOf(x);
		boolean isNeg = str.startsWith("-");
		if (isNeg) {
			str = str.substring(1);
		}
		char[] chars = str.toCharArray();
		int midIdx = str.length() / 2;
		for (int i = 0, j = str.length() - 1; i < midIdx && j > 0; i++, j--) {
			chars[i] ^= chars[i];
			chars[j] ^= chars[i];
			chars[i] ^= chars[j];
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

	public static void main(String[] args) {

	}
}
