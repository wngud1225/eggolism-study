package BOJ_3109;

// 빵집
import java.util.*;

public class Main {

	static int R;
	static int C;
	static char[][] arr;
	static int[] dr = { -1, 0, 1 };
	static boolean[][] visited;
	static boolean isDone;
	static int ans;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		arr = new char[R][C];
		for (int r = 0; r < R; r++) {
			String str = sc.next();
			for (int c = 0; c < C; c++) {
				arr[r][c] = str.charAt(c);
			}
		}

		visited = new boolean[R][C];

		for (int r = 0; r < R; r++) { // 1열 모든 행에서 시작
			isDone = false;
			dfs(r, 0);
		}

		System.out.println(ans);
	}

	static void dfs(int r, int c) {

		if (isDone) { // 이미 끝 열에 도착한 dfs가 있다면 현재 재귀를 중지
			return;
		}

		if (visited[r][c]) { // 방문 체크 먼저 실시
			return;
		}

		visited[r][c] = true; // 이후 표시

		if (c == C - 1) { // 끝 열에 도착 시
			ans++; // 가능한 경우의 수 ++
			isDone = true; // 끝 열에 도착했다는 표시
			return;
		}
		for (int i = 0; i < 3; i++) {

			int nr = r + dr[i]; // 오른쪽 윗 대각선으로 가는 경우부터 체크
			int nc = c + 1;
			if (nr >= 0 && nr < R && arr[nr][nc] == '.') {
				dfs(nr, nc);
			}
		}
	}
}