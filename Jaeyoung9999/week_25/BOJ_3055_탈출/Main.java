package BOJ_3055;

import java.util.*;

public class Main {

	static class Position {
		int r;
		int c;
		int t;

		Position(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();

		char[][] arr = new char[R][C];
		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };

		Queue<Position> bfs1 = new LinkedList<>();
		boolean[][] visited1 = new boolean[R][C];
		Queue<Position> bfs2 = new LinkedList<>();

		for (int r = 0; r < R; r++) {
			String str = sc.next();
			for (int c = 0; c < C; c++) {
				arr[r][c] = str.charAt(c);
				if (arr[r][c] == '*') {
					bfs2.add(new Position(r, c, 0));
				} else if (arr[r][c] == 'S') {
					bfs1.add(new Position(r, c, 0));
					visited1[r][c] = true;
					arr[r][c] = '.';
				}
			}
		}
		int time = 0;
		start: while (true) {
			while (true) {
				if (bfs1.isEmpty()) {
					System.out.println("KAKTUS");
					break start;
				}

				Position curr = bfs1.poll();

				if (arr[curr.r][curr.c] == '*') {
					continue;
				}
				if (curr.t != time) {
					bfs1.add(curr);
					break;
				}
				if (arr[curr.r][curr.c] == 'D') {
					System.out.println(curr.t);
					break start;
				}

				for (int i = 0; i < 4; i++) {
					int nr = curr.r + dr[i];
					int nc = curr.c + dc[i];

					if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited1[nr][nc] && arr[nr][nc] != 'X'
							&& arr[nr][nc] != '*') {
						visited1[nr][nc] = true;
						bfs1.add(new Position(nr, nc, curr.t + 1));
					}
				}
			}

			while (true) {

				if (bfs2.isEmpty()) {
					break;
				}

				Position curr = bfs2.poll();

				if (curr.t != time) {
					bfs2.add(curr);
					break;
				}

				for (int i = 0; i < 4; i++) {
					int nr = curr.r + dr[i];
					int nc = curr.c + dc[i];

					if (nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] != '*' && arr[nr][nc] != 'X'
							&& arr[nr][nc] != 'D') {
						arr[nr][nc] = '*';
						bfs2.add(new Position(nr, nc, curr.t + 1));
					}
				}
			}
			time++;
		}
	}
}