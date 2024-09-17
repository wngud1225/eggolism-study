package PGS_블록게임;

class Solution {

	static int N;
	static int num;

	public static int solution(int[][] board) {
		int answer = 0;

		N = board.length;
		num = -1;

		while (true) {
			for (int c = 0; c < N; c++) {
				int r = 0;
				while (r < N) {
					if (board[r][c] == 0 || board[r][c] == num) {
						board[r][c] = num;
						r++;
					} else {
						break;
					}
				}
			}
			int tmp = check(board);

			if (tmp > 0) {
				answer += tmp;
			} else {
				break;
			}
		}
		return answer;
	}

	static int check(int[][] board) {

		int result = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {

				if (board[r][c] == num || board[r][c] == 0) {
					continue;
				} else {
					int curr = board[r][c];
					int cnt = 0;

					start: for (int nr = 0; nr <= 1; nr++) {
						for (int nc = 0; nc <= 2; nc++) {
							int nnr = nr + r;
							int nnc = nc + c;

							if (nnr >= 0 && nnr < N && nnc >= 0 && nnc < N) {
								if (board[nnr][nnc] == curr) {
									cnt++;
								} else if (board[nnr][nnc] != num) {
									cnt = 0;
									break start;
								}
							} else {
								cnt = 0;
								break start;
							}
						}
					}
					if (cnt == 4) {
						result++;
						for (int nr = 0; nr <= 1; nr++) {
							for (int nc = 0; nc <= 2; nc++) {
								int nnr = nr + r;
								int nnc = nc + c;

								board[nnr][nnc] = 0;
							}
						}
					}

					cnt = 0;

					start: for (int nr = 0; nr <= 2; nr++) {
						for (int nc = -1; nc <= 0; nc++) {
							int nnr = nr + r;
							int nnc = nc + c;

							if (nnr >= 0 && nnr < N && nnc >= 0 && nnc < N) {
								if (board[nnr][nnc] == curr) {
									cnt++;
								} else if (board[nnr][nnc] != num) {
									cnt = 0;
									break start;
								}
							} else {
								cnt = 0;
								break start;
							}
						}
					}
					if (cnt == 4) {
						result++;
						for (int nr = 0; nr <= 2; nr++) {
							for (int nc = -1; nc <= 0; nc++) {
								int nnr = nr + r;
								int nnc = nc + c;

								board[nnr][nnc] = 0;
							}
						}
					}

					cnt = 0;

					start: for (int nr = 0; nr <= 2; nr++) {
						for (int nc = 0; nc <= 1; nc++) {
							int nnr = nr + r;
							int nnc = nc + c;

							if (nnr >= 0 && nnr < N && nnc >= 0 && nnc < N) {
								if (board[nnr][nnc] == curr) {
									cnt++;
								} else if (board[nnr][nnc] != num) {
									cnt = 0;
									break start;
								}
							} else {
								cnt = 0;
								break start;
							}
						}
					}
					if (cnt == 4) {
						result++;
						for (int nr = 0; nr <= 2; nr++) {
							for (int nc = 0; nc <= 1; nc++) {
								int nnr = nr + r;
								int nnc = nc + c;

								board[nnr][nnc] = 0;
							}
						}
					}

					cnt = 0;

					start: for (int nr = 0; nr <= 1; nr++) {
						for (int nc = -2; nc <= 0; nc++) {
							int nnr = nr + r;
							int nnc = nc + c;

							if (nnr >= 0 && nnr < N && nnc >= 0 && nnc < N) {
								if (board[nnr][nnc] == curr) {
									cnt++;
								} else if (board[nnr][nnc] != num) {
									cnt = 0;
									break start;
								}
							} else {
								cnt = 0;
								break start;
							}
						}
					}
					if (cnt == 4) {
						result++;
						for (int nr = 0; nr <= 1; nr++) {
							for (int nc = -2; nc <= 0; nc++) {
								int nnr = nr + r;
								int nnc = nc + c;

								board[nnr][nnc] = 0;
							}
						}
					}

					cnt = 0;

					start: for (int nr = 0; nr <= 1; nr++) {
						for (int nc = -1; nc <= 1; nc++) {
							int nnr = nr + r;
							int nnc = nc + c;

							if (nnr >= 0 && nnr < N && nnc >= 0 && nnc < N) {
								if (board[nnr][nnc] == curr) {
									cnt++;
								} else if (board[nnr][nnc] != num) {
									cnt = 0;
									break start;
								}
							} else {
								cnt = 0;
								break start;
							}
						}
					}
					if (cnt == 4) {
						result++;
						for (int nr = 0; nr <= 1; nr++) {
							for (int nc = -1; nc <= 1; nc++) {
								int nnr = nr + r;
								int nnc = nc + c;

								board[nnr][nnc] = 0;
							}
						}
					}
				}
			}
		}
		return result;
	}
}