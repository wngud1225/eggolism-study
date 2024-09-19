package BOJ_7579;

import java.util.*;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		int[] memories = new int[N + 1];
		int[] costs = new int[N + 1];
		int totalCost = 0;

		for (int i = 1; i <= N; i++) {
			memories[i] = sc.nextInt();
		}
		for (int i = 1; i <= N; i++) {
			costs[i] = sc.nextInt();
			totalCost += costs[i];
		}
		int[][] dp = new int[N + 1][totalCost + 1];
		int ans = Integer.MAX_VALUE;

		for (int r = 1; r <= N; r++) {
			for (int c = 0; c <= totalCost; c++) {
				if (costs[r] <= c) {
					dp[r][c] = Math.max(dp[r - 1][c], dp[r - 1][c - costs[r]] + memories[r]);
				} else {
					dp[r][c] = dp[r - 1][c];
				}
				if (dp[r][c] >= M && c < ans) {
					ans = c;
				}
			}
		}
		System.out.println(ans);
	}
}