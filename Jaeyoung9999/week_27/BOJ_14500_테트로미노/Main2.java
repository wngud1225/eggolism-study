package BOJ_14500;

import java.util.*;

public class Main2 {

	static int N;
	static int M;
	static int[][] arr;
	static boolean[][] visited;
	static int max;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				arr[r][c] = sc.nextInt();
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if ((r + c) % 2 == 0) {
					visited = new boolean[N][M];
					visited[r][c] = true;
					dfs(r, c, 0, 1);
				}
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				visited = new boolean[N][M];
				visited[r][c] = true;
				dfs2(r, c, 0, 1);
			}
		}
		System.out.println(max);
	}

	static void dfs(int r, int c, int sum, int pick) {
		sum += arr[r][c];
		if (pick == 4) {
			if (sum > max) {
				max = sum;
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc, sum, pick + 1);
				visited[nr][nc] = false;
			}
		}

	}

	static void dfs2(int r, int c, int sum, int pick) {
		sum += arr[r][c];

		if (pick == 2) {
			for (int i = 0; i < 3; i++) {
				for (int j = i + 1; j < 4; j++) {
					int nr1 = r + dr[i];
					int nc1 = c + dc[i];
					int nr2 = r + dr[j];
					int nc2 = c + dc[j];
					if (nr1 >= 0 && nr1 < N && nc1 >= 0 && nc1 < M && !visited[nr1][nc1] && nr2 >= 0 && nr2 < N
							&& nc2 >= 0 && nc2 < M && !visited[nr2][nc2]) {
						sum += arr[nr1][nc1] + arr[nr2][nc2];
						if (sum > max) {
							max = sum;
						}
						sum -= arr[nr1][nc1] + arr[nr2][nc2];
					}
				}
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs2(nr, nc, sum, pick + 1);
				visited[nr][nc] = false;
			}
		}

	}
}