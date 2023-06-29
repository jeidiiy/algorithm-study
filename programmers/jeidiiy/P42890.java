package jeidiiy;

import java.util.*;

public class P42890 {
	class Solution {
		public int solution(String[][] relation) {
			int rowSize = relation.length, colSize = relation[0].length;
			ArrayList<Integer> result = new ArrayList<Integer>();

			// 1. 인덱스의 부분집합을 구해 후보키인지 확인
			for (int i = 1; i < (1 << colSize); i++) { // (1<<colSize)=부분집합의 개수
				if (!isMinimal(i, result))
					continue;
				if (!isUnique(i, relation, rowSize, colSize))
					continue;

				result.add(i);
			}

			return result.size();
		}

		// 2. 결과 리스트에 이미 들어간 키가 현재 부분집합에 포함되어 있는지 최소성 검사
		private boolean isMinimal(int arr, ArrayList<Integer> result) {
			for (int key : result)
				if ((arr & key) == key)
					return false; // 중복이면 false
			return true;
		}

		// 3, 부분 집합에 포함된 인덱스들이 후보키가 될 수 있는지 유일성 검사
		private boolean isUnique(int arr, String[][] relation, int rowSize, int colSize) {
			ArrayList<Integer> s = convertToIdx(arr, colSize);
			HashSet<String> set = new HashSet<>();

			for (int i = 0; i < rowSize; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j : s)
					sb.append(relation[i][j]);
				set.add(sb.toString());
			}

			return set.size() == rowSize;
		}

		// 이진수를 인덱스로 바꿔주는 함수
		private ArrayList<Integer> convertToIdx(int arr, int colSize) {
			ArrayList<Integer> list = new ArrayList<Integer>();

			for (int i = 0; i < colSize; i++)
				if (((arr >> i) & 1) == 1)
					list.add(i);

			return list;
		}
	}
}
