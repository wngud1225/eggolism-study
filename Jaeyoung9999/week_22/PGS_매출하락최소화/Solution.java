package PGS_매출하락최소화;

import java.util.*;

class Solution {

	static List<Integer>[] children;
	static int[] salesCopy;
	static int[][] dp;

	public int solution(int[] sales, int[][] links) {
		int answer = 0;

		salesCopy = sales;
		children = new ArrayList[sales.length + 1]; // 각 팀장이 가지는 팀원 리스트
		dp = new int[sales.length + 1][2]; // 각 직원을 포함했을 때의 최소비용/비포함했을 때의 최소비용 dp

		for (int i = 0; i <= sales.length; i++) {
			children[i] = new ArrayList<>();
		}

		for (int i = 0; i < sales.length - 1; i++) {
			children[links[i][0]].add(links[i][1]);
		}
		answer = Math.min(dp(1, 1), dp(1, 0));

		return answer;
	}

	int dp(int num, int isContain) { // 직원 번호 + 이 직원을 포함하는지 여부

		if (dp[num][isContain] != 0) { // 이미 저장한 dp면 바로 반환
			return dp[num][isContain];
		}
		
		if (isContain == 1) { // 포함 시

			int sum = salesCopy[num - 1]; // 자신의 매출액을 더해줌

			for (int i = 0; i < children[num].size(); i++) { // 자신의 자식들의 dp 중 더 작은 값을 더해줌
				sum += Math.min(dp(children[num].get(i), 1), dp(children[num].get(i), 0));
			}
			dp[num][1] = sum;
			return sum;

		} else { // 비포함 시

			if (children[num].size() == 0) { // 자식이 없다면 0으로 설정
				dp[num][0] = 0;
				return 0;
			}
			int result = Integer.MAX_VALUE;

			for (int i = 0; i < children[num].size(); i++) {
				int sum = 0;

				for (int j = 0; j < children[num].size(); j++) {
					if (i == j) { // 자신의 자식들 중 하나는 반드시 자기 자신을 포함하는 dp여야 함
						sum += dp(children[num].get(j), 1);
					} else { // 나머지는 더 작은 값으로 가져옴
						sum += Math.min(dp(children[num].get(j), 1), dp(children[num].get(j), 0));
					}
				}
				result = Math.min(result, sum);
			}
			dp[num][0] = result;
			return result;
		}
	}
}