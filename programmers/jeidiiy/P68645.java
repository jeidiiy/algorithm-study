package jeidiiy;

public class P68645 {
    class Solution {
        public int[] solution(int n) {
            int number = 1;
            int row = -1, col = 0;
            int[][] matrix = new int[n][n];
            int[] answer = new int[n * (n + 1) / 2];

            for (int i = n; i > 0; i -= 3) {
                for (int j = 0; j < i; j++) {
                    matrix[++row][col] = number++;
                }

                for (int j = 0; j < i - 1; j++) {
                    matrix[row][++col] = number++;
                }

                for (int j = 0; j < i - 2; j++) {
                    matrix[--row][--col] = number++;
                }
            }

            int k = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] != 0) {
                        answer[k++] = matrix[i][j];
                    }
                }
            }
            return answer;
        }
    }
}