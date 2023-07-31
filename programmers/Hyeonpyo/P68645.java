package programmers.Hyeonpyo;

public class P68645 {
	public static void main(String[] args) {
		solution(4);
	}

	public static int[] solution(int n) {
		int[][] array = new int[n][n];
		int x = -1;
		int y = 0;
		int saveVal = 1;

		for (int i = 0; i < n; i++) { // %3 하면서 3가지 상태
			for (int j = i; j < n; j++) { // 한방향 이동
				if (i % 3 == 0) {
					x++;
				}
				else if (i % 3 == 1) {
					y++;
				}
				else {
					x--;
					y--;
				}
				array[x][y] = saveVal++;
			}
		}
		int[] answer = new int[saveVal - 1];
		saveVal = 0;

		for (int[] a : array) {
			for (int i : a) {
				if (i == 0) {
					break;
				}
				answer[saveVal] = i;
				saveVal++;
			}
		}

		return answer;
	}

}
