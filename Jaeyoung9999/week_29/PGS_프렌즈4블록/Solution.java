package PGS_프렌즈4블록;

import java.util.*;

class Solution {

	static class Position {
		int r;
		int c;

		Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = { 1, 1, 0 };
	static int[] dc = { 0, 1, 1 };

	public int solution(int m, int n, String[] board) {

		int answer = 0;
		char[][] arr = new char[m][n];

		for (int r = 0; r < m; r++) {
			String tmp = board[r];
			for (int c = 0; c < n; c++) {
				arr[r][c] = tmp.charAt(c);
			}
		}

		boolean isChanged = true;

		while (isChanged) {

			isChanged = false;
			List<Position> list = new ArrayList<>();

			for (int r = 0; r < m; r++) {
				start: for (int c = 0; c < n; c++) {
					if (arr[r][c] != '0') {
						char curr = arr[r][c];
						for (int i = 0; i < 3; i++) {
							int nr = r + dr[i];
							int nc = c + dc[i];
							if (!(nr < m && nc < n && arr[nr][nc] == curr)) {
								continue start;
							}
						}
						isChanged = true;
						list.add(new Position(r, c));
					}
				}
			}
			for (Position pos : list) {
				arr[pos.r][pos.c] = '0';
				for (int i = 0; i < 3; i++) {
					int nr = pos.r + dr[i];
					int nc = pos.c + dc[i];
					arr[nr][nc] = '0';
				}
			}

			for (int c = 0; c < n; c++) {
				List<Character> tmp = new ArrayList<>();
				for (int r = m - 1; r >= 0; r--) {
					if (arr[r][c] != '0') {
						tmp.add(arr[r][c]);
					}
				}
				int nr = m - 1;
				for (char curr : tmp) {
					arr[nr--][c] = curr;
				}
				while (nr >= 0) {
					arr[nr--][c] = '0';
				}
			}
		}
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				if (arr[r][c] == '0') {
					answer++;
				}
			}
		}
		return answer;
	}
}