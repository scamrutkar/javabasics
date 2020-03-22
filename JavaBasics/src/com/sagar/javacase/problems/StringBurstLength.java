package com.sagar.javacase.problems;

import java.util.Stack;

class Entity {

	int frequency;
	char ch;

	public Entity(int frequency, char ch) {
		this.frequency = frequency;
		this.ch = ch;
	}

	@Override
	public String toString() {
		return "Entity [frequency=" + frequency + ", ch=" + ch + "]";
	}

}

public class StringBurstLength {

	public static void main(String[] args) {
		int k = 3;
		String st = "abbacccaad";
		String ans = bustLenghtString(st, st.length(), k);
		System.out.println(ans);

	}

	private static String bustLenghtString(String st, int length, int k) {

		Stack<Entity> stack = new Stack<Entity>();
		stack.add(new Entity(1, st.charAt(0)));
		for (int i = 1; i < length; i++) {

			char ch = st.charAt(i);

			if (stack.size() > 0 && stack.peek().frequency == k) {
				char tempChar = stack.peek().ch;
				while (stack.peek().ch == tempChar) {
					stack.pop();
				}
			}

			if (stack.peek().ch == ch) {
				stack.push(new Entity((stack.peek().frequency + 1), ch));
			} else {
				stack.push(new Entity(1, ch));
			}

		}

		if (stack.size() > 0 && stack.peek().frequency == k) {
			char tempChar = stack.peek().ch;
			while (stack.peek().ch == tempChar) {
				stack.pop();
			}
		}

		// System.out.println(stack.toString());
		StringBuffer str = new StringBuffer();
		for (Entity entity : stack) {
			str.append(entity.ch);
		}

		return str.toString();

	}

}
