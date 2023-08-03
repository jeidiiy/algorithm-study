package jeidiiy;

import java.util.Arrays;

public class P147354 {
    class Solution {
        public int solution(int[][] data, int col, int row_begin, int row_end) {
            // 1. 튜플 정렬
            Arrays.sort(data, (t1, t2) -> {
                // 값이 같은 경우 기본 키 기준 내림차순
                int result = Integer.compare(t1[col - 1], t2[col - 1]);
                if (result == 0) {
                    return Integer.compare(t2[0], t1[0]);
                }
                // 다른 경우 해당 컬럼 값 기준 오름차순
                return result;
            });

            // 2. 해시값 계산
            int answer = 0;
            for (int i = row_begin - 1; i < row_end; i++) {
                int s_i = 0;
                for (int j = 0; j < data[0].length; j++) {
                    s_i += data[i][j] % (i + 1);
                }
                answer ^= s_i;
            }

            return answer;
        }
    }
}
