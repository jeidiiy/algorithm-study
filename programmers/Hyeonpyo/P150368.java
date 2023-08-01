package programmers.Hyeonpyo;

import java.util.Stack;

public class P150368 {

	private final int[] DISCOUNT_VALUES = new int[]{10, 20, 30, 40};
	private final Stack<Integer> discountStack = new Stack<>();
	int[][] users;
	int[] emoticons;
	private int maxSub = 0, maxSales = 0;

	public int[] solution(int[][] users, int[] emoticons) {
		this.users = users;
		this.emoticons = emoticons;
		search();
		return new int[] {maxSub, maxSales};
	}

	private void search() {
		if (emoticons.length == discountStack.size()) {
			int sub = 0, sales = 0;
			for (int[] user : users) {
				int payment = 0;
				for (int i = 0; i < emoticons.length; i++) {
					if (discountStack.get(i) >= user[0]) { // 구매하면
						payment += (100 - discountStack.get(i)) * emoticons[i] / 100;
					}
				}
				if (payment >= user[1]) {
					sub++;
				} else {
					sales+=payment;
				}
				if (sub > maxSub) {
					maxSub = sub;
					maxSales = sales;
				} else if (sub == maxSub && sales > maxSales) {
					maxSales = sales;
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				discountStack.push(DISCOUNT_VALUES[i]);
				search();
				discountStack.pop();
			}
		}
	}
}
