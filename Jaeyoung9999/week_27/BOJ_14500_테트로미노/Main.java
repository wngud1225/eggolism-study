package BOJ_14500;

// 테트로미노
import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] arr = new int[N][M];
		int max = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				arr[r][c] = sc.nextInt();
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (r + 3 < N) {
					max = Integer.max(max, arr[r][c] + arr[r + 1][c] + arr[r + 2][c] + arr[r + 3][c]);
				}
				if (c + 3 < M) {
					max = Integer.max(max, arr[r][c] + arr[r][c + 1] + arr[r][c + 2] + arr[r][c + 3]);
				}
				if (r + 1 < N && c + 1 < M) {
					max = Integer.max(max, arr[r][c] + arr[r + 1][c] + arr[r][c + 1] + arr[r + 1][c + 1]);
				}
				if (r + 2 < N && c + 1 < M) {
					max = Integer.max(max, arr[r][c] + arr[r + 1][c] + arr[r + 2][c] + arr[r][c + 1]);
					max = Integer.max(max, arr[r][c] + arr[r][c + 1] + arr[r + 1][c + 1] + arr[r + 2][c + 1]);
					max = Integer.max(max, arr[r][c] + arr[r + 1][c] + arr[r + 1][c + 1] + arr[r + 2][c + 1]);
					max = Integer.max(max, arr[r][c] + arr[r + 1][c] + arr[r + 2][c] + arr[r + 1][c + 1]);
					max = Integer.max(max, arr[r][c] + arr[r + 1][c] + arr[r + 2][c] + arr[r + 2][c + 1]);
				}
				if (r + 1 < N && c + 2 < M) {
					max = Integer.max(max, arr[r][c] + arr[r + 1][c] + arr[r + 1][c + 1] + arr[r + 1][c + 2]);
					max = Integer.max(max, arr[r][c] + arr[r][c + 1] + arr[r][c + 2] + arr[r + 1][c + 2]);
					max = Integer.max(max, arr[r][c] + arr[r][c + 1] + arr[r + 1][c + 1] + arr[r + 1][c + 2]);
					max = Integer.max(max, arr[r][c] + arr[r][c + 1] + arr[r + 1][c + 1] + arr[r][c + 2]);
				}
				if (r - 1 >= 0 && c + 2 < M) {
					max = Integer.max(max, arr[r][c] + arr[r - 1][c] + arr[r - 1][c + 1] + arr[r - 1][c + 2]);
					max = Integer.max(max, arr[r][c] + arr[r][c + 1] + arr[r][c + 2] + arr[r - 1][c + 2]);
					max = Integer.max(max, arr[r][c] + arr[r][c + 1] + arr[r - 1][c + 1] + arr[r - 1][c + 2]);
					max = Integer.max(max, arr[r][c] + arr[r][c + 1] + arr[r - 1][c + 1] + arr[r][c + 2]);
				}
				if (r - 2 >= 0 && c + 1 < M) {
					max = Integer.max(max, arr[r][c] + arr[r][c + 1] + arr[r - 1][c + 1] + arr[r - 2][c + 1]);
					max = Integer.max(max, arr[r][c] + arr[r - 1][c] + arr[r - 1][c + 1] + arr[r - 2][c + 1]);
				}
				if (r + 2 < N && c - 1 >= 0) {
					max = Integer.max(max, arr[r][c] + arr[r + 1][c] + arr[r + 2][c] + arr[r + 1][c - 1]);
				}
			}
		}
		System.out.println(max);
	}
}