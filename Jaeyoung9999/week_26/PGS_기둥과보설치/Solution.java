package PGS_기둥과보설치;

import java.util.*;

class Solution {

	static class Structure implements Comparable<Structure> {
		int x;
		int y;
		int type; // 0이면 기둥, 1이면 보

		Structure(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}

		@Override
		public int compareTo(Structure s) {
			if (this.x == s.x) {
				if (this.y == s.y) {
					return this.type - s.type;
				}
				return this.y - s.y;
			}
			return this.x - s.x;
		}
	}

	public int[][] solution(int n, int[][] build_frame) {
		TreeSet<Structure> set = new TreeSet<>();
		boolean[][] gi = new boolean[n + 1][n + 1]; // 기둥
		boolean[][] bo = new boolean[n + 1][n + 1]; // 보

		for (int i = 0; i < build_frame.length; i++) {
			int x = build_frame[i][0];
			int y = build_frame[i][1];
			int type = build_frame[i][2];
			int install = build_frame[i][3];

			if (install == 1) { // 설치
				if (type == 0) { // 기둥
					if (y == 0 || (x >= 1 && bo[x - 1][y]) || bo[x][y] || (y >= 1 && gi[x][y - 1])) {
						// 바닥이거나 아래에 보나 기둥이 있다면 설치 가능
						gi[x][y] = true;
						set.add(new Structure(x, y, 0));
					}
				} else { // 보
					if ((y >= 1 && gi[x][y - 1]) || (x < n && y >= 1 && gi[x + 1][y - 1])
							|| (x >= 1 && x < n && bo[x - 1][y] && bo[x + 1][y])) {
						// 아래에 기둥이 있거나 좌우에 보가 있으면 설치 가능
						bo[x][y] = true;
						set.add(new Structure(x, y, 1));
					}
				}
			} else { // 삭제
				if (type == 0) { // 기둥
					if (y < n && gi[x][y + 1]) { // 기둥 위에 기둥이 있다면
						if (bo[x][y + 1] || (x >= 1 && bo[x - 1][y + 1])) {
							// 윗 기둥 아래에 보가 있어야 함
						} else {
							continue;
						}
					}
					if (y < n && bo[x][y + 1]) { // 기둥 위에서 오른쪽으로 펴진 보가 있다면
						if ((x >= 1 && x < n && bo[x - 1][y + 1] && bo[x + 1][y + 1]) || (x < n && gi[x + 1][y])) {
							// 좌우에 보가 있거나 오른쪽 아래에 기둥이 있어야 함
						} else {
							continue;
						}
					}
					if (x >= 1 && y < n && bo[x - 1][y + 1]) { // 기둥 위에서 왼쪽으로 펴진 보가 있다면
						if ((x >= 2 && x < n && bo[x - 2][y + 1] && bo[x][y + 1]) || (x >= 1 && gi[x - 1][y])) {
							// 좌우에 보가 있거나 왼쪽 아래에 기둥이 있어야 삭제 가능
						} else {
							continue;
						}
					}
					set.remove(new Structure(x, y, 0));
					gi[x][y] = false;
				} else { // 보
					if (gi[x][y]) { // 보의 왼쪽에 올려진 기둥이 있다면
						if (y >= 1 && gi[x][y - 1] || (x >= 1 && bo[x - 1][y])) { // 기둥 아래에 기둥이나 다른 보가 존재해야 함
						} else {
							continue;
						}
					}

					if (x < n && gi[x + 1][y]) {// 보의 오른쪽에 올려진 기둥이 있다면
						if (y >= 1 && gi[x + 1][y - 1] || (x < n && bo[x + 1][y])) { // 기둥 아래에 기둥이나 다른 보가 존재해야 함
						} else {
							continue;
						}
					}

					if (x >= 1 && bo[x - 1][y]) { // 자신의 왼쪽 보가 존재한다면
						if ((x >= 1 && y >= 1 && gi[x - 1][y - 1]) || (y >= 1 && gi[x][y - 1])) { // 왼쪽 보 아래에 기둥이 있어야 함
						} else {
							continue;
						}
					}
					if (x < n && bo[x + 1][y]) { // 오른쪽 보가 존재한다면
						if ((x < n && y >= 1 && gi[x + 1][y - 1]) || (x < n - 1 && y >= 1 && gi[x + 2][y - 1])) {
						} else {
							continue;
						}
					}
					set.remove(new Structure(x, y, 1));
					bo[x][y] = false;
				}
			}
		}

		int[][] answer = new int[set.size()][3];
		int idx = 0;

		while (!set.isEmpty()) {
			Structure s = set.pollFirst();
			int[] arr = { s.x, s.y, s.type };
			answer[idx++] = arr;
		}
		return answer;
	}
}