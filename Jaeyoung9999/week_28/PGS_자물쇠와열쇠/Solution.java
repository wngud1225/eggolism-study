package PGS_자물쇠와열쇠;

class Solution {
	public boolean solution(int[][] key, int[][] lock) {
		
		boolean answer = false;

		int M = key.length;
		int N = lock.length;
		int lockCnt = 0;
		int[][][] keys = new int[4][M][M]; // 원본 키와 회전한 키들을 저장
		
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < M; c++) {
				keys[0][r][c] = key[r][c]; // 원본
				keys[1][c][M - 1 - r] = key[r][c]; // 90도
				keys[2][M - 1 - r][M - 1 - c] = key[r][c]; // 180도
				keys[3][M - 1 - c][r] = key[r][c]; // 270도
			}
		}

		// lock 내의 홈의 수
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (lock[r][c] == 0) {
					lockCnt++;
				}
			}
		}

		// 왼쪽 위 기준 좌표를 하나 잡아
		start: for (int i = -1 * M + 1; i < N; i++) {
			for (int j = -1 * M + 1; j < N; j++) {
				con: for (int d = 0; d < 4; d++) { // 앞서 만든 4개의 배열을 이용
					
					int cnt = 0;

					for (int r = 0; r < M; r++) { // key를 순회하며
						for (int c = 0; c < M; c++) {
							// lock 상의 key의 좌표
							int nr = r + i;
							int nc = c + j;

							if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
								if (keys[d][r][c] == 1 && lock[nr][nc] == 0) { // 홈과 돌기가 일치한다면 cnt++
									cnt++;
								} else if (keys[d][r][c] == 1 && lock[nr][nc] == 1) { // 돌기끼리 만나면 안됨
									continue con;
								}
							}
						}
					}
					if (cnt == lockCnt) { // 홈, 돌기 일치 수가 처음에 구한 lock의 홈 수와 일치하면 가능한 경우라 판단
						answer = true;
						break start;
					}
				}
			}
		}
		return answer;
	}
}