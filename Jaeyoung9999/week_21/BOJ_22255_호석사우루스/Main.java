package BOJ_22255;

// 호석사우루스
import java.util.*;
import java.io.*;

public class Main {

	static class Dino implements Comparable<Dino> {
		int t; // time, 몇 회째에 이 칸에 왔는지
		int r;
		int c;
		int v; // 이 칸까지 온 최소비용

		Dino(int t, int r, int c, int v) {
			this.t = t;
			this.r = r;
			this.c = c;
			this.v = v;
		}

		@Override
		public int compareTo(Dino d) {

			return this.v - d.v;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int startR = Integer.parseInt(st.nextToken()) - 1;
		int startC = Integer.parseInt(st.nextToken()) - 1;

		int endR = Integer.parseInt(st.nextToken()) - 1;
		int endC = Integer.parseInt(st.nextToken()) - 1;

		int[][] arr = new int[R][C];
		int[][][] dist = new int[R][C][3]; // t에 따라 이동방식이 다르기 때문에 dist를 3차원으로 만듦

		// 상하좌우
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				Arrays.fill(dist[r][c], Integer.MAX_VALUE);
			}
		}
		PriorityQueue<Dino> pq = new PriorityQueue<>();
		
		// 시작점 설정
		dist[startR][startC][1] = 0;
		pq.add(new Dino(1, startR, startC, 0));

		diikstra: while (!pq.isEmpty()) {
			Dino curr = pq.poll();

			if (curr.r == endR && curr.c == endC) { // 목표 칸에 도착 시 종료
				break diikstra;
			}

			if (curr.t % 3 == 0 || curr.t % 3 == 1) { // 상하
				for (int i = 0; i <= 1; i++) {
					int nr = curr.r + dr[i];
					int nc = curr.c + dc[i];

					if (nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] != -1) { // 경계조건 + 벽 처리
						if (dist[nr][nc][(curr.t + 1) % 3] > dist[curr.r][curr.c][curr.t % 3] + arr[nr][nc]) { // 이동할 곳의 dist를 갱신 가능하다면
							dist[nr][nc][(curr.t + 1) % 3] = dist[curr.r][curr.c][curr.t % 3] + arr[nr][nc]; // 갱신 후
							pq.add(new Dino(curr.t + 1, nr, nc, dist[nr][nc][(curr.t + 1) % 3])); // 갱신한 위치의 dist 값을 v로 넣어줌
						}
					}
				}
			}

			if (curr.t % 3 == 0 || curr.t % 3 == 2) { // 좌우
				for (int i = 2; i <= 3; i++) {
					int nr = curr.r + dr[i];
					int nc = curr.c + dc[i];

					if (nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] != -1) {
						if (dist[nr][nc][(curr.t + 1) % 3] > dist[curr.r][curr.c][curr.t % 3] + arr[nr][nc]) {
							dist[nr][nc][(curr.t + 1) % 3] = dist[curr.r][curr.c][curr.t % 3] + arr[nr][nc];
							pq.add(new Dino(curr.t + 1, nr, nc, dist[nr][nc][(curr.t + 1) % 3]));
						}
					}
				}
			}
		}

		int result = Math.min(Math.min(dist[endR][endC][0], dist[endR][endC][1]), dist[endR][endC][2]);

		if (result == Integer.MAX_VALUE) { // dist가 변하지 않았다면 가는 방법 없음
			result = -1;
		}
		System.out.println(result);
	}
}