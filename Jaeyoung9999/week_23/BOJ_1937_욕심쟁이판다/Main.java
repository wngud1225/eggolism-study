package BOJ_1937;

// 욕심쟁이판다
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
		public int compareTo(Point p) { // 대나무가 많은 곳부터 정렬

			return p.v - this.v;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[][] arr = new int[n][n];
		int[][] dp = new int[n][n];

		PriorityQueue<Point> pq = new PriorityQueue<>();

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				arr[r][c] = sc.nextInt();
				pq.add(new Point(r, c, arr[r][c]));
			}
		}

		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		int ans = 0;

		while (!pq.isEmpty()) {

			Point curr = pq.poll();
			int max = 0;

			for (int i = 0; i < 4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];

				if (nr >= 0 && nr < n && nc >= 0 && nc < n && arr[nr][nc] > arr[curr.r][curr.c]) { // 옆 칸이랑 같을 경우를 대비해 조건문 추가
					max = Math.max(max, dp[nr][nc]);
				}
			}
			dp[curr.r][curr.c] = max + 1; // (4방 中 가장 dp가 큰 곳 & 가능한 곳) + 1
			
			if (max + 1 > ans) {
				ans = max + 1;
			}
		}
		System.out.println(ans);
	}
}