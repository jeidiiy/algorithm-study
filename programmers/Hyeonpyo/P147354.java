package programmers.Hyeonpyo;

import java.util.Arrays;

public class P147354 {

	public int solution(int[][] data, int col, int row_begin, int row_end) {
		sortArray(data, col);
		return sOperation(row_begin, row_end, data);
	}

	private int sOperation(int row_begin, int row_end, int[][] sortedArray) {
		int answer = 0;
		for (int i = row_begin - 1; i < row_end; i++) {
			int sOperationVal = 0;
			for (int j = 0; j < sortedArray[i].length; j++) {
				sOperationVal += modOperation(sortedArray[i][j], i + 1);
			}
			answer = xorOperation(sOperationVal, answer);
		}
		return answer;
	}

	private int modOperation(int a, int b) {
		return a % b;
	}

	private int xorOperation(int a, int b) {
		return a ^ b;
	}

	private void sortArray(int[][] data, int col) {
		Arrays.sort(data, (a, b)->{ //a>b 오름차순, a<b 내림차순
			int compare = a[col - 1] - b[col - 1];
			if (compare == 0) {
				compare = b[0] - a[0];
			}
			return compare;
		});
	}

}
