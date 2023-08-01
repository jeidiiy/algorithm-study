package jeidiiy;

public class P150368 {
    class Solution {
        private final static int[] discountRate = { 10, 20, 30, 40 };
        private static int totalMembers;
        private static int totalSales;

        public int[] solution(int[][] users, int[] emoticons) {
            resolve(new int[emoticons.length], users, emoticons, 0);
            return new int[] { totalMembers, totalSales };
        }

        public void resolve(int[] discounts, int[][] users, int[] emoticons, int depth) {
            if (depth == emoticons.length) {
                int members = 0;
                int sales = 0;

                for (int[] user : users) {
                    int thresholdOfDiscountRate = user[0];
                    int thresholdOfPrice = user[1];

                    int sum = 0;

                    for (int i = 0; i < emoticons.length; i++) {
                        if (discounts[i] >= thresholdOfDiscountRate) {
                            sum += emoticons[i] / 100 * (100 - discounts[i]);
                        }
                    }
                    if (sum >= thresholdOfPrice)
                        members++;
                    else
                        sales += sum;
                }
                if (members > totalMembers) {
                    totalMembers = members;
                    totalSales = sales;
                } else if (members == totalMembers) {
                    totalSales = Math.max(totalSales, sales);
                }
                return;
            }
            for (int i = 0; i < discountRate.length; i++) {
                discounts[depth] = discountRate[i];
                resolve(discounts, users, emoticons, depth + 1);
            }
        }
    }
}
