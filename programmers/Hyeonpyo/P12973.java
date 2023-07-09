package programmers.Hyeonpyo;

import java.util.Stack;

public class P12973 {
	/**
	 * 모든 데이터가 제거 가능하면 1, 불가능하면 0
	 * 모든 데이터에서 알파벳 2개 붙어있는걸 제거해야함
	 */
	public static void main(String[] args) {
		String s1 = "baabaa";//result 1
		String s2 = "cdcd";//result 0
		String s3 = "abcdda";//result 0

		System.out.println(solution(s2));

	}

	public static int solution(String s) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			if (stack.isEmpty()) {
				stack.push(s.charAt(i));
			} else {
				if (stack.peek() == s.charAt(i)) {
					stack.pop();
				} else {
					stack.push(s.charAt(i));
				}
			}
		}

		return stack.isEmpty() ? 1 : 0;
	}
}
