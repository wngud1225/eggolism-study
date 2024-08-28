package BOJ_1987;

import java.util.*;

public class Main {

	static int R;
	static int C;
	static boolean[] visited;
	static char[][] arr;
	static int max;

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		visited = new boolean[26];
		arr = new char[R][C];
		max = 0;

		for (int r = 0; r < R; r++) {
			String str = sc.next();
			for (int c = 0; c < C; c++) {
				arr[r][c] = str.charAt(c);
			}
		}
		dfs(0, 0, 1);
		System.out.println(max);
	}

	static void dfs(int r, int c, int t) {

		visited[toInt(arr[r][c])] = true;

		if (t > max) {
			max = t;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[toInt(arr[nr][nc])]) {
				dfs(nr, nc, t + 1);
			}
		}
		visited[toInt(arr[r][c])] = false;
	}

	static int toInt(char c) {
		return c - 65;
	}
}