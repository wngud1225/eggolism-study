package BOJ_2636;

import java.util.*;

public class Main {

	static class Position {
		int r;
		int c;

		Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();

		int[][] arr = new int[R][C];
		int cnt = 0;
		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				arr[r][c] = sc.nextInt();
				if (arr[r][c] == 1) { // 처음 치즈의 개수를 구해 놓음
					cnt++;
				}
			}
		}

		Queue<Position> bfs = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		bfs.add(new Position(0, 0));
		visited[0][0] = true;

		while (!bfs.isEmpty()) { // 공기인 부분을 bfs로 2로 표시
			Position curr = bfs.poll();
			arr[curr.r][curr.c] = 2;
			for (int i = 0; i < 4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];

				if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && arr[nr][nc] == 0) {
					visited[nr][nc] = true;
					bfs.add(new Position(nr, nc));
				}
			}
		}

		int ans = 0; // 치즈가 모두 녹기 한 시간 전에 남아있는 치즈 조각의 수를 저장
		int time = 0; // 걸리는 시간 저장

		while (true) {
			ans = cnt;
			time++;
			for (int r = 0; r < R; r++) { // 4방 탐색으로 공기와 닿아있는 치즈를 경계라 판단, 배열의 값을 3으로 변경
				for (int c = 0; c < C; c++) {
					if (arr[r][c] == 1) {
						for (int i = 0; i < 4; i++) {
							int nr = r + dr[i];
							int nc = c + dc[i];

							if (nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] == 2) {
								arr[r][c] = 3;
							}
						}
					}
				}
			}
			for (int r = 0; r < R; r++) { // 치즈의 경계를 하나씩 없애며 만약 내부에 구멍이 있다면 공기(2)로 채워줌
				for (int c = 0; c < C; c++) {
					if (arr[r][c] == 3) {
						for (int i = 0; i < 4; i++) {
							int nr = r + dr[i];
							int nc = c + dc[i];

							if (nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] == 0) {
								bfs.add(new Position(nr, nc));
								visited[nr][nc] = true;

								while (!bfs.isEmpty()) {
									Position curr = bfs.poll();
									arr[curr.r][curr.c] = 2;
									for (int j = 0; j < 4; j++) {
										int nnr = curr.r + dr[j];
										int nnc = curr.c + dc[j];

										if (nnr >= 0 && nnr < R && nnc >= 0 && nnc < C && !visited[nnr][nnc]
												&& arr[nnr][nnc] == 0) {
											visited[nnr][nnc] = true;
											bfs.add(new Position(nnr, nnc));
										}
									}
								}
							}
						}
						arr[r][c] = 2; // 경계였던 치즈도 2로 바꿔주고
						cnt--; // 치즈의 수 감소
					}
				}
			}
			if (cnt == 0) { // 치즈의 수가 0이 되면 반복 종료, 이 때 ans에는 마지막 cnt가 저장되어 있음
				break;
			}
		}
		System.out.println(time);
		System.out.println(ans);
	}
}