package com.bob.algorithm.queue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StackCalculaterDemo {

	public static void main(String[] args) {
		StackCalculater calculater = new StackCalculater();
		String expression = "32%3*(6.5-3)";
		BigDecimal result = calculater.calc(expression);
		System.out.println(String.format("%s = %s", expression, result.toString()));

	}
}

class StackCalculater {

	private Stack<BigDecimal> numStack;
	private Stack<Character> optStack;
	private static Map<Character, Integer> optMap = new HashMap<Character, Integer>() {{
		put('+', 1);
		put('-', 1);
		put('*', 2);
		put('/', 2);
		put('%', 2);
		put('(', 3);
		put(')', 3);
	}};

	public StackCalculater() {
		numStack = new Stack<>();
		optStack = new Stack<>();
	}

	public void clear() {
		numStack.clear();
		optStack.clear();
	}

	public BigDecimal calc(String expression) {
		//解析表达式
		decodeExpression(expression);
		//开始计算剩余 的
		calcInternal();
		//得到最终结果
		return numStack.pop();
	}

	/**
	 * 解析表达式.如: 3+2*6-2
	 *
	 * @param expression
	 */
	private void decodeExpression(String expression) {
		if (expression == null || expression.equals("")) return;
		int idx = 0;
		int length = expression.length();
		while (idx < length) {
			char ch = expression.charAt(idx);
			if (optMap.containsKey(ch)) {
				//是操作符
				if (isLeftBracket(ch)) {
					//判断是前括号,直接放
					optStack.push(ch);
				} else if (isRightBracket(ch)) {
					//是后括号,需要将整个括号里面的表达式运算出结果
					calcUntilBracket();
				} else if (!optStack.isEmpty() && isPrior(optStack.peek(), ch) && !isLeftBracket(optStack.peek())) {
					// 之前的操作符 比 当前的操作符优先级 大或等, 则进行运算
					calcOnce();
					optStack.push(ch);
				} else {
					//是普通操作符,直接放
					optStack.push(ch);
				}
			} else {
				// 数字, 直接入栈.且要判断是否是多位数或小数.
				int tempIdx = idx + 1;
				if (tempIdx == length) {
					numStack.push(new BigDecimal(String.valueOf(ch)));
				} else {
					while (tempIdx < length) {
						char tempCh = expression.charAt(tempIdx);
						if (optMap.containsKey(tempCh)) {
							//如果当前这个已经是操作符了, 说明位数到头,解析出多位数或小数
							String num = expression.substring(idx, tempIdx);
							numStack.push(new BigDecimal(num));
							idx = tempIdx - 1;
							break;
						}
						tempIdx++;
					}
				}
			}
			idx++;
		}
	}

	private void calcInternal() {
		while (!optStack.isEmpty()) {
			calcOnce();
		}
	}

	private void calcUntilBracket() {
		Character ch = optStack.peek();
		while (!isLeftBracket(ch)) {
			calcOnce();
			ch = optStack.peek();
		}
		//弹出左括号
		optStack.pop();
	}

	/**
	 * 判断操作符 ch1 的优先级是否大于 ch2 .
	 *
	 * @param ch1
	 * @param ch2
	 * @return
	 */
	private boolean isPrior(char ch1, char ch2) {
		return optMap.get(ch1) >= optMap.get(ch2);
	}

	private boolean isLeftBracket(char ch) {
		return ch == '(';
	}

	private boolean isRightBracket(char ch) {
		return ch == ')';
	}

	private void calcOnce() {
		char ch = optStack.pop();
		BigDecimal num2 = numStack.pop();
		BigDecimal num1 = numStack.pop();
		BigDecimal result = calc(num1, num2, ch);
		numStack.push(result);
	}

	private BigDecimal calc(BigDecimal num1, BigDecimal num2, char opt) {
		switch (opt) {
			case '+':
				return num1.add(num2);
			case '-':
				return num1.subtract(num2);
			case '*':
				return num1.multiply(num2);
			case '/':
				return num1.divide(num2, BigDecimal.ROUND_UNNECESSARY);
			case '%':
				return num1.divideAndRemainder(num2)[1];
		}
		throw new RuntimeException();
	}
}
