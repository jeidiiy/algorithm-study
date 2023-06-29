package programmers.Hyeonpyo;

public class P12905 {
	public static void main(String[] args) {

		int[][] boardA = {
			{0, 1, 1, 1},
			{1, 1, 1, 1},
			{1, 1, 1, 1},
			{0, 0, 1, 0}}; //answer 9

		int[][] boardB = {
			{0, 0, 1, 1},
			{1, 1, 1, 1}}; //answer 4

		int[][] boardC = {
			{0, 0, 0, 0},
			{0, 0, 0, 0}};
		System.out.println(solution(boardA));
		System.out.println(solution(boardB));
		System.out.println(solution(boardC));

	}
	public static int solution(int[][] board) {
		int answer = 0;
		for (int[] rows : board) {
			for (int selected : rows) {
				answer = Math.max(answer, selected);
			}
		}
		int column = board[0].length; //세로 n개
		int row = board.length; //가로 n개

		for (int i = 1; i < row; i++) {
			for (int j = 1; j < column; j++) {
				if (board[i][j] == 0)
					continue;
				board[i][j] = Math.min(Math.min(board[i - 1][j], board[i][j - 1]),
					board[i - 1][j - 1]) + 1;
				answer = Math.max(answer, board[i][j]);
			}
		}

		return answer * answer;
	}

}
