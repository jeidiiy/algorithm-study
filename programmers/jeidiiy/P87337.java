package jeidiiy;

import java.util.Set;
import java.util.HashSet;

public class P87337 {
    class Solution {
        public String[] solution(int[][] lines) {
            Set<Intersection> set = new HashSet<>();

            long minX = Long.MAX_VALUE;
            long minY = Long.MAX_VALUE;
            long maxX = Long.MIN_VALUE;
            long maxY = Long.MIN_VALUE;

            for (int i = 0; i < lines.length - 1; i++) {
                int[] line = lines[i];
                double A = line[0], B = line[1], E = line[2];

                for (int j = i + 1; j < lines.length; j++) {
                    int[] nextLine = lines[j];
                    double C = nextLine[0], D = nextLine[1], F = nextLine[2];

                    if (A * D - B * C == 0) {
                        continue;
                    }

                    double x = (B * F - E * D) / (A * D - B * C);
                    if (x != (long) x) {
                        continue;
                    }

                    double y = (E * C - A * F) / (A * D - B * C);
                    if (y != (long) y) {
                        continue;
                    }

                    set.add(new Intersection((long) x, (long) y));

                    if (minX > x) {
                        minX = (long) x;
                    }

                    if (minY > y) {
                        minY = (long) y;
                    }

                    if (maxX < x) {
                        maxX = (long) x;
                    }

                    if (maxY < y) {
                        maxY = (long) y;
                    }
                }
            }

            long height = maxY - minY + 1;
            long width = maxX - minX + 1;
            boolean[][] starMap = new boolean[(int) height][(int) width];
            String[] answer = new String[(int) height];

            for (Intersection i : set) {
                int x = (int) (i.x - minX);
                int y = (int) (maxY - i.y);

                starMap[y][x] = true;
            }

            for (int y = 0; y < height; y++) {
                StringBuilder sb = new StringBuilder();
                for (int x = 0; x < width; x++) {
                    sb.append(starMap[y][x] ? "*" : ".");
                }
                answer[y] = sb.toString();
            }

            return answer;
        }

        static class Intersection {
            long x;
            long y;

            public Intersection(long x, long y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}