package PGS_외벽점검;

import java.util.*;

class Solution {

	static int N;
	static int[] weak;
	static int[] dist;
	static boolean[] visited;
	static int ans;

	public static int solution(int n, int[] w, int[] d) {
		N = n;
		weak = Arrays.copyOf(w, w.length);
		dist = Arrays.copyOf(d, d.length);
		Arrays.sort(dist);
		visited = new boolean[weak.length];
		ans = Integer.MAX_VALUE; // 보내야 하는 친구 수의 최솟값
		dfs(0, 0);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	static void dfs(int cnt, int point) { // cnt는 이번까지 해서 보낼 친구의 수, point는 이번에 보낼 친구의 시작 위치

		if (cnt >= ans || cnt > dist.length) { // 만약 이미 cnt가 ans보다 크거나, cnt가 전체 친구의 수보다 크다면 return
			return;
		}

		boolean[] tmp = Arrays.copyOf(visited, visited.length); // visited 배열 깊은 복사

		if (cnt != 0) { // cnt가 0이면 이번 dfs에서 친구를 보내지 않음
			for (int i = 0; i < weak.length; i++) { // 각 취약 지점들에 대해
				if (weak[i] >= weak[point] && weak[i] <= weak[point] + dist[dist.length - cnt]) { // 이번 친구가 한 바퀴를 넘어가지 않는 범위 내에 있는 취약 지점이라면
					visited[i] = true;
				} else if (weak[i] + N <= weak[point] + dist[dist.length - cnt]) { // 이번 친구가 한 바퀴를 넘어가는 범위 내에 있는 취약 지점이라면
					visited[i] = true;
				}
			}
			// 모든 취약 지점을 방문했는지 체크
			boolean isCheck = true;

			for (int i = 0; i < weak.length; i++) {
				if (!visited[i]) {
					isCheck = false;
					break;
				}
			}
			if (isCheck && cnt < ans) { // 모두 방문했고 cnt가 기존 최소보다 작다면 갱신
				ans = cnt;
				return;
			}
		}
		for (int i = 0; i < weak.length; i++) { // 아직 방문하지 않은 취약 지점에 대해 새로운 dfs
			if (!visited[i]) {
				dfs(cnt + 1, i);
			}
		}
		visited = tmp; // 다시 원상복구
	}
}