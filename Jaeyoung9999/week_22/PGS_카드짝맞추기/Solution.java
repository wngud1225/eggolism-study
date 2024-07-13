package PGS_카드짝맞추기;

import java.util.*;

class Solution {

	static class Point {
		int r;
		int c;
		int t;

		Point(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}

	static int[][] arr;
	static int[][] org;
	static List<Integer> list;
	static int[] sel;
	static boolean[] visited;
	static int R;
	static int C;
	static int min = Integer.MAX_VALUE;

	public int solution(int[][] board, int r, int c) {
		int answer = 0;
		R = r; // 시작점 설정
		C = c;
		arr = new int[4][4]; // 깊은 복사할 배열
		org = board; // 원본 배열
		list = new ArrayList<>(); // 그림의 종류를 담을 리스트(중복 제거)
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				arr[i][j] = board[i][j];
				if (board[i][j] != 0 && !list.contains(board[i][j])) {
					list.add(board[i][j]);
				}
			}
		}
		sel = new int[list.size()];
		visited = new boolean[list.size()];

		perm(0);

		answer = min + list.size() * 2;

		return answer;
	}

	void perm(int idx) { // 순열
		if (idx == list.size()) { // 순열 완성 시

			Point start = startBfs(R, C, 0, sel[0]); // 순열 순서대로 bfs 탐색 시작
			start = startBfs(start.r, start.c, start.t, sel[0]); // 페어를 찾아야 하므로 2번씩 진행
			for (int i = 1; i < list.size(); i++) {
				for (int j = 0; j < 2; j++) {
					start = startBfs(start.r, start.c, start.t, sel[i]);
				}
			}

			if (start.t < min) {
				min = start.t;
			}

			for (int i = 0; i < 4; i++) { // 탐색 종료 이후 arr을 원래대로 초기화
				for (int j = 0; j < 4; j++) {
					arr[i][j] = org[i][j];
				}
			}
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			sel[idx] = list.get(i);
			perm(idx + 1);
			visited[i] = false;
		}
	}

	Point startBfs(int r, int c, int t, int target) {

		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		Queue<Point> bfs = new LinkedList<>();
		boolean[][] visited = new boolean[4][4];

		bfs.add(new Point(r, c, t));
		visited[r][c] = true;

		while (!bfs.isEmpty()) {
			Point curr = bfs.poll();

			if (arr[curr.r][curr.c] == target) {
				arr[curr.r][curr.c] = 0;
				return curr; // 끝난 지점 위치까지 알기 위해 Point 객체로 반환
			}

			for (int d = 0; d < 4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				// 한 칸만 이동했을 때
				if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					bfs.add(new Point(nr, nc, curr.t + 1));

				}
				// 끝까지 이동했을 때
				while (true) {
					if (nr == -1 || nr == 4 || nc == -1 || nc == 4) {
						nr -= dr[d];
						nc -= dc[d];
						break;
					}
					if (arr[nr][nc] != 0) {
						break;
					}
					nr += dr[d];
					nc += dc[d];
				}
				if (!visited[nr][nc]) {
					visited[nr][nc] = true;
					bfs.add(new Point(nr, nc, curr.t + 1));
				}
			}
		}
		return null;
	}
}