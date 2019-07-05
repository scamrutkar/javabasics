package com.sagar.javacase.problems;

public class LongestPallindrome {

	public static void main(String[] args) {

		String string = "9912333321456";
		longestPallindrome(string);

	}

	public static String longestPallindrome(String string) {

		String longestPallindrome = string.substring(0, 1);

		for (int i = 0; i < string.length() - 1; i++) {

			String current = intermediateString(i, i, string);
			if (current.length() > longestPallindrome.length())
				longestPallindrome = current;

			current = intermediateString(i, i + 1, string);
			if (current.length() > longestPallindrome.length())
				longestPallindrome = current;

		}
		System.out.println("Longest pallindrome is - " + longestPallindrome);
		return longestPallindrome;

	}

	public static String intermediateString(int left, int right, String string) {

		while (left >= 0 && right < string.length() && string.charAt(left) == string.charAt(right)) {
			left--;
			right++;
		}
		return string.substring(left + 1, right);

	}

}
