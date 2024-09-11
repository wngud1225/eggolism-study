package BOJ_14500;

import java.util.*;

public class Main3 {

	static int R;
	static int C;
	static int[][] arr;
	static boolean[][] visited;
	static int ans;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		arr = new int[R][C];
		ans = Integer.MIN_VALUE;
		visited = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				arr[r][c] = sc.nextInt();
			}
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				dfs(r, c, 0, 1);
			}
		}
		System.out.println(ans);
	}

	static void dfs(int r, int c, int sum, int cnt) {

		visited[r][c] = true;
		sum += arr[r][c];

		if (cnt == 4) {
			if (sum > ans) {
				ans = sum;
			}
			visited[r][c] = false;
			return;
		}
		if (cnt == 2) {
			for (int i = 0; i < 4; i++) {
				for (int j = i + 1; j < 4; j++) {
					int nr1 = r + dr[i];
					int nc1 = c + dc[i];
					int nr2 = r + dr[j];
					int nc2 = c + dc[j];

					if (nr1 >= 0 && nr1 < R && nc1 >= 0 && nc1 < C && !visited[nr1][nc1]) {
						if (nr2 >= 0 && nr2 < R && nc2 >= 0 && nc2 < C && !visited[nr2][nc2]) {
							int tmpSum = sum + arr[nr1][nc1] + arr[nr2][nc2];
							if (tmpSum > ans) {
								ans = tmpSum;
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc]) {
				dfs(nr, nc, sum, cnt + 1);
			}
		}
		visited[r][c] = false;
	}
}