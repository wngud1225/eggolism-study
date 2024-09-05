package PGS_블록이동하기;

import java.util.*;

class Solution {

	static class Position {
		int r1;
		int c1;
		int r2;
		int c2;
		int t;

		Position(int r1, int c1, int r2, int c2, int t) {
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
			this.t = t;
		}
	}

	public int solution(int[][] board) {
		int answer = 0;

		int N = board.length;
		Queue<Position> bfs = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][N][N][N];
		bfs.add(new Position(0, 0, 0, 1, 0));
		visited[0][0][0][1] = true;
		int[] dr = { 1, -1, 0, 0 };
		int[] dc = { 0, 0, 1, -1 };

		while (!bfs.isEmpty()) {
			Position curr = bfs.poll();

			if ((curr.r1 == N - 1 && curr.c1 == N - 1) || (curr.r2 == N - 1 && curr.c2 == N - 1)) {
				answer = curr.t;
				break;
			}

			for (int i = 0; i < 4; i++) { // 4방 이동
				int nr1 = curr.r1 + dr[i];
				int nc1 = curr.c1 + dc[i];
				int nr2 = curr.r2 + dr[i];
				int nc2 = curr.c2 + dc[i];
				if (nr1 >= 0 && nr1 < N && nc1 >= 0 && nc1 < N && nr2 >= 0 && nr2 < N && nc2 >= 0 && nc2 < N
						&& !visited[nr1][nc1][nr2][nc2] && board[nr1][nc1] == 0 && board[nr2][nc2] == 0) {
					visited[nr1][nc1][nr2][nc2] = true;
					bfs.add(new Position(nr1, nc1, nr2, nc2, curr.t + 1));
				}
			}

			if (curr.r1 == curr.r2) { // 가로로 놓였을 경우
				if (curr.r1 >= 1 && board[curr.r1 - 1][curr.c1] == 0 && board[curr.r2 - 1][curr.c2] == 0) { // 위 두 칸이 빔
					if (!visited[curr.r1][curr.c1][curr.r1 - 1][curr.c1]) {
						visited[curr.r1][curr.c1][curr.r1 - 1][curr.c1] = true;
						bfs.add(new Position(curr.r1, curr.c1, curr.r1 - 1, curr.c1, curr.t + 1));
					}
					if (!visited[curr.r2 - 1][curr.c2][curr.r2][curr.c2]) {
						visited[curr.r2 - 1][curr.c2][curr.r2][curr.c2] = true;
						bfs.add(new Position(curr.r2 - 1, curr.c2, curr.r2, curr.c2, curr.t + 1));
					}
				}
				if (curr.r1 < N - 1 && board[curr.r1 + 1][curr.c1] == 0 && board[curr.r2 + 1][curr.c2] == 0) { // 아래 두 칸이 빔
					if (!visited[curr.r1][curr.c1][curr.r1 + 1][curr.c1]) {
						visited[curr.r1][curr.c1][curr.r1 + 1][curr.c1] = true;
						bfs.add(new Position(curr.r1, curr.c1, curr.r1 + 1, curr.c1, curr.t + 1));
					}
					if (!visited[curr.r2 + 1][curr.c2][curr.r2][curr.c2]) {
						visited[curr.r2 + 1][curr.c2][curr.r2][curr.c2] = true;
						bfs.add(new Position(curr.r2 + 1, curr.c2, curr.r2, curr.c2, curr.t + 1));
					}
				}
			} else {
				if (curr.c1 >= 1 && board[curr.r1][curr.c1 - 1] == 0 && board[curr.r2][curr.c2 - 1] == 0) { // 왼쪽 두 칸이 빔
					if (!visited[curr.r1][curr.c1][curr.r1][curr.c1 - 1]) {
						visited[curr.r1][curr.c1][curr.r1][curr.c1 - 1] = true;
						bfs.add(new Position(curr.r1, curr.c1, curr.r1, curr.c1 - 1, curr.t + 1));
					}
					if (!visited[curr.r2][curr.c2 - 1][curr.r2][curr.c2]) {
						visited[curr.r2][curr.c2 - 1][curr.r2][curr.c2] = true;
						bfs.add(new Position(curr.r2, curr.c2 - 1, curr.r2, curr.c2, curr.t + 1));
					}
				}
				if (curr.c1 < N - 1 && board[curr.r1][curr.c1 + 1] == 0 && board[curr.r2][curr.c2 + 1] == 0) { // 오른쪽 두 칸이 빔
					if (!visited[curr.r1][curr.c1][curr.r1][curr.c1 + 1]) {
						visited[curr.r1][curr.c1][curr.r1][curr.c1 + 1] = true;
						bfs.add(new Position(curr.r1, curr.c1, curr.r1, curr.c1 + 1, curr.t + 1));
					}
					if (!visited[curr.r2][curr.c2 + 1][curr.r2][curr.c2]) {
						visited[curr.r2][curr.c2 + 1][curr.r2][curr.c2] = true;
						bfs.add(new Position(curr.r2, curr.c2 + 1, curr.r2, curr.c2, curr.t + 1));
					}
				}
			}
		}
		return answer;
	}
}