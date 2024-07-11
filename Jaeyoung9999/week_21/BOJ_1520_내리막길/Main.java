import java.io.*;
import java.util.*;

public class Main {

	static class Point implements Comparable<Point> {
		int r;
		int c;
		int v;

		Point(int r, int c, int v) {
			this.r = r;
			this.c = c;
			this.v = v;
		}

		@Override
		public int compareTo(Point p) {

			return p.v - this.v;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[][] arr = new int[R][C];
		boolean[][] visited = new boolean[R][C];
		int[][] dp = new int[R][C];

		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		PriorityQueue<Point> bfs = new PriorityQueue<>();
		bfs.add(new Point(0, 0, arr[0][0]));
		dp[0][0] = 1;
		visited[0][0] = true;

		while (!bfs.isEmpty()) {
			Point curr = bfs.poll();

			if (curr.v <= arr[R - 1][C - 1]) {
				continue;
			}

			for (int d = 0; d <= 3; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];

				if (nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] < arr[curr.r][curr.c]) {
					if (!visited[nr][nc]) {
						visited[nr][nc] = true;
						bfs.add(new Point(nr, nc, arr[nr][nc]));
					}
					dp[nr][nc] += dp[curr.r][curr.c];
				}
			}
		}
		System.out.println(dp[R - 1][C - 1]);
	}
}