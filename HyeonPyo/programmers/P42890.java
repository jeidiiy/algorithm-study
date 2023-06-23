package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class P42890 {
	public static void main(String[] args) {
		String[][] relation = {
			{"100", "ryan", "music", "2"},
			{"200", "apeach", "math", "2"},
			{"300", "tube", "computer", "3"},
			{"400", "con", "computer", "4"},
			{"500", "muzi", "music", "3"},
			{"600", "apeach", "music", "2"}
		};
		System.out.println(solution(relation));
	}

	public static int solution(String[][] relation) {
		int column = relation.length; // 세로 몇개
		int row = relation[0].length; // 가로 몇개
		ArrayList<String> answerList = new ArrayList<>();

		for (int i = 0; i <= row; i++) {
			boolean[] isVisited = new boolean[row];
			dfs(relation, isVisited, 0, 0, i + 1, row, answerList);
		}

		return answerList.size();
	}

	private static void dfs(String[][] relation, boolean[] isVisited, int start, int depth, int goalDepth, int rowSize, ArrayList<String> answerList) {
		//끝에 도달했을때
		if (depth == goalDepth) {
			List<Integer> list = new ArrayList<>();
			HashSet<String> hashSet = new HashSet<>();
			StringBuilder keys = new StringBuilder(); // key 들의 집합

			// column(세로) 을 선택해서 돌면서 선택 (1, 12, 123 이런식)
			for (int i = 0; i < rowSize; i++) {
				if (isVisited[i]) {
					// 이게 키인데 이걸 더하자
					list.add(i);
					keys.append(i);
				}
			}
			
			// StringBuilder 에다가 모든 String 을 넣고 돌면서
			for (int i = 0; i < relation.length; i++) {
				StringBuilder sb = new StringBuilder();
				for (Integer integer : list) {
					sb.append(relation[i][integer]);
				}
				if (hashSet.contains(sb.toString())) { // hashSet 데이터가 없으면 데이터를 넣고 겹치면 유일성을 위반하기 때문에 탈출
					return;
				} else {
					hashSet.add(sb.toString());
				}
			}
			//여기까지 오면 유일성 만족한 애들이 들어옴 따라서 최소성을 만족해야 함
			//answerList에 없으면, answerList에 넣기
			for (String s : answerList) {
				int count = 0;
				for (int i = 0; i < keys.length(); i++) {
					String subString = String.valueOf(keys.charAt(i));
					if (s.contains(subString)) {
						count++;
					}
				}
				if (count == s.length()) {
					return;
				}
			}

			answerList.add(keys.toString());

			return;
		}
		//끝에 도달하기 이전에 isVisted 를 depth 까지 맞추기 == 키 n개 선택하기
		for (int i = start; i < rowSize; i++) {
			isVisited[i] = true;
			dfs(relation, isVisited, i + 1, depth + 1, goalDepth, rowSize, answerList);
			isVisited[i] = false;
		}
	}

}