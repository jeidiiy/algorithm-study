package programmers.Hyeonpyo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P87377 {
	/**
	 * 교점이 유일하게 존재할 경우 그 교점은 아래와 같다
	 * Ax + By+ E = 0
	 * Cx + Dy+ F = 0
	 * 일때
	 * X = BF-ED/AD-BC, Y = EC-AF/AD-BC
	 * ----------
	 * AD - BC = 0 인 경우 평행||일치 한다
	 *
	 */
	private List<long[]> contacts = new ArrayList<>();

	private long minX = Long.MAX_VALUE;
	private long maxX = Long.MIN_VALUE;
	private long minY = Long.MAX_VALUE;
	private long maxY = Long.MIN_VALUE;

	public String[] solution(int[][] line) {
		for (int i = 0; i < line.length; i++) {
			for (int j = 0; j < line.length; j++) {
				long A = line[i][0];
				long B = line[i][1];
				long E = line[i][2];

				long C = line[j][0];
				long D = line[j][1];
				long F = line[j][2];

				if (isExist(A, D, B, C)) {
					Long x = findX(A, B, C, D, E, F);
					Long y = findY(A, B, C, D, E, F);
					if (x != null && y != null) {
						reflectMinMax(x, y);
						contacts.add(new long[] {x, y});
					}
				}

			}
		}
		int width = (int)(maxX - minX + 1);
		int height = (int)(maxY - minY + 1);
		return draw(width, height);
	}

	/**
	 * AD - BC = 0 인 경우 평행||일치 한다
	 */
	private boolean isExist(long A, long D, long B, long C) {
		return 0 != ((A * D) - (B * C));
	}

	/**
	 * 교점이 유일하게 존재할 경우 그 교점은 아래와 같다
	 * Ax + By+ E = 0
	 * Cx + Dy+ F = 0
	 * 일때
	 * X = BF-ED/AD-BC, Y = EC-AF/AD-BC
	 */

	private Long findX(long A, long B, long C, long D, long E, long F) {
		double value = (double)((B * F) - (E * D)) / (double)((A * D) - (B * C));
		return (value % 1 == 0) ? (long)value : null;
	}

	private Long findY(long A, long B, long C, long D, long E, long F) {
		double value = (double)((E * C) - (A * F)) / (double)((A * D) - (B * C));
		return (value % 1 == 0) ? (long)value : null;
	}

	private void reflectMinMax(long x, long y) {
		if (x < minX) {
			minX = x;
		}
		if (x > maxX) {
			maxX = x;
		}
		if (y < minY) {
			minY = y;
		}
		if (y > maxY) {
			maxY = y;
		}
	}

	private String[] draw(int width, int height) {
		char[][] temp = new char[height][width];
		for (char[] chars : temp) {
			Arrays.fill(chars,'.');
		}
		for (long[] contact : contacts) {
			temp[(int)(maxY - contact[1])][(int)(contact[0] - minX)] = '*';
		}
		String[] strings = new String[temp.length];
		for (int i = 0; i < temp.length; i++) {
			strings[i] = new String(temp[i]);
		}
		return strings;
	}
}
