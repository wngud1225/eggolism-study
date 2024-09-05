package PGS_외벽점검;

import java.util.*;

class Solution2 {
	static int[] w;
	static int[] d;

	static int range;
	static int N;
	static int[] sel;
	static boolean[] visited;
	static boolean isPossible;

	public int solution(int n, int[] weak, int[] dist) {
		int answer = 0;

		w = Arrays.copyOf(weak, weak.length);
		d = Arrays.copyOf(dist, dist.length);
		Arrays.sort(d);
		range = n;

		int ans = 1;
		while (ans <= dist.length) {
			N = ans;
			sel = new int[N];
			visited = new boolean[w.length];
			isPossible = false;
			perm(0);
			if (isPossible) {
				answer = ans;
				break;
			}
			ans++;
		}

		if (ans > dist.length) {
			return -1;
		} else {
			return answer;
		}
	}

	static void perm(int idx) {

		if (isPossible) {
			return;
		}

		if (idx == N) {
			boolean[] check = new boolean[w.length];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < w.length; j++) {					
					if (w[j] >= sel[i] && w[j] <= sel[i] + d[d.length - i - 1]) {
						check[j] = true;
					} else if (w[j] + range <= sel[i] + d[d.length - i - 1]) {
						check[j] = true;
					}
				}
			}
			boolean isCheck = true;

			for (int i = 0; i < w.length; i++) {
				if (!check[i]) {
					isCheck = false;
					break;
				}
			}
			if (isCheck) {
				isPossible = true;
			}
			return;
		}
		for (int i = 0; i < w.length; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			sel[idx] = w[i];
			perm(idx + 1);
			visited[i] = false;
		}
	}
}